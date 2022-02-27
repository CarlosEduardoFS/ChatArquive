package model.service;

import java.util.List;

import model.entities.Account;
import model.entities.Friends;
import model.entities.Chat;


import model.service.interfaces.ChatS;

public class ChatService implements ChatS{

    @Override
    public void listFullChat(Account account, ArquiveService as) {

        account.setListFriends(as.getFriends(account.getFolder()+"\\friends.txt"));
        
    }

    @Override
    public Chat coinversation(int num, Account account, ArquiveService as) {

        List<Friends> list = as.getFriends(account.getFolder()+"\\friends.txt");
        Friends fiends = list.get(num);

        Chat chat = new Chat(fiends.getChat().getPath());
        chat.setListMsg(as.getMenssages(fiends.getChat().getPath().getPathSent()));

        return chat;
    }
    
}
