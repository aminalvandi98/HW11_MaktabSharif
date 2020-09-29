package ir.maktabsharif.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @PrimaryKeyJoinColumn
    private int id;
   @Column(columnDefinition = "LONGTEXT")
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    private String brief;
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    private String createDate;
    private boolean isPublished;
    private LocalDate lastUpdateDate;
    private LocalDate publishDate;
    private String writer;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.PERSIST)
    private Category category;

    @ManyToMany(targetEntity = Tag.class)

    private List<Tag> articleTags;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return brief;
    }

    public String getContent() {
        return content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public List<Tag> getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(List<Tag> articleTags) {
        this.articleTags = articleTags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
