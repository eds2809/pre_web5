package dao;

import util.PropertyReader;

public class UserDaoFactory {

    public static UserDao getUserDao() {
        String jpaType = PropertyReader.getProperties("config.properties").getProperty("jpaType");

        if (jpaType.equalsIgnoreCase("UserJDBCDao")) {
            return new UserJDBCDao();
        } else if (jpaType.equalsIgnoreCase("UserHibernateDAO")) {
            return new UserHibernateDAO();
        }
        return null;
    }
}
