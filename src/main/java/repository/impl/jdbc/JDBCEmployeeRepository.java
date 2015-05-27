package repository.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import repository.EmployeeRepository;
import models.Employee;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class JDBCEmployeeRepository implements EmployeeRepository {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Employee> getEmloyeeListById(int dep_id) throws Exception {
        ArrayList<Employee> emp = new ArrayList<Employee>();
        int id;
        String title;
        String firstName;
        String secondName;
        int salary;
        Date date;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE id_dep= (?)");
            ps.setInt(1, dep_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                title = rs.getString("title");
                firstName = rs.getString("first_name");
                secondName = rs.getString("second_name");
                salary = rs.getInt("salary");
                date = rs.getDate("date");
                Employee test = new Employee();
                test.setId(id);
                test.setDep_id(dep_id);
                test.setJob_title(title);
                test.setFirst_name(firstName);
                test.setSecond_name(secondName);
                test.setSalary(salary);
                test.setDate(date);
                emp.add(test);
            }
            ps.close();

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    @Override
    public void deleteById(int id) throws Exception {

        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Employee WHERE id=(?)");
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createEmployee(int idDep, String title, String firstName, String secondName, int salary, Date date) throws Exception {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Employee (id_dep, title, first_name, second_name, salary, date) " +
                            "VALUES ( ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, idDep);
            ps.setString(2, title);
            ps.setString(3, firstName);
            ps.setString(4, secondName);
            ps.setInt(5, salary);
            ps.setDate(6, date);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Employee getEmloyeeById(int idd) throws Exception {
        Employee emp = new Employee();
        Integer id;
        String title;
        String firstName;
        String secondName;
        int salary;
        Date date;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE id= (?)");
            ps.setInt(1, idd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                title = rs.getString("title");
                firstName = rs.getString("first_name");
                secondName = rs.getString("second_name");
                salary = rs.getInt("salary");
                date = rs.getDate("date");
                emp.setId(id);
                emp.setDep_id(idd);
                emp.setJob_title(title);
                emp.setFirst_name(firstName);
                emp.setSecond_name(secondName);
                emp.setSalary(salary);
                emp.setDate(date);
            }
            ps.close();

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    @Override
    public void editEmployee(Integer id, String title, String firstName, String secondName, Integer salary, Date date) throws Exception {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Employee SET " +
                    "title =?, first_name = ?, second_name = ?, salary = ?, date = ? WHERE id =?");
            ps.setString(1, title);
            ps.setString(2, firstName);
            ps.setString(3, secondName);
            ps.setInt(4, salary);
            ps.setDate(5, date);
            ps.setInt(6, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Integer getId_dById(Integer id) throws Exception {
        Integer id_d = null;

        Connection con = dataSource.getConnection();
        try {
            con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Employee WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_d = rs.getInt("id_dep");
            }
            ps.close();

        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id_d;
    }
}
