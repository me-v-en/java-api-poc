package com.example.meven.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.meven.api.model.Employee;
import com.example.meven.api.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testCreateOrUpdateEmployee() throws Exception {
        //Test create employee
        Employee employee = new Employee();
        // Id is 4 because there is already 3 rows by default
        employee.setId(4L);
        employee.setFirstName("test");
        employee.setLastName("test");
        employee.setMail("test");
        employee.setPassword("test");

        MvcResult res = mockMvc.perform(post("/employee")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("test"))
                .andReturn();

        System.out.println("Result create employee : " + res.getResponse().getContentAsString());


        // Test update employee
        employee.setLastName("test2");
        MvcResult res2 = mockMvc.perform(post("/employee")
                        .content(objectMapper.writeValueAsString(employee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("test2"))
                .andReturn();

        System.out.println("Result update employee : " + res2.getResponse().getContentAsString());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        MvcResult res = mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("GINA"))
                .andReturn();

        System.out.println("Get employee by id : "+ res.getResponse().getContentAsString());

    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employee/delete/4"))
                .andExpect(status().isOk());

        System.out.println("Delete employee test, does he still exist : "+employeeService.getEmployee(4L));

    }

    @Test
    public void testGetAllEmployees() throws Exception {
        MvcResult res = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Laurent"))
                .andReturn();

        System.out.println("Result getAllEmployees : " + res.getResponse().getContentAsString());
    }
}