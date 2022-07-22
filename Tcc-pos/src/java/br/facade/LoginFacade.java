package br.facade;

import br.bean.Login;
import br.dao.LoginDao;

public class LoginFacade {
    public static Login verificaUsuario(Login l) throws Exception{
        try{
            LoginDao dao = new LoginDao();
            return dao.getUsuarioByEmailAndPassword(l);  
        }catch(Exception e){
            throw new Exception();
        }
    }
    
     public static int getId(String email) throws Exception{
        try{
            LoginDao dao = new LoginDao();
            return dao.getId(email);  
        }catch(Exception e){
            throw new Exception();
        }
    }
}
