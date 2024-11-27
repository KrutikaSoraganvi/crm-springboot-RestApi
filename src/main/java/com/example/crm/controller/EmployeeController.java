package com.example.crm.controller;

import com.example.crm.payload.EmployeeDto;
import com.example.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    // Create method to add employee
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto,
                                                   BindingResult result)
    {
        if(result.hasErrors())
        {
            return new ResponseEntity<>(result.getFieldError(), HttpStatus.INTERNAL_SERVER_ERROR);  // Return validation errors
        }
      EmployeeDto employeeDto=employeeService.addEmployee(dto);

        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }





    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable long id) {
        try {
            employeeService.deleteEmployeeById(id);
            System.out.println("2000");
            System.out.println("2000");
            return "Employee with ID " + id + " deleted successfully.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam long id, @RequestBody EmployeeDto dto) {
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);

        return  new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping("/employeeId/{empId}")
    public ResponseEntity<EmployeeDto>getEmployee(@PathVariable long empId)

     {
        EmployeeDto empdto = employeeService.getemployeeById(empId);
        return new ResponseEntity<>(empdto, HttpStatus.OK);  // This will return the employee details as JSON
    }
    @GetMapping("/add")
    public ResponseEntity<Iterable<EmployeeDto>> getAllEmployees(@RequestParam(name="pageSize",required = false, defaultValue = "5") int pageSize,
    @RequestParam(name="pageNo",required = false, defaultValue = "0") int pageNo, @RequestParam(name="sortBy",required = false, defaultValue = "name") String sortBy
    ) {
        return new ResponseEntity<>(employeeService.getAllEmployees(pageSize,pageNo,sortBy), HttpStatus.OK);
    }



}