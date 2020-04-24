package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User get(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        // 3.create query
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        // 4.query implement
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        // 5.result mapping
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        // 6.source close
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 7. result return
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        //mysql
        // 1.driver loading
        Connection connection = dataSource.getConnection();
        // 3.create query
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) value (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 7. result return
    }
}
