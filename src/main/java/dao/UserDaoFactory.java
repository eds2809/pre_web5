package dao;

public class UserDaoFactory {

    public static UserDao getUserDao(String jpaType) {
        if (jpaType.equalsIgnoreCase("UserJDBCDao")) {
            return new UserJDBCDao();
        } else if (jpaType.equalsIgnoreCase("UserHibernateDAO")) {
            return new UserHibernateDAO();
        }
        return null;
    }
}
