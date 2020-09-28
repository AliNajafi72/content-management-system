package ir.maktabsharif.database_jdbc;

import ir.maktabsharif.models_jdbc.ArticleModel;
import ir.maktabsharif.models_jdbc.Category;
import ir.maktabsharif.models_jdbc.User;
import ir.maktabsharif.singletons.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleDao implements DatabaseAccessObject<ArticleModel> {
    private static final String SELECT_ALL_ARTICLES =
            "select article.*, user.username, category.title from article" +
                    " join user on article.user_id = user.id" +
                    " join category on article.category_id = category.id where article.isPublished = true;";
    private static final String SELECT_ALL_ARTICLES_BY_USER_ID =
            "select * from article where user_id = ?;";

    private static final String UPDATE_ARTICLE =
            "update article set title = ?, brief = ?, content = ?, isPublished = ?" +
                    " where user_id = ? and id = ?";

    private static final String INSERT_ARTICLE =
            "INSERT INTO article (title, brief, content, create_date, isPublished, user_id, category_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public Optional<ArticleModel> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ArticleModel> getAll() throws SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        List<ArticleModel> articlesList = new ArrayList<>();
        ArticleModel articleModel ;
        User userModel;
        Category categoryModel;
        PreparedStatement articleSelectStatement = databaseConnection.prepareStatement(SELECT_ALL_ARTICLES);
        ResultSet articlesSelectResult = articleSelectStatement.executeQuery();
        while (articlesSelectResult.next()) {
            articleModel = new ArticleModel();
            userModel = new User();
            categoryModel = new Category();
            articleModel.setId(articlesSelectResult.getLong(1));
            articleModel.setTitle(articlesSelectResult.getString(2));
            articleModel.setBrief(articlesSelectResult.getString(3));
            articleModel.setContent(articlesSelectResult.getString(4));
            articleModel.setCreateDate(articlesSelectResult.getDate(5));
            userModel.setUsername(articlesSelectResult.getString(9));
            articleModel.setUser(userModel);
            categoryModel.setTitle(articlesSelectResult.getString(10));
            articleModel.setCategory(categoryModel);
            articlesList.add(articleModel);
        }
        databaseConnection.close();
        return articlesList;
    }

    public List<ArticleModel> getAll(long userId) throws SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        List<ArticleModel> articlesList = new ArrayList<>();
        ArticleModel articleModel ;
        PreparedStatement articleSelectStatement = databaseConnection.prepareStatement(SELECT_ALL_ARTICLES_BY_USER_ID);
        articleSelectStatement.setLong(1,userId);
        ResultSet articlesSelectResult = articleSelectStatement.executeQuery();
        while (articlesSelectResult.next()) {
            articleModel = new ArticleModel();
            articleModel.setId(articlesSelectResult.getLong("id"));
            articleModel.setTitle(articlesSelectResult.getString("title"));
            articleModel.setBrief(articlesSelectResult.getString("brief"));
            articleModel.setContent(articlesSelectResult.getString("content"));
            articleModel.setCreateDate(articlesSelectResult.getDate("create_date"));
            articlesList.add(articleModel);
        }
        databaseConnection.close();
        return articlesList;
    }

    @Override
    public void insert(ArticleModel articleModel) throws SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        try {
            PreparedStatement insertStatement = databaseConnection.prepareStatement(INSERT_ARTICLE);
            insertStatement.setString(1, articleModel.getTitle());
            insertStatement.setString(2, articleModel.getBrief());
            insertStatement.setString(3, articleModel.getContent());
            java.sql.Date createDate = new java.sql.Date(articleModel.getCreateDate().getTime());
            insertStatement.setDate(4, createDate);
            insertStatement.setBoolean(5, articleModel.getPublished());
            insertStatement.setLong(6, articleModel.getUserId());
            insertStatement.setLong(7, articleModel.getCategoryId());
            insertStatement.executeUpdate();
            databaseConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnection.rollback();
        }
        finally {
            databaseConnection.close();
        }

    }

    public void update(ArticleModel articleModel, long userId, long articleId) throws SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        try {
            PreparedStatement articleUpdateStatement = databaseConnection.prepareStatement(UPDATE_ARTICLE);
            articleUpdateStatement.setString(1,articleModel.getTitle());
            articleUpdateStatement.setString(2,articleModel.getBrief());
            articleUpdateStatement.setString(3,articleModel.getContent());
            articleUpdateStatement.setBoolean(4,articleModel.getPublished());
            articleUpdateStatement.setLong(5,userId);
            articleUpdateStatement.setLong(6,articleId);
            articleUpdateStatement.executeUpdate();
            databaseConnection.commit();
        } catch(SQLException e) {
            e.printStackTrace();
            databaseConnection.rollback();
        } finally {
            databaseConnection.close();
        }
    }

    @Override
    public void update(ArticleModel articleModel) {

    }

    @Override
    public void delete(ArticleModel articleModel) {

    }
}
