package repository;

import models.Department;

import java.util.List;


public interface DepartmentRepository {

    List<Department> getAll();

    void createDepartment(String title);

    void deleteDepartment(int id);

    Department getDepartmentById(int id);

    void editDepartment(String test, int id);

}
