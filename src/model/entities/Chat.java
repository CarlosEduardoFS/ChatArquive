package model.entities;

import java.util.LinkedList;
import java.util.List;

public class Chat {

    private int position;
    private Path path;

    private List<Message> listMsg = new LinkedList<>();
    
    public Chat(Path path) {
        this.path = path;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public Path getPath() {
        return path;
    }
    public void setPath(Path path) {
        this.path = path;
    }
    public void setListMsg(List<Message> listMsg) {
        this.listMsg = listMsg;
    }
   
    public List<Message> getListMsg() {
        return listMsg;
    }
    public void addListMsg(Message msg) {
        listMsg.add(msg);
    }

    @Override
    public String toString() {
        return listMsg + ","+ path;
    }

}
