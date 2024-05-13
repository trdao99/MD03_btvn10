package controller;

import dto.request.EmployeeRequest;
import service.IEmployeeService;
import service.ipl.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB - kích thước bộ nhớ tạm
        maxFileSize = 1024 * 1024 * 5,   // 5MB
        maxRequestSize = 1024 * 1024 * 5 * 10 // 50MB
)
public class EmployeeServlet extends HttpServlet {
    private static final IEmployeeService employeeService = new EmployeeService();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "LIST":
                    request.setAttribute("list", employeeService.getAllEmployees());
                    request.getRequestDispatcher("/employee/list.jsp").forward(request, response);
                    break;
                case "ADD":
                    request.getRequestDispatcher("/employee/add.jsp").forward(request, response);
                    break;
                case "DEL":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    employeeService.DeleteEmployee(id);
                    System.out.println(id);
                    request.setAttribute("list", employeeService.getAllEmployees());
                    request.getRequestDispatcher("/employee/list.jsp").forward(request, response);
                    break;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADD":
                    String name = request.getParameter("name");
                    String dob = request.getParameter("dob");
                    String sex = request.getParameter("sex");
                    Part file = request.getPart("file");
                    try {
                        EmployeeRequest employeeRequest = new EmployeeRequest(null, name, Boolean.valueOf(sex), sdf.parse(dob), file);
                        employeeService.SaveEmployee(employeeRequest, getServletContext());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    response.sendRedirect("/EmployeeServlet?action=LIST");
                    break;
            }
        }
    }
}