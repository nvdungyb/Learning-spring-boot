package com.dzungyb.learning_spring_boot.controller;

import com.dzungyb.learning_spring_boot.model.Employee;
import com.dzungyb.learning_spring_boot.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public String listAllEmployee(Model model) {
        model.addAttribute("employees", employeeService.listAllEmployees());

        return "employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable(value = "id") long id, Model model) {
        employeeService.deleteEmployeeById(id);

        return "redirect:/employees";
//        return "all";     Tìm hiểu về vòng đời của dữ liệu trong Model.
    }

    @GetMapping("/new-employee")
    public String createNew(Model model) {
        String message = "Create new employee";
        Employee emp = new Employee();
        model.addAttribute("employee", emp);
        model.addAttribute("message", message);

        return "new-employee";
    }

    @PostMapping("/create")
    public String createEmp(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        boolean checkExited = employeeService.checkEmailUnique(employee);
        if (checkExited) {
            String message = "New employee was created";
            redirectAttributes.addFlashAttribute("message", message);
            employeeService.saveEmployee(employee);
        } else {
            String message = "User has been updated successfully!";
            redirectAttributes.addFlashAttribute("message", message);
            employeeService.updateEmployee(employee);
        }

        return "redirect:/employees";
    }

    @GetMapping("/change/{id}")
    public String changeEmployee(@PathVariable(value = "id") long id, Model model) {
        String message = "Change employee infor";
        Employee emp = employeeService.getEmployeeById(id);
        model.addAttribute("message", message);
        model.addAttribute("employee", emp);

        return "new-employee";
    }

}
