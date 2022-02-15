package model.service.interfaces;

import java.util.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.entities.Friends;
import model.entities.Message;

public interface Arquive {
    
    default void WriteArquive(String path, String msg){

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path, true))){

            bf.write(msg);
            bf.newLine();

        }catch (IOException e){
            System.out.println("Error: Could not write message || "+e.getMessage());
        } 
    }

    public void WriteArquiveSent(Message message);
    public void WriteArquiveDestiny(Message message);
    public List<Message> getMenssages(String path);
    public List<Friends> getFriends(String path);
}
