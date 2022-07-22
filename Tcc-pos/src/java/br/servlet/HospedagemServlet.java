package br.servlet;

import br.bean.Hospedagem;
import br.facade.AdministradorFacade;
import br.facade.HospedagemlFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.postgresql.util.PSQLException;

@WebServlet(name="HotelServlet", urlPatterns={"/HotelServlet"})
public class HospedagemServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        try {
            HttpSession session = request.getSession(); 
            System.out.println("chegando aki para buscar por bairro??");
            if ( session.getAttribute("Usuario") != null){      
                String action = request.getParameter("action");
                //System.out.println("oq tem no action do HotelServlet: "+action);
                if ( "list".equalsIgnoreCase(action) ){
                    //pega id do funcionario do hotel
                    int idHospedagem = Integer.valueOf(request.getParameter("idHospedagem"));
                    //primeira pagina com paginação. Seta valor hardoce
                    List<Hospedagem> listaHospedagens = HospedagemlFacade.getHospedagens(5,1,idHospedagem);
                    //List<Hotel> listaHotel = HotelFacade.getTodosHoteis();
                    request.setAttribute("listaHospedagens", listaHospedagens);   
                    
                    int qtdHospedagens = HospedagemlFacade.quantidadeTotalHospedagens();
                    request.setAttribute("qtdHospedagens", qtdHospedagens);  
                    /**/
                    //rd = getServletContext().getRequestDispatcher("/lista_hotel.jsp");
                    
                    //teste paginacao
                    int rows = HospedagemlFacade.quantidadeTotalHospedagens();
        
                    int nOfPages = rows / 5;

                    if (nOfPages % 5 > 0) {
                        nOfPages++;
                    }
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", 1);
                    request.setAttribute("recordsPerPage", 5);
                    rd = getServletContext().getRequestDispatcher("/testePaginacao.jsp");
                    rd.forward(request, response);                    
                }else if ( "pagination".equalsIgnoreCase(action) ){
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
                    int idHospedagem = Integer.valueOf(request.getParameter("idHospedagem"));

                    List<Hospedagem> listaHospedagem = HospedagemlFacade.getHospedagens(currentPage, recordsPerPage, idHospedagem);
                    request.setAttribute("listaHospedagem", listaHospedagem);

                    int rows = HospedagemlFacade.quantidadeTotalHospedagens();
                    int nOfPages = rows / recordsPerPage;

                    if (nOfPages % recordsPerPage > 0) {
                        nOfPages++;
                    }

                    request.setAttribute("qtdHospedagem", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("lista_hospedagem.jsp");
                    dispatcher.forward(request, response);      
                }
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
