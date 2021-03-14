package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

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
        try {
            statement = Util.getConnection().createStatement();
            statement.execute("CREATE TABLE coretaskdb.users (" +
                    " id BIGINT(20) NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(45) NOT NULL," +
                    " lastname VARCHAR(45) NOT NULL," +
                    " age TINYINT(3) NOT NULL," +
                    " PRIMARY KEY (id))" +
                    " ENGINE = InnoDB" +
                    " DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException ex){
        }
    }

    public void dropUsersTable() {
        try {
            statement = Util.getConnection().createStatement();
            statement.execute("DROP TABLE users");
        } catch (SQLException ex){
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement = Util.getConnection().createStatement();
            statement.execute("INSERT INTO users(name, lastname, age) VALUES ('" + name + "'," +
                    "'" + lastName + "'," +
                    "'" + age + "')");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException ex){
        }
    }

    public void removeUserById(long id) {
        try {
            statement = Util.getConnection().createStatement();
            statement.execute("DELETE FROM users WHERE id = " + id);
        } catch (SQLException ex){
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        ResultSet res;
        try {
            statement = Util.getConnection().createStatement();
            res = statement.executeQuery("SELECT * FROM users");
            while (res.next()) {
                list.add(new User(res.getString(2), res.getString(3), res.getByte(4)));
            }
        } catch (SQLException ex){
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            statement = Util.getConnection().createStatement();
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException ex){
        }
    }
}
