package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM models.user")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User User = new User(resultSet.getLong(1), resultSet.getString(2));
                list.add(User);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM models.User WHERE id = " + id)) {
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            User user = new User(resultSet.getLong(1), resultSet.getString(2));
            return user;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(User entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE models.user SET email = ? WHERE id = ?")) {

            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getIdentifier());
            Integer resultSet = statement.executeUpdate();

            if (resultSet == 0) {
                System.err.println("User with id: " + entity.getIdentifier() + " wasn't updated");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO models.user(id, email) VALUES (?, ?)")) {

            statement.setLong(1, entity.getIdentifier());
            statement.setString(2, entity.getEmail());
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM models.user WHERE id = " + id)) {
            Integer result = statement.executeUpdate();

            if (result == 0) {
                System.err.println("User with id: " + id + " not found");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM models.user WHERE email = ?")) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }
}
