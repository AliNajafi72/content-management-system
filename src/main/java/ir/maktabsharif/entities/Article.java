package ir.maktabsharif.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "article", schema = "cms")
public class Article {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column (name = "title")
    private String title;
    @Column (name = "brief")
    private String brief;
    @Column (name = "content")
    private String content;
    @Column (name = "create_date")
    private Date createDate;
    @Column (name = "publish_status")
    private Boolean isPublished;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn (name = "category_id")
    private Category category;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
