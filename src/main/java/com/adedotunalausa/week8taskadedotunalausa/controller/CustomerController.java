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
            return "customerAddForm";
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
        List<Vehicle> currentCustomerVehicles = vehicleService.getAllVehiclesByCustomerId(customerId);
        model.addAttribute("currentCustomer", currentCustomer);
        model.addAttribute("currentCustomerVehicles", currentCustomerVehicles);
        return "customerDetails";
    }

    @GetMapping("/edit-customer")
    private String showCustomerEditForm(@RequestParam Long customerId, Model model) {
        Customer currentCustomer = customerService.getCustomerById(customerId);
        model.addAttribute("currentCustomer", currentCustomer);
        return "customerEditForm";
    }

    @PostMapping("/update-customer")
    private String updateCustomer(@ModelAttribute("currentCustomer") Customer currentCustomer, Model model) {
        customerService.updateCustomer(currentCustomer.getCustomerId(), currentCustomer.getFirstname(),
                currentCustomer.getLastname(), currentCustomer.getGender(), currentCustomer.getOccupation(),
                currentCustomer.getAddress(), currentCustomer.getCity(), currentCustomer.getState(),
                currentCustomer.getEmail(), currentCustomer.getPhoneNo());
        Customer customer = customerService.getCustomerById(currentCustomer.getCustomerId());
        model.addAttribute("currentCustomer", customer);
        return "customerEditForm";
    }


}
