package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private Integer id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> usedRooms;

    public User(Integer id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> usedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.usedRooms = usedRooms;
    }

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getUsedRooms() {
        return usedRooms;
    }

    public void setUsedRooms(List<Chatroom> usedRooms) {
        this.usedRooms = usedRooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, usedRooms);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return id.equals(user.id) && login.equals(user.login) && password.equals(user.password)
                && createdRooms.equals(user.createdRooms) && usedRooms.equals(user.usedRooms);
    }

    @Override
    public String toString() {
        return "{id=" + id + ", login='" + login + "', password='"
                + password + "', createdRooms=" + createdRooms + ", rooms=" + usedRooms + "}";
    }
}