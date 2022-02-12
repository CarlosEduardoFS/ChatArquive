package model.entities;

import java.util.LinkedList;
import java.util.List;

public class Chat {

    private String pathSent;
    private String pathDestiny;

    private List<Message> listMsg = new LinkedList<>();
    
    public Chat(String pathSent, String pathDestiny) {
        this.pathSent = pathSent;
        this.pathDestiny = pathDestiny;
    }
    public String getPathSent() {
        return pathSent;
    }
    public void setPathSent(String pathSent) {
        this.pathSent = pathSent;
    }
    public String getPathDestiny() {
        return pathDestiny;
    }
    public void setPathDestiny(String pathDestiny) {
        this.pathDestiny = pathDestiny;
    }
    public List<Message> getListMsg() {
        return listMsg;
    }
    public void setListMsg(Message msg) {
        listMsg.add(msg);
    }

}
