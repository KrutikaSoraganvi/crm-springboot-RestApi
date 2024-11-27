package com.example.crm.service;
//import org.springframework.stereotype.Service;

import com.example.crm.entity.Employee;
import com.example.crm.exception.ResourceNotFound;
import com.example.crm.payload.EmployeeDto;
import com.example.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
private EmployeeRepository employeeRepository;
@Autowired
private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee=mapToEntity(dto);

       Employee emp= employeeRepository.save(employee);
       EmployeeDto empdto=mapToDto(emp);
       return empdto;
    }



    public void deleteEmployeeById(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }



    public EmployeeDto updateEmployee(long id, EmployeeDto dto) {
       Employee employee=mapToEntity(dto);
       employee.setId(id);
        Employee updateEmp=employeeRepository.save(employee);
        EmployeeDto empdto=mapToDto(updateEmp);
        return empdto;

    }
    //with using modelmapper
    EmployeeDto mapToDto(Employee employee)
    {
      EmployeeDto dto= modelMapper.map(employee,EmployeeDto.class);
      return dto;
    }
    Employee mapToEntity(EmployeeDto dto)
    {
        Employee emp=modelMapper.map(dto,Employee.class);
        return emp;
    }


    //    {
//    EmployeeDto mapToDto(Employee employee)
//    {
//        EmployeeDto dto=new EmployeeDto();
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
//        return dto;
//    }
//    Employee mapToEntity(EmployeeDto dto)
//    {
//        Employee employee = new Employee();
//        employee.setName(dto.getName());
//        employee.setEmailId(dto.getEmailId());
//        employee.setMobile(dto.getMobile());
//        return employee;
//    }
    public List<EmployeeDto> getAllEmployees(int pageSize, int pageNo, String sortBy) {
        // Create a pageable object with sorting
        PageRequest pageable =  PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        // Fetch paginated data from the repository
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        // Map the Employee entities to DTOs
        List<EmployeeDto> employeeDtos = employeePage.getContent()
                .stream()
                .map(this::mapToDto) // Map each Employee entity to a DTO
                .collect(Collectors.toList());

        return employeeDtos;
    }

    public EmployeeDto getemployeeById(long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ResourceNotFound("Record not found with ID: " + empId));
        return mapToDto(employee);
    }



}
