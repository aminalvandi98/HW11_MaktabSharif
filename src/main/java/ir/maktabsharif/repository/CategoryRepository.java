package ir.maktabsharif.repository;

import ir.maktabsharif.entities.Article;
import ir.maktabsharif.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepository extends Repository<Category, Integer> {
    private static CategoryRepository categoryRepository;

    private CategoryRepository() {

    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    public static CategoryRepository getCategoryRepository() {
        if (categoryRepository == null) categoryRepository = new CategoryRepository();
        return categoryRepository;
    }

}
