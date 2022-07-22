package br.dao;

import br.bean.Horario;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HorarioDao {
    private Connection connection;
    private final String sqlSelectHorarioPorQuarto = "SELECT t1.id_horas, t1.horario, " +
                    "CASE WHEN t2.disponibilidade is null THEN 'DISPONÍVEL' " +
                    "ELSE t2.disponibilidade  END AS disponibilidade " +
                    "FROM HORAS t1 " +
                    "LEFT JOIN ( " +
                    "select * from quarto_horas " +
                    "WHERE dt_reservada = CURRENT_DATE AND id_quarto = ? " +
                    ") AS t2 on t1.id_horas = t2.id_horas";
    
    public HorarioDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public List<Horario> getHorarioByQuartoId(int id) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectHorarioPorQuarto);
            stmt.setInt(1, id);            
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Horario> listaHorarios = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Horario horario = new Horario();
                horario.setIdHorario(rs.getInt("id_horas")); 
                
                SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
                Date a = ft.parse(ft.format(rs.getTime("horario")));
                horario.setHorario(a); 
                horario.setDisponibilidade(rs.getString("disponibilidade"));                 
                listaHorarios.add(horario);
            }

            stmt.close();
            connection.close();
            return listaHorarios;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
