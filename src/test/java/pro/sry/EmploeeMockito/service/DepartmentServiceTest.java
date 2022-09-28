package pro.sry.EmploeeMockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sry.EmploeeMockito.Employee;
import pro.sry.EmploeeMockito.WorkingExceptions.EmployeeNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Иван", "Сидоров", 45_000D, 1),
                new Employee("Петр", "Иванов", 55_000D, 2),
                new Employee("Маргарита", "Петрова", 70_000D, 1),
                new Employee("Ольга", "Иванова", 40_000D, 1),
                new Employee("Виктор", "Петров", 60_000D, 2)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("searchMaxSalaryParams")
    public void searchMaxSalaryPositiveTest(Integer departmentId, Employee expected) {
        assertThat(departmentService.searchMaxSalary(departmentId)).isEqualTo(expected);
    }

    @Test
    public void searchMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).
                isThrownBy(() -> departmentService.searchMaxSalary(4));
    }

    @ParameterizedTest
    @MethodSource("searchMinSalaryParams")
    public void searchMinSalaryPositiveTest(Integer department, Employee expected) {
        assertThat(departmentService.searchMinSalary(department)).isEqualTo(expected);
    }

    @Test
    public void searchMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class).
                isThrownBy(() -> departmentService.searchMinSalary(4));
    }

    @ParameterizedTest
    @MethodSource("getEmployeesByiDepartmentIDParams")
    public void getEmployeesByiDepartmentID(Integer department,List<Employee> expected) {
        assertThat(departmentService.getEmployeesByiDepartmentID(department).containsExactlyElementsOf(expected));
    }

    @Test
    public void getEmployeesGroupedByDepartments() {
        assertThat(departmentService.getEmployeesGroupedByDepartments().containsAllEntriesOf(
                        Map.of(
                                1, List.of(new Employee("Иван", "Сидоров", 45_000D, 1),
                                        new Employee("Маргарита", "Петрова", 70_000D, 1),
                                        new Employee("Ольга", "Иванова", 40_000D, 1)),
                                2, List.of(new Employee("Петр", "Иванов", 55_000D, 2),
                                        new Employee("Виктор", "Петров", 60_000D, 2))
                        )
                )
        );
    }

    public static Stream<Arguments> searchMaxSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Виктор", "Петров", 60_000D, 2),
                        Arguments.of(2, new Employee("Маргарита", "Петрова", 70_000D, 1)))
        );
    }

    public static Stream<Arguments> searchMinSalaryParams() {
        return Stream.of(
                Arguments.of(1, new Employee("Иван", "Сидоров", 45_000D, 1),
                        Arguments.of(2, new Employee("Ольга", "Иванова", 40_000D, 1)))
        );
    }

    public static Stream<Arguments> getEmployeesByiDepartmentIDParams() {
        return Stream.of(
                Arguments.of(1,new Employee("Иван", "Сидоров", 45_000D, 1),
                        new Employee("Маргарита", "Петрова", 70_000D, 1),
                        new Employee("Ольга", "Иванова", 40_000D, 1)),
                Arguments.of(2, new Employee("Петр", "Иванов", 55_000D, 2),
                        new Employee("Виктор", "Петров", 60_000D, 2)),
                Arguments.of(3, Collections.emptyList())
        );
    }
}