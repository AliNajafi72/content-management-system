package ir.maktabsharif.routes;

import ir.maktabsharif.entities.cms.User;
import ir.maktabsharif.manager.UserManager;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Register {
    public void startUp() throws ParseException, SQLException {
        Scanner stringValueScanner = new Scanner(System.in);
        Scanner integerValueScanner = new Scanner(System.in);
        System.out.println("Please enter username:");
        String username = stringValueScanner.nextLine();
        System.out.println("Please enter national code:");
        String nationalCode = stringValueScanner.nextLine();
        System.out.println("Please enter birth year:");
        int birthYear = integerValueScanner.nextInt();
        System.out.println("Please enter birth month:");
        int birthMonth = integerValueScanner.nextInt();
        System.out.println("Please enter birth day:");
        int birthDay = integerValueScanner.nextInt();
        String birthDateString = birthYear + "/" + birthMonth + "/" + birthDay;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date birthDate = dateFormat.parse(birthDateString);
        System.out.println("Please enter password:");
        String password = stringValueScanner.nextLine();
        User user = new User();
        user.setUsername(username);
        user.setNationalCode(nationalCode);
        user.setBirthDate(birthDate);
        user.setPassword(password);
        // Using JDBC
//        UserDao userDao = new UserDao();
//        userDao.insert(user);
        // Using JPA
        UserManager userManager = new UserManager();
        userManager.insert(user);
    }
}
