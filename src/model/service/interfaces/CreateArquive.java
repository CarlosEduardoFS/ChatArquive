package model.service.interfaces;

import model.exception.ServiceExeption;

public interface CreateArquive {

    public void createNewFolder(String path) throws ServiceExeption;
    public void createArquichive(String path) throws ServiceExeption; 
}
