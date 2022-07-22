package br.facade;

import br.bean.Relatorio;
import br.bean.Reserva;
import br.dao.ReservaDao;
import java.util.Date;
import java.util.List;
import org.postgresql.util.PSQLException;

public class ReservaFacade {
    public static Reserva getReserva(int idQuarto, int idHorario) throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            return dao.getReserva(idQuarto,idHorario);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static Reserva realizarCheckIn(int idQuarto, int idHorario) throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            return dao.realizarCheckIn(idQuarto,idHorario);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static void fecharPagamento(int idFormaPagamento, int idReserva, Double preco) throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            dao.fecharPagamento(idFormaPagamento, idReserva, preco);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static void fecharReserva(int idReserva) throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            dao.fecharReserva(idReserva);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static void quartoOcupado(String disponibilidade, int idQuarto, int idHorario, int idHotel, Date dtReservada) 
            throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            dao.quartoOcupado(disponibilidade,idQuarto, idHorario, idHotel, dtReservada);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    public static void reservarQuarto(Reserva reserva) 
            throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            dao.reservarQuarto(reserva);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Relatorio> getLucroPorMes() throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            return dao.getLucroPorMes();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Relatorio> getQtdHospedagens() throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            return dao.getQtdHospedagens();        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Reserva> getReservasPorIdUsuario(int recordsPerPage, int currentPage, int idUsuario) throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            return dao.getReservasPorIdUsuario(recordsPerPage,currentPage,idUsuario);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static int getQtdReservasPorIdUsuario(int idUsuario) throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            return dao.getQtdReservasPorIdUsuario(idUsuario);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
    
    public static List<Reserva> getHorarioReservasPorQuarto(int idQuarto, Date data) throws PSQLException, Exception{
        try{
            ReservaDao dao = new ReservaDao();
            return dao.getHorarioReservasPorQuarto(idQuarto,data);        
        }catch(RuntimeException e){
            System.out.println("erro facade runtime. "+e);
            throw new RuntimeException();
        }catch(Exception e){
            System.out.println("erro geral facade. "+e);
            throw new Exception();
        }
    }
}
