package entities;

import java.util.LinkedList;
import java.util.List;

public class Friends {

    private long id;
    private String name;
    private Chat chat;

    List<Message> listMessage = new LinkedList<>();
    
    public Friends(long id, String name, Chat chat) {
        this.id = id;
        this.name = name;
        this.chat = chat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public List<Message> getListMessage() {
        return listMessage;
    }

    public void setListMessage(Message msg) {
        listMessage.add(msg);
    } 
}
