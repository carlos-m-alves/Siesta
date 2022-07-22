package br.servlet;

import br.bean.Relatorio;
import br.facade.ReservaFacade;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="AjaxServletRelatorio", urlPatterns={"/AjaxServletRelatorio"})
public class AjaxServletRelatorio extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Exception {
        try{           
            List<Relatorio> listaRelatorios = ReservaFacade.getQtdHospedagens();
            /*
            Relatorio r = new Relatorio();
            r.setBairro("Centro");            
            r.setQuantidade(6);
            listaRelatorios.add(r);
            r = new Relatorio();
            r.setBairro("Água Verde");            
            r.setQuantidade(5);
            listaRelatorios.add(r);            
            r = new Relatorio();
            r.setBairro("Cabral");            
            r.setQuantidade(3);
            listaRelatorios.add(r);
            */
            //transforma map em JSON
            String json = new Gson().toJson(listaRelatorios);
            
            //retorna JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);           
        }catch(IOException | NumberFormatException e){
            e.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AjaxServletRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AjaxServletRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
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
