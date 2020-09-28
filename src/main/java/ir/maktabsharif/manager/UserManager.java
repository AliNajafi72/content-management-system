package ir.maktabsharif.manager;

import ir.maktabsharif.entities.cms.User;
import ir.maktabsharif.entities.cms.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UserManager implements Manager<User> {
    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    public Optional getUserByUsername(String username) {
        EntityManagerFactory userEntityManagerFactory =
                Persistence.createEntityManagerFactory("ContentManagementSystem");
        EntityManager userEntityManager = userEntityManagerFactory.createEntityManager();
        User user;
        Optional userOptional = Optional.empty();
        try {
            Query query = userEntityManager.createQuery(
                    "select count(u) from User u where u.username = :username"
                    ,Long.class
            );
            query.setParameter("username", username);
            Long rowCount = (Long) query.getSingleResult();
            if (rowCount == 1L) {
                query = userEntityManager.createQuery(
                        "select u from User u where u.username = :username"
                        ,User.class
                );
                query.setParameter("username", username);
                user = (User) query.getSingleResult();
                System.out.println(user.toString());
                userOptional = Optional.of(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userEntityManagerFactory.close();
        }
        return userOptional;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void insert(User t) {
        EntityManagerFactory userEntityManagerFactory =
                Persistence.createEntityManagerFactory("ContentManagementSystem");
        EntityManager userEntityManager = userEntityManagerFactory.createEntityManager();
        try {
            UserRole userRole = new UserRole();
            userRole.setRole("Author");
            t.setUserRole(userRole);
            userEntityManager.getTransaction().begin();
            userEntityManager.persist(t);
            userEntityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            userEntityManager.getTransaction().rollback();
        } finally {
            userEntityManagerFactory.close();
        }
    }

    @Override
    public void update(User t) {

    }

    @Override
    public void delete(User t) {

    }
}
