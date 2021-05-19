package com.adedotunalausa.week8taskadedotunalausa.controller;

import com.adedotunalausa.week8taskadedotunalausa.model.Employee;
import com.adedotunalausa.week8taskadedotunalausa.service.CustomerService;
import com.adedotunalausa.week8taskadedotunalausa.service.EmployeeService;
import com.adedotunalausa.week8taskadedotunalausa.service.VehicleService;
import com.adedotunalausa.week8taskadedotunalausa.service.WorkService;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private WorkService workService;

    @GetMapping("/")
    public String viewAuthenticationPage(Model model) {
        Employee employee = new Employee();
        model.addAttribute("staff", employee);
        return "login";
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("services", workService.getAllWorks());
            model.addAttribute("pendingServices", workService.getAllPendingWorks());
            model.addAttribute("completedServices", workService.getAllCompletedWorks());
            return "dashboard";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/add-employee")
    private String showEmployeeAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Employee newEmployee = new Employee();
            model.addAttribute("newEmployee", newEmployee);
            return "employeeAddForm";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add-employee")
    private String addEmployee(@ModelAttribute("staff") Employee newEmployee, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            employeeService.addUser(newEmployee);
            return "redirect:/staff";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/staff")
    private String viewStaffPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("staff", employeeService.getAllUsers());
            return "staff";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    private String login(@ModelAttribute("user") Employee employee, HttpServletRequest request) {
        Employee returningEmployee = employeeService.getUser(employee.getEmail(), employee.getPassword());

        if (returningEmployee != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", returningEmployee.getEmployeeId());
            session.setAttribute("userFirstname", returningEmployee.getFirstname());
            session.setAttribute("userLastname", returningEmployee.getLastname());
            session.setAttribute("userRole", returningEmployee.getRole());

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
