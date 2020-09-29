package ir.maktabsharif.services;

import ir.maktabsharif.entities.Article;
import ir.maktabsharif.entities.Category;
import ir.maktabsharif.entities.Tag;
import ir.maktabsharif.entities.User;
import ir.maktabsharif.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ArticleServices {
    private Scanner scanner;
    private ArticleRepository articleRepository;
    private CategoryServices categoryServices;

    public ArticleServices() {
        scanner = new Scanner(System.in);
        articleRepository = ArticleRepository.getArticleRepository();
        categoryServices = new CategoryServices();
    }

    public void changePublishStatus() {
        LocalDate date = LocalDate.now();
        List<Article> articleList = articleRepository.getAllEntity();
        for (Article a : articleList) {
            System.out.println(a.getId() + " -> " + "Title: " + a.getTitle() + "  ->  " + "Publish: " + a.isPublished());
        }
        System.out.print("which article do you like to change publish: ");
        int id = scanner.nextInt();
        Article article = articleRepository.getEntityById(id);
        System.out.println("1 -> To Publish" + "\n" +
                "2 -> Cancel Publish");
        System.out.print("choice: ");
        boolean statusPublish = (scanner.nextInt() == 1) ? true : false;
        article.setPublished(statusPublish);
        article.setPublishDate(date);
        articleRepository.update(article);
        System.out.println("### DONE ###");
    }

    public void removeArticle() {
        try {
            List<Article> articleList = articleRepository.getAllEntity();
            for (Article a : articleList) {
                System.out.println(a.getId() + " -> " + "Title: " + a.getTitle());
            }
            System.out.print("which article do you like to delete: ");
            int id = scanner.nextInt();
            articleRepository.deleteById(id);

            System.out.println("### DONE ###");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showAllArticle() {
        List<Article> articleList = articleRepository.getAllPublishedArticle();
        if (articleList.size() == 0) System.out.println("There is no Atricle");
        else {
            for (Article a : articleList) {
                System.out.println("\t**********");
                System.out.println("Category: " + a.getCategory().getTitle() + "\tDescription: " + a.getCategory().getDescription());
                System.out.println("Title: " + a.getTitle());
                System.out.println("Brief: " + a.getBrief());
                System.out.println("Content: " + a.getContent());
                System.out.println("Writer: " + a.getWriter());
                String temp = a.isPublished() == true ? "YES" : "NO";
                System.out.println("Is Published? " + temp);
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

    public void showAllArticleByUser(User user) {
        List<Article> articleList = articleRepository.getAllArticleByUser(user);
        if (articleList.size() == 0) System.out.println("There is no Atricle");
        else {
            for (Article a : articleList) {
                System.out.println("\t********");
                System.out.println("Category: " + a.getCategory().getTitle() + "\tDescription: " + a.getCategory().getDescription());
                System.out.println("Title: " + a.getTitle());
                System.out.println("Brief: " + a.getBrief());
                System.out.println("Content: " + a.getContent());
                System.out.println("Writer: " + a.getWriter());
                String temp = a.isPublished() == true ? "YES" : "NO";
                System.out.println("Is Published? " + temp);
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

    public void createArticleByUser(User user) throws ParseException {

        categoryServices.showAllCategory();
        Category category = null;
        Article article = new Article();
        System.out.print("Enter Category number: ");
        int categoryID = scanner.nextInt();
        category = CategoryRepository.getCategoryRepository().getEntityById(categoryID);
        article.setCategory(category);
        System.out.print("Enter writer:");
        article.setWriter(new Scanner(System.in).nextLine());
        System.out.print("Enter title:");
        article.setTitle(new Scanner(System.in).nextLine());
        article.setPublished(false);
        System.out.print("Enter brief:");
        article.setBrief(new Scanner(System.in).nextLine());
        System.out.print("Enter content:");
        article.setContent(new Scanner(System.in).nextLine());
        System.out.println("Enter create Date (year/month/day):");
        String inputDate = scanner.next();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        date = simpleDateFormat.parse(inputDate);
        article.setCreateDate(simpleDateFormat.format(date));

        article.setUser(user);
        List<Tag> tagList = TagRepository.getTagRepository().getAllEntity();
        List<Tag> chosenTags = new ArrayList<>();
        for (Tag t : tagList) {
            System.out.println("Title: " + t.getTitle());
            System.out.println("You want this tag?(y/n)");
            char temp = scanner.next().charAt(0);
            if (temp == 'y' || temp == 'Y') {
                chosenTags.add(t);
            } else continue;
        }
        article.setArticleTags(chosenTags);


        articleRepository.insert(article);
    }

    public void editArticleByUser(User user) {
        LocalDate date = LocalDate.now();
        List<Article> articleList = articleRepository.getAllArticleByUser(user);
        if (articleList.size() == 0) System.out.println("there is no submitted article.");
        else {
            for (Article a : articleList) {
                System.out.println("Article number: " + a.getId());
                System.out.println("Title: " + a.getTitle());
                System.out.println("Brief: " + a.getBrief());
                System.out.println("Content: " + a.getContent());
                System.out.println("Writer: " + a.getWriter());
            }
            boolean outerFlag = true;
            while (outerFlag) {
                System.out.print("Enter the article number: ");
                int articleID = scanner.nextInt();
                Article article = articleRepository.getEntityById(articleID);
                boolean innerFlag = true;
                while (innerFlag) {
                    System.out.println("1 ->edit title" + "\n" +
                            "2 ->edit brief" + "\n" +
                            "3 ->edit content" + "\n" +
                            "4 ->Writer" + "\n" +
                            "5 ->exit");
                    int temp = scanner.nextInt();
                    switch (temp) {
                        case 1:
                            System.out.println("Enter new title:");
                            String newTitle = new Scanner(System.in).nextLine();
                            article.setTitle(newTitle);
                            article.setLastUpdateDate(date);
                            System.out.println("Done");
                            break;
                        case 2:
                            System.out.println("Enter new brief:");
                            String newBrief = new Scanner(System.in).nextLine();
                            article.setBrief(newBrief);
                            article.setLastUpdateDate(date);
                            System.out.println("Done");
                            break;
                        case 3:
                            System.out.println("Enter new content:");
                            String newContent = new Scanner(System.in).nextLine();
                            article.setContent(newContent);
                            article.setLastUpdateDate(date);
                            System.out.println("Done");
                            break;
                        case 4:
                            System.out.println("Enter new writer:");
                            String newWriter = new Scanner(System.in).nextLine();
                            article.setWriter(newWriter);
                            article.setLastUpdateDate(date);
                            System.out.println("Done");
                            break;
                        case 5:
                            articleRepository.update(article);
                            innerFlag = false;
                            break;
                    }
                }
                System.out.println("Do you want to continue edit articles? (1 -> yes / 2 -> no )");
                int temp = scanner.nextInt();
                if (temp == 2) outerFlag = false;
            }

        }
    }
}
