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

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAll(){

        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from Department");

        return (List<Department>) q.list();
    }

    @Override
    public void createDepartment(String title){

        Department dep = new Department();
        dep.setTitle(title);
        Session session = sessionFactory.getCurrentSession();
        session.save(dep);
    }

    @Override
    public void deleteDepartment(int id){

        Session session = sessionFactory.getCurrentSession();
        Department dep = (Department) session.load(Department.class, id);
        session.delete(dep);

    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(int id){

            Session  session = sessionFactory.getCurrentSession();
            String hql = "from Department where idDepartment=:id";
            Query q = session.createQuery(hql);
            q.setParameter("id", id);

        return (Department) q.uniqueResult();
    }

    @Override
    public void editDepartment(String test, int id){

            Session session = sessionFactory.getCurrentSession();
            String hql = "UPDATE Department SET title =:test WHERE idDepartment =:id";
            Query q = session.createQuery(hql);
            q.setParameter("test", test);
            q.setParameter("id", id);
            q.executeUpdate();

    }
}
