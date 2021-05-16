package com.adedotunalausa.week8taskadedotunalausa.controller;

import com.adedotunalausa.week8taskadedotunalausa.model.Customer;
import com.adedotunalausa.week8taskadedotunalausa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    private String viewCustomersPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Customer newCustomer = new Customer();
            model.addAttribute("newCustomer", newCustomer);
            model.addAttribute("customers", customerService.getAllCustomers());
            return "customers";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add-customer")
    private String addCustomer(@ModelAttribute("newCustomer") Customer newCustomer, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            customerService.addCustomer(newCustomer);
            return "redirect:/customers";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/view-customer")
    private String showCustomerDetailsPage(@RequestParam Long customerId, Model model) {
        Customer currentCustomer = customerService.getCustomerById(customerId);
        model.addAttribute("currentCustomer", currentCustomer);
        return "customerDetails";
    }

    @GetMapping("/edit-customer")
    private String showCustomerEditForm(@RequestParam Long customerId, Model model) {
        Customer currentCustomer = customerService.getCustomerById(customerId);
        model.addAttribute("currentCustomer", currentCustomer);
        return "customerEditForm";
    }


}
