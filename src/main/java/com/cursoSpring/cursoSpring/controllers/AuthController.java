package com.cursoSpring.cursoSpring.controllers;

import com.cursoSpring.cursoSpring.DAO.UserDAO;
import com.cursoSpring.cursoSpring.model.User;
import com.cursoSpring.cursoSpring.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("api/login")
    public String login(@RequestBody User user){
        User loggedUser = userDao.getUser(user);
        if (loggedUser != null){
            String token = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            return token;
        }
        return "FAIL";
    }
}
