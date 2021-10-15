package com.example.project.Service;

import com.example.project.Model.User;

public interface UserService {

    public User userLogin(String username, String password);

    public User userRegister(String username, String password);
}
