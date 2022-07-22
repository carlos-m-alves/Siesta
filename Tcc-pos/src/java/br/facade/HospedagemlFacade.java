package br.facade;

import br.bean.Hospedagem;
import br.dao.HospedagemDao;
import java.util.List;
import org.postgresql.util.PSQLException;

public class HospedagemlFacade {
    public static void adicionarHospedagem(Hospedagem hospedagem) throws Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            dao.adicionarHospedagem(hospedagem);
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static void atualizaHospedagem(Hospedagem hospedagem) throws Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            dao.atualizaHospedagem(hospedagem);
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Hospedagem> getTodasHospedagens() throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            return dao.getTodasHospedagens();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Hospedagem> getHospedagens(int currentPage, int recordsPerPage, int idHospedagem) throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            return dao.getHospedagens(currentPage,recordsPerPage,idHospedagem);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }        
    
    public static int quantidadeTotalHospedagens() throws PSQLException, Exception{
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
    
    public static int quantidadeTotalHospedagensPorBairro(String bairro) throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            return dao.getQuantidadeTotalHospedagensPorBairro(bairro);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static void deletaHospedagem(int idHospedagem) throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            dao.deletaHospedagem(idHospedagem);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static Hospedagem getHospedagemById(int idHospedagem) throws PSQLException, Exception{
        try{
            HospedagemDao dao = new HospedagemDao();
            return dao.getHospedagemById(idHospedagem);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
}
