package br.dao;

import br.bean.FormaPagamento;
import br.bean.Reserva;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormaPagamentosDao {
    private Connection connection;
    private final String sqlSelectAll = "select * from forma_pagamento";
    
    public FormaPagamentosDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public List<FormaPagamento> getFormasPagamentos() {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectAll);  
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<FormaPagamento> listaFormaPagamentos = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setIdFormaPagamento(rs.getInt("id_forma_pagamento")); 
                formaPagamento.setDescPagamento(rs.getString("desc_pagamento")); 
                listaFormaPagamentos.add(formaPagamento);
            }
            stmt.close();
            connection.close();
            return listaFormaPagamentos;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar quarto: "+e.toString());
        }
        return null;
    }
    
}
