package com.adedotunalausa.week8taskadedotunalausa.controller;

import com.adedotunalausa.week8taskadedotunalausa.model.Staff;
import com.adedotunalausa.week8taskadedotunalausa.service.StaffService;
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
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    public String viewAuthenticationPage(Model model) {
        Staff staff = new Staff();
        model.addAttribute("staff", staff);
        return "login";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model) {
        return "dashboard";
    }

    @PostMapping("/add-staff")
    private String addStaff(@ModelAttribute("staff") Staff newStaff) {
        staffService.addUser(newStaff);
        return "redirect:/staff";
    }

    @GetMapping("/customers")
    private String viewCustomersPage() {
        return "customers";
    }

    @GetMapping("/staff")
    private String viewStaffPage(Model model) {
        Staff newStaff = new Staff();
        model.addAttribute("newStaff", newStaff);
        model.addAttribute("staff", staffService.getAllUsers());
        return "staff";
    }

    @PostMapping("/login")
    private String login(@ModelAttribute("user") Staff staff, HttpServletRequest request) {
        Staff returningStaff = staffService.getUser(staff.getEmail(), staff.getPassword());

        if (returningStaff != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", returningStaff.getStaffId());
            session.setAttribute("userFirstname", returningStaff.getFirstname());
            session.setAttribute("userLastname", returningStaff.getLastname());
            session.setAttribute("userRole", returningStaff.getRole());

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
