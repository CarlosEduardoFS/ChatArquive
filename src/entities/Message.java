package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message>{

    private String msg;
    private Date date;
    private Chat chat;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Message(String msg, Chat chat) {
        this.msg = msg;
        date = new Date();
        this.chat = chat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public static SimpleDateFormat getSdf() {
        return sdf;
    }

    @Override
    public int compareTo(Message msg2) {
        return date.compareTo(msg2.getDate());
    } 

    @Override
    public String toString() {
        return "[" + sdf.format(date)+"] "+ msg;
    }
}
