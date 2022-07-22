package br.dao;

import br.bean.Adicional;
import br.bean.Quarto;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class AdicionalDao {

    // a conexão com o banco de dados
    private Connection connection;
    private final String sqlAllItensAdicionais = "select * from adicional;";
    private final String sqlInsertQuartoItemAdicional = "insert into quarto_adicional (id_quarto,id_adicional) " +
                                "values (?,?);";   
    private final String sqlRemoveAdicional = "DELETE FROM quarto_adicional WHERE id_quarto = ?";
    
    public AdicionalDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public List<Adicional> getItensAdicionais() {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlAllItensAdicionais);
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Adicional> listaAdicionais = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Adicional adicional = new Adicional();
                adicional.setId(rs.getInt("id")); 
                adicional.setDescricao(rs.getString("descricao")); 
              
                listaAdicionais.add(adicional);
            }

            stmt.close();
            connection.close();
            return listaAdicionais;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void adicionaQuartoEItemAdicional(Quarto quarto) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            for( Adicional adicional : quarto.getListaAdicionais()){
                PreparedStatement stmt = connection.prepareStatement(sqlInsertQuartoItemAdicional);
                stmt.setInt(1, quarto.getId());
                stmt.setInt(2, adicional.getId());
                //System.out.println("sql add item: ");
                //System.out.println(stmt.toString()); //visualizar a query de consulta
                // executa
                stmt.execute();
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
    
    public void deletaParceiro(int idQuarto) {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlRemoveAdicional);
            stmt.setInt(1, idQuarto);
            //System.out.println("sql del item: ");
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            stmt.executeUpdate();            
            stmt.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
