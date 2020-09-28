package ir.maktabsharif.routes;

import ir.maktabsharif.entities.cms.User;
import ir.maktabsharif.manager.UserManager;

import java.util.Optional;
import java.util.Scanner;

public class Login {
    public void startUp() {
        Scanner stringValueScanner = new Scanner(System.in);
        System.out.println("Please enter username:");
        String username = stringValueScanner.nextLine();
        System.out.println("Please enter password:");
        String password = stringValueScanner.nextLine();
        try {
            // JDBC
//            UserDao userDao = new UserDao();
//            Optional<User> userOptional = userDao.get(username,password);
            // JPA
            UserManager userManager = new UserManager();
            Optional<User> userOptional = userManager.getUserByUsername(username);
            User user;
            if (userOptional.isEmpty()){
                // User not found
                System.out.println("User not found!");
            } else {
                user = userOptional.get();
                if (user.getPassword().equals(password)) {
                    // Route to panel
                    Panel panel = new Panel();
                    panel.startUp(userOptional.get().getId());
                } else {
                    // Username or password is incorrect
                    System.out.println("Username or password is incorrect!");
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
