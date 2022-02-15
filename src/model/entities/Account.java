package model.entities;

import java.util.LinkedList;
import java.util.List;

public class Account {
    
    private long id;
    private String nameUser;
    private Email email;
    private String folder;
    private List<Friends> listFriends = new LinkedList<>();

    public Account(long id, String nameUser, Email email, String folder) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
        this.folder = folder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<Friends> getListFriends() {
        return listFriends;
    }

    public void addListFriends(Friends friends) {
        listFriends.add(friends);
    }

    public void setListFriends(List<Friends> listFriends) {
        this.listFriends = listFriends;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    @Override
    public String toString() {
        return "NameUser: " + nameUser+ ", " + email +", id: " + id;
    }    
}
