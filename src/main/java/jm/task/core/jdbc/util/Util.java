package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQLDialect;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/coretaskdb";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";

    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry standardServiceRegistry;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                Map<String, String> dbSettings = new HashMap<>();
                dbSettings.put(Environment.URL, URL);
                dbSettings.put(Environment.USER, USERNAME);
                dbSettings.put(Environment.PASS, PASSWORD);
                dbSettings.put(Environment.DRIVER, DRIVER);
                dbSettings.put(Environment.DIALECT, DIALECT);
                dbSettings.put(Environment.SHOW_SQL, "true");

                registryBuilder.applySettings(dbSettings);

                standardServiceRegistry = registryBuilder.build();

                MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
                Metadata metadata = metadataSources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                System.out.println("исключение!");
            }
        }
        return sessionFactory;
    }
}