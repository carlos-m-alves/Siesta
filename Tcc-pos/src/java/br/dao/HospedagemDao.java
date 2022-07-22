package br.dao;

import br.bean.Hospedagem;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class HospedagemDao {
    // a conexão com o banco de dados
    private Connection connection;
    private final String sqlSelectAll = "select * from hospedagem where ativo <> false";
    private final String sqlSelectPagination = "select * from hospedagem WHERE id = ? AND ativo <> false LIMIT ? OFFSET ?";
    private final String sqlSelectPaginationAdm = "select * from hospedagem t1 " +
            "inner join endereco t2 " +
            "on t1.id_endereco = t2.id_endereco "+
            "inner join bairro t3 on t2.id_bairro = t3.id_bairro " +
            "where t1.ativo = true AND ativo <> false LIMIT ? OFFSET ? ";
    private final String sqlCountAllHospedagens = "select count(*) as qtd from hospedagem where ativo <> false";
    private final String sqlCountAllHospedagensPorBairro = "select count(*) as qtd from hospedagem t1 " +
            "inner join endereco t2 " +
            "on t1.id_endereco = t2.id_endereco " +
            "inner join bairro t3 on t2.id_bairro = t3.id_bairro " +
            "where t1.ativo = true AND ativo <> false AND UPPER(t3.descricao) LIKE UPPER(?)";
    private final String sqlSelectAllById = "select * from hospedagem where id = ?";
    private final String sqlInsertHospedagem = "insert into hospedagem " +
             "(nome, ativo, id_parceiro, id_endereco) values (?,?,?,?)";
    private final String sqlInsertEndereco = "insert into endereco " +
             "(rua,numero,complemento,id_bairro,cep) values (?,?,?,?,?)";
    private final String sqlUpdateHospedagem = "update hospedagem " +
             " set nome=? WHERE id_hospedagem=?";
    private final String sqlUpdateEndereco = "update endereco " +
             "set rua=?,numero=?,complemento=?,id_bairro=?,cep=? WHERE id_endereco=?";
    private final String sqlDeletaHospedagem = "update hospedagem set ativo=? WHERE id_hospedagem=?";
    private final String sqlHospedagemById = "select * from hospedagem t1 " +
            "inner join endereco t2 " +
            "on t1.id_endereco = t2.id_endereco " +
            "WHERE t1.id_hospedagem=?";    
    private final String sqlHospedagemPorBairro = "select t1.id_hospedagem, t1.nome, t2.rua, t3.descricao from hospedagem t1 " +
            "inner join endereco t2 " +
            "on t1.id_endereco = t2.id_endereco " +
            "inner join bairro t3 on t2.id_bairro = t3.id_bairro " +
            "where t1.ativo = true AND ativo <> false AND UPPER(t3.descricao) LIKE UPPER(?) LIMIT ? OFFSET ? ";
    
    public HospedagemDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adicionarHospedagem(Hospedagem hospedagem) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlInsertEndereco, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, hospedagem.getRua());
            stmt.setInt(2, hospedagem.getNumero());
            stmt.setString(3, hospedagem.getComplemento());
            stmt.setInt(4, hospedagem.getIdBairro());
            stmt.setString(5, hospedagem.getCep());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert hotel failed, no rows affected.");
            }
            Long id;
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong(1);
                    //stmt.close();
                    //connection.close();                   
                }
                else {
                    throw new SQLException("Insert hotel failed, no ID obtained.");
                }
            }  
            
            //insere endereco
            stmt = connection.prepareStatement(sqlInsertHospedagem);
            stmt.setString(1, hospedagem.getNome());
            stmt.setBoolean(2, true);
            stmt.setInt(3, hospedagem.getIdParceiro());
            stmt.setInt(4, id.intValue());
            
            //System.out.println("sql do insert do hotel: "+stmt.toString());
            // executa
            stmt.executeUpdate();
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
    
    public List<Hospedagem> getTodasHospedagens() throws PSQLException {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlSelectAll);
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Hospedagem> listaHospedagens = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setId(rs.getInt("id")); 
                hospedagem.setNome(rs.getString("nome")); 
                listaHospedagens.add(hospedagem);
            }
            
            stmt.close();
            connection.close();
            return listaHospedagens;
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada",PSQLState.DATA_ERROR);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public List<Hospedagem> getHospedagens(int currentPage, int recordsPerPage, int idHospedagens) throws PSQLException {
        try{            
            int start = currentPage * recordsPerPage - recordsPerPage;
            //System.out.println("start: "+start);
            PreparedStatement stmt = connection.prepareStatement(sqlSelectPagination);
            stmt.setInt(1, idHospedagens);
            stmt.setInt(2, recordsPerPage);            
            //stmt.setInt(1, recordsPerPage);
            stmt.setInt(3, start);
            
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Hospedagem> listaHospedagens = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setId(rs.getInt("id")); 
                hospedagem.setNome(rs.getString("nome")); 
                hospedagem.setBairro(rs.getString("bairro"));   
                hospedagem.setDtCadastro(rs.getDate("dt_cadastro")); 
                hospedagem.setDtRevisao(rs.getDate("dt_revisao")); 
                listaHospedagens.add(hospedagem);
            }
            
            stmt.close();
            connection.close();
            return listaHospedagens;
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada",PSQLState.DATA_ERROR);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public List<Hospedagem> getHospedagens(int currentPage, int recordsPerPage) throws PSQLException {
        try{            
            int start = currentPage * recordsPerPage - recordsPerPage;
            //System.out.println("start: "+start);
            PreparedStatement stmt = connection.prepareStatement(sqlSelectPaginationAdm);
            stmt.setInt(1, recordsPerPage);            
            //stmt.setInt(1, recordsPerPage);
            stmt.setInt(2, start);
            
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Hospedagem> listaHospedagens = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setId(rs.getInt("id_hospedagem")); 
                hospedagem.setNome(rs.getString("nome")); 
                hospedagem.setRua(rs.getString("rua")); 
                hospedagem.setBairro(rs.getString("descricao"));   
                listaHospedagens.add(hospedagem);
            }
            
            stmt.close();
            connection.close();
            return listaHospedagens;
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada",PSQLState.DATA_ERROR);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public int getQuantidadeTotalHospedagens() {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlCountAllHospedagens);
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
    
    public int getQuantidadeTotalHospedagensPorBairro(String bairro) {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlCountAllHospedagensPorBairro);
            stmt.setString(1, "%"+bairro+"%");            
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
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
    
    public void deletaHospedagem(int idHospedagem) throws PSQLException {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlDeletaHospedagem);
            stmt.setBoolean(1, false);
            stmt.setInt(2, idHospedagem);
            
            stmt.executeUpdate();            
            stmt.close();
            connection.close();
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("Erro sql.",PSQLState.DATA_ERROR);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public Hospedagem getHospedagemById(int idHospedagem) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlHospedagemById);
            stmt.setInt(1, idHospedagem);                       
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            Hospedagem hospedagem = new Hospedagem();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                hospedagem.setId(rs.getInt("id_hospedagem"));                 
                hospedagem.setNome(rs.getString("nome")); 
                hospedagem.setIdEndereco(rs.getInt("id_endereco"));   
                hospedagem.setRua(rs.getString("rua")); 
                hospedagem.setNumero(rs.getInt("numero"));   
                hospedagem.setIdBairro(rs.getInt("id_bairro"));   
                hospedagem.setComplemento(rs.getString("complemento"));  
                hospedagem.setCep(rs.getString("cep"));  
            }
            
            stmt.close();
            connection.close();
            return hospedagem;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public void atualizaHospedagem(Hospedagem hospedagem) throws PSQLException {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlUpdateHospedagem);
            stmt.setString(1, hospedagem.getNome());
            stmt.setInt(2, hospedagem.getId());
            
            stmt.executeUpdate();    
            
            stmt = connection.prepareStatement(sqlUpdateEndereco);
            stmt.setString(1, hospedagem.getRua());
            stmt.setInt(2, hospedagem.getNumero());
            stmt.setString(3, hospedagem.getComplemento());
            stmt.setInt(4, hospedagem.getIdBairro());
            stmt.setString(5, hospedagem.getCep());
            stmt.setInt(6, hospedagem.getIdEndereco());
            
            stmt.executeUpdate();    
            stmt.close();
            connection.close();
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("Erro sql",PSQLState.DATA_ERROR);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public List<Hospedagem> getHospedagensPorBairro(int currentPage, int recordsPerPage, String bairro) throws PSQLException {
        try{            
            int start = currentPage * recordsPerPage - recordsPerPage;
            //System.out.println("start: "+start);
            PreparedStatement stmt = connection.prepareStatement(sqlHospedagemPorBairro);
            stmt.setString(1, "%"+bairro+"%");            
            stmt.setInt(2, recordsPerPage);
            stmt.setInt(3, start);  
            //stmt.setInt(1, recordsPerPage);
            //stmt.setInt(2, start);  
            
            //System.out.println("nome bairro SQL: "+bairro);
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Hospedagem> listaHospedagens = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                //System.out.println("entrou no laco??");
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setId(rs.getInt("id_hospedagem")); 
                //System.out.println("oq tem aqui?? "+rs.getInt("id_hospedagem"));
                hospedagem.setNome(rs.getString("nome")); 
                hospedagem.setRua(rs.getString("rua")); 
                hospedagem.setBairro(rs.getString("descricao"));   
                listaHospedagens.add(hospedagem);
            }
            
            stmt.close();
            connection.close();
            return listaHospedagens;
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("erro psql",PSQLState.DATA_ERROR);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
}
