package ir.maktabsharif.models_jdbc;

import java.util.Date;

public class User {
    private long id;
    private String username;
    private String national_code;
    private Date birthDate;
    private String password;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNational_code() {
        return national_code;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNational_code(String national_code) {
        this.national_code = national_code;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
