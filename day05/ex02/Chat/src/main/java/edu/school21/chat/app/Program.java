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
        User creator = new User(1L, "Billy", "1", new ArrayList<>(), new ArrayList<>());
        User author = creator;
        Chatroom room = new Chatroom(1L, "Chat1", creator, new ArrayList<>());
        Message message = new Message(null, author, room, "Hello world!", LocalDateTime.now());

        try {
            messagesRepository.save(message);
            System.out.println("New message id = " + message.getId());
        } catch (NotSavedSubEntityException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("-----author is empty-----");
        try {
            creator = new User(2L, "Willy", "12", null, null);
            author = null;
            room = new Chatroom(2L, "Chat2", creator, null);
            message = new Message(null, author, room, "What is your name?", LocalDateTime.now());
            messagesRepository.save(message);
        } catch (NotSavedSubEntityException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("-----chatroom doesn't exist-----");
        try {
            creator = new User(2L, "Willy", "12", null, null);
            author = creator;
            room = new Chatroom(13L, "Chat13", creator, null);
            message = new Message(null, author, room, "See you later", LocalDateTime.now());
            messagesRepository.save(message);
        } catch (NotSavedSubEntityException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("-----author doesn't exist-----");
        try {
            creator = new User(22L, "Devil", "666", null, null);
            author = creator;
            room = new Chatroom(2L, "Chat2", creator, null);
            message = new Message(null, author, room, "Who is that guy?", LocalDateTime.now());
            messagesRepository.save(message);
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-----Another Chuks's message-----");
        try {
            creator = new User(4L, "Chuck", "qwe", null, null);
            author = creator;
            room = new Chatroom(4L, "Chat4", creator, null);
            message = new Message(null, author, room, "Good luck, Chuck", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println("New message id = " + message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
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