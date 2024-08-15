//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private static final String CREATE_USERS_TABLE = "CREATE TABLE users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) , lastName VARCHAR(45) , age INT)";
//    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
//    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
//    private static final String SAVE_USER = "INSERT INTO users(name, lastName, age) VALUES (?,?,?)";
//    private static final String GET_ALL_USERS = "SELECT * FROM users";
//    private static final String CLEAN_USER_TABLE = "DELETE FROM users";
//
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() {
//        try (Connection connection = Util.getConnection()) {
//            try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USERS_TABLE)) {
//
//                preparedStatement.executeUpdate();
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void dropUsersTable() {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DROP_USERS_TABLE)) {
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        createUsersTable();
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {
//
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//
//            preparedStatement.executeUpdate();
//
//            System.out.println("User с именем — "+name+" добавлен в базу данных");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void removeUserById(long id) {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {
//
//            preparedStatement.setLong(1, id);
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastName"));
//                user.setAge(resultSet.getByte("age"));
//                users.add(user);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return users;
//    }
//
//    public void cleanUsersTable() {
//        try (Connection connection = Util.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_USER_TABLE)) {
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
