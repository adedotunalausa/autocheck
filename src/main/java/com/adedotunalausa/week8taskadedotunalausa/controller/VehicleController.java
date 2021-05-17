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

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/vehicles")
    private String viewVehiclePage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            return "vehicles";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/add-vehicle")
    private String showVehicleAddForm(@RequestParam Long customerId,Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Customer currentCustomer = customerService.getCustomerById(customerId);
            Vehicle newVehicle = new Vehicle();
            model.addAttribute("currentCustomer", currentCustomer);
            model.addAttribute("newVehicle", newVehicle);
            return "vehicleAddForm";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add-vehicle")
    private String addVehicleToCustomer(@ModelAttribute("newVehicle") Vehicle newVehicle,
                                        @ModelAttribute("currentCustomer") Customer currentCustomer, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            newVehicle.setCustomer(currentCustomer);
            vehicleService.addVehicle(newVehicle);
            return "redirect:/customers";
        } else {
            return "redirect:/";
        }
    }

}
