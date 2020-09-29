package ir.maktabsharif.services;

import ir.maktabsharif.entities.Tag;
import ir.maktabsharif.entities.Tag;
import ir.maktabsharif.repository.TagRepository;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.Scanner;

public class TagServices {
    private TagRepository tagRepository;

    public TagServices() {
        tagRepository = TagRepository.getTagRepository();
    }

    public void createNewTag() {
        Tag tag = new Tag();
        System.out.print("Enter title: ");
        tag.setTitle(new Scanner(System.in).nextLine());
        tagRepository.insert(tag);
        System.out.println("### category added ###");
    }

    public void showAllTag() {
        List<Tag> tagList = tagRepository.getAllEntity();
        if (tagList.size() == 0) System.out.println("There is no Tag.");
        else {
            for (Tag t : tagList) {
                System.out.println("**********");
                System.out.println("\t"+t.getId());
                System.out.println("Title: " + t.getTitle());
                System.out.println("**********");
            }
        }
    }
}
