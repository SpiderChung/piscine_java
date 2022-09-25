package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;
    private final String allQuery = "SELECT u.*, c.id, c.title, uc.chat_id, ct.title, us.id, us.name, us.password\n" +
            "FROM (SELECT * FROM chat.user LIMIT ? OFFSET ?) u\n" +
            "LEFT JOIN chat.chatroom c ON u.id = c.owner\n" +
            "LEFT JOIN chat.user_chatroom uc ON u.id = uc.user_id\n" +
            "LEFT JOIN chat.chatroom ct ON uc.chat_id = ct.id\n" +
            "LEFT JOIN chat.user us ON ct.owner = us.id\n" +
            "ORDER BY u.id, c.id, uc.chat_id;";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> users = new ArrayList<>();
        int offset = page * size;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(allQuery)) {
            statement.setLong(1, size);
            statement.setLong(2, offset);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                final Long userId, createChatId, usedChatId;
                User user;
                Chatroom chatroom;
                userId = resultSet.getLong(1);

                if (users.stream().noneMatch(u -> userId.equals(u.getId()))) {
                    user = new User(userId, resultSet.getString(2), resultSet.getString(3),
                            new ArrayList<>(), new ArrayList<>());
                    users.add(user);
                } else {
                    user = users.stream().filter(u -> userId.equals(u.getId())).collect(Collectors.toList()).get(0);
                }
                createChatId = resultSet.getLong(4);

                if (createChatId != 0 && user.getCreatedRooms().stream().noneMatch(c -> createChatId.equals(c.getId()))) {
                    chatroom = new Chatroom(createChatId, resultSet.getString(5),
                            new User(user.getId(), user.getLogin(), user.getPassword()), null);
                    user.getCreatedRooms().add(chatroom);
                }
                usedChatId = resultSet.getLong(6);

                if (usedChatId != 0 && user.getUsedRooms().stream()
                        .noneMatch(c -> usedChatId.equals(c.getId()))) {
                    chatroom = new Chatroom(usedChatId, resultSet.getString(7),
                            new User(resultSet.getLong(8), resultSet.getString(9),
                                    resultSet.getString(10)), null);
                    user.getUsedRooms().add(chatroom);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }
}
