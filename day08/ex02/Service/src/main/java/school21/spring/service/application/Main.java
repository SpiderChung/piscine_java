package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;

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
        ApplicationContext context = new AnnotationConfigApplicationContext("school21.spring.service");
        UsersService usersService = context.getBean("usersService", UsersService.class);
        UsersRepository usersJdbcTemplate = context.getBean("jdbcTemplateRepository", UsersRepository.class);
        UsersRepository usersJdbc = context.getBean("jdbcRepository", UsersRepository.class);
        createTable(context);

        System.out.println("\n----------CREATE NEW USERS----------");
        System.out.println(usersService.signUp("Amaribel@student.21-school.ru"));
        System.out.println(usersService.signUp("Schung@student.21-school.ru"));
        System.out.println(usersService.signUp("Kferterb@student.21-school.ru"));
        System.out.println(usersService.signUp("Jchopped@student.21-school.ru"));
        System.out.println(usersService.signUp("Sroper@student.21-school.ru"));
        System.out.println(usersService.signUp("Bcarlee@student.21-school.ru"));
        System.out.println(usersService.signUp("Enoye@student.21-school.ru"));
        System.out.println(usersService.signUp("Dcloud@student.21-school.ru"));
        System.out.println(usersService.signUp("Hzona@student.21-school.ru"));

        System.out.println("\n----------TESTING ALL METHODS----------");
        try {
            for (User user : usersJdbc.findAll()) {
                System.out.println(user);
            }
            System.out.println();
            for (User user : usersJdbcTemplate.findAll()) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.err.println("There's nothing to show");
        }
        System.out.println();
        System.out.println(usersJdbcTemplate.findById(7L));
        System.out.println(usersJdbc.findById(7L));
        System.out.println(usersJdbcTemplate.findByEmail("Enoye@student.21-school.ru"));
        System.out.println(usersJdbc.findByEmail("Enoye@student.21-school.ru"));

        context.getBean("hikariDataSource", HikariDataSource.class).close();

    }

    private static void createTable(ApplicationContext context) {
        DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement()) {
            st.executeUpdate("drop schema if exists models cascade;");
            st.executeUpdate("create schema if not exists models;");
            st.executeUpdate("create table if not exists models.user (\n" +
                    "     id serial primary key,\n" +
                    "     email varchar(50) not null,\n" +
                    "    password varchar (50));");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
