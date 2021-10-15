package com.example.project.Service.Implementation;

import com.example.project.Model.Enum.Role;
import com.example.project.Model.User;
import com.example.project.Repository.UserRepository;
import com.example.project.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User userLogin(String username, String password) {
        List<User> userList = this.userRepository.findAll();
        User user = userList.stream().filter(u->u.getUsername().equals(username)).findFirst().get();
        if(user == null || !user.getPassword().equals(password)){
            //TODO: Handle if user credentials are invalid
            return null;
        }
        return user;
    }

    @Override
    public User userRegister(String username, String password) {
        List<User> userList = this.userRepository.findAll();
        //TODO: Handle if username already exists
        User user = new User(username, password, Role.USER);
        return this.userRepository.save(user);
    }
}
