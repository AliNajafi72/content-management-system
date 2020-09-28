package ir.maktabsharif.database_jdbc;

import ir.maktabsharif.models_jdbc.Category;
import ir.maktabsharif.singletons.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao implements DatabaseAccessObject<Category> {

    public static final String SELECT_ALL_ARTICLE =
            "SELECT * FROM category";

    public static final String CREATE_CATEGORY =
            "INSERT INTO category (title, description) VALUES (?, ?)";

    @Override
    public Optional<Category> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        Connection databaseConnection = DatabaseConnection.getInstance();
        try {
            Statement statement = databaseConnection.createStatement();
            ResultSet selectCategoryResult = statement.executeQuery(SELECT_ALL_ARTICLE);
            while (selectCategoryResult.next()) {
                Category category = new Category();
                category.setId(selectCategoryResult.getLong("id"));
                category.setTitle(selectCategoryResult.getString("title"));
                category.setDescription("description");
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnection.rollback();
        }
        finally {
            databaseConnection.close();
        }
        return categories;
    }

    @Override
    public void insert(Category category) throws SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        try {
            PreparedStatement addCategoryStatement = databaseConnection.prepareStatement(CREATE_CATEGORY);
            addCategoryStatement.setString(1,category.getTitle());
            addCategoryStatement.setString(2,category.getDescription());
            addCategoryStatement.executeUpdate();
            databaseConnection.commit();
        } catch(SQLException e) {
            e.printStackTrace();
            databaseConnection.rollback();
        } finally {
            databaseConnection.close();
        }
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }
}
