package com.example.empmanagerclasswork.service;

import com.example.empmanagerclasswork.exceprion.UserNotFoundException;
import com.example.empmanagerclasswork.model.Employee;
import com.example.empmanagerclasswork.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/** @Transactional;
 * The javax. transaction. Transactional annotation provides the application the ability to control transaction boundaries declaratively.
 * This annotation can be applied to any class that the Jakarta EE specification defines as a managed bean (which includes CDI managed beans).
 */

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional //uzbaigti transakcijas tokias kaip update ir delete.
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
    public void addEmployee (Employee employee) {
            employee.setEmployeeCode(UUID.randomUUID().toString());
            employeeRepository.save(employee);
            log.info("Employee was added");
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    public Employee findEmployeeById(Long id) throws UserNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found!"));
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }
}
