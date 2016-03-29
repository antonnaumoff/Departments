package service;

import models.Department;
import models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.DepartmentRepository;
import repository.EmployeeRepository;

import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DataServiceImpl implements DataService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Cacheable(value="department")
    @Transactional(readOnly = true)
    public List<Department> getDepartmentList() {
        return departmentRepository.getAll();
    }

    @Override
    @CachePut(value="department")
    public void createDepartment(String title)  {
        departmentRepository.createDepartment(title);
    }

    @Override
    @CacheEvict(value = "department", key="id")
    public void deleteDepartment(int id) {
        departmentRepository.deleteDepartment(id);
    }

    @Override
    @Cacheable(value="department", key="#id")
    @Transactional(readOnly = true)
    public Department getDepartmentById(int id) {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    @CacheEvict(value = "department", key="#dep.id")
    public void editDepartment(Department dep)  {
        departmentRepository.editDepartment(dep.getTitle(), dep.getId());
    }

    @Override
    @Cacheable(value = "employee")
    @Transactional(readOnly = true)
    public List<Employee> getEmloyeeListById(int id) {
        return employeeRepository.getEmloyeeListById(id);
    }

    @Override
    @CacheEvict(value = "employee", key="#id")
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @CachePut(value = "employee", key="#emp.id")
    public void createEmployee(Employee emp) {
        employeeRepository.createEmployee(emp);
    }

    @Override
    @Cacheable(value="employee", key="#id")
    public Employee getEmloyeeById(int id) {
        return employeeRepository.getEmloyeeById(id);
    }

    @Override
    @CacheEvict(value = "employee", key="#emp.id")
    public void editEmployee(Employee emp) {
        employeeRepository.editEmployee(emp);
    }

    @Override
    public Integer getId_dById(Integer id) {
        return employeeRepository.getId_dById(id);
    }
}
