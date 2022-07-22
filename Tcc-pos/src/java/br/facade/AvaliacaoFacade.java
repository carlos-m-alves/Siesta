package br.facade;

import br.bean.Avaliacao;
import br.dao.AvaliacaoDao;
import org.postgresql.util.PSQLException;

public class AvaliacaoFacade {
    public static void adicionaAvaliacao(Avaliacao avaliacao) throws PSQLException, Exception{
        try{
            AvaliacaoDao dao = new AvaliacaoDao();
            dao.adicionaAvaliacao(avaliacao);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade Adicionais. "+e);
            throw new Exception();
        }
    }
}
