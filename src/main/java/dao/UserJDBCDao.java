package dao;

import executor.Executor;
import model.User;
import util.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDao implements UserDao {
    private Executor executor;

    public UserJDBCDao() {
        executor = new Executor(DBHelper.getMysqlConnection());
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return executor.execQuery("SELECT * FROM users", result -> {
                List<User> users = new ArrayList<>();
                while (result.next()) {
                    users.add(
                            new User(
                                    result.getLong("id"),
                                    result.getString("name"),
                                    result.getString("pass"),
                                    result.getLong("age")
                            )
                    );
                }
                return users;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean addUser(User user) {
        try {
            return executor.execUpdate(
                    String.format("INSERT INTO users (name,pass,age) VALUES (\"%s\",\"%s\",%d)",
                            user.getName(), user.getPass(), user.getAge()
                    )
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delUser(User user) {
        try {
            return executor.execUpdate(String.format("DELETE FROM users WHERE id = %d ", user.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        try {
            return executor.execUpdate(
                    String.format("UPDATE users SET name = \"%s\", pass = \"%s\", age = %d WHERE id = %d",
                            user.getName(),
                            user.getPass(),
                            user.getAge(),
                            user.getId()
                    )
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User getUser(String name, String pass) {
        return null;
    }
}
