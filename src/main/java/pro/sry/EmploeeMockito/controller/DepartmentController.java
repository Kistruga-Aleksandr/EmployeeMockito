package pro.sry.EmploeeMockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sry.EmploeeMockito.Employee;
import pro.sry.EmploeeMockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
   private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartments() {
        return departmentService.getEmployeesGroupedByDepartments();
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> getEmployeesByiDepartmentID(@RequestParam Integer departmentId) {
        return departmentService.getEmployeesByiDepartmentID(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee searchMinSalary(@RequestParam Integer departmentId) {
        return departmentService.searchMinSalary(departmentId);
    }

    @GetMapping(path = "/max-salary")
    public Employee searchMaxSalary(@RequestParam Integer departmentId) {
        return departmentService.searchMaxSalary(departmentId);
    }
}
