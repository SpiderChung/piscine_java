package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        createTable(context);
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepositoryJdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        System.out.println("\n----------FIND ALL USERS----------");
        try {
            for (User user : usersRepositoryJdbc.findAll()) {
                System.out.println(user);
            }
            System.out.println();
            for (User user : usersRepositoryJdbcTemplate.findAll()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.err.println("There's nothing to show");
        }

        System.out.println("\n----------INSERT NEW USERS WITH JDBC_TEMPLATE----------");
        User user5 = new User(5L, "Kferterb@student.21-school.ru");
        User user6 = new User(6L, "Jchopped@student.21-school.ru");
        User user7 = new User(7L, "Sroper@student.21-school.ru");
        User user8 = new User(8L, "Bcarlee@student.21-school.ru");
        User user9 = new User(9L, "Enoye@student.21-school.ru");
        User user10 = new User(10L, "Dcloud@student.21-school.ru");
        List<User> users = Arrays.asList(user5, user6, user7, user8, user9, user10);
        users.forEach(System.out::println);
        users.forEach(user -> usersRepositoryJdbcTemplate.save(user));

        System.out.println("\n----------FIND ALL USERS----------");
        try {
            for (User user : usersRepositoryJdbc.findAll()) {
                System.out.println(user);
            }
            System.out.println();
            for (User user : usersRepositoryJdbcTemplate.findAll()) {
                System.out.println(user);
            }
        } catch (Exception e) {
        System.err.println("There's nothing to show");
       }

        System.out.println("\n----------DELETE USERS 9 10----------");
        usersRepositoryJdbc.delete(10L);
        usersRepositoryJdbcTemplate.delete(9L);

        System.out.println("\n-----------UPDATE USERS 5 6 7 8----------");
        user5.setEmail(user5.getEmail() + " Philantropist");
        user6.setEmail(user6.getEmail() + "Khonvoum");
        user7.setEmail(user7.getEmail() + "Khonvoum");
        user8.setEmail(user8.getEmail() + " Philantropist");
        usersRepositoryJdbc.update(user5);
        usersRepositoryJdbc.update(user6);
        usersRepositoryJdbcTemplate.update(user7);
        usersRepositoryJdbcTemplate.update(user8);

        System.out.println("\n----------ALL USERS----------");
        try {
            for (User user : usersRepositoryJdbc.findAll()) {
                System.out.println(user);
            }
            System.out.println();
            for (User user : usersRepositoryJdbcTemplate.findAll()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.err.println("There's nothing to show");
        }


        System.out.println("\n----------FIND BY ID----------");
        System.out.println(usersRepositoryJdbc.findById(1l));
        System.out.println(usersRepositoryJdbcTemplate.findById(1l));
        System.out.println(usersRepositoryJdbc.findById(2l));
        System.out.println(usersRepositoryJdbcTemplate.findById(2l));

        System.out.println("\n----------FIND BY EMAIL----------");
        System.out.println(usersRepositoryJdbc.findByEmail("Amaribel@student.21-school.ru"));
        System.out.println(usersRepositoryJdbc.findByEmail("Schung@student.21-school.ru"));
        System.out.println(usersRepositoryJdbcTemplate.findByEmail("Amaribel@student.21-school.ru"));
        System.out.println(usersRepositoryJdbcTemplate.findByEmail("Schung@student.21-school.ru"));
        System.out.println(usersRepositoryJdbc.findByEmail("SOMETHING EMPTY"));
        System.out.println(usersRepositoryJdbcTemplate.findByEmail("SOMETHING EMPTY"));
    }

    private static void createTable(ApplicationContext context) {
        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists models cascade;");
            st.executeUpdate("create schema if not exists models;");
            st.executeUpdate("create table if not exists models.user (\n" +
                    "     id serial primary key,\n" +
                    "     email varchar(50) not null\n" +
                    "    );");
            st.executeUpdate("INSERT INTO models.user(email) VALUES\n" +
                    "('Schung@student.21-school.ru'),\n" +
                    "('Amaribel@student.21-school.ru'),\n" +
                    "('Sshera@student.21-school.ru'),\n" +
                    "('Hzona@student.21-school.ru')\n" +
                    "ON CONFLICT DO NOTHING;");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
