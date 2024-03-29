package edu.models;

import java.util.List;
import java.util.Objects;

public class User {

    private long id;
    private String login;
    private String password;
    private List<Chatroom> myCreatedRooms;
    private List<Chatroom> mySocializesRooms;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", myCreatedRooms=" + myCreatedRooms +
                ", mySocializesRooms=" + mySocializesRooms +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(myCreatedRooms, user.myCreatedRooms) && Objects.equals(mySocializesRooms, user.mySocializesRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, myCreatedRooms, mySocializesRooms);
    }

}