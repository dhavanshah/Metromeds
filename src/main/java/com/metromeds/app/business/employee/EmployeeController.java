package com.metromeds.app.business.employee;


import com.metromeds.app.repository.EmployeeRepository;
import com.metromeds.app.models.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    private Map<String, String> parms;
    public List<Employee> getEmployee() {
        List<Employee> employees = new ArrayList<>();
        if (this.parms.containsKey("id")) {
            employees.add(employeeById(Integer.parseInt(this.parms.get("id"))));
        } else {
            employeeRepository.findAll().forEach(employees::add);
        }
        return employees;
    }

    public Employee employeeById(Integer id) {
        return employeeRepository.findOne(id);
    }

    public void setParameters(Map<String, String> parms) {
        this.parms = parms;
    }


}