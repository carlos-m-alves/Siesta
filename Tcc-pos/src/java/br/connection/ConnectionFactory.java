package br.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {
    public Statement stm;
    public ResultSet rs;
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/tccpos";
    private String usuario = "postgres";
    private String senha = "admin";
    public Connection con;
    
    public Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(caminho, usuario, senha);
            return con;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 
}
