package br.dao;

import br.bean.Adicional;
import br.bean.Avaliacao;
import br.bean.Horario;
import br.bean.Quarto;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class QuartoDao {
    // a conexão com o banco de dados
    private Connection connection;
    private final String sqlSelectAllQuartosById = "select * from quarto t1 WHERE t1.id_hospedagem = ? AND ativo=true " +
                    "LIMIT ? OFFSET ?";    
    private final String sqlSelQuartoPorAdicionalAr = "select * from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 IS NULL AND t2.a3 IS NULL AND t1.ativo=true " +
            "LIMIT ? OFFSET ?";
    private final String sqlCountQuartoPorAdicionalAr = "select count(*) as qtd from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 IS NULL AND t2.a3 IS NULL AND t1.ativo=true";
    private final String sqlSelQuartoPorAdicionalWifi = "select * from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 2 AND t2.a2 IS NULL AND t2.a3 IS NULL AND t1.ativo=true " +
            "LIMIT ? OFFSET ?";
    private final String sqlCountQuartoPorAdicionalWifi = "select count(*) as qtd from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 2 AND t2.a2 IS NOT NULL AND t2.a3 IS NULL AND t1.ativo=true";
    private final String sqlSelQuartoPorAdicionalTv = "select * from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 3 AND t2.a2 IS NULL AND t2.a3 IS NOT NULL AND t1.ativo=true " +
            "LIMIT ? OFFSET ?";
    private final String sqlCountQuartoPorAdicionalTv = "select count(*) as qtd from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 3 AND t2.a2 IS NULL AND t2.a3 IS NOT NULL AND t1.ativo=true";    
    private final String sqlSelQuartoPorAdicionalArEWifi = "select * from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 = 2 AND t2.a3 IS NULL AND t1.ativo=true " +
            "LIMIT ? OFFSET ?";
    private final String sqlCountQuartoPorAdicionalArEWifi = "select count(*) as qtd from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 = 2 AND t2.a3 IS NULL AND t1.ativo=true ";
    private final String sqlSelQuartoPorAdicionalArETv = "select * from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 =3 AND t2.a3 IS NULL AND t1.ativo=true " +
            "LIMIT ? OFFSET ?";
    private final String sqlCountQuartoPorAdicionalArETv = "select count(*) as qtd from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 = 3 AND t2.a3 IS NULL AND t1.ativo=true ";
    private final String sqlSelQuartoPorAdicionalWifiETv = "select * from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 2 AND t2.a2 = 3 AND t2.a3 IS NULL AND t1.ativo=true " +
            "LIMIT ? OFFSET ?";
    private final String sqlCountQuartoPorAdicionalWifiETv = "select count(*) as qtd from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 2 AND t2.a2 = 3 AND t2.a3 IS NULL AND t1.ativo=true ";
    private final String sqlQuartoPorTodosAdicionais = "select * from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 = 2 AND t2.a3 = 3 AND t1.ativo=true " +
            "LIMIT ? OFFSET ?";
    private final String sqlCountQuartoPorTodosAdicionais = "select count(*) as qtd from quarto t1 " +
            "LEFT join ( " +
            "	select id_quarto, " +
            "  	max(case when seq='1' then id_adicional end) as a1, " +
            "  	max(case when seq='2' then id_adicional end) as a2, " +
            "  	max(case when seq='3' then id_adicional end) as a3 " +
            "	FROM (" +
            "		select tt.*, row_number() OVER (PARTITION by id_quarto)::INTEGER as seq from quarto_adicional as tt " +
            "	) as t " +
            "	group by 1 " +
            ")as t2 on t1.id_quarto = t2.id_quarto " +
            "WHERE t1.id_hospedagem = ? AND t2.a1 = 1 AND t2.a2 = 2 AND t2.a3 = 3 AND t1.ativo=true ";
    
    private final String sqlCountAllQuartoPorHotel = "select count(*) as qtd from quarto WHERE id_hospedagem = ? AND ativo=true";
    private final String sqlSelectQuartoById = "select t1.*,t2.*,t3.*,t4.f1,t4.f2,t4.f3,t5.a1,t5.a2,t5.a3 from quarto t1 " +
"LEFT join quarto_horas t2 " +
"on t1.id_quarto = t2.id_quarto " +
"LEFT join horas t3 " +
"on t2.id_horas = t3.id_horas " +
"LEFT join (" +
"	select id_quarto, " +
"  	max(case when seq='1' then id_firebase end) as f1, " +
"  	max(case when seq='2' then id_firebase end) as f2, " +
"  	max(case when seq='3' then id_firebase end) as f3 " +
"	FROM (" +
"		select tt.*, row_number() OVER (PARTITION by 0)::INTEGER as seq from imagem as tt " +
"		where id_quarto = ? " +
"	) as t " +
"	group by 1" +
")as t4 on t1.id_quarto = t4.id_quarto " +
"LEFT join ( " +
"	select id_quarto, " +
"  	max(case when seq='1' then id_adicional end) as a1, " +
"  	max(case when seq='2' then id_adicional end) as a2, " +
"  	max(case when seq='3' then id_adicional end) as a3 " +
"	FROM (" +
"		select tt.*, row_number() OVER (PARTITION by 0)::INTEGER as seq from quarto_adicional as tt " +
"		where id_quarto = ? " +
"	) as t " +
"	group by 1 " +
")as t5 on t1.id_quarto = t5.id_quarto " +                  
"WHERE t1.id_quarto = ? AND t1.ativo=true ";
    private final String sqlInsertQuarto = "insert into quarto (id_hospedagem,nome,ativo,preco,descricao)"
            + " values (?,?,?,?,?);";
    private final String sqlSelectQuartoByIdWithComp = "select " +
"  t1.id_quarto, t1.nome, t1.preco, t1.descricao, t2.ad1, t2.ad2, t2.ad3, t3.f1, t3.f2, t3.f3 " +
"from quarto t1 " +
"LEFT join (" +
"	select id_quarto," +
"  	max(case when seq='1' then id_adicional end) as ad1," +
"  	max(case when seq='2' then id_adicional end) as ad2," +
"  	max(case when seq='3' then id_adicional end) as ad3" +
"	FROM (" +
"		select tt.*, row_number() OVER (PARTITION by 0)::INTEGER as seq from quarto_adicional as tt" +
"		where id_quarto = ?" +
"	) as t" +
"	group by 1" +
")as t2 on t1.id_quarto = t2.id_quarto " +
"LEFT join (" +
"	select id_quarto," +
"  	max(case when seq='1' then id_firebase end) as f1," +
"  	max(case when seq='2' then id_firebase end) as f2," +
"  	max(case when seq='3' then id_firebase end) as f3" +
"	FROM (" +
"		select tt.*, row_number() OVER (PARTITION by 0)::INTEGER as seq from imagem as tt" +
"		where id_quarto = ?" +
"	) as t " +
"	group by 1" +
")as t3 on t1.id_quarto = t3.id_quarto " +
"WHERE t1.id_quarto = ? AND t1.ativo=true ";
    private final String sqlSelectQuartoByIdAndCep = " select * from quarto t1 " +
        " inner join hospedagem t2 " +
        " on t1.id_hospedagem = t2.id_hospedagem" +
        " inner join endereco t3" +
        " on t2.id_endereco = t3.id_endereco" +
        " WHERE t1.id_quarto = ?";
    
    private final String sqlSelectAvaliacao = "SELECT " +
        "	tb.media,count(id_quarto) as qtd " +
        "FROM avaliacao " +
        "cross join( " +
        " select sum(classificacao)/count(id_quarto) as media from avaliacao" +
        " WHERE id_quarto =?" +
        ") as tb	" +
        "WHERE id_quarto = ?" +
        "GROUP BY 1;";
    private final String sqlDelQuarto = "UPDATE quarto SET ativo=? WHERE id_quarto=?";
    
    public QuartoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public List<Quarto> getQuartosByIdHotels(int id, int currentPage, int recordsPerPage, int consulta) {
        try{            
            String query = "";
            switch(consulta){
                case 0:
                    query = sqlSelectAllQuartosById;
                    break;
                case 1:
                    query = sqlSelQuartoPorAdicionalAr;
                    break;
                case 2:
                    query = sqlSelQuartoPorAdicionalWifi;
                    break;
                case 3:
                    query = sqlSelQuartoPorAdicionalTv;
                    break;
                case 4:
                    query = sqlSelQuartoPorAdicionalArEWifi;
                    break;
                case 5:
                    query = sqlSelQuartoPorAdicionalArETv;
                    break;
                case 6:
                    query = sqlSelQuartoPorAdicionalWifiETv;
                    break; 
                 case 7:
                    query = sqlQuartoPorTodosAdicionais;
                    break;
            }
            //System.out.println("oq tem em query sql: "+query);
            
            
            int start = currentPage * recordsPerPage - recordsPerPage;
            //System.out.println("start: "+start);
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);            
            stmt.setInt(2, recordsPerPage);
            stmt.setInt(3, start);
            
            System.out.println("SQL ANTES PAGINACAO sql getAdd:");
            System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Quarto> listaQuartos = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Quarto quarto = new Quarto();
                quarto.setId(rs.getInt("id_quarto")); 
                quarto.setIdHospedagem(rs.getInt("id_hospedagem")); 
                quarto.setNome(rs.getString("nome"));   
                quarto.setPreco(rs.getDouble("preco")); 
              
                listaQuartos.add(quarto);  
            }           
            
            stmt.close();
            connection.close();
            return listaQuartos;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public Quarto getQuartoById(int id) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectQuartoById);
            stmt.setInt(1, id);        
            stmt.setInt(2, id);       
            stmt.setInt(3, id);       
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            Quarto quarto = null;
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                quarto = new Quarto();
                quarto.setId(rs.getInt("id_quarto")); 
                quarto.setIdHospedagem(rs.getInt("id_hospedagem")); 
                quarto.setNome(rs.getString("nome"));   
                quarto.setPreco(rs.getDouble("preco")); 
                quarto.setDescricao(rs.getString("descricao")); 
                List<String> listaFotos = new ArrayList<>();
                listaFotos.add(rs.getString("f1"));
                listaFotos.add(rs.getString("f2"));
                listaFotos.add(rs.getString("f3"));
                quarto.setListaImagemFirebase(listaFotos);  
                List<Adicional> listaAdicionais = new ArrayList<>();
                Adicional adicional = new Adicional();
                adicional.setId(rs.getInt("a1"));
                //System.out.println("trouxe inf correta? "+rs.getInt("a1"));
                listaAdicionais.add(adicional);
                adicional = new Adicional();
                adicional.setId(rs.getInt("a2"));
                listaAdicionais.add(adicional);
                adicional = new Adicional();
                adicional.setId(rs.getInt("a3"));
                listaAdicionais.add(adicional);
                quarto.setListaAdicionais(listaAdicionais);
                /*
                List<Horario> listaHorarios = new ArrayList<>();
                //System.out.println("hora q vem do bd: "+rs.getTime("horario"));             
                SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
                //System.out.println(" Date formatada: " + ft.format(rs.getTime("horario")));              
                Date a = ft.parse(ft.format(rs.getTime("horario")));
                //System.out.println("hora formatada para date: "+a);
                Horario horario = new Horario();
                horario.setIdHorario(rs.getInt("id_horas"));
                horario.setDisponivel(rs.getBoolean("disponivel"));
                horario.setHorario(a);
                listaHorarios.add(horario);*/
            }
            stmt.close();
            connection.close();
            return quarto;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar quarto: "+e.toString());
        }
        return null;
    }
    
    public int getQuantidadeTotalHotel(int id, int consulta) {
        try{            
            String query = "";
            switch(consulta){
                case 0:
                    query = sqlCountAllQuartoPorHotel;
                    break;
                case 1:
                    query = sqlCountQuartoPorAdicionalAr;
                    break;
                case 2:
                    query = sqlCountQuartoPorAdicionalWifi;
                    break;
                case 3:
                    query = sqlCountQuartoPorAdicionalTv;
                    break;
                case 4:
                    query = sqlCountQuartoPorAdicionalArEWifi;
                    break;
                case 5:
                    query = sqlCountQuartoPorAdicionalArETv;
                    break;
                case 6:
                    query = sqlCountQuartoPorAdicionalWifiETv;
                    break; 
                 case 7:
                    query = sqlCountQuartoPorTodosAdicionais;
                    break;
            }
            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);       
             
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
    
    public int adicionaQuarto(Quarto quarto) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlInsertQuarto, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, quarto.getIdHospedagem());
            stmt.setString(2, quarto.getNome());            
            stmt.setBoolean(3, true);
            stmt.setDouble(4, quarto.getPreco());
            stmt.setString(5, quarto.getDescricao());
            
            // executa
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert room failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //System.out.println("ID do QUARTO inserido: "+generatedKeys.getLong(1));
                    Long id = generatedKeys.getLong(1);
                    stmt.close();
                    connection.close();
                    return id.intValue();                    
                }
                else {
                    throw new SQLException("Insert room failed, no ID obtained.");
                }
            }               
        } catch (PSQLException e) {
            System.out.println("erro dentro do dao! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada", PSQLState.NO_DATA);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public Quarto getQuartoByIdWithComp(int id) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectQuartoByIdWithComp);
            stmt.setInt(1, id);     
            stmt.setInt(2, id);     
            stmt.setInt(3, id);   
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            Quarto quarto = null;
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                quarto = new Quarto();
                quarto.setId(rs.getInt("id_quarto")); 
                quarto.setNome(rs.getString("nome"));   
                quarto.setPreco(rs.getDouble("preco")); 
                quarto.setDescricao(rs.getString("descricao")); 
                
                List<Adicional> listaAdicionais = new ArrayList<>();
                listaAdicionais.add(new Adicional(rs.getInt("ad1")));
                listaAdicionais.add(new Adicional(rs.getInt("ad2")));
                listaAdicionais.add(new Adicional(rs.getInt("ad3")));
                quarto.setListaAdicionais(listaAdicionais);
                List<String> listaFotos = new ArrayList<>();
                listaFotos.add(rs.getString("f1"));
                listaFotos.add(rs.getString("f2"));
                listaFotos.add(rs.getString("f3"));
                quarto.setListaImagemFirebase(listaFotos);             
            }
            stmt.close();
            connection.close();
            return quarto;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar quarto: "+e.toString());
        }
        return null;
    }
    
    //retorna o cep do estabelecimento
    public String getQuartoByIdAndCep(int id) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectQuartoByIdAndCep);
            stmt.setInt(1, id);       
            
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            Quarto quarto = null;
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                quarto = new Quarto();
                quarto.setNome(rs.getString("cep"));                         
            }
            stmt.close();
            connection.close();
            return quarto.getNome();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar quarto: "+e.toString());
        }
        return null;
    }
    
    public Avaliacao getAvaliacao(int id) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectAvaliacao);
            stmt.setInt(1, id);       
            stmt.setInt(2, id);     
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            Avaliacao avaliacao = null;
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                avaliacao = new Avaliacao();
                //avaliacao.setMedia(rs.getDouble("media"));                  
                avaliacao.setMedia(Double.valueOf(Math.round(rs.getDouble("media") * 2) / 2.0f));  
                avaliacao.setQuantidade(rs.getInt("qtd"));    
            }
            stmt.close();
            connection.close();
            return avaliacao;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar quarto: "+e.toString());
        }
        return null;
    }
    
    public void deleteQuarto(int id) {
        try{            
            //System.out.println("dentro do dao: "+user.getLogin());
            PreparedStatement stmt = connection.prepareStatement(sqlDelQuarto);
            stmt.setBoolean(1, false);
            stmt.setInt(2, id);
            
            stmt.executeUpdate();    
            System.out.println("executou o update");
            stmt.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
