package com.example.project.Web;

import com.example.project.Model.User;
import com.example.project.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginPage(){
        return "login.html";
    }

    @PostMapping
    public String loginUser(HttpServletRequest httpServletRequest,
                            @RequestParam String username,
                            @RequestParam String password){
        User user = this.userService.userLogin(username, password);
        //TODO: Handle error
        httpServletRequest.getSession().setAttribute("user", user);
        return "redirect:/home";
    }
}
