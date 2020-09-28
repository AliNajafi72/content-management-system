package ir.maktabsharif.manager;

import ir.maktabsharif.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CategoryManager implements Manager<Category> {
    @Override
    public Optional<Category> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getAll() {
        EntityManagerFactory categoryEntityManagerFactory =
                Persistence.createEntityManagerFactory("ContentManagementSystem");
        EntityManager categoryEntityManager = categoryEntityManagerFactory.createEntityManager();
        List<Category> categories = List.of();
        try {
            Query query = categoryEntityManager.createQuery(
                    "select c from Category c"
                    ,Category.class
            );
            categories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            categoryEntityManagerFactory.close();
        }
        return categories;
    }

    @Override
    public void insert(Category t) {
        EntityManagerFactory categoryEntityManagerFactory =
                Persistence.createEntityManagerFactory("ContentManagementSystem");
        EntityManager categoryEntityManager = categoryEntityManagerFactory.createEntityManager();
        try {
            categoryEntityManager.getTransaction().begin();
            categoryEntityManager.persist(t);
            categoryEntityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            categoryEntityManager.getTransaction().rollback();
        } finally {
            categoryEntityManagerFactory.close();
        }
    }

    @Override
    public void update(Category t) {

    }

    @Override
    public void delete(Category t) {

    }
}
