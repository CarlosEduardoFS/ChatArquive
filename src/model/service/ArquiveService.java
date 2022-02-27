package model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.LinkedList;
import java.util.List;

import model.entities.Friends;
import model.entities.Message;
import model.entities.Path;
import model.exception.ServiceExeption;
import model.entities.Account;
import model.entities.Chat;
import model.entities.Email;
import model.service.interfaces.Arquive;

public class ArquiveService implements Arquive {

    @Override
    public void WriteArquive(String msg, String path) {
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))){
            
            bf.write(changeTheComma(msg));

        }catch (IOException e){
            System.out.println("Error: Could not write message || "+e.getMessage());
        }
        
    }

    @Override
    public void WriteArquiveDontErase(String msg, String path) {
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path, true))){
            
            bf.write(changeTheComma(msg));
            bf.newLine();

        }catch (IOException e){
            System.out.println("Error: Could not write message || "+e.getMessage());
        }
        
    }

    @Override
    public void WriteArquiveSent(Message message) {
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(message.getPath().getPathSent(), true))){

            bf.write(changeTheComma(message.toString()));
            bf.newLine();

        }catch (IOException e){
            System.out.println("Error: Could not write message || "+e.getMessage());
        }
        
    }

    @Override
    public void WriteArquiveDestiny(Message message) {
        
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(message.getPath().getPathDestiny(), true))){

            bf.write(changeTheComma(message.toString()));
            bf.newLine();

        }catch (IOException e){
            System.out.println("Error: Could not write message || "+e.getMessage());
        }  
    }

    private String changeTheComma (String msg){

        msg = msg.replace(",", "/*");

        return msg;
    }

    private String insertingComma (String msg){

        msg = msg.replace("/*",",");

        return msg;
    }

    private void getMessageComma(String[] values){
        for (int i = 0; i < values.length; i++){
            values[i] = insertingComma(values[i]);
        }
    }

    @Override
    public Message getValue(String path){

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            Message msg = new Message(line);

            return msg;


        }catch (IOException e){
            System.out.println("Error: Could not read from file || "+e.getMessage());
        }
        return null;

    }

    @Override
    public List<Message> getMenssages(String path) {
        
        List<Message> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String line = br.readLine();
            while (line != null){
                line = insertingComma(line);
                String[] values = line.split(",");
                getMessageComma(values);
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
            if (line != null)
                line = insertingComma(line);
            while(line != null){
                String[] s = line.split(",");
                getMessageComma(s);
                list.add(new Friends(Long.parseLong(s[0]), s[1], new Chat(new Path(s[2], s[3]))));
                line = br.readLine(); 
                if (line != null)
                    line = insertingComma(line);       
            }

        } catch (IOException e){
            System.out.println("Error list friends || "+e.getMessage());
        }

        return list;
    }

    @Override
    public List<Account> getAccounts(String path) {
        
        List<Account> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            while(line != null){
               line = insertingComma(line);
                String[] s = line.split(",");
                list.add(new Account(Long.parseLong(s[0]),s[1], new Email(s[2], s[3]), s[4]));
                line = br.readLine();        
            }

        } catch (IOException e){
            System.out.println("Error list account || "+e.getMessage());
        }

        return list;
    }

    @Override
    public List<Email> getEmail(String path) {
        
        List<Email> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            while(line != null){
                line = insertingComma(line);
                String[] s = line.split(",");
                list.add(new Email(s[0],s[1]));
                line = br.readLine();        
            }

        } catch (IOException e){
            System.out.println("Error list account || "+e.getMessage());
        }

        return list;
    }

    @Override
    public List<Account> getFrindes(String path) {
        
        List<Account> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();
            while(line != null){
                line = insertingComma(line);
                String[] s = line.split(",");
                list.add(new Account(Long.parseLong(s[0]), s[1], new Email(s[2], "****"), "..."));
                line = br.readLine();        
            }

        } catch (IOException e){
            System.out.println("Error list account || "+e.getMessage());
        }

        return list;
    }
   
    @Override
    public void eraseLine(String path, int position, List<Friends> listF, CreateArquiveService ca) throws ServiceExeption {

        File f = new File(path);
        System.out.println(f.delete());
        ca.createArquichive(path);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(path))){

            for (int i = 0; i < listF.size(); i++){
                Friends friend = listF.get(i);
                if ( i != position){
                    bf.write(friend.toString());
                    bf.newLine();
                }
            }

        } catch (IOException e){
            System.out.println("Error erase line  || "+e.getMessage());
        }
    }

    
}
