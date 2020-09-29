package ir.maktabsharif.services;

import ir.maktabsharif.entities.Role;
import ir.maktabsharif.entities.User;
import ir.maktabsharif.repository.RoleRepository;
import ir.maktabsharif.repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class RoleServices {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private Scanner scanner;

    public RoleServices() {
        scanner = new Scanner(System.in);
        userRepository = UserRepository.getUserRepository();
        roleRepository = RoleRepository.getRoleRepository();
    }

    public void changeUserRole() {
        User userUpdate = null;

        List<User> userList = userRepository.getAllEntity();
        for (User u : userList) {
            System.out.println(u.getId() + " -> " + "Username: " + u.getUsername() + "\t" + "Role: " + u.getRole().getTitle());
        }
        System.out.print("which user do you like to change role: ");
        int roleID = scanner.nextInt();
        for (User u : userList) {
            if (u.getId() == roleID) userUpdate = u;
        }
        Role roleUpdate = null;
        List<Role> roleList = roleRepository.getAllEntity();
        for (Role r : roleList) {
            System.out.println(r.getId() + " -> " + r.getTitle());
        }
        System.out.print("Enter the role you want: ");
        int role = scanner.nextInt();
        for (Role r : roleList) {
            if (r.getId() == role) roleUpdate = r;
        }
        userUpdate.setRole(roleUpdate);
        userRepository.update(userUpdate);
        System.out.println("### DONE ###");
    }
}
