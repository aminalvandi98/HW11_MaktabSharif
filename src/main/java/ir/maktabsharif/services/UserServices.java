package ir.maktabsharif.services;

import ir.maktabsharif.entities.User;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class UserServices {
    private UserRepository userRepository;

    public UserServices() {
        userRepository = UserRepository.getUserRepository();
    }

    public void showAllUser() {
        List<User> userList = userRepository.getAllEntity();
        for (User u : userList) {
            System.out.println("Username: " + u.getUsername() + "\t" + "Role: " + u.getRole().getTitle() +
                    " -- " + "National Code: " + u.getNationalCode() + " -- " + "birthday: " + u.getBirthday());
        }
    }

    public void changePasswordByUser(User user) {
        System.out.println("Enter New Password:");
        String newPassword = new Scanner(System.in).next();
        user.setPassword(newPassword);
        userRepository.update(user);
        System.out.println("password was changed.");

    }
}
