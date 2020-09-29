package ir.maktabsharif.ui;

import ir.maktabsharif.JPAUtill;
import ir.maktabsharif.services.MainServices;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class MainUI {
    private Scanner scanner;
    private MainServices mainServices;

    public MainUI(){
        scanner = new Scanner(System.in);
        mainServices = new MainServices();
    }
    public void startingPage() throws Exception {
        boolean flag = true;
        while (flag) {
            System.out.println("1 -> SIGN UP" + "\n" +
                    "2 -> SIGN IN" + "\n" +
                    "3 -> VIEW ARTICLE" + "\n" +
                    "4 -> EXIT");
            int temp = scanner.nextInt();
            switch (temp) {
                case 1:
                    mainServices.getInformationForSignUp();
                    break;
                case 2:
                    mainServices.getInformationForSignIn();
                    break;
                case 3:
                    mainServices.showAllPublishedArticle();
                    break;
                case 4:
                    JPAUtill.closeEntityManager();
                    System.exit(0);
                    break;
            }
        }
    }
}
