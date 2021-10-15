package com.example.project.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logoutUser(HttpServletRequest servletRequest){
        servletRequest.getSession().invalidate();
        return "redirect:/home";
    }
}
