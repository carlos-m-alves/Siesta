package br.facade;

import br.bean.Parceiro;
import br.bean.Usuario;
import br.dao.UsuarioDao;
import org.postgresql.util.PSQLException;

public class UsuarioFacade {
    public static Usuario verificaUsuario(Usuario user) throws Exception{
        try{
            UsuarioDao dao = new UsuarioDao();
            return dao.getUsuarioByEmailAndPassword(user);  
        }catch(Exception e){
            throw new Exception();
        }
    }
    
    public static void adicionarUsuario(Usuario user) throws Exception{
        try{
            UsuarioDao dao = new UsuarioDao();
            dao.adicionarUsuario(user);  
        }catch(Exception e){
            throw new Exception();
        }
    }
    
    public static void adicionarParceiro(Usuario user) throws Exception{
        try{
            UsuarioDao dao = new UsuarioDao();
            dao.adicionarParceiro(user);  
        }catch(Exception e){
            throw new Exception();
        }
    }
    
    public static Parceiro getParceiro(int idParceiro) throws Exception{
        try{
            UsuarioDao dao = new UsuarioDao();
            return dao.getParceiro(idParceiro);  
        }catch(Exception e){
            throw new Exception();
        }
    }
    
    public static void deletaParceiro(int idParceiro) throws PSQLException, Exception{
        try{
            UsuarioDao dao = new UsuarioDao();
            dao.deletaParceiro(idParceiro);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
     
    public static void atualizaParceiro(Parceiro parceiro) throws PSQLException, Exception{
        try{
            UsuarioDao dao = new UsuarioDao();
            dao.atualizaParceiro(parceiro);        
        }catch(RuntimeException e){
            System.out.println("erro facade atualizar Parceiro runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static boolean procuraEmail(String email) throws PSQLException, Exception{
        try{
            UsuarioDao dao = new UsuarioDao();
            return dao.procuraEmail(email);        
        }catch(RuntimeException e){
            System.out.println("erro facade pesquisar email runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
     
}
