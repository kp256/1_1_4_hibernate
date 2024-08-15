package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
//package jm.task.core.jdbc.dao;
//
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//public class UserDaoHibernateImpl implements UserDao {
//
//    public UserDaoHibernateImpl() {
//
//    }
//
//    SessionFactory sessionFactory = Util.getSessionFactory();
//    private static final Logger logger = Logger.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
//
//    static {
//        logger.setLevel(Level.FINEST);
//    }
//    //    User user;
//
//
//
//    @Override
//    public void dropUsersTable() {
//
//        Transaction transaction = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            session.createNativeQuery("DROP TABLE IF EXISTS users", User.class).executeUpdate();
//
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//
//        }
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        Transaction transaction = null;
//        User user = new User();
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//            user.setName(name);
//            user.setLastName(lastName);
//            user.setAge(age);
//            session.persist(user);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                System.out.println("Fuck");
//                logger.warning("error" + e.getMessage());
//                transaction.rollback();
//
//            }
//            logger.log(Level.SEVERE, e, () -> "Failed to save user: " + name + " " + lastName);
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void removeUserById(long id) {
//
//        Transaction transaction = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            session.remove(session.get(User.class, id));
//
//
//            session.getTransaction().commit();
//
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//
//        Transaction transaction = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            List<User> list = session.createQuery("FROM User ", User.class).getResultList();
//
//            transaction.commit();
//            return list;
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//
//
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public void cleanUsersTable() {
//
//
//        try (Session session = sessionFactory.openSession()) {
//
//            session.beginTransaction();
//
//            User user = new User();
//            session.persist(user);
//
//            session.remove(user);
//
//
//            session.getTransaction().commit();
//
//
//        }
//    }
//}
public class UserDaoHibernateImpl extends Util implements UserDao {
    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) , lastName VARCHAR(45) , age INT);";
    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users;";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?;";
    private static final String CLEAN_USER_TABLE = "DELETE FROM users;";
    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CREATE_USERS_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery(DROP_USERS_TABLE).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE id=" + id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CLEAN_USER_TABLE).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
