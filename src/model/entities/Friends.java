package model.entities;


public class Friends {

    private long id;
    private String name;
    private Chat chat;

    public Friends(){}
    
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

    @Override
    public String toString() {
        return id+","+name+","+ chat.getPath().getPathSent()+","+chat.getPath().getPathDestiny();
    }  

}
