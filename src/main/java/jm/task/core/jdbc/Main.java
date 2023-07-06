package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("name1", "nam1", (byte) 10);
        userService.saveUser("name2", "nam2", (byte) 11);
        userService.saveUser("name3", "nam3", (byte) 12);
        userService.saveUser("name4", "nam4", (byte) 13);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

