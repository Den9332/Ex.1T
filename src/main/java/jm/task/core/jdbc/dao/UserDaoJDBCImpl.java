package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
            "name VARCHAR(30) NOT NULL, " +
            "lastName VARCHAR(30) NOT NULL, " +
            "age TINYINT NOT NULL)";
    private final static String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private final static String SAVE_USER = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
    private final static String REMOVE_USER = "DELETE FROM users where id = ?";
    private final static String GET_USERS = "SELECT * FROM users";
    private final static String CLEAN_USERS = "TRUNCATE TABLE users";
    private List<User> allUsers = new ArrayList<>();
    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement(CREATE_TABLE)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement(DROP_TABLE)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement(SAVE_USER)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement(REMOVE_USER)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        try (PreparedStatement ps = connection.prepareStatement(GET_USERS)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                User user = new User(result.getString("name"), result.getString("lastName"), result.getByte("age"));
                user.setId(result.getLong("id"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement(CLEAN_USERS)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
