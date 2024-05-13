package service.ipl;

import com.modal.Employee;
import dao.IEmployeeDao;
import dao.ipl.EmployeeDao;
import dto.request.EmployeeRequest;
import service.IEmployeeService;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    public static final IEmployeeDao employeeDao = new EmployeeDao();

    @Override
    public List<Employee>  getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public void SaveEmployee(EmployeeRequest request, ServletContext context) {
        Employee modal = new Employee(request.getId(), request.getName(), request.getSex(), request.getBirthday(), null);
        String path = context.getRealPath("/upload");
        File fileUp = new File(path);
        if (!fileUp.exists()) {
            fileUp.mkdir();
        }

        if (request.getId() != null) {

        } else {
            modal.setAvatar("#");
        }

        Part file = request.getFile();
        if (file.getSize() > 0) {
            modal.setAvatar("/upload/" + file.getSubmittedFileName());
            try {
                file.write(path + File.separator + file.getSubmittedFileName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        employeeDao.SaveEmployee(modal);
    }

    @Override
    public void DeleteEmployee(int id) {
        employeeDao.DeleteEmployee(id);
    }

}
