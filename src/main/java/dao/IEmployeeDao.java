package dao;

import com.modal.Employee;
import dto.request.EmployeeRequest;

import java.util.List;

public interface IEmployeeDao {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    void SaveEmployee(Employee employee);
    void DeleteEmployee(int id);
}
