package ir.maktabsharif.ui;

import ir.maktabsharif.entities.Article;
import ir.maktabsharif.entities.User;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.services.ArticleServices;
import ir.maktabsharif.services.CategoryServices;
import ir.maktabsharif.services.TagServices;
import ir.maktabsharif.services.UserServices;

import java.util.Scanner;

public class UserUI {
    private ArticleServices articleServices;
    private Scanner scanner;

    public UserUI() {
        scanner = new Scanner(System.in);
        articleServices = new ArticleServices();
    }

    public void menu(User user) throws Exception {
        boolean flag = true;
        while (flag) {
            System.out.println("\t***User dashboard***");
            System.out.println("\t##" + user.getUsername() + " Welcome##");
            System.out.println("1 -> Create Article" + "\n" +
                    "2 -> Edit Article" + "\n" +
                    "3 -> View Article" + "\n" +
                    "4 -> Change Password" + "\n" +
                    "5 -> Exit");
            int temp = scanner.nextInt();
            switch (temp) {
                case 1:
                    articleServices.createArticleByUser(user);
                    break;
                case 2:
                    articleServices.editArticleByUser(user);
                    break;
                case 3:
                    articleServices.showAllArticleByUser(user);
                    break;
                case 4:
                    new UserServices().changePasswordByUser(user);
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }
}
