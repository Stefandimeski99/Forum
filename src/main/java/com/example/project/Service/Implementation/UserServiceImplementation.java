package com.example.project.Service.Implementation;

import com.example.project.Model.Enum.Role;
import com.example.project.Model.Exceptions.InvalidUserCredentialsException;
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
        User user = this.userRepository.findByUsernameAndPassword(username, password);
        if(user == null){
            throw new InvalidUserCredentialsException();
        }
        return user;
    }

    @Override
    public User userRegister(String username, String password) {
        User user1 = this.userRepository.findByUsername(username);
        if(user1 != null){
            throw new InvalidUserCredentialsException();
        }
        User user = new User(username, password, Role.USER);
        return this.userRepository.save(user);
    }
}
