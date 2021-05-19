package com.adedotunalausa.week8taskadedotunalausa.controller;

import com.adedotunalausa.week8taskadedotunalausa.model.Employee;
import com.adedotunalausa.week8taskadedotunalausa.model.Vehicle;
import com.adedotunalausa.week8taskadedotunalausa.model.Work;
import com.adedotunalausa.week8taskadedotunalausa.service.EmployeeService;
import com.adedotunalausa.week8taskadedotunalausa.service.VehicleService;
import com.adedotunalausa.week8taskadedotunalausa.service.WorkService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
public class WorkController {

    @Autowired
    private WorkService workService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SpringTemplateEngine templateEngine;

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

    @GetMapping("/invoice")
    public ResponseEntity<?> getPDF(@RequestParam Long workId, HttpServletRequest request, HttpServletResponse response){
        Work currentWork = workService.getWorkById(workId);

        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("currentWork", currentWork);
        context.setVariable("author", currentWork.getCreatedBy());
        context.setVariable("vehicle", currentWork.getVehicle());
        context.setVariable("vehicleOwner", currentWork.getVehicle().getCustomer());
        String invoiceHtml = templateEngine.process("serviceInvoice", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:6040");

        HtmlConverter.convertToPdf(invoiceHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
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
