package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        //mysql
        // 1.driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=Asia/Seoul"
                , "jinsu", "1234");
        // 3.query 생성
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        // 4.query 실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        // 5.결과 매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        // 6.자원 해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
        // 7.결과 리턴
        return user;
    }
}
