package ir.maktabsharif.manager;

import ir.maktabsharif.entities.Article;
import ir.maktabsharif.entities.Category;
import ir.maktabsharif.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class ArticleManager implements Manager<Article> {
    @Override
    public Optional<Article> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Article> getAll() {
        return null;
    }

    @Override
    public void insert(Article t) {

    }

    public void insert(Article t, long categoryId, long userId) {
        EntityManagerFactory articleEntityManagerFactory =
                Persistence.createEntityManagerFactory("ContentManagementSystem");
        EntityManager articleEntityManager = articleEntityManagerFactory.createEntityManager();
        User user = articleEntityManager.find(User.class,userId);
        Category category = articleEntityManager.find(Category.class, categoryId);
        t.setUser(user);
        t.setCategory(category);
        try {
            articleEntityManager.getTransaction().begin();
            articleEntityManager.persist(t);
            articleEntityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            articleEntityManager.getTransaction().rollback();
        } finally {
            articleEntityManagerFactory.close();
        }
    }

    @Override
    public void update(Article t) {

    }

    @Override
    public void delete(Article t) {

    }
}
