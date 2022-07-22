package br.servlet;

import br.bean.Avaliacao;
import br.bean.Bairro;
import br.bean.Horario;
import br.bean.Hospedagem;
import br.bean.Login;
import br.bean.Quarto;
import br.bean.Reserva;
import br.bean.Usuario;
import br.facade.AdministradorFacade;
import br.facade.AvaliacaoFacade;
import br.facade.HorarioFacade;
import br.facade.HospedagemlFacade;
import br.facade.QuartoFacade;
import br.facade.ReservaFacade;
import br.facade.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="UsuarioServlet", urlPatterns={"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        try {
            HttpSession session = request.getSession();
            //System.out.println("se quer chegou aqui??UsuarioServlet");
            String action = request.getParameter("action");
            //System.out.println("oq tem no action: "+action);
            // System.out.println("sessao: "+session.getAttribute("Usuario"));
            if ( session.getAttribute("Usuario") != null){
                //System.out.println("oq tem no action(dentro USUARIO): "+action);
                if ( "pagination".equalsIgnoreCase(action) ){
                    //System.out.println("chegou aqui???pagination");
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

                    List<Hospedagem> listaHospedagens = AdministradorFacade.getHospedagens(currentPage, recordsPerPage);
                    request.setAttribute("listaHospedagens", listaHospedagens);

                    int rows = AdministradorFacade.quantidadeTotalHotel();
                    int nOfPages = rows / recordsPerPage;

                    if (nOfPages % recordsPerPage > 0) {
                        nOfPages++;
                    }
/*
                    for(Hospedagem a : listaHospedagens){
                        System.out.println("nome: "+a.getNome());
                        System.out.println("nome: "+a.getRua());
                        System.out.println("nome: "+a.getBairro());
                    }*/
                    
                    request.setAttribute("qtdHospedagem", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);
                        
                    //System.out.println("chegou aqui??pra direcionar pagina");
                    rd = request.getRequestDispatcher("lista_hospedagens_usuarios.jsp");
                    rd.forward(request, response);  
                }else if ( "infHospedagem".equalsIgnoreCase(action) ){
                    System.out.println("chegou aqui???INFHOSPEDAGEM");
                    int idHospedagem = Integer.valueOf(request.getParameter("idHospedagem"));
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

                    List<Quarto> listaQuartos = QuartoFacade.getQuartosByIdHotels(idHospedagem, currentPage, recordsPerPage,0 );
                    request.setAttribute("listaQuartos", listaQuartos);

                    int rows = QuartoFacade.quantidadeTotalQuartosPorHotel(idHospedagem, 0);
                    //System.out.println("qtd quartos na hospedagem: "+rows);
                    int nOfPages = rows / recordsPerPage;

                    if ((nOfPages % recordsPerPage) > 0) {
                        nOfPages++;
                    }                    
                    
                    request.setAttribute("idHospedagem", idHospedagem);
                    request.setAttribute("qtdQuartos", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);

                    rd = getServletContext().getRequestDispatcher("/lista_quartos_alugar.jsp");   
                    rd.forward(request, response);     
                }else if ( "inf".equalsIgnoreCase(action) ){
                    int id = Integer.valueOf(request.getParameter("idQuarto"));

                    Quarto quarto = QuartoFacade.getQuartoById(id);
                    List<Horario> listaHorarios = HorarioFacade.getHorarioByQuartoId(id);
                    Avaliacao avaliacao = QuartoFacade.getAvaliacao(id);
                    /*
                    System.out.println("ava0: ");
                    if( avaliacao != null ){
                        System.out.println("aee");
                        
                    }else{
                        System.out.println("ah naao");
                        avaliacao = new Avaliacao();
                        
                        //avaliacao.setMedia(Double.valueOf(3.3));
                        avaliacao.setMedia(Double.valueOf(Math.round(1.5 * 2) / 2.0f));
                        System.out.println("aee: "+Math.round(avaliacao.getMedia()));
                    }
                    */
                    request.setAttribute("quarto", quarto);
                    request.setAttribute("listaHorarios", listaHorarios);
                    request.setAttribute("avaliacao", avaliacao);
                    rd = getServletContext().getRequestDispatcher("/quarto_alugar.jsp");   
                    rd.forward(request, response);
                }else if ( "trajeto".equalsIgnoreCase(action) ){
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    //testando as 2linhas abaixo
                    Quarto quarto = QuartoFacade.getQuartoById(idQuarto);
                    request.setAttribute("quarto", quarto);                   
                    
                    String cep = QuartoFacade.getQuartoByIdAndCep(idQuarto);
                    System.out.println("qual o cep?? "+cep);
                    request.setAttribute("cep", cep);
                    request.setAttribute("idQuarto", 2);
                    request.setAttribute("qlqCOisa", "qlqCOisa");
                    rd = getServletContext().getRequestDispatcher("/RotaHospedagem.jsp");   
                    rd.forward(request, response);
                }else if ( "historico".equalsIgnoreCase(action) ){
                    String msg = request.getParameter("msg");
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
                    Login user = (Login)session.getAttribute("Usuario");
                    int idUsuario = user.getId();
                    List<Reserva> listaReservas = ReservaFacade.getReservasPorIdUsuario(currentPage, recordsPerPage, idUsuario);
                    
                    //System.out.println("qtd: "+listaReservas.size());
                    int rows = ReservaFacade.getQtdReservasPorIdUsuario(idUsuario);
                    
                    int nOfPages = rows / recordsPerPage;

                    if (nOfPages % recordsPerPage > 0) {
                        nOfPages++;
                    }
                    
                    request.setAttribute("msg", msg);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);
                    request.setAttribute("listaReservas", listaReservas);
                    rd = getServletContext().getRequestDispatcher("/lista_historico_reservas.jsp");   
                    rd.forward(request, response);
                }else if ( "avaliarPage".equalsIgnoreCase(action) ){
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    //System.out.println("id quarto aval: "+idQuarto);
                    int idReserva = Integer.valueOf(request.getParameter("idReserva"));
                    //System.out.println("id reserva aval: "+idReserva);                    
                    
                    request.setAttribute("idQuarto", idQuarto);
                    request.setAttribute("idReserva", idReserva);
                    rd = getServletContext().getRequestDispatcher("/avaliacao.jsp");   
                    rd.forward(request, response);
                }else if ( "avaliacao".equalsIgnoreCase(action) ){
                    System.out.println("chegou para avaliar");
                    Login l = (Login)session.getAttribute("Usuario");                    
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    System.out.println("id quarto aval: "+idQuarto);
                    int idReserva = Integer.valueOf(request.getParameter("idReserva"));
                    //System.out.println("id reserva aval: "+idReserva);
                    Double aval = Double.valueOf(request.getParameter("rating"));  
                    System.out.println("aval: "+aval);
                    
                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.setIdQuarto(idQuarto);
                    avaliacao.setIdReserva(idReserva);
                    avaliacao.setIdUsuario(l.getId());
                    avaliacao.setAvaliacao(aval);
                    //insere avaliacao
                    AvaliacaoFacade.adicionaAvaliacao(avaliacao);
                
                    String msg = "Obrigado pela avaliação!";                   
                    rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=historico&currentPage=1&recordsPerPage=5&msg="+msg);   
                    rd.forward(request, response);
                }else if ( "pesquisaBairro".equalsIgnoreCase(action) ){
                    String bairro = request.getParameter("bairro");
                    if( bairro.equals("")){
                        //System.out.println("entrou no cont para pesq bairro");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/UsuarioServlet?action=pagination&currentPage=1&recordsPerPage=5");
                        dispatcher.forward(request, response);
                        return;
                    }
                    System.out.println("bairro: "+bairro);
                    int currentPage = 1;
                    int recordsPerPage = 5;
                    
                    List<Hospedagem> listaHospedagem = AdministradorFacade.getHospedagensPorBairro(currentPage, recordsPerPage, bairro);
                    for(Hospedagem h : listaHospedagem){
                        System.out.println("hospedagens: "+h.getBairro());
                    }
                    
                    request.setAttribute("listaHospedagens", listaHospedagem);
                    System.out.println("ta passando por aqui deposi pra pega a qtd???");
                    int rows = HospedagemlFacade.quantidadeTotalHospedagensPorBairro(bairro);
                    int nOfPages = rows / recordsPerPage;

                    if (nOfPages % recordsPerPage > 0) {
                        nOfPages++;
                    }

                    request.setAttribute("qtdHospedagem", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("lista_hospedagens_usuarios.jsp");
                    dispatcher.forward(request, response);                  
                }else if ( "pesquisaAdicionais".equalsIgnoreCase(action) ){
                    System.out.println("chegou nos adds");
                    /*if( request.getParameter("add").equalsIgnoreCase(null) ){
                        System.out.println("eh nulo");
                    }else{
                        System.out.println("nao eh nulo"+request.getParameter("add"));
                    }*/
                    int idHospedagem = Integer.valueOf(request.getParameter("idHospedagem"));
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
                    //////////////////////
                    boolean temParam = false;
                    if(request.getParameter("add") == null){
                        System.out.println("prim: ");
                    }else if( request.getParameterValues("add") == null ){
                        System.out.println("seg: ");
                    }else if( request.getParameter("add").isEmpty() ){
                        System.out.println("ter: ");    
                    }else{
                        System.out.println("qua VALUES: ");  
                        System.out.println("qua VALUES: "+request.getParameter("add")); 
                        temParam = true;
                    }
                    //String[] lang =  ? "": request.getParameterValues("fav");

                    String[] checkedIds = request.getParameterValues("add");
                    int consulta = 0;
                    System.out.println("chegou nessa parte");
                    if( temParam ){                    
                        Boolean ar = false;
                        Boolean wifi = false;
                        Boolean tv = false;
                        for(String s : checkedIds){
                            int id = Integer.parseInt(s);                        
                            System.out.println("add: "+s);
                            if( id == 1){ 
                                ar = true;
                            }else if( id == 2){
                                wifi = true;
                            }else if( id == 3){
                                tv = true;
                            }
                        }
                        
                        if( !ar && !wifi && !tv){
                            consulta=0;
                        }if( ar && !wifi && !tv){
                            consulta=1;
                        }else if( !ar && wifi && !tv){
                            consulta=2;
                        }else if( !ar && !wifi && tv){
                            consulta=3;
                        }else if( ar && wifi && !tv){
                            consulta=4;
                        }else if( ar && !wifi && tv){
                            consulta=5;
                        }else if( !ar && wifi && tv){
                            consulta=6;
                        }else if( ar && wifi && tv){
                            consulta=7;
                        }    
                    }
                    System.out.println("consulta??"+consulta);
                    List<Quarto> listaQuartos = QuartoFacade.getQuartosByIdHotels(idHospedagem, currentPage, recordsPerPage, consulta);
                    request.setAttribute("listaQuartos", listaQuartos);

                    int rows = QuartoFacade.quantidadeTotalQuartosPorHotel(idHospedagem, consulta);
                    int nOfPages = rows / recordsPerPage;

                    if ((nOfPages % recordsPerPage) > 0) {
                        nOfPages++;
                    }                    
                    
                    request.setAttribute("idHospedagem", idHospedagem);
                    request.setAttribute("qtdQuartos", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);

                    rd = getServletContext().getRequestDispatcher("/lista_quartos_alugar.jsp");   
                    rd.forward(request, response);   
                }else {
                    System.out.println("nao encontrou nada.");
                }
            }else{
                rd = getServletContext().getRequestDispatcher("/erro.jsp");        
                //String msg = "Usuário/senha incorretos.";
                //request.setAttribute("msg", msg);
                rd.forward(request, response);
                return;
            }
        }catch(Exception e){
            rd = getServletContext().getRequestDispatcher("/erro.jsp");  
            System.out.println("erro: "+e.getMessage());
            //String msg = "Usuário/senha incorretos.";
            //request.setAttribute("msg", msg);
            rd.forward(request, response);
            return;
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
