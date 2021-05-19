package com.adedotunalausa.week8taskadedotunalausa.controller;

import com.adedotunalausa.week8taskadedotunalausa.model.Employee;
import com.adedotunalausa.week8taskadedotunalausa.model.Vehicle;
import com.adedotunalausa.week8taskadedotunalausa.model.Work;
import com.adedotunalausa.week8taskadedotunalausa.service.EmployeeService;
import com.adedotunalausa.week8taskadedotunalausa.service.VehicleService;
import com.adedotunalausa.week8taskadedotunalausa.service.WorkService;
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
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/services")
    private String viewServicesPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("services", workService.getAllWorks());
            return "services";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/add-work")
    private String showWorkAddForm(@RequestParam Long vehicleId,
                                   Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            List<Employee> employees = employeeService.getAllUsers();
            Vehicle currentVehicle = vehicleService.getVehicleById(vehicleId);
            Work newWork = new Work();
            model.addAttribute("employees", employees);
            model.addAttribute("currentVehicle", currentVehicle);
            model.addAttribute("newWork", newWork);
            return "workAddForm";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/view-work")
    private String showWorkDetailsPage(@RequestParam Long workId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            Work currentWork = workService.getWorkById(workId);
            model.addAttribute("currentWork", currentWork);
            model.addAttribute("author", currentWork.getCreatedBy());
            model.addAttribute("vehicle", currentWork.getVehicle());
            model.addAttribute("vehicleOwner", currentWork.getVehicle().getCustomer());
            return "workDetails";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add-work")
    private String addWork(@ModelAttribute("newWork") Work work,
                           @ModelAttribute("currentVehicle") Vehicle currentVehicle, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            work.setCreatedBy(employeeService.getUserById(userId));
            work.setVehicle(currentVehicle);
            workService.addWork(work);
            return "redirect:/vehicles";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/edit-work")
    private String showWorkEditForm(@RequestParam Long workId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            List<Employee> employees = employeeService.getAllUsers();
            Work currentWork = workService.getWorkById(workId);
            model.addAttribute("employees", employees);
            model.addAttribute("currentWork", currentWork);
            return "workEditForm";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/update-work")
    private String updateWork(@ModelAttribute("currentWork") Work currentWork, Model model,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            workService.updateWork(currentWork.getWorkId(), currentWork.getEmployeeInCharge(),
                    currentWork.getTypeOfService(), currentWork.getCost(), currentWork.getCurrentMileage(),
                    currentWork.getIsCompleted(), currentWork.getIsPaidFor());
            Work work = workService.getWorkById(currentWork.getWorkId());
            model.addAttribute("currentWork", work);
            return "workEditForm";
        } else {
            return "redirect:/";
        }
    }

}
