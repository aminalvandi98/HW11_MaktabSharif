package ir.maktabsharif.services;

import ir.maktabsharif.entities.Article;
import ir.maktabsharif.entities.Role;
import ir.maktabsharif.entities.Tag;
import ir.maktabsharif.entities.User;
import ir.maktabsharif.repository.ArticleRepository;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.repository.RoleRepository;
import ir.maktabsharif.repository.UserRepository;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainServices {
    private UserRepository userRepository;
    private Scanner scanner;

    public MainServices() {
        userRepository = UserRepository.getUserRepository();
        scanner = new Scanner(System.in);
    }

    public void getInformationForSignUp() throws Exception {
        User user = new User();
        System.out.println("\t**Enter the following information**");

        System.out.print("National Code:");
        String nationalCode = scanner.next();
        user.setNationalCode(nationalCode);

        System.out.print("Birthday (year/month/day): ");
        String birthday = scanner.next();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        date = simpleDateFormat.parse(birthday);
        user.setBirthday(simpleDateFormat.format(date));

        System.out.print("User Name:");
        user.setUsername(scanner.next());

        user.setPassword(nationalCode);
        System.out.println("Your password is your National Code by default.");

        Role userRole = RoleRepository.getRoleRepository().findRole("writer");
        if (userRole == null) {
            userRole = new Role();
            userRole.setTitle("writer");
        }
        user.setRole(userRole);
        userRepository.insert(user);
    }

    public void getInformationForSignIn() throws Exception {
        System.out.println("\t**SIGN IN**");
        System.out.print("User Name: ");
        String userName = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        userRepository.checkValidationForSignIn(userName, password);
    }

    public void showAllPublishedArticle() {
        List<Article> articleList = ArticleRepository.getArticleRepository().getAllPublishedArticle();
        if (articleList.size() == 0) System.out.println("There is no Atricle");
        else {
            for (Article a : articleList) {
                System.out.println("\t**********");
                System.out.println("Category: " + a.getCategory().getTitle() + "\tDescription: " + a.getCategory().getDescription());
                System.out.println("Title: " + a.getTitle());
                System.out.println("Brief: " + a.getBrief());
                System.out.println("Content: " + a.getContent());
                System.out.println("Writer: " + a.getWriter());
                System.out.println("Create Date: " + a.getCreateDate());
                System.out.println("Publish Date: " + a.getPublishDate());
                System.out.println("Last Update Date: " + a.getLastUpdateDate());
                List<Tag> tagList = a.getArticleTags();
                System.out.print("Tag: ");
                for (Tag t : tagList) {
                    System.out.print(t.getTitle() + ", ");
                }
                System.out.println("\t**********");
            }

        }
    }
}
