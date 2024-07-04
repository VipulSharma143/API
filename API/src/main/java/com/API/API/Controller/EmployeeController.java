package com.API.API.Controller;


import com.API.API.Exception.ResourceNotFoundException;
import com.API.API.Model.Employee;
import com.API.API.Service.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping(value = "/")
    public String getPage(){
        return "Hello World";
    }
    @GetMapping(value = "/employee")
    public List<Employee> getEmployee(){
        return employeeRepository.findAll();
    }
    @PostMapping(value = "/employee/save")
    public  String SaveEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "Employee Saved";
    }
    @PutMapping(value = "/employee/update/{id}")
    public String UpdateEmployee(@PathVariable long id,@RequestBody Employee employee){
        Employee UpdateEmployee = employeeRepository.findById(id).get();
        UpdateEmployee.setFirstname(employee.getFirstname());
        UpdateEmployee.setLastname(employee.getLastname());
        UpdateEmployee.setAge(employee.getAge());
        employeeRepository.save(UpdateEmployee);
        return "Employee Updated";
    }
    @DeleteMapping(value = "/employee/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
        Employee DeleteEmployee = employeeRepository.findById(id).get();
        employeeRepository.delete(DeleteEmployee);
        return "Employee Deleted with the id"+id;
    }
}