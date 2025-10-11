package crm_app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.MySQLConfig;
import entity.User;

public class LoginRepository {

    public User findUser(String email, String password) {
        String query = "SELECT u.id, u.fullname, u.email, u.role_id, r.description AS role "
                     + "FROM users u "
                     + "JOIN roles r ON u.role_id = r.id "
                     + "WHERE u.email = ? AND u.password = ?";

        try (
            Connection connection = MySQLConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFullname(resultSet.getString("fullname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRoleID(resultSet.getInt("role_id"));
                    user.setRoleDescription(resultSet.getString("role"));
                    return user;
                }
            }

        } catch (Exception e) {
            System.out.println("Error while finding user: " + e.getMessage());
        }
        return null;
    }
}
