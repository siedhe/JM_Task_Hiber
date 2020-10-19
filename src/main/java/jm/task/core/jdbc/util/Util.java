package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost/users?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234567890";

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Connection failed ...");
        }
        return connection;
    }

    public Util() {}

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                config.setProperty("hibernate.connection.url", URL);
                config.setProperty("hibernate.connection.username", USERNAME);
                config.setProperty("hibernate.connection.password", PASSWORD);
                config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                config.setProperty("current_session_context_class", "thread");
                config.setProperty("show_sql", "true");
                config.setProperty("hibernate.enable_lazy_load_no_trans", "true");
                config.setProperty("logging.level.org.hibernate.SQL", "debug");

                config.addAnnotatedClass(User.class);

                sessionFactory = config.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
