package com.iowniwant.controller.servlet;

import com.iowniwant.model.User;
import com.iowniwant.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.iowniwant.controller.helper.TestEntity.getTestUser;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest extends Mockito {
    private static final String FORWARD_TARGET = "/showGoalsServlet";

    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter writer;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private UserService userService;

    @InjectMocks
    private LoginServlet loginServlet = new LoginServlet();

    private User user;

    @Before
    public void setUp() throws Exception {
        when(request.getServletContext()).thenReturn(servletContext);
        when(response.getWriter()).thenReturn(writer);
        when(servletContext.getRequestDispatcher(FORWARD_TARGET)).thenReturn(requestDispatcher);

        user = getTestUser();

        when(request.getParameter("userName")).thenReturn(user.getUserName());
        when(request.getParameter("password")).thenReturn(user.getPassword());
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void shouldSucceedWithPassword() throws Exception {
        // given
        when(userService.getByUserName(user.getUserName()))
                .thenReturn(user);

        // when
        loginServlet.doPost(request, response);

        // then
        verify(request.getServletContext(), atLeastOnce()).setAttribute("user_id", user.getId());
        verify(request.getServletContext(), atLeastOnce()).setAttribute("token", "logged");
        verify(response, times(2)).addCookie(any(Cookie.class));
        verify(response.getWriter(), atLeastOnce()).write("success");
    }

    @Test
    public void shouldFailOnNonExistingUser() throws Exception {
        // given
        when(userService.getByUserName(user.getUserName()))
                .thenReturn(null);

        // when
        loginServlet.doPost(request, response);

        // then
        verify(request.getServletContext(), never()).setAttribute("user_id", user.getId());
        verify(request.getServletContext(), never()).setAttribute("token", "logged");
        verify(response, never()).addCookie(any(Cookie.class));
        verify(response.getWriter(), only()).write("fail");
    }

    @Test
    public void shouldProceedGetRequest() throws Exception {
        // when
        loginServlet.doGet(request, response);

        // then
        verify(servletContext.getRequestDispatcher(FORWARD_TARGET), atLeastOnce())
                .forward(request, response);
    }
}
