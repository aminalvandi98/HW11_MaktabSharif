package ir.maktabsharif.services;

import ir.maktabsharif.entities.Category;
import ir.maktabsharif.repository.CategoryRepository;
import ir.maktabsharif.repository.Repository;

import java.util.List;
import java.util.Scanner;

public class CategoryServices {
    private CategoryRepository categoryRepository;

    public CategoryServices() {
        categoryRepository = CategoryRepository.getCategoryRepository();
    }

    public void createNewCategory() {
        Category category = new Category();
        System.out.print("Enter title: ");
        category.setTitle(new Scanner(System.in).nextLine());
        System.out.print("Enter description: ");
        category.setDescription(new Scanner(System.in).nextLine());
        categoryRepository.insert(category);
        System.out.println("### category added ###");
    }

    public void showAllCategory() {
        List<Category> categoryList = categoryRepository.getAllEntity();
        if (categoryList.size() == 0) System.out.println("There is no Category.");
        else {
            for (Category c:categoryList){
                System.out.println("**********");
                System.out.println("\t"+c.getId());
                System.out.println("Title: " + c.getTitle());
                System.out.println("Description: " + c.getDescription());
                System.out.println("**********");
            }
        }
    }
}
