package com.iowniwant.controller.helper;

import com.iowniwant.model.Goal;
import com.iowniwant.model.User;

import java.sql.Date;

public class TestEntity {

    public static User getTestUser() {
        User user = new User();
        user.setId(99L);
        user.setUserName("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test");
        user.setMonthSalary(99.00);
        return user;
    }

    public static Goal getTestGoal() {
        Goal goal = new Goal();
        goal.setId(99L);
        goal.setDescription("test");
        goal.setNotes("test");
        goal.setTitle("test");
        goal.setCost(99.00);
        goal.setUser(getTestUser());
        goal.setPubdate(new Date(9L));
        goal.setV_id(99L);
        return goal;
    }
}
