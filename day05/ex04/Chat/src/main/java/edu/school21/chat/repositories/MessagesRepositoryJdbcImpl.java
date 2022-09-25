package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void update(Message message) {
        checkMessage(message);
        Timestamp localDateTime = null;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM chat.message WHERE id = " + message.getId());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                save(message);
                return;
            }

            if (message.getText() == null) {
                message.setText("");
            }

            if (message.getLocalDateTime() != null) {
                localDateTime = Timestamp.valueOf(message.getLocalDateTime());
            }
            statement = connection.prepareStatement("UPDATE chat.message SET author = ?, room = ?,"
                    + " text = ?, localDateTime = ? WHERE id = ?");
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, localDateTime);
            statement.setLong(5, message.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void save(Message message) {
        Long userId, roomId;
        String localDateTime = "'null'";

        checkMessage(message);
        userId = message.getAuthor().getId();
        roomId = message.getRoom().getId();

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            if (message.getLocalDateTime() != null) {
                localDateTime = "'" + Timestamp.valueOf(message.getLocalDateTime()) + "'";
            }

            ResultSet resultSet = statement.executeQuery("INSERT INTO chat.message (author, room, text, localDateTime) " +
                    "VALUES (" + userId + ", " + roomId + ", '" + message.getText() + "', " + localDateTime + ") RETURNING id");
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("Error of writing");
            }
            message.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void checkMessage(Message message) {
        if (message.getAuthor() == null || message.getAuthor().getId() == null) {
            throw new NotSavedSubEntityException("Author doesn't exist");
        }

        if (message.getRoom() == null || message.getRoom().getId() == null) {
            throw new NotSavedSubEntityException("Chatroom doesn't exist");
        }

        if (message.getRoom().getOwner() == null || message.getRoom().getOwner().getId() == null) {
            throw new NotSavedSubEntityException("Chatroom creator doesn't exist");
        }
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            Long userId = message.getAuthor().getId();
            Long roomId = message.getRoom().getId();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM chat.user WHERE id = " + userId);

            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("User with id = " + userId + " doesn't exist");
            }
            resultSet = statement.executeQuery("SELECT * FROM chat.chatroom WHERE id = " + roomId);

            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("Chatroom with id = " + roomId + " doesn't exist");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM chat.message WHERE id = " + id);

            if (!resultSet.next()) {
                return null;
            }
            Long userId = resultSet.getLong(2);
            Long roomId = resultSet.getLong(3);
            User user = findUser(userId);
            Chatroom room = findChat(roomId);
            LocalDateTime localDateTime = resultSet.getTimestamp(5) == null
                    ? null : resultSet.getTimestamp(5).toLocalDateTime();
            return Optional.of(new Message(resultSet.getLong(1), user, room,
                    resultSet.getString(4), localDateTime));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    private User findUser(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM chat.user WHERE id = " + id);

            if (!resultSet.next()) {
                return null;
            }
            return new User(id, resultSet.getString(2), resultSet.getString(3));
        }
    }

    private Chatroom findChat(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM chat.chatroom WHERE id = " + id);

            if (!resultSet.next()) {
                return null;
            }
            return new Chatroom(id, resultSet.getString(2));
        }
    }
}