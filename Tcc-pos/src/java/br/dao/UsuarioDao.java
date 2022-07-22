package br.dao;

import br.bean.Hospedagem;
import br.bean.Parceiro;
import br.bean.Usuario;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class UsuarioDao {
    private Connection connection;
    
    private final String sqlSelectByEmailAndPassword = "select * from usuario"
            + " WHERE email = ? AND senha=?;";
    private final String sqlSelectParceiroById = "select t1.*,t2.* from pessoa t1 " +
            "inner join parceiro t2 on t1.id_pessoa = t2.id_parceiro " +
            "WHERE t1.id_pessoa = ?";
    private final String sqlInsertPessoa = "INSERT INTO pessoa (nome, cpf, data_nascimento, email," +
            "senha,tipo,ativo ) VALUES (?,?,?,?,?,?,?)";
    private final String sqlInsertParceiro = "INSERT INTO parceiro (id_parceiro) VALUES (?)";
    private final String sqlDeletaParceiro = "update pessoa set ativo=? WHERE id_pessoa=?";
    private final String sqlUpdateParceiro = "UPDATE pessoa SET nome=?,data_nascimento=?,cpf=? WHERE id_pessoa = ?";
    private final String sqlInsertUsuario = "INSERT INTO usuario (id_usuario) VALUES (?)";
    private final String sqlPesquisaEmail = "select * from pessoa where email=?";        
            
    public UsuarioDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public Usuario getUsuarioByEmailAndPassword(Usuario usuario) throws Exception{
        // pega a conexão e o Statement
        try{
            Usuario cliente = null;
            //System.out.println("dentro do dao: "+usuario.getSenha());
            PreparedStatement stmt = connection.prepareStatement(sqlSelectByEmailAndPassword);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            // executa um select
            ResultSet rs = stmt.executeQuery();
            if ( rs.next() ){
                cliente = new Usuario();
                cliente.setId(rs.getInt("id")); 
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));  
                cliente.setTipo(rs.getString("tipo").charAt(0));
            }
            
            stmt.close();
            connection.close();
            return cliente;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }
    
    public void adicionarParceiro(Usuario usuario) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlInsertPessoa, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, new java.sql.Date(usuario.getDatanascimento().getTime()));
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, String.valueOf(usuario.getTipo()));
            stmt.setBoolean(7, true);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert user failed, no rows affected.");
            }
            Long id;
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //System.out.println("ID do QUARTO inserido: "+generatedKeys.getLong(1));
                    id = generatedKeys.getLong(1);
                    //stmt.close();
                    //connection.close();                   
                }
                else {
                    throw new SQLException("Insert partner failed, no ID obtained.");
                }
            }  
            
            //insere endereco
            stmt = connection.prepareStatement(sqlInsertParceiro);
            stmt.setInt(1, id.intValue());
            
            System.out.println("sql do insert: "+stmt.toString());
            
            // executa
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (PSQLException e) {
            System.out.println("erro dentro no insert! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada", PSQLState.NO_DATA);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public Parceiro getParceiro(int idParceiro) throws Exception{
        // pega a conexão e o Statement
        try{
            Parceiro parceiro = null;
            //System.out.println("dentro do dao: "+usuario.getSenha());
            PreparedStatement stmt = connection.prepareStatement(sqlSelectParceiroById);
            stmt.setInt(1, idParceiro);
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            // executa um select
            ResultSet rs = stmt.executeQuery();
            if ( rs.next() ){
                parceiro = new Parceiro();
                parceiro.setId(rs.getInt("id_pessoa")); 
                parceiro.setNome(rs.getString("nome"));
                parceiro.setDatanascimento(rs.getDate("data_nascimento"));
                parceiro.setCpf(rs.getString("cpf"));
                parceiro.setEmail(rs.getString("email"));  
                parceiro.setTipo(rs.getString("tipo").charAt(0));
            }
            
            stmt.close();
            connection.close();
            return parceiro;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }
    
    public void deletaParceiro(int idParceiro) {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlDeletaParceiro);
            stmt.setBoolean(1, false);
            stmt.setInt(2, idParceiro);
            
            stmt.executeUpdate();            
            stmt.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void atualizaParceiro(Parceiro parceiro) {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlUpdateParceiro);
            stmt.setString(1, parceiro.getNome());            
            stmt.setDate(2, new java.sql.Date(parceiro.getDatanascimento().getTime()));
            stmt.setString(3, parceiro.getCpf());
            stmt.setInt(4, parceiro.getId());
            
            System.out.println("sql atualiza parc: "+stmt.toString());
            stmt.executeUpdate();            
            stmt.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void adicionarUsuario(Usuario usuario) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlInsertPessoa, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setDate(3, new java.sql.Date(usuario.getDatanascimento().getTime()));
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.setString(6, String.valueOf(usuario.getTipo()));
            stmt.setBoolean(7, true);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert user failed, no rows affected.");
            }
            Long id;
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //System.out.println("ID do QUARTO inserido: "+generatedKeys.getLong(1));
                    id = generatedKeys.getLong(1);
                    //stmt.close();
                    //connection.close();                   
                }
                else {
                    throw new SQLException("Insert user failed, no ID obtained.");
                }
            }  
            
            //insere endereco
            stmt = connection.prepareStatement(sqlInsertUsuario);
            stmt.setInt(1, id.intValue());
            
            System.out.println("sql do insert: "+stmt.toString());
            
            // executa
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (PSQLException e) {
            System.out.println("erro dentro no insert! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada", PSQLState.NO_DATA);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public boolean procuraEmail(String email) throws Exception{
        // pega a conexão e o Statement
        try{
            boolean encontrou = false;
            //System.out.println("dentro do dao: "+usuario.getSenha());
            PreparedStatement stmt = connection.prepareStatement(sqlPesquisaEmail);
            stmt.setString(1, email);
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            // executa um select
            ResultSet rs = stmt.executeQuery();
            if ( rs.next() ){
                encontrou = true; 
            }
            //ystem.out.println("encontrou? "+encontrou);
            stmt.close();
            connection.close();
            return encontrou;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
