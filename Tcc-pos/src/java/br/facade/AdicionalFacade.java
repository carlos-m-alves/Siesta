package br.facade;

import br.bean.Adicional;
import br.bean.Quarto;
import br.dao.AdicionalDao;
import java.util.List;
import org.postgresql.util.PSQLException;

public class AdicionalFacade {
    public static List<Adicional> getItensAdicionais() throws PSQLException, Exception{
        try{
            AdicionalDao dao = new AdicionalDao();
            return dao.getItensAdicionais();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static void adicionaQuartoEItemAdicional(Quarto quarto) throws PSQLException, Exception{
        try{
            AdicionalDao dao = new AdicionalDao();
            dao.adicionaQuartoEItemAdicional(quarto);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade Adicionais. "+e);
            throw new Exception();
        }
    }
    
    public static void deletaParceiro(int idQuarto) throws PSQLException, Exception{
        try{
            AdicionalDao dao = new AdicionalDao();
            dao.deletaParceiro(idQuarto);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade Adicionais. "+e);
            throw new Exception();
        }
    }
}
