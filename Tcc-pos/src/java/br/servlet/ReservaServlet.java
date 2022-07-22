package br.servlet;

import br.bean.FormaPagamento;
import br.bean.Login;
import br.bean.Quarto;
import br.bean.Reserva;
import br.bean.Usuario;
import br.facade.FormaPagamentoFacade;
import br.facade.LoginFacade;
import br.facade.QuartoFacade;
import br.facade.ReservaFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.MaskFormatter;

@WebServlet(name="ReservaServlet", urlPatterns={"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        try {
            HttpSession session = request.getSession();            
            if ( session.getAttribute("Usuario") != null){      
                String action = request.getParameter("action");
                //System.out.println("oq tem no action do HotelServlet: "+action);
                if ( "update".equalsIgnoreCase(action) ){
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    int idHorario = Integer.valueOf(request.getParameter("idHorario"));
                    
                    //buscar formas de pagamento
                    List<FormaPagamento> listaFormasPagamento = FormaPagamentoFacade.getFormasPagamentos();
                    
                    Reserva r = ReservaFacade.getReserva(idQuarto,idHorario);
                    Reserva rCheckin = ReservaFacade.realizarCheckIn(idQuarto,idHorario);
                    
                    MaskFormatter mf = new MaskFormatter("###.###.###-##");
                    mf.setValueContainsLiteralCharacters(false);
                    //System.out.println("cpf format: "+mf.valueToString(r.getCpf())); // 12.345.678/0001-99
                    
                    //System.out.println("cpf na servlet1: "+rCheckin.getCpf());
                    if(r == null){
                        System.out.println("não tem nenhuma reserva");
                        r.setCpf(mf.valueToString(r.getCpf()));
                        rCheckin.setCpf(mf.valueToString(rCheckin.getCpf()));
                        //FAZER CHECK-IN                       
                        request.setAttribute("reserva", rCheckin);
                        
              /*          
                        System.out.println("nome: "+rCheckin.getNomePessoa());
                        System.out.println("dt reserva "+rCheckin.getDtReserva().toString());
                        System.out.println("hr entrada: "+rCheckin.getHorarioEntrada().toString());
                        System.out.println("preco: "+rCheckin.getPreco());
                        System.out.println("disponibilidade: "+rCheckin.getDisponibilidade());
                        System.out.println("id reserva: "+rCheckin.getIdReserva());
                        System.out.println("id quarto: "+rCheckin.getIdHotel());
                        System.out.println("id quarto: "+rCheckin.getIdQuarto());
                        System.out.println("hr chegada: "+rCheckin.getIdHrChegada());
              */          
                        request.setAttribute("listaFormasPagamento", listaFormasPagamento);                               
                        rd = getServletContext().getRequestDispatcher("/registrar.jsp");   
                        rd.forward(request, response); 
                    }else{
                        r.setCpf(mf.valueToString(r.getCpf()));
                        rCheckin.setCpf(mf.valueToString(rCheckin.getCpf()));
                    //    System.out.println("cpf na servlet2: "+rCheckin.getCpf());
                        //System.out.println("tem reserva"+r.getDisponibilidade());
                        request.setAttribute("reserva", r);
                        request.setAttribute("listaFormasPagamento", listaFormasPagamento);                               
                        rd = getServletContext().getRequestDispatcher("/registrar.jsp");   
                        rd.forward(request, response); 
                    }                                     
                }else if ( "registrar".equalsIgnoreCase(action) ){
                    int idFormaPagamento = Integer.valueOf(request.getParameter("formaPagamento"));
                    int idReserva = Integer.valueOf(request.getParameter("idReserva"));
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    int idHotel = Integer.valueOf(request.getParameter("idHotel"));
                    int idHoras = Integer.valueOf(request.getParameter("idHoras"));
                    Double preco = Double.valueOf(request.getParameter("preco"));
                    
                    String dt = request.getParameter("dtReservada");
                    Date dtReservada = new SimpleDateFormat("yyyy-MM-dd").parse(dt);

                    //insert reserva_pagamento com idFormaPagamento, idReserva e preco
                    ReservaFacade.fecharPagamento(idFormaPagamento, idReserva, preco);
                    
                    //atualizar tabela reserva com hr_atual e pago=true
                    ReservaFacade.fecharReserva(idReserva);
                    
                    //atualizar tabela com os horarios disponiveis do quarto  
                    ReservaFacade.quartoOcupado("REGISTRADO", idQuarto, idHoras, idHotel, dtReservada);
                            
                    //indisponibilizar o proximo horario                    
                    
                    rd = getServletContext().getRequestDispatcher("/QuartoServlet?action=inf&id="+idQuarto);   
                    rd.forward(request, response);   
                }else if ( "checkin".equalsIgnoreCase(action) ){
                    System.out.println("chegou aqui aleluia");
                    int idHotel = Integer.valueOf(request.getParameter("idHotel"));
                    System.out.println("id vindo do jsp: "+idHotel);
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    int idHoras = Integer.valueOf(request.getParameter("idHoras"));
                    String dt_reservada = request.getParameter("dtReservada");                    
                    //Date dt = new SimpleDateFormat("dd/MM/yyyy").parse(dt_reservada);
                    Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(dt_reservada);
                    
                    //System.out.println("data transformada: "+dt_reservada.toString());
                    //System.out.println("dt transf: "+new java.sql.Date(dt.getTime()));

                    ReservaFacade.quartoOcupado("CHECK-IN",idQuarto,idHoras,idHotel,dt);
                                                  
                    rd = getServletContext().getRequestDispatcher("/QuartoServlet?action=inf&id="+idQuarto);   
                    rd.forward(request, response); 
                }else if ( "reserva".equalsIgnoreCase(action) ){
                    Login login = (Login) session.getAttribute("Usuario");
                    System.out.println(""+login.getNome());
                    System.out.println(""+login.getEmail());
                    System.out.println(""+login.getId());
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    System.out.println(""+idQuarto);
                    int idHorario = Integer.valueOf(request.getParameter("idHorario"));
                    System.out.println(""+idHorario);                    
                    String dataRes = request.getParameter("dataRes");
                    Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(dataRes);
                    System.out.println(""+dataRes);
                    
                    //Quarto quarto = QuartoFacade.getQuartoById(idQuarto);
                    
                    Reserva reserva = new Reserva();
                    reserva.setIdQuarto(idQuarto);
                    reserva.setIdPessoa(login.getId());
                    reserva.setIdHrChegada(idHorario);
                    reserva.setDtReserva(dt);
                    //int idUsuario = LoginFacade.getId(login.getEmail());
                    ReservaFacade.reservarQuarto(reserva);
                    
                    request.setAttribute("msg", "Reserva confirmada!");
                    rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=pagination&recordsPerPage=5&currentPage=1");   
                    rd.forward(request, response); 
                }
            }
        }catch(Exception e){
            System.out.println("erro: "+ e.getMessage());
            System.out.println("erro: "+ e.toString());
        }
          
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
