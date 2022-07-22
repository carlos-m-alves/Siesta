package br.dao;

import br.bean.Bairro;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BairroDao {
    private Connection connection;
    private final String sqlSelectTodos = "SELECT * FROM bairro";   
    
    public BairroDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public List<Bairro> getBairros() {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectTodos);
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Bairro> listaBairros = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Bairro bairro = new Bairro();
                bairro.setIdBairro(rs.getInt("id_bairro")); 
                bairro.setDescricao(rs.getString("descricao")); 
              
                listaBairros.add(bairro);
            }

            stmt.close();
            connection.close();
            return listaBairros;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
