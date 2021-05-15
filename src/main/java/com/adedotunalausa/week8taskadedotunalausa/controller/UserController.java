package com.adedotunalausa.week8taskadedotunalausa.controller;

import com.adedotunalausa.week8taskadedotunalausa.model.User;
import com.adedotunalausa.week8taskadedotunalausa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String viewAuthenticationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        return "dashboard";
    }

    @PostMapping("/add-user")
    private String addUser(@ModelAttribute("user") User newUser) {
        userService.addUser(newUser);
        return "successful";
    }

    @PostMapping("/login")
    private String login(@ModelAttribute("user") User user, HttpServletRequest request) {
        User returningUser = userService.getUser(user.getEmail(), user.getPassword());

        if (returningUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", returningUser.getUserId());
            session.setAttribute("userFirstname", returningUser.getFirstname());
            session.setAttribute("userLastname", returningUser.getLastname());
            session.setAttribute("userRole", returningUser.getRole());

            return "redirect:/dashboard";
        } else {
            return "authenticationError";
        }
    }

    @RequestMapping("/sign-out")
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute("userId");
            session.removeAttribute("userFirstname");
            session.removeAttribute("userEmail");
            session.invalidate();
        }
        return "redirect:/";
    }

}
