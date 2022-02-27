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

    public static void pause(Scanner sc){
        System.out.print("\n\nPress enter to continue...");
        sc.nextLine();
    }

    public static void opcInvalid(Scanner sc){
        clearScreen();
        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_RED+"      OPTIONS INVALID    "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|"+ANSI_RESET);

        pause(sc);

    }

    public static int initial (Scanner sc){
        clearScreen();

        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"                         "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  1. Registrer           "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  2. Login               "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  3. Encerrar            "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|"+ANSI_RESET);
        
        System.out.print(ANSI_CYAN+"\n Enter an option: "+ANSI_RESET);
        int option = sc.nextInt();
        return option;

    }

    public static Account register(Scanner sc, ArquiveService as, String path, String pathId){
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

        long id = Long.parseLong(as.getValue(pathId).getMsg());
        id++;
        as.WriteArquive(Long.toString(id), pathId);

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
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  3. invitations         "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"  4. Sair                "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|"+ANSI_RESET);
        
        System.out.print(ANSI_CYAN+"\n Enter an option: "+ANSI_RESET);
        int option = sc.nextInt();
        return option;

    }

    public static long addFriendScreen (Scanner sc){
        clearScreen();

        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"        ADD FRIEND       "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|"+ANSI_RESET);

        System.out.print("\n Enter the friend's ID: ");
        long id = sc.nextLong();
        return id;
    }

    public static Chat listChatFrineds(Scanner sc, List<Friends> list, ArquiveService as) throws ProgramException{
        if (sc == null || as == null)
            throw new ProgramException("No parameter can be null!");

        clearScreen();
        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"         FRIENDS         "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|\n"+ANSI_RESET);
        
        for (int i = 0; i < list.size(); i++){
            int j = i + 1;
            Friends f = list.get(i);
            System.out.println(ANSI_CYAN +j+". name: "+f.getName()+", id: "+f.getId()+ ANSI_RESET);
        }

        System.out.print(ANSI_CYAN +"\n Which chat do you want to open: "+ ANSI_RESET);
        int option = sc.nextInt();
        sc.nextLine();

        if (option > list.size() || option < 0)
            throw new ProgramException("Error: Invalid typed chat!");

        Chat chat = list.get(option-1).getChat();
        chat.setListMsg(as.getMenssages(list.get(option-1).getChat().getPath().getPathSent()));
        chat.setPosition(option-1);
          
        return chat;

    }

    public static Message chat (Scanner sc, Chat chat, Account account, String friendsName) throws ProgramException{
        clearScreen();

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
        System.out.print(ANSI_CYAN+"to exit type: //* "+ANSI_RESET+ANSI_PURPLE);
        System.out.print(ANSI_CYAN+"\nEnter the new message: "+ANSI_RESET+ANSI_PURPLE);
        String text = sc.nextLine();
        System.out.println(ANSI_RESET);

        Message msg;
        if (text.equals("//*")){
            msg = null;
        }else{
           msg = new Message(text, chat.getPath(), account.getNameUser());
        }

       

        return msg;   

    }

    public static long invitations (Scanner sc, Account account) throws ProgramException{
        clearScreen();

        System.out.println(ANSI_WHITE+" _________________________"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|"+ANSI_RESET+ANSI_CYAN+"       Invitations       "+ANSI_RESET+ANSI_WHITE+"|"+ANSI_RESET);
        System.out.println(ANSI_WHITE+"|_________________________|\n"+ANSI_RESET);
        int i = 0;
        for (Friends m : account.getListInvitations()){
            System.out.println(ANSI_GREEN+(++i)+". "+m.getName()+"| id: "+m.getId()+ANSI_RESET);    
        }

        System.out.println("\n");
        System.out.println(ANSI_WHITE+"____________________________"+ANSI_RESET);
        System.out.print(ANSI_CYAN+"Enter the position: "+ANSI_RESET+ANSI_PURPLE);
        long opc = sc.nextLong();
        System.out.println(ANSI_RESET);

        return opc-1;   

    }

    public static void errorMessage (String msg){
        clearScreen();
        System.out.println(ANSI_RED+msg+ANSI_RESET);
    }

    public static void actionResponse (String msg, Scanner sc){
        clearScreen();
        System.out.println(ANSI_CYAN+msg+ANSI_RESET);
        pause(sc);
    }
    
}
