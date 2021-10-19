package com.example.project.Web;

import com.example.project.Model.Exceptions.InvalidUserCredentialsException;
import com.example.project.Model.User;
import com.example.project.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("message", error);
        }
        return "login.html";
    }

    @PostMapping
    public String loginUser(HttpServletRequest httpServletRequest,
                            @RequestParam String username,
                            @RequestParam String password){
        try {
            User user = this.userService.userLogin(username, password);
            httpServletRequest.getSession().setAttribute("user", user);
            return "redirect:/home";
        } catch (InvalidUserCredentialsException exception) {
            return "redirect:/login?error=InvalidUserCredentials";
        }
    }
}
