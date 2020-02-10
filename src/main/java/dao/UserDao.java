package dao;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    boolean addUser(User user);

    boolean delUser(User user);

    boolean update(User user);

    User getUser(String name, String pass);
}
