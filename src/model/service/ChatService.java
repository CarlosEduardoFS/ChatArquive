package model.service;

import java.util.List;

import model.entities.Account;
import model.entities.Friends;
import model.entities.Chat;


import model.service.interfaces.ChatS;

public class ChatService implements ChatS{

    @Override
    public void listFullChat(Account account) {
        
        ArquiveService as = new ArquiveService();

        account.setListFriends(as.getFriends(account.getFolder()+"\\friends.txt"));
        
    }

    @Override
    public Chat coinversation(int num, Account account) {
        
        ArquiveService as = new ArquiveService();

        List<Friends> list = as.getFriends(account.getFolder()+"\\friends.txt");
        Friends f = list.get(num);

        Chat chat = new Chat(f.getChat().getPath());
        chat.setListMsg(as.getMenssages(f.getChat().getPath().getPathSent()));

        return chat;
    }
    
}
