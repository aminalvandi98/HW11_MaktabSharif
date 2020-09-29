package ir.maktabsharif.repository;

import ir.maktabsharif.entities.Category;
import ir.maktabsharif.entities.Tag;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TagRepository extends Repository<Tag, Integer> {
    private static TagRepository tagRepository;

    private TagRepository() {

    }

    @Override
    protected Class<Tag> getEntityClass() {
        return Tag.class;
    }

    public static TagRepository getTagRepository() {
        if (tagRepository == null) tagRepository = new TagRepository();
        return tagRepository;
    }
}
