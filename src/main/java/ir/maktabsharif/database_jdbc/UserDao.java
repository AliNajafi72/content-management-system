package ir.maktabsharif.database_jdbc;

import ir.maktabsharif.models_jdbc.User;
import ir.maktabsharif.singletons.DatabaseConnection;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements DatabaseAccessObject<User>  {
    private User user;
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ? AND password=?";
    private static final String INSERT_USER = "INSERT INTO user (username, national_code, birth_date, password)" +
                                              " VALUES (?, ?, ?, ?)";

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    public Optional<User> get(String username, String password) throws SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        PreparedStatement userSelectStatement = databaseConnection.prepareStatement(SELECT_USER_BY_USERNAME);
        userSelectStatement.setString(1, username);
        userSelectStatement.setString(2, password);
        ResultSet userSelectionResult = userSelectStatement.executeQuery();
        if (userSelectionResult.next()) {
            user = new User();
            user.setId(userSelectionResult.getLong("id"));
            user.setUsername(userSelectionResult.getString("username"));
            user.setNational_code(userSelectionResult.getString("national_code"));
            user.setBirthDate(userSelectionResult.getDate("birth_date"));
            user.setPassword(userSelectionResult.getString("password"));
            databaseConnection.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void insert(User user) throws SQLException {
        Connection databaseConnection = DatabaseConnection.getInstance();
        try {
            PreparedStatement userSelectStatement = databaseConnection.prepareStatement(INSERT_USER);
            userSelectStatement.setString(1, user.getUsername());
            userSelectStatement.setString(2, user.getNational_code());
            userSelectStatement.setDate(3, new java.sql.Date(user.getBirthDate().getTime()));
            userSelectStatement.setString(4,user.getPassword());
            userSelectStatement.executeUpdate();
            databaseConnection.commit();
            System.out.println("Your registration is completed! Now you can login to your account.");
        } catch (SQLException e) {
            e.printStackTrace();
            databaseConnection.rollback();
        }
        finally {
            databaseConnection.close();
        }

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
