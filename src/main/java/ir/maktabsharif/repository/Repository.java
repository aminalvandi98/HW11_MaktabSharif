package ir.maktabsharif.repository;


import ir.maktabsharif.JPAUtill;

import javax.persistence.*;
import java.util.List;

public abstract class Repository<E, Id extends Number> {
    protected EntityManager entityManager;

    public Repository() {
        entityManager = JPAUtill.getEntityManager();
    }
    public E insert(E entity){
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    public E getEntityById(Id id){
        entityManager.getTransaction().begin();
        E entity = entityManager.find(getEntityClass(), id);
        entityManager.getTransaction().commit();
        return entity;
    }

    public List<E> getAllEntity(){
        entityManager.getTransaction().begin();
        TypedQuery<E> query = entityManager.createQuery("select entity from " + getEntityClass().getName()+" entity", getEntityClass());
        List<E> resultList = query.getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    public E update(E entity){
        entityManager.getTransaction().begin();
        E merge = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return merge;
    }

    public void delete(E entity){
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public void deleteById(Id id) throws Exception {
        E entity = getEntityById(id);
        if(entity==null){
            throw new Exception("Entity with class name: "+
                    getEntityClass().getSimpleName()+
                    " and id: "+id+" not exist");
        }
        delete(entity);
    }


    protected abstract Class<E> getEntityClass();
}
