package com.adedotunalausa.week8taskadedotunalausa.controller;

import com.adedotunalausa.week8taskadedotunalausa.model.Customer;
import com.adedotunalausa.week8taskadedotunalausa.model.Vehicle;
import com.adedotunalausa.week8taskadedotunalausa.service.CustomerService;
import com.adedotunalausa.week8taskadedotunalausa.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/customers")
    private String viewCustomersPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Customer newCustomer = new Customer();
            model.addAttribute("newCustomer", newCustomer);
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("link_name", "Customers"); // this is for the navbar css style in thymeleaf
            model.addAttribute("page_link", "/customers"); // this is for the navbar css style in thymeleaf
            model.addAttribute("customer_active", "active"); // this is for the sidebar css style in thymeleaf
            return "customers";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/add-customer")
    private String showCustomerAddForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Customer newCustomer = new Customer();
            model.addAttribute("newCustomer", newCustomer);
            model.addAttribute("link_name", "Customers");
            model.addAttribute("page_link", "/customers");
            model.addAttribute("customer_active", "active");
            return "customerAddForm";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add-customer")
    private String addCustomer(@ModelAttribute("newCustomer") Customer newCustomer, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            customerService.addCustomer(newCustomer);
            model.addAttribute("link_name", "Customers");
            model.addAttribute("page_link", "/customers");
            model.addAttribute("customer_active", "active");
            return "redirect:/customers";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/view-customer")
    private String showCustomerDetailsPage(@RequestParam Long customerId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Customer currentCustomer = customerService.getCustomerById(customerId);
            List<Vehicle> currentCustomerVehicles = vehicleService.getAllVehiclesByCustomerId(customerId);
            model.addAttribute("currentCustomer", currentCustomer);
            model.addAttribute("currentCustomerVehicles", currentCustomerVehicles);
            model.addAttribute("link_name", "Customers");
            model.addAttribute("page_link", "/customers");
            model.addAttribute("customer_active", "active");
            return "customerDetails";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/edit-customer")
    private String showCustomerEditForm(@RequestParam Long customerId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Customer currentCustomer = customerService.getCustomerById(customerId);
            model.addAttribute("currentCustomer", currentCustomer);
            model.addAttribute("link_name", "Customers");
            model.addAttribute("page_link", "/customers");
            model.addAttribute("customer_active", "active");
            return "customerEditForm";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/update-customer")
    private String updateCustomer(@ModelAttribute("currentCustomer") Customer currentCustomer, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            customerService.updateCustomer(currentCustomer.getCustomerId(), currentCustomer.getFirstname(),
                    currentCustomer.getLastname(), currentCustomer.getGender(), currentCustomer.getOccupation(),
                    currentCustomer.getAddress(), currentCustomer.getCity(), currentCustomer.getState(),
                    currentCustomer.getEmail(), currentCustomer.getPhoneNo());
            Customer customer = customerService.getCustomerById(currentCustomer.getCustomerId());
            model.addAttribute("currentCustomer", customer);
            model.addAttribute("link_name", "Customers");
            model.addAttribute("page_link", "/customers");
            model.addAttribute("customer_active", "active");
            return "customerEditForm";
        } else {
            return "redirect:/";
        }
    }


}
