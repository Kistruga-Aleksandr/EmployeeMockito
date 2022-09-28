package pro.sry.EmploeeMockito.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sry.EmploeeMockito.Employee;
import pro.sry.EmploeeMockito.WorkingExceptions.EmployeeAlreadyAddedException;
import pro.sry.EmploeeMockito.WorkingExceptions.EmployeeNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidateService());


    @ParameterizedTest
    @MethodSource("params")
    void addNegativeTest1(String lastName, String firstName, Double salary, Integer department) {
        Employee expected = new Employee(lastName, firstName, salary, department);
        assertThat(employeeService.add(lastName, firstName, salary, department)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(lastName, firstName, salary, department));
    }

    @ParameterizedTest
    @MethodSource("params")
    void addNegativeTest2(String lastName, String firstName, Double salary, Integer department) {
        List<Employee> employees = generateEmployees(20);
        employees.forEach(employee -> assertThat(employeeService.add(employee.getLastName(), employee.getFirstName(),
                employee.getSalary(), employee.getDepartment())).isEqualTo(employee));

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(lastName, firstName, salary, department));
    }

    @ParameterizedTest
    @MethodSource("params")
    void removeEmployeeNegativeTest(String lastName, String firstName, Double salary, Integer department) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("test", "Test"));

        Employee expected = new Employee(lastName, firstName, salary, department);
        assertThat(employeeService.add(lastName, firstName, salary, department)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("test", "Test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    void RemoveEmployeePositiveTest(String lastName, String firstName, Double salary, Integer department) {
        Employee expected = new Employee(lastName, firstName, salary, department);
        assertThat(employeeService.add(lastName, firstName, salary, department)).isEqualTo(expected);
        assertThat(employeeService.removeEmployee(lastName, firstName)).isEqualTo(expected);
        assertThat(employeeService.getAll()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("params")
    void getEmployeeNegativeTest(String lastName, String firstName, Double salary, Integer department) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.getEmployee("test", "Test"));
        Employee expected = new Employee(lastName, firstName, salary, department);
        assertThat(employeeService.add(lastName, firstName, salary, department)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.getEmployee("test", "Test"));

    }

    @ParameterizedTest
    @MethodSource("params")
    void testGetEmployeePositiveTest(String lastName, String firstName, Double salary, Integer department) {
        Employee expected = new Employee(lastName, firstName, salary, department);
        assertThat(employeeService.add(lastName, firstName, salary, department)).isEqualTo(expected);
        assertThat(employeeService.getEmployee(lastName, firstName)).isEqualTo(expected);
        assertThat(employeeService.getAll()).containsExactly(expected);
    }

    private List<Employee> generateEmployees(Integer size) {
        return Stream.iterate(1, i -> i + 1).limit(size).map(i -> new Employee ("Name" + (char) ((int) 'a' + 1),"FirstName" + (char)((int)'a' + i),
                        10_000D + i, i))
                .collect(Collectors.toList());
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Anton","Antonov",2,65_000D),
                Arguments.of("Aleks","Alekseev",2,45_000D),
                Arguments.of("Nata","Ivanova",1,85_000D)
        );

    }
}