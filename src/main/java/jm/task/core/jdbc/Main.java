package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Diana", "Ivanova", (byte) 35);
        userService.saveUser("Igor", "Orehov", (byte) 36);
        userService.saveUser("Swetlana", "Kireeva", (byte) 30);
        userService.saveUser("Anton", "Petrov", (byte) 39);
        userService.getAllUsers();
        System.out.println(userService.getAllUsers());
        userService.removeUserById(3);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        UserDaoHibernateImpl.getSessionFactory().close();
    }
}