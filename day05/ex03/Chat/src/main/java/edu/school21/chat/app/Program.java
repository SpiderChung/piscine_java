package edu.school21.chat.app;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args)  {
        JdbcDataSource dataSource = new JdbcDataSource();
        updateData("schema.sql", dataSource);
        updateData("data.sql", dataSource);
        MessagesRepository repository = new MessagesRepositoryJdbcImpl(dataSource.getDataSource());
        createNewMessage(repository);
    }

    public static void createNewMessage(MessagesRepository messagesRepository) {
        long id = 1L;
        User billy = new User(1L, "Billy", "1", new ArrayList<>(), new ArrayList<>());
        User willy = new User(2L, "Willy", "12", new ArrayList<>(), new ArrayList<>());
        User chuck = new User(4L, "Chuck", "qwe", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(2L, "Chat1", billy, new ArrayList<>());
        Message message;

        System.out.println("-----UPDATE MESSAGES-----");
        try {
            Optional<Message> optionalMessage = messagesRepository.findById(id);
            if (optionalMessage.isPresent()) {
                message = optionalMessage.get();
                System.out.println("-----MESSAGE BEFORE UPDATE:-----");
                System.out.println(message);
                message.setAuthor(willy);
                message.setRoom(room);
                message.setText("New message from Willy");
                message.setLocalDateTime(LocalDateTime.now());
                System.out.println("-----MESSAGE AFTER UPDATE:-----");
                messagesRepository.update(message);
                System.out.println(messagesRepository.findById(message.getId()).get());
            }
        } catch (NotSavedSubEntityException e) {
            System.err.println(e.getMessage());
        }

        try {
            id = 2L;
            Optional<Message> optionalMessage = messagesRepository.findById(id);
            if (optionalMessage.isPresent()) {
                message = optionalMessage.get();
                System.out.println("-----MESSAGE BEFORE UPDATE:-----");
                System.out.println(message);
                message.setAuthor(billy);
                message.setRoom(room);
                message.setText("New message from Billy");
                message.setLocalDateTime(LocalDateTime.now().minusDays(3));
                System.out.println("-----MESSAGE AFTER UPDATE:-----");
                messagesRepository.update(message);
                System.out.println(messagesRepository.findById(message.getId()).get());
            }
        } catch (NotSavedSubEntityException e) {
            System.err.println(e.getMessage());
        }

        try {
            id = 4L;
            Optional<Message> optionalMessage = messagesRepository.findById(id);
            if (optionalMessage.isPresent()) {
                message = optionalMessage.get();
                System.out.println("-----MESSAGE BEFORE UPDATE:-----");
                System.out.println(message);
                message.setAuthor(chuck);
                message.setRoom(room);
                message.setText("New message from Chuck");
                message.setLocalDateTime(null);
                System.out.println("-----MESSAGE AFTER UPDATE:-----");
                messagesRepository.update(message);
                System.out.println(messagesRepository.findById(message.getId()).get());
            }
        } catch (NotSavedSubEntityException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void updateData(String file, JdbcDataSource dataSource) {
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            InputStream input = Program.class.getClassLoader().getResourceAsStream(file);
            Scanner scanner = new Scanner(input).useDelimiter(";");

            while (scanner.hasNext()) {
                st.executeUpdate(scanner.next().trim());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}