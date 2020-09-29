package ir.maktabsharif.repository;

import ir.maktabsharif.entities.Article;
import ir.maktabsharif.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleRepository extends Repository<Article, Integer> {

    private static ArticleRepository articleRepository;

    private ArticleRepository() {

    }

    public static ArticleRepository getArticleRepository() {
        if (articleRepository == null) articleRepository = new ArticleRepository();
        return articleRepository;
    }

    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }


    public List<Article> getAllArticleByUser(User user) {
        List<Article> articleList = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();

            TypedQuery<Article> query = entityManager.createQuery("select a from Article a where user_id=:u", Article.class);
            query.setParameter("u", user.getId());
            articleList = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;
    }



    public List<Article> getAllPublishedArticle() {
        List<Article> articleList = null;
        try {
            entityManager.getTransaction().begin();

            TypedQuery<Article> query = entityManager.createQuery("select a from Article a where isPublished = true", Article.class);
            articleList = query.getResultList();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;
    }


}
