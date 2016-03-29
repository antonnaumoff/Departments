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

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernateEmployeeRepository implements EmployeeRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmloyeeListById(int id) {

        Session session = sessionFactory.getCurrentSession();

        String hql = "from Employee where id_dep =:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        //noinspection unchecked
        List<Employee> list = (List<Employee>) q.list();

        return list;
    }

    @Override
    public void deleteById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Employee emp = (Employee) session.load(Employee.class, id);
        session.delete(emp);

    }

    @Override
    public void createEmployee(Employee emp) {

        Session session = sessionFactory.getCurrentSession();
        session.save(emp);

    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmloyeeById(int id) {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        Employee emp = (Employee) q.uniqueResult();

        return emp;
    }

    @Override
    public void editEmployee(Employee emp) {

        Session session = sessionFactory.getCurrentSession();
        session.update(emp);

    }

    @Override
    @Transactional(readOnly = true)
    public Integer getId_dById(Integer id)  {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where id=:id";
        Query q = session.createQuery(hql);
        q.setParameter("id", id);
        Integer id_d = ((Employee) q.uniqueResult()).getDep_id();

        return id_d;
    }
}
