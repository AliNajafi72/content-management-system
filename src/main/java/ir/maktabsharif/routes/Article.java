package ir.maktabsharif.routes;

import ir.maktabsharif.database_jdbc.ArticleDao;
import ir.maktabsharif.models_jdbc.ArticleModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Article {
    public void startUp() throws SQLException {
        List<ArticleModel> articlesList = new ArrayList<>();
        ArticleDao articleDao = new ArticleDao();
        articlesList = articleDao.getAll();
        int counter = 0;
        for (ArticleModel article:articlesList) {
            System.out.println((counter + 1) + ":");
            System.out.println(articlesList.get(counter).getTitle());
            System.out.println(articlesList.get(counter).getBrief());
            counter ++;
            System.out.println("===================");
        }
        Scanner intValueScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter article number to read and 0 to exit:");
            int userChoice = intValueScanner.nextInt();
            if (userChoice == 0) break;
            System.out.println("Title: " + articlesList.get(userChoice - 1).getTitle());
            System.out.println("Brief: " + articlesList.get(userChoice - 1).getBrief());
            System.out.println("Content: " + articlesList.get(userChoice - 1).getContent());
            System.out.println("Create Date: " + articlesList.get(userChoice - 1).getCreateDate());
            System.out.println("Username: " + articlesList.get(userChoice - 1).getUser().getUsername());
            System.out.println("Category: " + articlesList.get(userChoice - 1).getCategory().getTitle());
        }
    }
}
