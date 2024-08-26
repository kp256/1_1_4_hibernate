package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();

//        Создание таблицы User(ов)
        usi.createUsersTable();
//        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль (User с именем — name добавлен в базу данных)
        usi.saveUser("Kolya", "K", (byte) 30);
        usi.saveUser("Sasha", "S", (byte) 35);
        usi.saveUser("Max", "M", (byte) 20);
        usi.saveUser("Pasha", "P", (byte) 40);
//        Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
        usi.getAllUsers();
//        Очистка таблицы User(ов)
        usi.cleanUsersTable();
//        Удаление таблицы
        usi.dropUsersTable();
    }
}

