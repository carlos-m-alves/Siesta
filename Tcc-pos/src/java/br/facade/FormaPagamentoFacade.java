package br.facade;

import br.bean.FormaPagamento;
import br.dao.FormaPagamentosDao;
import java.util.List;
import org.postgresql.util.PSQLException;

public class FormaPagamentoFacade {
    public static List<FormaPagamento> getFormasPagamentos() throws PSQLException, Exception{
        try{
            FormaPagamentosDao dao = new FormaPagamentosDao();
            return dao.getFormasPagamentos();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
}
