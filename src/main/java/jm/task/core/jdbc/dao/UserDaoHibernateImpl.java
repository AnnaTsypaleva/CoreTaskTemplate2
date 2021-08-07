package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final static SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(60), " +
                "lastname VARCHAR(60), age TINYINT, UNIQUE(id));").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user;").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createCriteria(User.class).list();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE user;";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery(cleanTable).executeUpdate();
        session.getTransaction().commit();
    }
}