package application;

import java.io.File;
import java.util.Scanner;

import model.entities.Account;
import model.entities.Email;
import model.entities.Friends;
import model.entities.Chat;
import model.entities.Message;
import model.entities.Path;
import model.exception.ProgramException;
import model.exception.ServiceExeption;

import model.service.ArquiveService;
import model.service.ChatService;
import model.service.CreateArquiveService;
import model.service.interfaces.Arquive;
import model.service.interfaces.ChatS;
import model.service.interfaces.CreateArquive;

public class Application {

    // path data folder
    static final String PATH_DATA = "C:\\ChatArquive\\data";

    // arquives name
    static final String INVITATIONS_NAME = "\\invitations.txt";
    static final String FRIENDS_NAME = "\\friends.txt";
    static final String LOGINS_FILE = PATH_DATA + "\\logins.txt";
    static final String ACCOUNT_FILE = PATH_DATA + "\\accounts.txt";
    static final String ACCOUNT_QTD_FILE = PATH_DATA + "\\qtdAccount.txt";

    // folder name
    static final String CHAT_FRIENDS_PATH = "\\chatfriends";
    static final String USER_FILE = PATH_DATA + "\\user";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CreateArquive create = new CreateArquiveService();
        Arquive arquiveService = new ArquiveService();
        ChatS chatService = new ChatService();

        Account accountLogin;
        boolean esc = false;

        while (!esc) {
            try {
                checkCumin(create, (ArquiveService) arquiveService);
                int opition = UIEXIBITION.initial(sc);
                cleanBuffer(sc);

                switch (opition) {

                    case 1:
                        Account account = UIEXIBITION.register(sc, new ArquiveService(), ACCOUNT_FILE,
                                ACCOUNT_QTD_FILE);
                        account.setFolder(USER_FILE + "\\" + account.getNameUser());
                        arquiveService.WriteArquiveDontErase(account.toAccount(), ACCOUNT_FILE);
                        arquiveService.WriteArquiveDontErase(account.getEmail().toEmail(), LOGINS_FILE);
                        create.createNewFolder(account.getFolder());
                        create.createArquichive(account.getFolder() + FRIENDS_NAME);
                        create.createArquichive(account.getFolder() + INVITATIONS_NAME);
                        UIEXIBITION.actionResponse("Account successfully registered", sc);
                        break;
                    case 2:
                        Email email = UIEXIBITION.login(sc);
                        accountLogin = arquiveService.getAccounts(ACCOUNT_FILE).stream()
                                .filter(x -> x.getEmail().hashCode() == email.hashCode() && x.getEmail().equals(email))
                                .findFirst().orElse(null);
                        if (accountLogin == null) {
                            throw new ProgramException("Invalid email or password!");
                        }
                        chatService.listFullChat(accountLogin, (ArquiveService) arquiveService);
                        while (accountLogin != null) {
                            int opcUser = UIEXIBITION.UserMenu(sc, accountLogin);
                            cleanBuffer(sc);
                            switch (opcUser) {

                                case 1:
                                    long idFriends = UIEXIBITION.addFriendScreen(sc);
                                    Account friends = arquiveService.getAccounts(ACCOUNT_FILE).stream()
                                            .filter(x -> x.getId() == idFriends).findFirst().orElse(null);
                                    Friends friend = new Friends(accountLogin.getId(), accountLogin.getNameUser(),
                                            new Chat(new Path(friends.getFolder()+"\\"+accountLogin.getNameUser() + ".txt",
                                            accountLogin.getFolder() +"\\"+ friends.getNameUser() + ".txt")));

                                    arquiveService.WriteArquiveDontErase(friend.toString(),friends.getFolder() + INVITATIONS_NAME);
                                    UIEXIBITION.actionResponse("Invitation sent successfully", sc);
                                    break;
                                case 2:

                                    accountLogin.setListFriends(arquiveService.getFriends(accountLogin.getFolder() + FRIENDS_NAME));
                                    Chat chat = UIEXIBITION.listChatFrineds(sc, accountLogin.getListFriends(),(ArquiveService) arquiveService);
                                    Friends friends2 = accountLogin.getListFriends().get(chat.getPosition()); 

                                    boolean escChat = false;
                                    do {
                                        chat.setListMsg(arquiveService.getMenssages(accountLogin.getListFriends().get(chat.getPosition()).getChat().getPath().getPathSent()));
                                        Message message = UIEXIBITION.chat(sc, chat, accountLogin, friends2.getName());
                                        if (message != null){
                                            arquiveService.WriteArquiveSent(message);
                                            arquiveService.WriteArquiveDestiny(message);
                                        } else{
                                            escChat = true;
                                        }
                                    }while (!escChat);
                                    
                                    break;
                                case 3:
                                    accountLogin.setListFriends(arquiveService.getFriends(accountLogin.getFolder() + FRIENDS_NAME));
                                    accountLogin.setlistInvitations(arquiveService.getFriends(accountLogin.getFolder() + INVITATIONS_NAME));
                                    long index = UIEXIBITION.invitations(sc, accountLogin);
                                    if (index >= 0 && index < accountLogin.getListInvitations().size()) {
                                        arquiveService.eraseLine(accountLogin.getFolder()+INVITATIONS_NAME, (int)index, accountLogin.getListInvitations(), (CreateArquiveService)create);;
                                        Friends best = accountLogin.getListInvitations().get((int)index);
                                        arquiveService.WriteArquiveDontErase(best.toString(), accountLogin.getFolder() + FRIENDS_NAME);
                                        create.createArquichive(best.getChat().getPath().getPathDestiny());
                                        File f = new File (best.getChat().getPath().getPathDestiny());
                                        Friends fri = new Friends(accountLogin.getId(), accountLogin.getNameUser(), new Chat(new Path(best.getChat().getPath().getPathDestiny(),
                                        best.getChat().getPath().getPathSent())));
                                        arquiveService.WriteArquiveDontErase(fri.toString(), f.getParent()+FRIENDS_NAME);
                                        create.createArquichive(best.getChat().getPath().getPathSent());
                                        

                                    }else{
                                        UIEXIBITION.errorMessage("index typed incorrectly");
                                    }
                                    break;
                                case 4:
                                    accountLogin = null;
                                    UIEXIBITION.actionResponse("logging out...", sc);
                                    break;
                                default:
                                    UIEXIBITION.opcInvalid(sc);
                                    break;

                            }
                        }
                        break;

                    case 3:
                        esc = true;
                        UIEXIBITION.actionResponse("To the next", sc);
                        break;
                    default:
                        UIEXIBITION.opcInvalid(sc);
                        break;
                }

            } catch (ServiceExeption e) {
                UIEXIBITION.errorMessage(e.getMessage());
                UIEXIBITION.pause(sc);
            } catch (ProgramException e) {
                UIEXIBITION.errorMessage(e.getMessage());
                UIEXIBITION.pause(sc);
            } finally {
                if (esc == true)
                    sc.close();
            }

        }

    }

    private static void checkCumin(CreateArquive ca, ArquiveService as) throws ServiceExeption {

        File f = new File(PATH_DATA);
        if (!f.exists())
            ca.createNewFolder(PATH_DATA);

        f = new File(USER_FILE);
        if (!f.exists())
            ca.createNewFolder(USER_FILE);

        f = new File(LOGINS_FILE);
        if (!f.exists())
            ca.createArquichive(LOGINS_FILE);

        f = new File(ACCOUNT_FILE);
        if (!f.exists()) {
            ca.createArquichive(ACCOUNT_FILE);
        }

        f = new File(ACCOUNT_QTD_FILE);
        if (!f.exists()) {
            ca.createArquichive(ACCOUNT_QTD_FILE);
            as.WriteArquive("0", ACCOUNT_QTD_FILE);

        }

    }

    private static void cleanBuffer(Scanner sc) {
        sc.nextLine();
    }

}
