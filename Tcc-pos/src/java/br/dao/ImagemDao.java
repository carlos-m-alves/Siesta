package br.dao;

import br.bean.Adicional;
import br.bean.Quarto;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class ImagemDao {
    // a conexão com o banco de dados
    private Connection connection;
    private final String sqlInsertImagem = "insert into imagem (id_quarto, id_firebase) values(?,?);";
    
    public ImagemDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adicionaImagemQuarto(Quarto quarto) throws PSQLException, Exception{        
        try {  
            // prepared statement para inserção
            for( String imagem : quarto.getListaImagemFirebase()){
                PreparedStatement stmt = connection.prepareStatement(sqlInsertImagem);
                stmt.setInt(1, quarto.getId());
                stmt.setString(2, imagem);
                // executa
                stmt.executeUpdate();
                stmt.close();
            }
            
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
