package br.facade;

import br.bean.Bairro;
import br.bean.Hospedagem;
import br.bean.Parceiro;
import br.dao.BairroDao;
import br.dao.HospedagemDao;
import br.dao.ParceiroDao;
import java.util.List;
import org.postgresql.util.PSQLException;

public class AdministradorFacade {
    public static List<Hospedagem> getHospedagens(int currentPage, int recordsPerPage) throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            return dao.getHospedagens(currentPage, recordsPerPage);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static int quantidadeTotalHotel() throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            return dao.getQuantidadeTotalHospedagens();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Parceiro> getParceiros(int currentPage, int recordsPerPage) throws PSQLException, Exception{
        try{
            ParceiroDao dao = new ParceiroDao();
            return dao.getParceiros(currentPage, recordsPerPage);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Parceiro> getParceiros() throws PSQLException, Exception{
        try{
            ParceiroDao dao = new ParceiroDao();
            return dao.getParceiros();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static int quantidadeTotalParceiros() throws PSQLException, Exception{
        try{
            ParceiroDao dao = new ParceiroDao();
            return dao.quantidadeTotalParceiros();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Bairro> getBairros() throws PSQLException, Exception{
        try{
            BairroDao dao = new BairroDao();
            return dao.getBairros();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Hospedagem> getHospedagensPorBairro(int currentPage, int recordsPerPage, String bairro) throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            return dao.getHospedagensPorBairro(currentPage, recordsPerPage, bairro);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
}
