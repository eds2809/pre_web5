package service;

import dao.UserDao;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserService {

    public static UserService INSTANCE = new UserService();

    private UserDao userDao;

    private UserService() {
        userDao = UserDaoFactory.INSTANCE.getUserDao();
    }


    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public boolean delUser(long id) {
        return userDao.delUser(new User(id));
    }

    public boolean addUser(String name, String pass, Long age, String role) {
        return name != null &&
                pass != null &&
                !name.isEmpty() &&
                !pass.isEmpty() &&
                age > 0 &&
                (role.equals("admin") || role.equals("user")) &&
                userDao.addUser(
                        new User(name, pass, age,role)
                );
    }

    public boolean addUser(String name, String pass, Long age){
        return addUser(name,pass,age,"user");
    }

    public boolean updateUser(long id, String name, String pass, Long age, String role) {
        User user = new User(id, name, pass, age,role);
        return user.validate() && userDao.update(user);
    }

    public User getUser(String name, String pass){
        return name != null &&
                pass != null &&
                !name.isEmpty() &&
                !pass.isEmpty() ?
                userDao.getUser(name, pass) : new User("","",1L,"");
    }

    public void createDefaultAdmin(){
        if (userDao.getUser(1L) == null) {
            UserService.INSTANCE.addUser("Dima", "12345", 24L, "admin");
        }
    }
}
