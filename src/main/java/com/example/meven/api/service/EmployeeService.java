package com.example.meven.api.service;

import com.example.meven.api.model.Employee;
import com.example.meven.api.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(final long id){
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final long id){
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(final Employee employee){
        Optional<Employee> e =this.getEmployee(employee.getId());
        if(e.isPresent()){
            Employee updatedEmployee = e.get();
            if(employee.getFirstName() != null){
                updatedEmployee.setFirstName(employee.getFirstName());
            }
            if(employee.getLastName() != null){
                updatedEmployee.setLastName(employee.getLastName());
            }
            if(employee.getMail() != null){
                updatedEmployee.setMail(employee.getMail());
            }
            if(employee.getPassword() != null){
                updatedEmployee.setPassword(employee.getPassword());
            }

            employeeRepository.save(updatedEmployee);
            return updatedEmployee;
        }
        return null;
    }

}
