package ir.maktabsharif.repository;

import ir.maktabsharif.entities.Role;
import ir.maktabsharif.entities.User;
import ir.maktabsharif.ui.AdminUI;
import ir.maktabsharif.ui.UserUI;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepository extends Repository<User, Integer> {
    private static UserRepository userRepository;

    private UserRepository() {

    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) userRepository = new UserRepository();
        return userRepository;
    }

    public void checkValidationForSignIn(String userName, String password) throws Exception {
        List<User> userFoundList = null;
        try {
            entityManager.getTransaction().begin();
            TypedQuery<User> query = entityManager.createQuery(
                    "select u from User u where username=:uname and password=:pass", User.class);
            query.setParameter("uname", userName);
            query.setParameter("pass", password);
            userFoundList = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userFoundList.size() != 0) {
            if (userFoundList.get(0).getRole().getTitle().equals("admin")) {
                new AdminUI().menu(userFoundList.get(0));
            } else if (userFoundList.get(0).getRole().getTitle().equals("writer")) {
                new UserUI().menu(userFoundList.get(0));
            }
        } else System.out.println("User Name Or Password is incorrct.");

    }


//
//    public int changePasswordInDatabase(User user, String newPass) {
//        int status = 0;
//        try {
//            entityManager.getTransaction().begin();
//            Query query = entityManager.createQuery("update User set password=:p where id=:i");
//            query.setParameter("p", newPass);
//            query.setParameter("i", user.getId());
//            status = query.executeUpdate();
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return status;
//    }
}
