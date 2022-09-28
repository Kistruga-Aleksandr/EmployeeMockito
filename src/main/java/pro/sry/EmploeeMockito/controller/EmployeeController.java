package pro.sry.EmploeeMockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sry.EmploeeMockito.Employee;
import pro.sry.EmploeeMockito.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam String lastName, @RequestParam String firstName,
                        @RequestParam Double salary, @RequestParam Integer department) {
        return employeeService.add(lastName, firstName, salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("lastName") String lastName,
                                   @RequestParam("firstName") String firstName) {
        return employeeService.removeEmployee(lastName, firstName);
    }

    @GetMapping(path = " /find")
    public Employee getEmployee(@RequestParam("lastName") String lastName,
                                @RequestParam("firstName") String firstName) {

        return employeeService.getEmployee(lastName, firstName);
    }

    public List<Employee> getAll() {
        return employeeService.getAll();
    }


}
