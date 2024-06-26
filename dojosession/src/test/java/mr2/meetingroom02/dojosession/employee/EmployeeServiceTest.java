package mr2.meetingroom02.dojosession.employee;

import mr2.meetingroom02.dojosession.employee.dao.EmployeeDAO;
import mr2.meetingroom02.dojosession.employee.entity.Employee;
import mr2.meetingroom02.dojosession.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeService employeeService;

    List<Employee> mockEmployees = Arrays.asList(
            Employee.builder()
                    .dateOfBirth(LocalDate.of(2004, 12, 12))
                    .build()
    );

//    @Test
//    public void getAllEmployees_Successfully() {
//        List<Employee> mockEmployees = Arrays.asList(
//                Employee.builder()
//                        .dateOfBirth(LocalDate.of(2004, 12, 12))
//                        .build()
//        );
//
//        when(employeeDAO.findAllDeptEmployee()).thenReturn(mockEmployees);
//
//        List<EmployeeResponseDTO> result = employeeService.getAllEmployees();
//
//        assertEquals(mockEmployees.size(), result.size());
//    }

    @Test
    public void getEmptyEmployeeList() {}

    @Test
    public void employeeNotFound() {}

    @Test
    public void employeeHasNoProject() {}

    @Test
    public void InvalidDateOfBirth_when_CreateEmployee() {}

    @Test
    public void MissingDateOfBirthInfo_when_CreateEmployee() {}

    @Test
    public void DuplicatedEmail_when_CreateEmployee() {}

    @Test
    public void MissingEmailInfo_when_CreateEmployee() {}

    @Test
    public void InvalidEmailFormat_when_CreateEmployee() {}

    @Test
    public void GenderInfoIsMissing_when_CreateEmployee() {}

    @Test
    public void InvalidGenderInput_when_CreateEmployee() {}

    @Test
    public void MissingSalaryInfo_when_CreateEmployee() {}

    @Test
    public void SalaryIsNegative_when_CreateEmployee() {}

    @Test
    public void SalaryHasLetter_when_CreateEmployee() {}

    @Test
    public void duplicatedPhoneNumber_when_CreateEmployee() {}

    @Test
    public void phoneNumberExceedingLength_when_CreateEmployee() {}

    @Test
    public void phoneNumberContainsLetters_when_CreateEmployee() {}

    @Test
    public void NoEmployeeIdFound_when_Delete() {}

    @Test
    public void EmployeeIdContainsLetter_when_Delete() {}

    @Test
    public void NoAuthority_when_Delete() {}

}