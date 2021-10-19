package com.example.project.Web;

import com.example.project.Model.Exceptions.InvalidUserCredentialsException;
import com.example.project.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registerPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("message", error);
        }
        return "register.html";
    }

    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password){
        try {
            this.userService.userRegister(username, password);
            return "redirect:/login";
        }catch (InvalidUserCredentialsException exception) {
            return "redirect:/register?error=InvalidUserCredentials";
        }
    }
}
