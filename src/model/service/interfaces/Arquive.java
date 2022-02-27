package model.service.interfaces;

import java.util.List;

import model.entities.Account;
import model.entities.Email;
import model.entities.Friends;
import model.entities.Message;
import model.exception.ServiceExeption;
import model.service.CreateArquiveService;

public interface Arquive {
    
    public void WriteArquive(String path, String msg);
    public void WriteArquiveSent(Message message);
    public void WriteArquiveDestiny(Message message);
    public List<Message> getMenssages(String path);
    public List<Friends> getFriends(String path);
    public Message getValue(String path);
    public List<Account> getAccounts(String path);
    public List<Email> getEmail(String path);
    public void WriteArquiveDontErase(String msg, String path);
    public List<Account> getFrindes(String path);
    public void eraseLine(String path, int position, List<Friends> listF, CreateArquiveService ca) throws ServiceExeption;
}
