package application;

import java.util.Scanner;
import java.util.List;

import model.entities.Account;
import model.entities.Chat;
import model.entities.Email;
import model.entities.Message;
import model.entities.Path;
import model.exception.ProgramException;
import model.service.ArquiveService;


public class Application {
    
    // path data folder
    static final String PATH_DATA = "C:\\ChatArquive\\data"; 

    //arquives name
    static final String INVITATIONS_NAME = "\\convites.txt";
    static final String FRIENDS_NAME = "\\friends.txt";

    // folder name
    static final String chatFriendsPath = "\\chatfriends";

    public static void main(String[] args) throws ProgramException{ 

        Scanner sc = new Scanner(System.in);
        ArquiveService as = new ArquiveService();
        Account account = new Account(10, "Eduardo",new Email("eduardo@gamil.com", "123"),"C:\\temp");

        UIEXIBITION.chat(sc, UIEXIBITION.listChatFrineds(sc, "C:\\ChatArquive\\data\\eduardo\\friends.txt", as), account);

        //Account ac = UIEXIBITION.register(sc, as, PATH_DATA+"\\qtdAccount.txt");

        //System.out.println("\n"+ac+"\n");

        /*
        Account account = new Account(10, "Eduardo",new Email("eduardo@gamil.com", "123"),"C:\\temp");

        List<Message> list = as.getMenssages(PATH_DATA+"\\teste.txt"); 

        Chat chat = new Chat(new Path(PATH_DATA+"\\teste.txt", PATH_DATA+"\\teste.txt"));

        chat.setListMsg(list);

        UIEXIBITION.chat(sc, chat, account);
        */

/*
        int id = UIEXIBITION.addFriendScreen(sc);
        System.out.println(id);*/

        //int option = UIEXIBITION.UserMenu(sc, account);

        //System.out.println(option);

        //Email email = UIEXIBITION.login(sc);

        //System.out.println(email);
    }
}
