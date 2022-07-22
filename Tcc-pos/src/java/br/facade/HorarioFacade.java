package br.facade;

import br.bean.Horario;
import br.dao.HorarioDao;
import java.util.List;
import org.postgresql.util.PSQLException;

public class HorarioFacade {
    public static List<Horario> getHorarioByQuartoId(int id) throws PSQLException, Exception{
        try{
            HorarioDao dao = new HorarioDao();
            return dao.getHorarioByQuartoId(id);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
}
