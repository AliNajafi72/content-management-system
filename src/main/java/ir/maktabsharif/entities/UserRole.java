package ir.maktabsharif.entities;

import javax.persistence.*;

@Entity
@Table (name = "user_role", schema = "cms")
public class UserRole {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;
    @Column (name = "role")
    private String Role;
    @OneToOne (mappedBy = "userRole")
    private User user;

    public long getId() {
        return id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", Role='" + Role + '\'' +
                ", user=" + user +
                '}';
    }
}
