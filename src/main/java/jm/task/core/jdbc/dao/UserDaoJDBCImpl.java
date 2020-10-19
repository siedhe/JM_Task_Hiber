package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Statement statement;
    Util util = new Util();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = util.getConnection()) {

            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                                        "Name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age SMALLINT NOT NULL )");
            System.out.println("Table has been created!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Table is not created.");
        }
    }

    public void dropUsersTable() {

        try (Connection connection = util.getConnection()) {

            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE user");
            System.out.println("Table has been deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Data base has not been deleted!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT user(name, lastName, age) " +
                    "VALUE ('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM user WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM user");

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));

                    users.add(user);
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM user");
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
