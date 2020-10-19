package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Evgeniy", "Morozov", (byte) 27);
        userService.saveUser("Maxim", "Golenko", (byte) 54);
        userService.saveUser("Andrey", "Ustinov", (byte) 31);
        userService.saveUser("Roman", "Zuev", (byte) 16);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
