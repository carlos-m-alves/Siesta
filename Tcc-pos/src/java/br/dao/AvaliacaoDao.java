package br.dao;

import br.bean.Avaliacao;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class AvaliacaoDao {
    private Connection connection;
    private final String sqlInsertAvaliacao = "insert into avaliacao "
            + "(id_quarto,id_reserva,id_usuario,classificacao) values (?,?,?,?)";
    
     public AvaliacaoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
     
     public void adicionaAvaliacao(Avaliacao avaliacao) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlInsertAvaliacao);
            stmt.setInt(1, avaliacao.getIdQuarto());
            stmt.setInt(2, avaliacao.getIdReserva());
            stmt.setInt(3, avaliacao.getIdUsuario());
            stmt.setDouble(4, avaliacao.getAvaliacao());
            // executa
            stmt.execute();
            stmt.close();
            
            connection.close();
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada", PSQLState.NO_DATA);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
}
