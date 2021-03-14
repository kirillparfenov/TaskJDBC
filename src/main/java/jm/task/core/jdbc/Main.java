package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Андрей", "Петров", (byte)23);
        userService.saveUser("Мария", "Балашова", (byte)18);
        userService.saveUser("Михаил", "Топоров", (byte)41);
        userService.saveUser("Кирилл", "Парфенов", (byte)30);

        List<User> allUsers = userService.getAllUsers();
        for (User u : allUsers) {
            System.out.println(u);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
