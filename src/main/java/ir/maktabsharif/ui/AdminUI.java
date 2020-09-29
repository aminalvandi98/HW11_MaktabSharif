package ir.maktabsharif.ui;

import ir.maktabsharif.entities.*;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.services.*;

import java.util.List;
import java.util.Scanner;

public class AdminUI {
    private RoleServices roleServices;
    private ArticleServices articleServices;
    private CategoryServices categoryServices;
    private TagServices tagServices;
    private UserServices userServices;
    private Scanner scanner;

    public AdminUI() {
        scanner = new Scanner(System.in);
        roleServices = new RoleServices();
        articleServices = new ArticleServices();
        categoryServices = new CategoryServices();
        tagServices = new TagServices();
        userServices = new UserServices();
    }

    public void menu(User adminUser) throws Exception {
        boolean flag = true;
        while (flag) {
            System.out.println("\t***Admin page***");
            System.out.println("\t##" + adminUser.getUsername() + " Welcome##");
            System.out.println("1 -> Change Role" + "\n" +
                    "2 -> Publish OR cancel Publication" + "\n" +
                    "3 -> Delete Article" + "\n" +
                    "4 -> Create category" + "\n" +
                    "5 -> Create Tag" + "\n" +
                    "6 -> View All users" + "\n" +
                    "7 -> View All Article" + "\n" +
                    "8 -> View All Category" + "\n" +
                    "9 -> View All Tag" + "\n" +
                    "10 -> Exit");
            int temp = scanner.nextInt();
            switch (temp) {
                case 1:
                    roleServices.changeUserRole();
                    break;
                case 2:
                    articleServices.changePublishStatus();
                    break;
                case 3:
                    articleServices.removeArticle();
                    break;
                case 4:
                    categoryServices.createNewCategory();
                    break;
                case 5:
                    tagServices.createNewTag();
                    break;
                case 6:
                    userServices.showAllUser();
                    break;
                case 7:
                    articleServices.showAllArticle();
                    break;
                case 8:
                    categoryServices.showAllCategory();
                    break;
                case 9:
                    tagServices.showAllTag();
                    break;
                case 10:
                    flag = false;
                    break;

            }
        }
    }

}
