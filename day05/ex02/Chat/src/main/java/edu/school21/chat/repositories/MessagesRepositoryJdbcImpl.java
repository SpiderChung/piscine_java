package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
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

            ResultSet resultSet = statement.executeQuery("SELECT * FROM chat.user WHERE id = " + userId);
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("User with ID " + userId + " doesn't exist");
            }

            resultSet = statement.executeQuery("SELECT * FROM chat.chatroom WHERE id = " + roomId);
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException("Chatroom with ID " + roomId + " doesn't exist");
            }

            if (message.getLocalDateTime() != null) {
                localDateTime = "'" + Timestamp.valueOf(message.getLocalDateTime()) + "'";
            }

            resultSet = statement.executeQuery("INSERT INTO chat.message (author, room, text, localDateTime) " +
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

        if (message.getText() == null || message.getText().length() == 0) {
            throw new NotSavedSubEntityException("Error: text message is empty");
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        String mQuery = "SELECT * FROM chat.message WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(mQuery);

            if (!rs.next()) {
                return null;
            }
            Long userId = rs.getLong(2);
            Long roomId = rs.getLong(3);
            User user = findUser(userId);
            Chatroom room = findChat(roomId);
            return Optional.of(new Message(rs.getLong(1), user, room,
                    rs.getString(4), rs.getTimestamp(5).toLocalDateTime()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    private User findUser(Long id) throws SQLException {
        String uQuery = "SELECT * FROM chat.user WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(uQuery);

            if (!rs.next()) {
                return null;
            }
            return new User(id, rs.getString(2), rs.getString(3));
        }
    }

    private Chatroom findChat(Long id) throws SQLException {
        String cQuery = "SELECT * FROM chat.chatroom WHERE id = " + id;

        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(cQuery);

            if (!rs.next()) {
                return null;
            }
            return new Chatroom(id, rs.getString(2));
        }
    }
}