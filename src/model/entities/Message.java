package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Comparable<Message>{

    private String msg;
    private Date date;
    private Path path;
    private String nameUser;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Message(String msg, String nameUser) {
        this.msg = msg;
        this.nameUser = nameUser;
    }

    public Message(String msg, Path path,String nameUser) {
        this.msg = msg;
        date = new Date();
        this.path = path;
        this.nameUser = nameUser;
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

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @Override
    public int compareTo(Message msg2) {
        return date.compareTo(msg2.getDate());
    } 

    @Override
    public String toString() {
        return sdf.format(date)+","+ msg + ","+ path+","+nameUser;
    }

    public String toMessage() {
        return "[" + sdf.format(date)+"] "+ msg;
    }
}
