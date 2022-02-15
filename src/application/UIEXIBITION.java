package application;

import java.util.List;
import java.util.Scanner;

import model.entities.Account;
import model.entities.Chat;
import model.entities.Email;
import model.entities.Friends;
import model.entities.Message;
import model.exception.ProgramException;
import model.service.ArquiveService;

public class UIEXIBITION {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

    public static Account register(Scanner sc, ArquiveService as, String path){
        clearScreen();

        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"        Register         "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|"+ANSI_RESET);
        
        System.out.print(ANSI_CYAN + "\n Enter your name: "+ ANSI_RESET);
        String nameUser = sc.nextLine();
        System.out.print(ANSI_CYAN + " Enter your email: "+ ANSI_RESET);
        String email = sc.nextLine();
        System.out.print(ANSI_CYAN + " Enter your password: "+ ANSI_RESET);
        String password = sc.nextLine();

        long id = Long.parseLong(as.getValue(path).getMsg());
        id++;
        as.WriteArquive(Long.toString(id), path);

        return new Account(id, nameUser, new Email(email, password), "");
    }

    public static Email login(Scanner sc){
        clearScreen();

        System.out.print(ANSI_CYAN + "Email: "+ ANSI_RESET);
        String email = sc.nextLine();
        System.out.print(ANSI_CYAN+"Password: "+ANSI_RESET);
        String password = sc.nextLine();

        return new Email(email, password);
    }

    public static int UserMenu (Scanner sc, Account account){
        clearScreen();
        
        System.out.print(ANSI_RED+" Your identifier is: "+account.getId()+"\n"+ANSI_RESET);
        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"                         "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  1. Add friend          "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  2. Friends             "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  3. convites            "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  4. Sair                "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|"+ANSI_RESET);
        
        System.out.print(ANSI_CYAN+"\n Enter an option: "+ANSI_RESET);
        int option = sc.nextInt();
        return option;

    }

    public static int addFriendScreen (Scanner sc){

        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"        ADD FRIEND       "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|"+ANSI_RESET);

        System.out.print("\n Enter the friend's ID: ");
        int id = sc.nextInt();
        return id;
    }

    public static Chat listChatFrineds(Scanner sc, String path, ArquiveService as) throws ProgramException{
        if (sc == null || path == null || as == null)
            throw new ProgramException("No parameter can be null!");

        clearScreen();
        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"         FRIENDS         "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|\n"+ANSI_RESET);
        
        List<Friends> list = as.getFriends(path);

        for (int i = 0; i < list.size(); i++){
            Friends f = list.get(i);
            System.out.println(ANSI_CYAN +" "+f.getId()+" "+f.getName()+ ANSI_RESET);
        }

        System.out.print(ANSI_CYAN +"\n Which chat do you want to open: "+ ANSI_RESET);
        int option = sc.nextInt();
        sc.nextLine();

        if (option >= list.size() || option < 0)
            throw new ProgramException("Error: Invalid typed chat!");

        Chat chat = list.get(option).getChat();
        chat.setListMsg(as.getMenssages(list.get(option).getChat().getPath().getPathSent()));
          
        return chat;

    }

    public static Message chat (Scanner sc, Chat chat, Account account){
        clearScreen();

        Message me = (Message)chat.getListMsg().stream().filter(x -> !x.getNameUser().equals(account.getNameUser())).findFirst().orElse(null);
        String friendsName = me.getNameUser();

        System.out.println(ANSI_WHITE+"___________________________"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"           "+friendsName+"       "+ANSI_RESET);
        System.out.println(ANSI_WHITE+"___________________________"+ANSI_RESET);
        System.out.println("\n");
        
        for (Message m : chat.getListMsg()){
            if (m.getNameUser().equals(friendsName))
                System.out.println(ANSI_GREEN+m.getMsg()+ANSI_RESET);
            else
                System.out.println(ANSI_BLUE+m.getMsg()+ANSI_RESET);  
        }

        System.out.println("\n");
        System.out.println(ANSI_WHITE+"____________________________"+ANSI_RESET);
        System.out.print(ANSI_CYAN+"Enter the new message: "+ANSI_RESET+ANSI_PURPLE);
        String text = sc.nextLine();
        System.out.println(ANSI_RESET);

        Message msg = new Message(text, chat.getPath(), account.getNameUser());

        return msg;   

    }
    
}
