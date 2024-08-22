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

    public Employee saveEmployee(final Employee employee){
            return employeeRepository.save(employee);
    }

}
