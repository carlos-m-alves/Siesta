package br.dao;

import br.bean.Login;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
    private Connection connection;
    
    private final String sqlSelectByEmailAndPassword = "select * from pessoa t1 " +
                "left join parceiro t2 " +
                "on t1.id_pessoa = t2.id_parceiro " +
                "left join administrador t3 " +
                "on t1.id_pessoa = t3.id_administrador " +
                "left join hospedagem t4 " +
                "on t2.id_parceiro = t4.id_parceiro " +
                "where email = ? and senha=?;";
    private final String sqlSelectByEmail = "SELECT id_pessoa FROM pessoa WHERE email=?";
    
    public LoginDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public Login getUsuarioByEmailAndPassword(Login usuario) throws Exception{
        // pega a conexão e o Statement
        try{
            Login l = null;
            //System.out.println("dentro do dao: "+usuario.getSenha());
            PreparedStatement stmt = connection.prepareStatement(sqlSelectByEmailAndPassword);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            // executa um select
            ResultSet rs = stmt.executeQuery();
            if ( rs.next() ){
                l = new Login();
                l.setId(rs.getInt("id_pessoa"));  
                l.setNome(rs.getString("nome"));
                l.setEmail(rs.getString("email"));  
                l.setTipo(rs.getString("tipo").charAt(0));
                l.setIdHospedagem(rs.getInt("id_hospedagem"));  
            }
            System.out.println("id hosp no loginDao: "+l.getIdHospedagem());
            stmt.close();
            connection.close();
            return l;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }
    
    public int getId(String email) throws Exception{
        // pega a conexão e o Statement
        try{
            PreparedStatement stmt = connection.prepareStatement(sqlSelectByEmail);
            stmt.setString(1, email);
            // executa um select
            int identificador = -1;
            ResultSet rs = stmt.executeQuery();
            if ( rs.next() ){
                identificador = rs.getInt("id_pessoa");  
            }
            stmt.close();
            connection.close();
            return identificador;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
