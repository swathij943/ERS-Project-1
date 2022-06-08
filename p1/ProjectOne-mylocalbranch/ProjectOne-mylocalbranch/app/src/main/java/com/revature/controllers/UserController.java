package com.revature.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginObject;
import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {
    private ObjectMapper om;
    private UserService uService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService uService) {
        this.uService = uService;
        this.om = new ObjectMapper();
    }

    public Handler handleLogin = ctx -> {
        LoginObject lo = om.readValue(ctx.body(),LoginObject.class);
        User u = uService.loginUser(lo.username, lo.password);

        if(u != null){
            //set up a session for logged user
            ctx.req.getSession().setAttribute("loggedIn", u.getUsername());
            ctx.req.getSession().setAttribute("user_id", u.getUser_id());
            ctx.req.getSession().setAttribute("role", u.getRole());
            ctx.result(om.writeValueAsString(u));
            logger.info("5. Login Successful!");
        } else{
            logger.info("4. Incorrect username and password, please enter your details carefully!");
            ctx.status(403);
            ctx.result("Username or password was incorrect");
        }
    };

    public Handler handleUpdate = ctx -> {
        if(ctx.req.getSession().getAttribute("loggedIn") == null){
            ctx.status(403);
            ctx.result("User isn't logged in!");
        } else{
            int userId = Integer.parseInt(ctx.req.getSession().getAttribute("user_id").toString());
            String username = ctx.req.getSession().getAttribute("loggedIn").toString();
            int role = Integer.parseInt(ctx.req.getSession().getAttribute("role").toString());
            User u = (om.readValue(ctx.body(), User.class));
            u.setUser_id(userId);
            u.setUsername(username);
            u.setRole(role);

            //We are retrieving user-id, role and username from the session, as we don't want user to modify
            //these settings.
            ctx.result(om.writeValueAsString(uService.updateUser(u)));
            ctx.status(201);
            logger.info("6. User updated successfully!");
        }
    };

    public Handler handleLogout = ctx -> {
        if(ctx.req.getSession().getAttribute("loggedIn") != null){
            ctx.req.getSession().invalidate();
            ctx.status(200);
            ctx.result("User logged out!");
            logger.info("7. User logged out successfully!");
        }else{
            ctx.status(403);
            ctx.result("User isn't logged in!");
        }
    };
}
