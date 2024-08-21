package com.example.meven.api.controller;

import com.example.meven.api.model.Employee;
import com.example.meven.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
             return employeeService.updateEmployee(employee);
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable("id") final Long id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/employee/{id}/delete")
    public void deleteEmployee(@PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees")
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
