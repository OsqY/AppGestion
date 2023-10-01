package com.cursoSpring.cursoSpring.DAO;

import com.cursoSpring.cursoSpring.model.User;

import java.util.List;

public interface UserDAO  {

    List<User> getUsers();

    void deleteUser(Long id);

    void registerUser(User user);

    User getUser(User user);
}
