package model.service.interfaces;


import model.entities.Account;
import model.entities.Chat;


public interface ChatS {

    public void listFullChat(Account account);
    public Chat coinversation(int num, Account acount);
    
}
