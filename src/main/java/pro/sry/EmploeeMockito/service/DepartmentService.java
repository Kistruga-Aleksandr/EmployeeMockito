package pro.sry.EmploeeMockito.service;

import org.springframework.stereotype.Service;
import pro.sry.EmploeeMockito.Employee;
import pro.sry.EmploeeMockito.WorkingExceptions.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByiDepartmentID(Integer department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }


    public Employee searchMinSalary(Integer department) {
        return employeeService.getAll().stream().filter(employee -> employee.getDepartment().equals(department))
                .max(Comparator.comparingDouble(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee searchMaxSalary(Integer department) {
        return employeeService.getAll().stream().filter(employee -> employee.getDepartment().equals(department))
                .max(Comparator.comparingDouble(Employee::getSalary)).orElseThrow(EmployeeNotFoundException::new);
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartments() {
        return employeeService.getAll().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
