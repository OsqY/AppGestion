package com.cursoSpring.cursoSpring.controllers;

import com.cursoSpring.cursoSpring.DAO.UserDAO;
import com.cursoSpring.cursoSpring.model.User;
import com.cursoSpring.cursoSpring.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("api/users")
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token){
        if (!validateToken(token)){
            System.out.println("No se validó el token");
            return null;
        }
        return userDao.getUsers();
    }

    public boolean validateToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }
    @DeleteMapping(value = "api/users/{id}")
    public void deleteUser(@RequestHeader(value = "Authorization") String token,@PathVariable Long id){

        if (validateToken(token)){
            return;
        }
        userDao.deleteUser(id);
    }

    @PostMapping( "api/user")
    public void registerUser(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword().toCharArray());
        user.setPassword(hash);
        userDao.registerUser(user);
    }
}
