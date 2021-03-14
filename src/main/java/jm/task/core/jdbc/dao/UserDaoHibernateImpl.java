package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE coretaskdb.users (" +
                    " id BIGINT(20) NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(45) NOT NULL," +
                    " lastname VARCHAR(45) NOT NULL," +
                    " age TINYINT(3) NOT NULL," +
                    " PRIMARY KEY (id))" +
                    " ENGINE = InnoDB" +
                    " DEFAULT CHARACTER SET = utf8;").executeUpdate();

            transaction.commit();
        } catch (PersistenceException ex) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("DROP TABLE users").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new User(name, lastName, age));

        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setId(id);
            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            list = (List<User>) Util.getSessionFactory().openSession().createQuery("FROM User").list();
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createQuery("DELETE FROM User").executeUpdate();

            transaction.commit();
        } catch (Exception e) {
        }






    }
}
