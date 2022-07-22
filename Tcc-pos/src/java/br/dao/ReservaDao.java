package br.dao;

import br.bean.Avaliacao;
import br.bean.Endereco;
import br.bean.Relatorio;
import br.bean.Reserva;
import br.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.text.MaskFormatter;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class ReservaDao {
    private Connection connection;
    private final String sqlReservaByIds = "select " +
                "t1.*, t2.cpf, t2.nome AS nome_pessoa, t3.nome AS nome_quarto, t3.preco AS preco_quarto, t4.horario, t5.disponibilidade, t3.id_hospedagem " +
                "from reserva t1 inner join pessoa t2 " +
                "on t1.id_usuario = t2.id_pessoa " +
                "inner join quarto t3 on t1.id_quarto = t3.id_quarto " +
                "inner join horas t4 on t1.id_hr_chegada = t4.id_horas " +
                "inner join quarto_horas t5 on t1.id_hr_chegada = t5.id_horas AND t1.id_quarto = t5.id_quarto " +
                "WHERE t1.dt_reserva = CURRENT_DATE AND t1.id_hr_chegada = ? AND t1.id_quarto = ? ";    
    private final String sqlCheckIn = "SELECT t1.*,t2.cpf, t2.nome AS nome_pessoa, t3.*, t4.preco, t5.horario " +
                "FROM reserva t1 " +
                "INNER JOIN pessoa t2 ON t1.id_usuario = t2.id_pessoa " +
                "INNER JOIN quarto_horas t3 ON t1.id_hr_chegada = t3.id_horas AND t1.id_quarto = t3.id_quarto " +
                "INNER JOIN quarto t4 ON t1.id_quarto = t4.id_quarto " +
                "INNER JOIN horas t5 ON t1.id_hr_chegada = t5.id_horas " + 
                "WHERE DT_RESERVADA = CURRENT_DATE " +
                "AND t1.id_hr_chegada = ? AND t1.id_quarto = ? AND t1.hr_saida is null AND pago = false";
    private final String sqlFecharPagamento = "insert into reserva_pagamento " +
                "(id_forma_pagamento, id_reserva, preco) values (?,?,?);";
    private final String sqlFecharReserva = "update reserva " +
                "set hr_saida = current_time, pago=true " +
                "WHERE id_reserva = ?";
    private final String sqlUpdateQuartoHoras = "update quarto_horas " +
                "set disponibilidade = ? " +
                "where id_quarto=? and id_horas = ? AND dt_reservada = ?";
    private final String sqlInsertReserva = "insert into reserva (id_quarto,id_usuario,id_hr_chegada,pago,dt_reserva) " +
                "values (?,?,?,?,?);";
    private final String sqlInsertHorario = "insert into quarto_horas (id_quarto,id_horas,disponibilidade,dt_reservada)" +
                "values (?,?,?,?);";
    private final String sqlSelectLucroPorMes = "select t1.data, sum(soma) as soma from (" +
                "	select " +
                "	TO_DATE(CONCAT('01','/',EXTRACT(MONTH FROM DATE_TRUNC('month',t1.dt_reserva)),'/',EXTRACT(YEAR FROM DATE_TRUNC('month',t1.dt_reserva))),'DD/MM/YYYY') as data" +
                "	,sum(t2.preco) as soma " +
                "	from reserva t1 " +
                "	inner join quarto t2 " +
                "	on t1.id_quarto = t2.id_quarto " +
                "	where t1.pago=true " +
                "	GROUP BY t1.dt_reserva" +
                ")as t1 " +
                "group by 1 " +
                "order by 1 ASC";
    private final String sqlSelectQtdHospedagens = "select t3.descricao as bairro,count(*) as qtd from hospedagem t1 " +
                "inner join endereco t2 " +
                "on t1.id_endereco = t2.id_endereco " +
                "inner join bairro t3 " +
                "on t2.id_bairro = t3.id_bairro " +
                "group by 1";
    private final String sqlSelectReservasPorUsuarios = "select " +
                "t1.id_reserva, t1.id_quarto, t1.pago, t1.dt_reserva, t2.nome as nomeQuarto, t2.preco," +
                "t3.nome as nomeHosp, t4.rua, " +
                "t4.numero, t4.complemento,t5.descricao, t6.horario, t7.classificacao " +
                "from reserva t1 " +
                "inner join quarto t2 " +
                "on t1.id_quarto = t2.id_quarto " +
                "inner join hospedagem t3 " +
                "on t2.id_hospedagem = t3.id_hospedagem " +
                "inner join endereco t4 " +
                "on t3.id_endereco = t4.id_endereco " +
                "inner join bairro t5 " +
                "on t4.id_bairro = t5.id_bairro " +
                "inner join horas t6 " +
                "on t1.id_hr_chegada = t6.id_horas " +
                "left join avaliacao t7 " +
                "on t1.id_reserva = t7.id_reserva " +
                "where t1.id_usuario = ? ORDER BY t1.dt_reserva DESC LIMIT ? OFFSET ? ";
    
    private final String sqlSelectQtdReservasPorUsuarios = "select " +
                "count(*) as qtd " +
                "from reserva t1 " +
                "inner join quarto t2 " +
                "on t1.id_quarto = t2.id_quarto " +
                "inner join hospedagem t3 " +
                "on t2.id_hospedagem = t3.id_hospedagem " +
                "inner join endereco t4 " +
                "on t3.id_endereco = t4.id_endereco " +
                "inner join bairro t5 " +
                "on t4.id_bairro = t5.id_bairro " +
                "inner join horas t6 " +
                "on t1.id_hr_chegada = t6.id_horas " +
                "where t1.id_usuario = ? ";
    private final String sqlSelectHorarioReservaPorQuarto = "select t1.*,t2.*,t3.* " +
                "from quarto t1 " +
                "LEFT join quarto_horas t2 " +
                "on t1.id_quarto = t2.id_quarto " +
                "LEFT join horas t3 " +
                "on t2.id_horas = t3.id_horas " +
                "WHERE t1.id_quarto = ? and t2.dt_reservada = ?";
    
    public ReservaDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public Reserva getReserva(int idQuarto, int idHorario) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlReservaByIds);
            stmt.setInt(1, idHorario);   
            stmt.setInt(2, idQuarto);   
            
            //System.out.println("sql da reserva: ");
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            Reserva quarto = null;
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                quarto = new Reserva();
                quarto.setIdReserva(rs.getInt("id_reserva")); 
                quarto.setIdHotel(rs.getInt("id_hospedagem")); 
                quarto.setIdQuarto(rs.getInt("id_quarto")); 
                quarto.setIdPessoa(rs.getInt("id_usuario")); 
                quarto.setIdHrChegada(rs.getInt("id_hr_chegada")); 
                
                SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
                try{                    
                    Date a = ft.parse(ft.format(rs.getTime("hr_saida")));
                    quarto.setHrSaida(a);
                }catch(Exception e){
                    System.out.println("erro ao formatar data HR_SAIDA");
                }
                       
                quarto.setPago(rs.getBoolean("pago"));   
                quarto.setPreco(rs.getDouble("preco_quarto")); 
                
                //MaskFormatter mf = new MaskFormatter("###.###.###-##");
                //mf.setValueContainsLiteralCharacters(false);
                //System.out.println("cpf format: "+mf.valueToString(r.getCpf())); // 12.345.678-99
                //String cpfFormatado = mf.valueToString(rs.getString("cpf"));
                quarto.setCpf(rs.getString("cpf")); 
                
                quarto.setNomePessoa(rs.getString("nome_pessoa")); 
                quarto.setNomeQuarto(rs.getString("nome_quarto"));
                quarto.setDisponibilidade(rs.getString("disponibilidade"));
                try{                    
                    Date a2 = ft.parse(ft.format(rs.getTime("horario")));
                    quarto.setHorarioEntrada(a2);
                }catch(Exception e){
                    System.out.println("erro ao formatar data HORARIO");
                }               
                
                quarto.setDtReserva(rs.getDate("dt_reserva"));
                
            }
            stmt.close();
            connection.close();
            return quarto;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar reserva: "+e.toString());
        }
        return null;
    }
    
    public Reserva realizarCheckIn(int idQuarto, int idHorario) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlCheckIn);
            stmt.setInt(1, idHorario);   
            stmt.setInt(2, idQuarto);   
            
            //System.out.println("sql do check-in: ");
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            Reserva reserva = null;
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                reserva = new Reserva();
                reserva.setIdReserva(rs.getInt("id_reserva")); 
                reserva.setIdQuarto(rs.getInt("id_quarto")); 
                reserva.setIdPessoa(rs.getInt("id_usuario")); 
                reserva.setIdHrChegada(rs.getInt("id_hr_chegada")); 
                
                SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
                try{                    
                    Date a = ft.parse(ft.format(rs.getTime("hr_saida")));
                    reserva.setHrSaida(a);
                }catch(Exception e){
                    System.out.println("erro ao formatar data HR_SAIDA");
                }
                       
                reserva.setPago(rs.getBoolean("pago"));                 
                reserva.setNomePessoa(rs.getString("nome_pessoa"));
                
                //MaskFormatter mf = new MaskFormatter("###.###.###-##");
                //mf.setValueContainsLiteralCharacters(false);
                //System.out.println("cpf format: "+mf.valueToString(r.getCpf())); // 12.345.678-99
                //String cpfFormatado = mf.valueToString(rs.getString("cpf"));
                reserva.setCpf(rs.getString("cpf")); 
                //reserva.setIdHotel(rs.getInt("id_hospedagem")); 
                //System.out.println("cpf vindo: "+reserva.getCpf());
                try{                    
                    Date a2 = ft.parse(ft.format(rs.getTime("horario")));
                    reserva.setHorarioEntrada(a2);
                }catch(Exception e){
                    System.out.println("erro ao formatar data HORARIO");
                }               
                
                reserva.setDisponibilidade(rs.getString("disponibilidade"));
                reserva.setDtReserva(rs.getDate("dt_reservada"));
                reserva.setPreco(rs.getDouble("preco")); 
            }
            stmt.close();
            connection.close();
            return reserva;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar reserva: "+e.toString());
        }
        return null;
    }
    
    public void fecharPagamento(int idFormaPagamento, int idReserva, Double preco) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlFecharPagamento);
            stmt.setInt(1, idFormaPagamento);
            stmt.setInt(2, idReserva);
            stmt.setDouble(3, preco);
            
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
    
    public void fecharReserva(int idReserva) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlFecharReserva);
            stmt.setInt(1, idReserva);
         
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
    
    public void quartoOcupado(String disponibilidade, int idQuarto, int idHorario, int idHotel, Date dtReservada)
            throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlUpdateQuartoHoras);
            stmt.setString(1, disponibilidade);
            stmt.setInt(2, idQuarto);
            stmt.setInt(3, idHorario);
            
            //converte de java.util.date para java.sql.date
            //java.sql.Date data = new java.sql.Date(dtReservada.getTime());
            stmt.setDate(4, new java.sql.Date(dtReservada.getTime()));            
            //stmt.setDate(5, dtReservada);
            System.out.println("dt transf: "+new java.sql.Date(dtReservada.getTime()));
            System.out.println("sql do check-in: ");
            System.out.println(stmt.toString()); //visualizar a query de consulta
            
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
    
    public void reservarQuarto(Reserva reserva) throws PSQLException, Exception{        
        try {
            // prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sqlInsertReserva);
            stmt.setInt(1, reserva.getIdQuarto());
            stmt.setInt(2, reserva.getIdPessoa());
            stmt.setInt(3, reserva.getIdHrChegada());
            stmt.setBoolean(4, false);
            stmt.setDate(5, new java.sql.Date(reserva.getDtReserva().getTime()));
         
            // executa
            stmt.executeUpdate();
            
            PreparedStatement stmt2 = connection.prepareStatement(sqlInsertHorario);
            stmt2.setInt(1, reserva.getIdQuarto());
            stmt2.setInt(2, reserva.getIdHrChegada());
            stmt2.setString(3, "RESERVADO");           
            stmt2.setDate(4, new java.sql.Date(reserva.getDtReserva().getTime()));
         
            // executa
            stmt2.executeUpdate();
            
            stmt.close();
            connection.close();
        } catch (PSQLException e) {
            System.out.println("erro dentro do daoReserva! "+e);            
            e.getMessage();
            throw new PSQLException("Chave duplicada", PSQLState.NO_DATA);
        }catch(Exception e){
            e.getMessage();
            throw new RuntimeException(e);
        }
    }
    
    public List<Relatorio> getLucroPorMes() {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectLucroPorMes);
            //stmt.setInt(1, idHorario);     
            
            //System.out.println("sql da reserva: ");
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Relatorio> listaRelatorio = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Relatorio relatorio = new Relatorio();
                relatorio.setLucro(rs.getDouble("soma")); 
                relatorio.setData(rs.getString("data")); 
                
                /*SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
                try{                    
                    Date a = ft.parse(ft.format(rs.getTime("hr_saida")));
                    relatorio.setMes(a);
                }catch(Exception e){
                    System.out.println("erro ao formatar data HR_SAIDA");
                } */    
                listaRelatorio.add(relatorio);
            }
            stmt.close();
            connection.close();
            return listaRelatorio;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar lucro: "+e.toString());
        }
        return null;
    }
    
    public List<Relatorio> getQtdHospedagens() {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectQtdHospedagens);

            List<Relatorio> listaRelatorio = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Relatorio relatorio = new Relatorio();
                relatorio.setBairro(rs.getString("bairro")); 
                relatorio.setQuantidade(rs.getInt("qtd"));    
                listaRelatorio.add(relatorio);
            }
            stmt.close();
            connection.close();
            return listaRelatorio;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar relBairros: "+e.toString());
        }
        return null;
    }
    
    public List<Reserva> getReservasPorIdUsuario(int currentPage, int recordsPerPage, int idUsuario) {
        try{            
            int start = currentPage * recordsPerPage - recordsPerPage;
            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectReservasPorUsuarios);
            stmt.setInt(1, idUsuario);   
            stmt.setInt(2, recordsPerPage);   
            stmt.setInt(3, start);   
            
            System.out.println("sql do historico: ");
            System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Reserva> listaReservas = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Reserva reserva = new Reserva();
                Endereco endereco = new Endereco();
                reserva.setIdReserva(rs.getInt("id_reserva")); 
                reserva.setIdQuarto(rs.getInt("id_quarto")); 
                reserva.setPago(rs.getBoolean("pago")); 
                reserva.setDtReserva(rs.getDate("dt_reserva")); 
                reserva.setNomeQuarto(rs.getString("nomeQuarto")); 
                reserva.setPreco(rs.getDouble("preco")); 
                reserva.setNomeHospedagem(rs.getString("nomeHosp")); 
                
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                
                reserva.setEndereco(endereco);
                reserva.setBairro(rs.getString("descricao"));
                SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
                try{                    
                    Date a = ft.parse(ft.format(rs.getTime("horario")));
                    reserva.setHorarioEntrada(a);
                }catch(Exception e){
                    System.out.println("erro ao formatar data HORARIO");
                }                                   
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setAvaliacao(rs.getDouble("classificacao"));
                reserva.setAvaliacao(avaliacao);
                //System.out.println("foi algo??");
                listaReservas.add(reserva);
            }
            stmt.close();
            connection.close();
            return listaReservas;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar reservas: "+e.toString());
        }
        return null;
    }
    
    public int getQtdReservasPorIdUsuario(int idUsuario) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectQtdReservasPorUsuarios);
            stmt.setInt(1, idUsuario);     
            
            //System.out.println("sql qtd do historico: ");
            //System.out.println(stmt.toString()); //visualizar a query de consulta
            int qtd = -1;
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                qtd = rs.getInt("qtd"); 
            }
            stmt.close();
            connection.close();
            return qtd;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar reservas: "+e.toString());
        }
        return -1;
    }
    
    public List<Reserva> getHorarioReservasPorQuarto(int idQuarto, Date data) {
        try{            
            PreparedStatement stmt = connection.prepareStatement(sqlSelectHorarioReservaPorQuarto);
            stmt.setInt(1, idQuarto);   
            stmt.setDate(2, new java.sql.Date(data.getTime()));    
            
            System.out.println("sql do horarios de reserva por data do quarto: ");
            System.out.println(stmt.toString()); //visualizar a query de consulta
            List<Reserva> listaHorarios = new ArrayList<>();
            
            // executa um select
            ResultSet rs = stmt.executeQuery();            
            while ( rs.next() ){
                Reserva reserva = new Reserva();
                reserva.setIdHrChegada(rs.getInt("id_horas")); 
                SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss");
                try{                    
                    Date a = ft.parse(ft.format(rs.getTime("horario")));
                    reserva.setHorarioEntrada(a);
                }catch(Exception e){
                    System.out.println("erro ao formatar data HORARIO");
                }                                   

                System.out.println("foi algo??"+reserva.getHorarioEntrada());
                listaHorarios.add(reserva);
            }
            stmt.close();
            connection.close();
            return listaHorarios;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("deu erro ao buscar horario de reservas: "+e.toString());
        }
        return null;
    }
}
