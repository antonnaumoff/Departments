package repository;

import models.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> getEmloyeeListById(int id);

    void deleteById(int id);

    void createEmployee(Employee emp);

    Employee getEmloyeeById(int id);

    void editEmployee(Employee emp);

    Integer getId_dById(Integer id);
}
