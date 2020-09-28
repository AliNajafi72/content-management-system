package ir.maktabsharif.routes;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) throws ParseException,SQLException {
        startUp();
    }

    private static void startUp() throws ParseException, SQLException {
        System.out.println("Welcome to content management system.");
        System.out.println("Please select one below to proceed:");
        System.out.println("1:Log in\n2:Sign up\n3:Articles\n0:Exit");
        Scanner integerValueScanner = new Scanner(System.in);
        int userChoice = integerValueScanner.nextInt();
        while(userChoice != 0) {
            switch(userChoice) {
                case 1:
                    // Log in route
                    Login login = new Login();
                    login.startUp();
                    break;
                case 2:
                    // Sign up route
                    Register register = new Register();
                    register.startUp();
                    break;
                case 3:
                    // ArticleModel route
                    Article article = new Article();
                    article.startUp();
                    break;
            }
            System.out.println("Please select one below to proceed:");
            System.out.println("1:Log in\n2:Sign up\n3:Articles\n0:Exit");
            userChoice = integerValueScanner.nextInt();
        }
    }
}
