package edu.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {

    private long id;
    private String name;
    private String owner;
    private List<Message> messageList;

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", massageList=" + messageList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return id == chatroom.id && Objects.equals(name, chatroom.name) && Objects.equals(owner, chatroom.owner) && Objects.equals(messageList, chatroom.messageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, messageList);
    }
}
