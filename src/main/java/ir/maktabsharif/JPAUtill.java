package ir.maktabsharif;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtill {
    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("my-persistence-unit");
    private  static EntityManager entityManager;

    private JPAUtill(){}

    public static EntityManager getEntityManager(){
        if (entityManager==null){
            entityManager = entityManagerFactory.createEntityManager() ;
        }
        return entityManager;
    }

    public static void closeEntityManager(){
        entityManager.close();
    }

}
