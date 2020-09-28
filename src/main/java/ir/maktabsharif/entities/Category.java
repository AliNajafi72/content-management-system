package ir.maktabsharif.entities;

import javax.persistence.*;

@Entity
@Table (name = "category", schema = "cms")
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column (name = "title")
    private String title;
    @Column (name = "description")
    private String description;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
