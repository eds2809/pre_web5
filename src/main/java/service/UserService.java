package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    boolean delUser(long id);

    boolean addUser(String name, String pass, Long age, String role);

    boolean addUser(String name, String pass, Long age);

    boolean updateUser(long id, String name, String pass, Long age, String role);

    User getUser(String name, String pass);

    void createDefaultAdmin();

    User getUser(long id);
}
