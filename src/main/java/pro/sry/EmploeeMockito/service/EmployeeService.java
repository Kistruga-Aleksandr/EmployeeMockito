package pro.sry.EmploeeMockito.service;



import org.springframework.stereotype.Service;
import pro.sry.EmploeeMockito.Employee;
import pro.sry.EmploeeMockito.WorkingExceptions.EmployeeAlreadyAddedException;
import pro.sry.EmploeeMockito.WorkingExceptions.EmployeeNotFoundException;
import pro.sry.EmploeeMockito.WorkingExceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final ValidateService validateService;
    public static final int limitEmployees = 20;
    private final List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Иванов", "Иван", 300_000D, 5),
            new Employee("Петров", "Роман", 105_000D, 2),
            new Employee("Сидоров", "Юрий", 150_000D, 5),
            new Employee("Куликов", "Михайл", 800_000D, 1),
            new Employee("Ильин", "Александр", 200_000D, 1),
            new Employee("Рогонов", "Александр", 350_000D, 3),
            new Employee("Белов", "Евгений", 80_000D, 4),
            new Employee("Сосновцев", "Виктор", 120_000D, 4),
            new Employee("Смирнов", "Андрей", 180_000D, 3),
            new Employee("Андреев", "Роман", 250_000D, 2)
    ));

    public EmployeeService(ValidateService validateService) {
        this.validateService = validateService;
    }

    public Employee add(String lastName, String firstName, Double salary, Integer department) {
        Employee employee = new Employee(validateService.validateLastName(lastName),
                validateService.validateFirstName(firstName), salary, department);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < limitEmployees) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();

    }

    public Employee removeEmployee(String lastName, String firstName) {
        Employee employee = employees.stream().filter(emp -> emp.getLastName().equals(lastName)
                        && emp.getFirstName().equals(firstName)).findFirst().
                orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;

    }

    public Employee getEmployee(String lastName, String firstName) {
        return employees.stream().filter(emp -> emp.getLastName().equals(lastName)
                        && emp.getFirstName().equals(firstName)).findFirst().
                orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

}
