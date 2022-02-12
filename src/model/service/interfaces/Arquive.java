package model.service.interfaces;

import java.util.List;

import model.entities.Message;

public interface Arquive {
    
    public void WriteArquiveSetOwner(Message message);
    public void WriteArquiveReceivedDestinity(Message message);
    public List<Message> getMenssages();
}
