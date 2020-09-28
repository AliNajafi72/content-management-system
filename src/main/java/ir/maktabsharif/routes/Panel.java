package ir.maktabsharif.routes;

import ir.maktabsharif.database_jdbc.ArticleDao;
import ir.maktabsharif.database_jdbc.CategoryDao;
import ir.maktabsharif.entities.Article;
import ir.maktabsharif.entities.Category;
import ir.maktabsharif.manager.ArticleManager;
import ir.maktabsharif.manager.CategoryManager;
import ir.maktabsharif.models_jdbc.ArticleModel;
import ir.maktabsharif.singletons.ScannerSingleton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Panel {
    public void startUp(long userId) throws SQLException {
        System.out.println("Please select one below to proceed:");
        System.out.println("1:Articles\n2:Edit article\n3:Add article\n0:Sign out");
        Scanner integerValueScanner = new Scanner(System.in);
        int userChoice = integerValueScanner.nextInt();
        while(userChoice != 0) {
            switch(userChoice) {
                case 1:
                    showAllArticles(userId);
                    break;
                case 2:
                    editArticle(userId);
                    break;
                case 3:
                    createArticle(userId);
                    break;

            }
            System.out.println("Please select one below to proceed:");
            System.out.println("1:Articles\n2:Edit articles\n3:Add article\n0:Sign out");
            userChoice = integerValueScanner.nextInt();
        }
    }

    private void createArticle(long userId) throws SQLException {
        System.out.println("Please enter id of desired category or enter 0 to create new one:");
        // JDBC
//        CategoryDao categoryDao = new CategoryDao();
//        List<Category> categories = categoryDao.getAll();
//        printAllCategories(categories);
        // JPA
        CategoryManager categoryManager = new CategoryManager();
        List<Category> categories = categoryManager.getAll();
        printAllCategories(categories);
        int selectedCategoryId = Integer.parseInt(ScannerSingleton.getInstance().nextLine());
        while (selectedCategoryId == 0) {
            // Get inputs to create new category
            System.out.println("Please enter title:");
            String categoryTitle = ScannerSingleton.getInstance().nextLine();
            System.out.println("Please enter description:");
            String categoryDescription = ScannerSingleton.getInstance().nextLine();
            // JDBC
//            Category categoryModel = new Category();
//            categoryModel.setTitle(categoryTitle);
//            categoryModel.setDescription(categoryDescription);
//            categoryDao.insert(categoryModel);
//            System.out.println("Please enter id of desired category or enter 0 to create new one:");
//            categories = categoryDao.getAll();
//            printAllCategories(categories);
            // JPA
            Category category = new Category();
            category.setTitle(categoryTitle);
            category.setDescription(categoryDescription);
            categoryManager.insert(category);
            System.out.println("Please enter id of desired category or enter 0 to create new one:");
            categories = categoryManager.getAll();
            printAllCategories(categories);
            selectedCategoryId = Integer.parseInt(ScannerSingleton.getInstance().nextLine());
        }
        // Get inputs to create new article
        System.out.println("Please enter article title:");
        String articleTitle = ScannerSingleton.getInstance().nextLine();
        System.out.println("Please enter article brief:");
        String articleBrief = ScannerSingleton.getInstance().nextLine();
        System.out.println("Please enter article content:");
        String articleContent = ScannerSingleton.getInstance().nextLine();
        Date articleCreateDate = new Date();
        System.out.println("Please select publish status:");
        boolean articlePublishStatus = Boolean.parseBoolean(ScannerSingleton.getInstance().nextLine());
        // JDBC
//        ArticleModel articleModel = new ArticleModel();
//        articleModel.setTitle(articleTitle);
//        articleModel.setBrief(articleBrief);
//        articleModel.setContent(articleContent);
//        articleModel.setCreateDate(articleCreateDate);
//        articleModel.setPublished(articlePublishStatus);
//        articleModel.setUserId(userId);
//        articleModel.setCategoryId(selectedCategoryId);
//        ArticleDao articleDao = new ArticleDao();
//        articleDao.insert(articleModel);
        // JPA
        Article article = new Article();
        article.setTitle(articleTitle);
        article.setBrief(articleBrief);
        article.setContent(articleContent);
        article.setCreateDate(articleCreateDate);
        article.setPublished(articlePublishStatus);
        ArticleManager articleManager = new ArticleManager();
        articleManager.insert(article, selectedCategoryId, userId);
    }

    private void printAllCategories(List<Category> categories) {
        for (Category category:categories) {
            System.out.println("Id: " + category.getId());
            System.out.println("Title: " + category.getTitle());
            System.out.println("====================");
        }
    }

    private void editArticle(long userId) throws SQLException {
        ArticleModel articleModel = new ArticleModel();
        System.out.println("Please enter article id:");
        Scanner integerValueScanner = new Scanner(System.in);
        int articleId = integerValueScanner.nextInt();
        Scanner stringValueScanner = new Scanner(System.in);
        System.out.println("Please enter new title:");
        String title = stringValueScanner.nextLine();
        System.out.println("Please enter new brief:");
        String brief = stringValueScanner.nextLine();
        System.out.println("Please enter new content:");
        String content = stringValueScanner.nextLine();
        Scanner booleanValueScanner = new Scanner(System.in);
        System.out.println("Please choose publishing status:");
        boolean isPublished = booleanValueScanner.nextBoolean();
        articleModel.setTitle(title);
        articleModel.setBrief(brief);
        articleModel.setContent(content);
        articleModel.setPublished(isPublished);
        ArticleDao articleDao = new ArticleDao();
        articleDao.update(articleModel,userId,articleId);
    }

    private void showAllArticles(long userId) throws SQLException {
        List<ArticleModel> articlesList = new ArrayList<>();
        ArticleDao articleDao = new ArticleDao();
        articlesList = articleDao.getAll(userId);
        for (int i=0; i<articlesList.size(); i++) {
            System.out.println("Id: " + articlesList.get(i).getId());
            System.out.println("Title: " + articlesList.get(i).getTitle());
            System.out.println("Brief: " + articlesList.get(i).getBrief());
            System.out.println("Content: " + articlesList.get(i).getContent());
            System.out.println("Create date: " + articlesList.get(i).getCreateDate());
            System.out.println("====================");
        }
    }


}
