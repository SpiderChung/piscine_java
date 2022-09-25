package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        init();
    }

    private void init() {
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS server;");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS server.user (\n" +
                "id serial primary key,\n" +
                "username varchar(40) not null unique,\n" +
                "password varchar(100) not null);");
    }

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();

            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }

    @Override
    public User findById(Long id) {
        User user = jdbcTemplate.query("SELECT * FROM server.user WHERE id = ?",
                new Object[]{id},
                new int[]{Types.INTEGER},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);

        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query("SELECT * FROM server.user", new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public void save(User entity) {
        int i = jdbcTemplate.update("INSERT INTO server.user (username, password) VALUES (?, ?)",
                entity.getUsername(), entity.getPassword());

        if (i == 0) {
            System.err.println("User wasn't saved: " + entity);
        }
    }

    @Override
    public void update(User entity) {
        int i = jdbcTemplate.update("UPDATE server.user SET username = ?, password = ? WHERE id = ?",
                entity.getUsername(),
                entity.getPassword(),
                entity.getId());

        if (i == 0) {
            System.err.println("User wasn't updated: " + entity);
        }
    }

    @Override
    public void delete(Long id) {
        int i = jdbcTemplate.update("DELETE FROM server.user WHERE id = ?", id);

        if (i == 0) {
            System.err.println("User not found with id: " + id);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = jdbcTemplate.query("SELECT * FROM server.user WHERE username = ?",
                new Object[]{username},
                new int[]{Types.VARCHAR},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
        return Optional.ofNullable(user);
    }
}
