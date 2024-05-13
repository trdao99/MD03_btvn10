package service;

import com.modal.Employee;
import dto.request.EmployeeRequest;

import javax.servlet.ServletContext;
import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    void SaveEmployee(EmployeeRequest request, ServletContext context);
    void DeleteEmployee(int id);
}
