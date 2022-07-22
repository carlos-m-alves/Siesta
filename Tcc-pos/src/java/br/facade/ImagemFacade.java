package br.facade;

import br.bean.Quarto;
import br.dao.AdicionalDao;
import br.dao.ImagemDao;
import org.postgresql.util.PSQLException;

public class ImagemFacade {
    public static void adicionaImagemQuarto(Quarto quarto) throws PSQLException, Exception{
        try{
            ImagemDao dao = new ImagemDao();
            dao.adicionaImagemQuarto(quarto);        
        }catch(RuntimeException e){
            System.out.println("erro facade IMAGEM runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade IMAGEM. "+e);
            throw new Exception();
        }
    }
}
