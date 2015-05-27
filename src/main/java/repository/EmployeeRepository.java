package repository;

import models.Employee;

import java.sql.Date;
import java.util.List;

public interface EmployeeRepository {

    List<Employee> getEmloyeeListById(int id) throws Exception;

    void deleteById(int id) throws Exception;

    void createEmployee(int idDep, String title, String firstName, String secondName, int salary, Date date) throws Exception;

    Employee getEmloyeeById(int id)throws Exception;

    void editEmployee(Integer id, String title, String firstName, String secondName, Integer salary, Date date) throws Exception;

    Integer getId_dById(Integer id) throws Exception;
}
