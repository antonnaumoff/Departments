package repository.impl.hibernate;

import models.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.EmployeeRepository;

import java.sql.Date;
import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernateEmployeeRepository implements EmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmloyeeListById(int id) throws Exception {

        Session session = sessionFactory.getCurrentSession();

        String hql = "from Employee where id_dep =:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        //noinspection unchecked
        return (List<Employee>) q.list();
    }

    @Override
    public void deleteById(int id) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        Employee emp = (Employee) session.load(Employee.class, id);
        session.delete(emp);

    }

    @Override
    public void createEmployee(int idDep, String title, String firstName, String secondName, int salary, Date date) throws Exception {//TODO create model
        Employee emp = new Employee();
        emp.setDep_id(idDep);
        emp.setJob_title(title);
        emp.setFirst_name(firstName);
        emp.setSecond_name(secondName);
        emp.setSalary(salary);
        emp.setDate(date);

        Session session = sessionFactory.getCurrentSession();
        session.save(emp);

    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmloyeeById(int id) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        Employee emp = (Employee) q.uniqueResult();

        return emp;
    }

    @Override
    public void editEmployee(Integer id, String title, String firstName, String secondName, Integer salary, Date date) throws Exception {

        Session session = sessionFactory.getCurrentSession();

        String hql = "UPDATE Employee SET job_title=:title,first_name=:firstName,second_name=:secondName,salary=:salary,date=:date WHERE id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("title", title);
        q.setParameter("firstName", firstName);
        q.setParameter("secondName", secondName);
        q.setParameter("salary", salary);
        q.setParameter("date", date);
        q.setParameter("id", id);
        q.executeUpdate();

    }

    @Override
    @Transactional(readOnly = true)
    public Integer getId_dById(Integer id) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        Integer id_d = ((Employee) q.uniqueResult()).getDep_id();

        return id_d;
    }
}
