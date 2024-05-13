package dao.ipl;

import com.modal.Employee;
import dao.IEmployeeDao;
import util.ConnecDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IEmployeeDao {
    @Override
    public List<Employee> getAllEmployees() {
        Connection conn = ConnecDB.getConnection();
        List<Employee> list = new ArrayList<Employee>();
        try {
            PreparedStatement ps = conn.prepareStatement("select*from Employee");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setBirthday(rs.getDate("birthday"));
                emp.setSex(rs.getBoolean("sex"));
                emp.setAvatar(rs.getString("avatar"));
                list.add(emp);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnecDB.closeConnection(conn);
        }

    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public void SaveEmployee(Employee employee) {
        Connection conn = ConnecDB.getConnection();
        PreparedStatement ps = null;
        try{
            if(employee.getId() == null) {
                ps = conn.prepareStatement("insert into employee(name,birthday, sex, avatar)values (?,?,?,?)");
                ps.setString(1,employee.getName());
                ps.setDate(2, new  java.sql.Date(employee.getBirthday().getTime()));
                ps.setBoolean(3,employee.getSex());
                ps.setString(4, employee.getAvatar());
            }
            else{

            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnecDB.closeConnection(conn);
        }
    }

    @Override
    public void DeleteEmployee(int id_IN) {
        Connection conn = ConnecDB.getConnection();
        PreparedStatement ps = null;
        try{
                ps = conn.prepareStatement("delete from employee where id = ?");
                ps.setInt(1, id_IN);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnecDB.closeConnection(conn);
        }
    }
}
