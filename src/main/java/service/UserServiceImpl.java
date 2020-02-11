package service;

import dao.UserDao;
import dao.UserDaoFactory;
import model.User;
import util.PropertyReader;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = UserDaoFactory.getUserDao();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean delUser(long id) {
        return userDao.delUser(new User(id));
    }

    @Override
    public boolean addUser(String name, String pass, Long age, String role) {
        return name != null &&
                pass != null &&
                !name.isEmpty() &&
                !pass.isEmpty() &&
                age > 0 &&
                (role.equals("admin") || role.equals("user")) &&
                userDao.addUser(
                        new User(name, pass, age, role)
                );
    }

    @Override
    public boolean addUser(String name, String pass, Long age) {
        return addUser(name, pass, age, "user");
    }

    @Override
    public boolean updateUser(long id, String name, String pass, Long age, String role) {
        User user = new User(id, name, pass, age, role);
        return user.validate() && userDao.update(user);
    }

    @Override
    public User getUser(String name, String pass) {
        return name != null &&
                pass != null &&
                !name.isEmpty() &&
                !pass.isEmpty() ?
                userDao.getUser(name, pass) : new User("", "", 1L, "");
    }

    @Override
    public void createDefaultAdmin() {
        if (userDao.getUser(1L) == null) {
            addUser("Dima", "12345", 24L, "admin");
            addUser("Den", "1", 24L, "user");
        }
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }
}
