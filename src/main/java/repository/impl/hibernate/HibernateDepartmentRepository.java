package repository.impl.hibernate;

import models.Department;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.DepartmentRepository;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class HibernateDepartmentRepository implements DepartmentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAll() throws Exception {

        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from Department");

        return (List<Department>) q.list();
    }

    @Override
    public void createDepartment(String title) throws Exception {

        Department dep = new Department();
        dep.setTitle(title);
        Session session = sessionFactory.getCurrentSession();
        session.save(dep);
    }

    @Override
    public void deleteDepartment(int id) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        Department dep = (Department) session.load(Department.class, id);
        session.delete(dep);

    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(int id) throws Exception {

            Session  session = sessionFactory.getCurrentSession();
            String hql = "from Department where department_id=:id";
            Query q = session.createQuery(hql);
            q.setParameter("id", id);

        return (Department) q.uniqueResult();
    }

    @Override
    public void editDepartment(String test, int id) throws Exception {

            Session session = sessionFactory.getCurrentSession();
            String hql = "UPDATE Department SET title =:test WHERE department_id =:id";
            Query q = session.createQuery(hql);
            q.setParameter("test", test);
            q.setParameter("id", id);
            q.executeUpdate();
    }
}
