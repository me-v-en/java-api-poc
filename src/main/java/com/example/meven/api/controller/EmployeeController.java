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
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") final int id, @RequestBody Employee employee) {
        Optional<Employee> e = employeeService.getEmployee(id);
        if (e.isPresent()) {
            Employee employeeToUpdate = e.get();

            // Update the fields
            final String firstName = employee.getFirstName();
            if (firstName != null) {
                employeeToUpdate.setFirstName(firstName);
            }

            final String lastName = employee.getLastName();
            if (lastName != null) {
                employeeToUpdate.setLastName(lastName);
            }

            final String mail = employee.getMail();
            if (mail != null) {
                employeeToUpdate.setMail(mail);
            }

            final String password = employee.getPassword();
            if (password != null) {
                employeeToUpdate.setPassword(password);
            }

            // Save the employee entity
            return employeeService.saveEmployee(employee);
        }
        return null;
    }

    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable("id") final int id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/employee/delete/{id}")
    public void deleteEmployee(@PathVariable("id") final int id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees")
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
