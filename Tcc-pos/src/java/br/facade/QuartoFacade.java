package br.facade;

import br.bean.Avaliacao;
import br.bean.Quarto;
import br.dao.HospedagemDao;
import br.dao.QuartoDao;
import java.util.List;
import org.postgresql.util.PSQLException;

public class QuartoFacade {
    public static List<Quarto> getQuartosByIdHotels(int id, int currentPage, int recordsPerPage, int consulta) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            return dao.getQuartosByIdHotels(id, currentPage,recordsPerPage, consulta);        
        }catch(RuntimeException e){
            System.out.println("erro facade QUARTO runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade QUARTO. "+e);
            throw new Exception();
        }
    }
    
    public static int quantidadeTotalQuartosPorHotel(int id, int consulta) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            return dao.getQuantidadeTotalHotel(id, consulta);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static Quarto getQuartoById(int id) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            return dao.getQuartoById(id);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static int adicionaQuarto(Quarto quarto) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            return dao.adicionaQuarto(quarto);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static Quarto getQuartoByIdWithComp(int id) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            return dao.getQuartoByIdWithComp(id);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static String getQuartoByIdAndCep(int id) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            return dao.getQuartoByIdAndCep(id);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    public static Avaliacao getAvaliacao(int id) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            return dao.getAvaliacao(id);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    public static void deleteQuarto(int id) throws PSQLException, Exception{
        try{
            QuartoDao dao = new QuartoDao();
            dao.deleteQuarto(id);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
}
