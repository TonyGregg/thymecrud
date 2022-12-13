package com.example.thymecrud.controller;

import com.example.thymecrud.data.Employee;
import com.example.thymecrud.repo.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on Tue, 12/13/22 at 10:35 AM by Genil.
 */
@Controller
public class EmpController {
  @Autowired
  EmpRepo empRepo;

  @GetMapping({"/list","/"})
  public ModelAndView getAllEmployees() {
    ModelAndView modelAndView = new ModelAndView("list-employees");
    modelAndView.addObject("employees", empRepo.findAll());
    return modelAndView;
  }

  @PostMapping("/addEmployee")
  public String addEmployee(@ModelAttribute Employee employee) {
    empRepo.save(employee);
    return "redirect:/list";
  }

  @GetMapping("/addEmployeeForm")
  public ModelAndView addEmployeeForm() {
    ModelAndView modelAndView = new ModelAndView("add-employee-form");
    Employee employee = new Employee();
    modelAndView.addObject("employee", employee);
    return modelAndView;

  }

  @GetMapping("/showUpdateForm")
  public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
    ModelAndView modelAndView = new ModelAndView("add-employee-form");
    final Employee employee = empRepo.findById(employeeId).get();
    modelAndView.addObject("employee", employee);
    return modelAndView;
  }


  @GetMapping("/deleteEmployee")
  public String deleteEmployee(@RequestParam Long employeeId) {
    empRepo.deleteById(employeeId);
    return "redirect:/list";
  }



}
