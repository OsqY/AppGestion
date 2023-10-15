package com.oscar.appgestion.controllers;

import com.oscar.appgestion.DAO.UserDAO;
import com.oscar.appgestion.model.User;
import com.oscar.appgestion.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("api/login")
    public String login(@RequestBody User user) {
        User loggedUser = userDao.getUser(user);
        if (loggedUser != null) {
            return jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
        }
        return "FAIL";
    }
}
