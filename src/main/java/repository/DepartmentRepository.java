package repository;

import models.Department;

import java.util.List;


public interface DepartmentRepository {

    List<Department> getAll() throws Exception;

    void createDepartment(String title) throws Exception;

    void deleteDepartment(int id) throws Exception;

    Department getDepartmentById(int id) throws Exception;

    void editDepartment(String test, int id) throws Exception;

}
