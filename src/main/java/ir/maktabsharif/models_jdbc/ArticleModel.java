package ir.maktabsharif.models_jdbc;

import java.util.Date;

public class ArticleModel {
    private long id;
    private String title;
    private String brief;
    private String content;
    private Date createDate;
    private Boolean isPublished;
    private User user;
    private Category category;
    private long userId;
    private int categoryId;

    public long getId() {
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

    public Date getCreateDate() {
        return createDate;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    public long getUserId() {
        return userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(long id) {
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

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
