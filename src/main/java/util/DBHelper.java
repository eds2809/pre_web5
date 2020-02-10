package util;


import com.mysql.jdbc.Driver;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static SessionFactory sessionFactory;

    public synchronized static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        Properties prop = PropertyReader.getProperties("config.properties");

        configuration.setProperty("hibernate.dialect", prop.getProperty("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", prop.getProperty("driver_class"));
        configuration.setProperty("hibernate.connection.username", prop.getProperty("DB.USERNAME"));
        configuration.setProperty("hibernate.connection.password", prop.getProperty("DB.PASS"));
        configuration.setProperty("hibernate.show_sql", prop.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", prop.getProperty("hibernate.hbm2ddl.auto"));

        //То что добавлял я
        configuration.setProperty("hibernate.connection.useUnicode", prop.getProperty("useUnicode"));

        configuration.setProperty("hibernate.connection.url", prop.getProperty("DB.URL"));


        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            return DriverManager.getConnection(
                    PropertyReader.getProperties("config.properties").getProperty("DB.URL")
            );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
