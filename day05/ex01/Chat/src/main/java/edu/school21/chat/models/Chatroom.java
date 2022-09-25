package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {

    private Integer id;
    private String title;
    private User owner;
    private List<Message> messages;

    public Chatroom(Integer id, String title, User owner, List<Message> messages) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.messages = messages;
    }

    public Chatroom(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, owner, messages);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Chatroom chatroom = (Chatroom) obj;
        return id.equals(chatroom.id) && title.equals(chatroom.title) && owner.equals(chatroom.owner)
                && messages.equals(chatroom.messages);
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name='" + title + "', creator="
                + owner + ", messages=" + messages + "}";
    }
}