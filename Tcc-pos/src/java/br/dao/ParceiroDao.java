package br.dao;

import br.bean.Parceiro;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ParceiroDao {
    private Connection connection;
    private final String sqlCountAllParceiros = "select count(*) as qtd from pessoa t1 " +
                        "inner join parceiro t2 " +
                        "on t1.id_pessoa = t2.id_parceiro WHERE t1.ativo<>false";
    private final String sqlSelectAll = "select t1.*,t2.* from pessoa t1 " +
                        "inner join parceiro t2 on t1.id_pessoa = t2.id_parceiro " +
                        "WHERE t1.ativo<>false LIMIT ? OFFSET ?";
    private final String sqlSelectAllParceiros = "select t1.*,t2.* from pessoa t1 " +
                        "inner join parceiro t2 on t1.id_pessoa = t2.id_parceiro WHERE t1.ativo<>false";

    public ParceiroDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public List<Parceiro> getParceiros(int currentPage, int recordsPerPage) {
        try{            
            int start = currentPage * recordsPerPage - recordsPerPage;
            PreparedStatement stmt = connection.prepareStatement(sqlSelectAll);
            stmt.setInt(1, recordsPerPage);
            stmt.setInt(2, start);
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Parceiro> listaParceiros = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Parceiro parceiro = new Parceiro();
                parceiro.setId(rs.getInt("id_pessoa")); 
                parceiro.setNome(rs.getString("nome")); 
                parceiro.setDatanascimento(rs.getDate("data_nascimento"));   
                parceiro.setEmail(rs.getString("email"));  
                //parceiro.setNomeHospedagem(rs.getString("nome_hospedagem")); 
                listaParceiros.add(parceiro);
            }
            
            stmt.close();
            connection.close();
            return listaParceiros;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Parceiro> getParceiros() {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectAllParceiros);
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Parceiro> listaParceiros = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Parceiro parceiro = new Parceiro();
                parceiro.setId(rs.getInt("id_pessoa")); 
                parceiro.setNome(rs.getString("nome")); 
                parceiro.setDatanascimento(rs.getDate("data_nascimento"));   
                parceiro.setEmail(rs.getString("email"));  
                listaParceiros.add(parceiro);
            }
            
            stmt.close();
            connection.close();
            return listaParceiros;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public int quantidadeTotalParceiros() {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlCountAllParceiros);
            int quantidadeTotal = 0;
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                quantidadeTotal = rs.getInt("qtd"); 
            }
            
            stmt.close();
            connection.close();
            return quantidadeTotal;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
