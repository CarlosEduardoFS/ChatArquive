package model.service.interfaces;


import model.entities.Account;
import model.entities.Chat;
import model.service.ArquiveService;


public interface ChatS {

    public void listFullChat(Account account, ArquiveService as);
    public Chat coinversation(int num, Account account, ArquiveService as);
    
}
