package application;

import java.io.IOException;
import java.util.List;

import model.entities.Message;
import model.entities.Path;
import model.exception.ServiceExeption;
import model.service.ArquiveService;
import model.service.CreateArquiveService;
import model.service.interfaces.CreateArquive;

public class Application {
    
    static final String PATH_DATA = "C:\\ChatArquive\\data"; 

    public static void main(String[] args) throws IOException, ServiceExeption, InterruptedException{ 

        CreateArquive ca = new CreateArquiveService();
        ArquiveService as = new ArquiveService();
        

        String teste = PATH_DATA+"\\teste.txt";
        Message msg = new Message("Teste mensagem", new Path(teste, teste), "Eduardo");

        ca.createArquichive(msg.getPath().getPathSent());
        
        as.WriteArquiveDestiny(msg);

        //Thread.sleep(61000);
        
        Message msg2 = new Message("Teste 2", new Path(teste, teste), "Lucas");
        as.WriteArquiveDestiny(msg2);
        List<Message> list = as.getMenssages(msg.getPath().getPathSent());

        System.out.println("\n\n");
        for (Message m : list)
            System.out.println(m.toMessage());
        
        System.out.println("\n\n");
    }
}
