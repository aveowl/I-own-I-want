package com.iowniwant.controller.servlet;

import com.iowniwant.model.Goal;
import com.iowniwant.model.User;
import com.iowniwant.service.impl.GoalService;
import com.iowniwant.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Using users input persists new Goal in DataBase, bonds it with
 * this user. Obtains essential data from the ajax on the clients side.
 */
@WebServlet(name = "AddGoalsServlet", urlPatterns = {"/addGoalsServlet"})
public class AddGoalsServlet extends HttpServlet {
    private static Logger LOG = LoggerFactory.getLogger(AddGoalsServlet.class);

    private UserService userService = new UserService();
    private GoalService goalService = new GoalService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = (Long) request.getServletContext().getAttribute("user_id");
        LOG.debug("Fetching user_id from the servletContext: {}", id);

        // user associated with goal
        User user = this.userService.getById(id);

        String title = request.getParameter("title");
        Double cost = Double.valueOf(request.getParameter("cost"));
        String shorten = request.getParameter("shorten");
        String description = request.getParameter("description");
        Date pubdate = new Date(new java.util.Date().getTime());

        LOG.debug("Title was obtained due to the ajax function: {}", title);
        LOG.debug("Cost was obtained due to the ajax function: {}", cost);
        LOG.debug("Brief notes were obtained due to the ajax function: {}", shorten);
        LOG.debug("Description was obtained due to the ajax function: {}", description);

        // persists goal_view
        Goal goal = this.goalService.save(new Goal(title, cost, shorten, pubdate, description, user));

        // sends view_goal_id to ajax function, could be used via data object
        String jsonObject = "" + goal.getV_id();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonObject);
    }
}
