package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Statement statement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = Util.getConnection().createStatement();
            statement.execute("CREATE TABLE `coretaskdb`.`users` (" +
                    " `id` BIGINT(20) NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(45) NOT NULL," +
                    " `lastname` VARCHAR(45) NOT NULL," +
                    " `age` TINYINT(3) NOT NULL," +
                    " PRIMARY KEY (`id`))" +
                    " ENGINE = InnoDB" +
                    " DEFAULT CHARACTER SET = utf8;");
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = Util.getConnection().createStatement();
            statement.execute("DROP TABLE users");
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = Util.getConnection().createStatement();
            statement.execute("INSERT INTO users(name, lastname, age) VALUES ('" + name + "'," +
                    "'" + lastName + "'," +
                    "'" + age + "')");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = Util.getConnection().createStatement();
            statement.execute("DELETE FROM users WHERE id = " + id);
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        ResultSet res;
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = Util.getConnection().createStatement();
            res = statement.executeQuery("SELECT * FROM users");
            while (res.next()) {
                list.add(new User(res.getString(2), res.getString(3), res.getByte(4)));
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            connection.setAutoCommit(false);
            statement = Util.getConnection().createStatement();
            statement.execute("TRUNCATE TABLE users");
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
            }
        }
    }
}
