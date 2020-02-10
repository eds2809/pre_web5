package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.persistence.Query;
import java.util.List;

public class UserHibernateDAO implements UserDao {

    public UserHibernateDAO() {

    }

    private Session getSession() {
        return DBHelper.getSessionFactory().openSession();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSession();
        Query query = session.createQuery("from User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public boolean addUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        long id = (long) session.save(user);
        transaction.commit();
        session.close();
        return id > 0;
    }

    @Override
    public boolean delUser(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        user = session.get(User.class,user.getId());
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update User set name=:name,pass=:pass,age=:age where id=:id"
        );
        query.setParameter("name", user.getName());
        query.setParameter("id", user.getId());
        query.setParameter("age", user.getAge());
        query.setParameter("pass", user.getPass());
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result > 0;
    }

    @Override
    public User getUser(String name, String pass) {
        Session session = getSession();
        Query query = session.createQuery(
                "from User where  name=:name and pass=:pass"
        );

        query.setParameter("name",name);
        query.setParameter("pass",pass);


        User user = (User) query.getSingleResult();
        session.close();
        return user;
    }
}
