package model.entities;

import java.util.LinkedList;
import java.util.List;

public class Account {
    
    private long id;
    private String nameUser;
    private Email email;
    private List<Friends> listFriends = new LinkedList<>();

    public Account(long id, String nameUser, Email email) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
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

    public void setListFriends(Friends friends) {
        listFriends.add(friends);
    }

    @Override
    public String toString() {
        return "email=" + email + ", id=" + id + ", listFriends=" + listFriends + ", nameUser=" + nameUser;
    }

    
    
}
