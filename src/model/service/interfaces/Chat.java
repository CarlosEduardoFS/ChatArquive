package model.service.interfaces;

import java.util.List;

import model.entities.Account;

public interface Chat {

    public List<Chat> listFullChat(Account account);
    public Chat coinversation(Account sent, Account received);
    
}
