//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private static final String CREATE_USERS_TABLE = "CREATE TABLE users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) , last_name VARCHAR(45) , age INT)";
//    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
//    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
//    private static final String SAVE_USER = "INSERT INTO users(name, last_name, age) VALUES (?,?,?)";
//    private static final String GET_ALL_USERS = "SELECT * FROM users";
//    private static final String CLEAN_USER_TABLE = "DELETE FROM users";
//
//    private final Connection connection = Util.getConnection();
//
//    public UserDaoJDBCImpl() {
//    }
//
//    @Override
//    public void createUsersTable() {
//        rollbackWrapper(CREATE_USERS_TABLE);
//    }
//
//    @Override
//    public void dropUsersTable() {
//        rollbackWrapper(DROP_USERS_TABLE);
//    }
//
//    private void rollbackWrapper(String sql) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            connection.setAutoCommit(false);
//            preparedStatement.executeUpdate();
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER)) {
//            connection.setAutoCommit(false);
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();
//            connection.commit();
//            System.out.println("User с именем — " + name + " добавлен в базу данных");
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//    }
//
//    @Override
//    public void removeUserById(long id) {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_BY_ID)) {
//            connection.setAutoCommit(false);
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//            } catch (SQLException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastName"));
//                user.setAge(resultSet.getByte("age"));
//                users.add(user);
//            }
//            return users;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void cleanUsersTable() {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(CLEAN_USER_TABLE)) {
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
