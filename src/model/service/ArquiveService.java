package model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.LinkedList;
import java.util.List;

import model.entities.Friends;
import model.entities.Message;
import model.entities.Path;
import model.entities.Chat;

import model.service.interfaces.Arquive;

public class ArquiveService implements Arquive {

    @Override
    public void WriteArquiveSent(Message message) {
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(message.getPath().getPathSent(), true))){

            bf.write(message.toString());
            bf.newLine();

        }catch (IOException e){
            System.out.println("Error: Could not write message || "+e.getMessage());
        }
        
    }

    @Override
    public void WriteArquiveDestiny(Message message) {
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(message.getPath().getPathDestiny(), true))){

            bf.write(message.toString());
            bf.newLine();

        }catch (IOException e){
            System.out.println("Error: Could not write message || "+e.getMessage());
        }  
    }

    @Override
    public List<Message> getMenssages(String path) {
        
        List<Message> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String line = br.readLine();
            while (line != null){
                String[] values = line.split(",");
                Message msg = new Message(values[1],values[4]);
                msg.setDate(sdf.parse(values[0]));
                Path p = new Path(values[2], values[3]);
                msg.setPath(p);
                list.add(msg);
                line = br.readLine();
            }

        }catch (IOException e){
            System.out.println("Error: Could not read from file || "+e.getMessage());
        }catch (ParseException e){
            System.out.println("Error: incompatible date || "+ e.getMessage());
        }

        return list;
    }

    @Override
    public List<Friends> getFriends(String path) {
        
        List<Friends> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            while(line != null){
                String[] s = line.split(",");
                list.add(new Friends(Long.parseLong(s[0]), s[1], new Chat(new Path(s[2], s[3]))));
                line = br.readLine();        
            }

        } catch (IOException e){
            System.out.println("Error list friends || "+e.getMessage());
        }

        return list;
    }

   
    
}
