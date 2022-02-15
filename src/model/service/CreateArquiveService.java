package model.service;

import java.io.File;
import java.io.IOException;

import model.exception.ServiceExeption;
import model.service.interfaces.CreateArquive;


public class CreateArquiveService implements CreateArquive{

    @Override
    public void createNewFolder(String path) throws ServiceExeption {
        File folder = new File(path);
        if(!folder.mkdir())
            throw new ServiceExeption("folder not created");
    }

    @Override
    public void createArquichive(String path) throws ServiceExeption {
        if (!path.substring(path.length()-4,path.length()).equals(".txt"))
            throw new ServiceExeption("Error: The file must be text");
        
        File arquive = new File(path);
        try{
            if (!arquive.createNewFile())
                throw new ServiceExeption("Arquive not created");          
        }catch (IOException e){
            System.out.println("Error: Arquive not created || "+e.getMessage());
        }
        
    } 
    
}
