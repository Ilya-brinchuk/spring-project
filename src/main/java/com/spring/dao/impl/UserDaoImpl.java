package com.spring.dao.impl;

import com.spring.dao.UserDao;
import com.spring.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save in data base this user: " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> getAllUsers = session.createQuery("SELECT u FROM User u", User.class);
            return getAllUsers.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all users", e);
        }
    }

    @Override
    public User get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Can't get user by this id: " + id, e);
        }
    }
}
