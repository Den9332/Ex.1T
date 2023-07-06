package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("name1", "Joss", (byte) 45);
        userService.saveUser("name2", "Johnson", (byte) 24);
        userService.saveUser("name3", "Petrov", (byte) 34);
        userService.saveUser("name4", "Papaev", (byte) 63);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

