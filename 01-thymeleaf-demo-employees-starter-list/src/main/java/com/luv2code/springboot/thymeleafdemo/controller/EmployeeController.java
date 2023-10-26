package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> employees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("showFormAdd")

	public String showFormAdd(Model theModel){
		Employee employee = new Employee();

		theModel.addAttribute("employee",employee);

		return "employees/employe-form";
	}

	@GetMapping("showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId")int theId, Model theModel){
		Employee employee = employeeService.findById(theId);

		theModel.addAttribute("employee",employee);

		return "employees/employe-form";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("employee") Employee employee){
		employeeService.save(employee);

		return "redirect:/employees/list";
	}

	@GetMapping("delete")
	public String delete(@RequestParam("employeeId") int theId){
		employeeService.deleteById(theId);

		return "redirect:/employees/list";


	}
}









