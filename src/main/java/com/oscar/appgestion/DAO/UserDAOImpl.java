package com.oscar.appgestion.DAO;

import com.oscar.appgestion.model.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public List<User> getUsers() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void registerUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> usersList = entityManager.createQuery(query).setParameter("email", user.getEmail())
                .getResultList();
        if (usersList.isEmpty()) {
            return null;
        }
        String hashedPassword = usersList.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(hashedPassword, user.getPassword())) {
            return usersList.get(0);
        }
        return null;
    }

}
