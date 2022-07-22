package br.servlet;

import br.bean.Login;
import br.facade.LoginFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd;
        try {
            HttpSession session = request.getSession();            
            String action = request.getParameter("action");
            //System.out.println("se quer chegou aqui??loginServlet: "+action);
            if ( session.getAttribute("Usuario") == null){              
                System.out.println("oq tem no action: "+action);
                if ( "login".equalsIgnoreCase(action) ){
                    String email = request.getParameter("email");
                    if(email.equals("")) return;
                    String senha = request.getParameter("senha");    
                    if(senha.equals("")) return;
                    System.out.println("email digitado: "+email);
                    
                    MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                    byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

                    StringBuilder hexString = new StringBuilder();
                    for (byte b : messageDigest) {
                      hexString.append(String.format("%02X", 0xFF & b));
                    }
                    String senhaCriptografada = hexString.toString();
                    //System.out.println("senha crip: "+senhaCriptografada);
                    //puxar um dAO aki
                    Login usuario = new Login(email,senhaCriptografada);
                    Login u = LoginFacade.verificaUsuario(usuario);
                    if( u != null){
                        //System.out.println("encontrou!! Usuario logado!");
                        session.setAttribute("Usuario", u);        
                        session.setAttribute("logado", true); 
                        
                        if(u.getTipo() == 'A'){
                            System.out.println("é administrador");
                            rd = getServletContext().getRequestDispatcher("/LoginServlet?action=listA");   
                            rd.forward(request, response);  
                        }else if(u.getTipo() == 'P'){
                            System.out.println("é parceiro");
                            System.out.println("id da hospedagem do parceiro: "+u.getIdHospedagem());
                            rd = getServletContext().getRequestDispatcher("/LoginServlet?action=listP&idHospedagem="+u.getIdHospedagem());   
                            rd.forward(request, response);
                        }else if(u.getTipo() == 'U'){
                            System.out.println("é usuario");
                            //System.out.println("id da hospedagem do parceiro: "+u.getIdHospedagem());
                            rd = getServletContext().getRequestDispatcher("/LoginServlet?action=listU");   
                            //rd = getServletContext().getRequestDispatcher("/RotaHospedagem.jsp");   
                            rd.forward(request, response);
                        }                        
                        return;
                    }else{
                        System.out.println("nao encontrou nada");
                        rd = getServletContext().getRequestDispatcher("/index.jsp");        
                        String msg = "Usuário/senha incorretos.";
                        request.setAttribute("msg", msg);
                        rd.forward(request, response);
                        return;
                    }                    
                }
            }else if ( "listA".equalsIgnoreCase(action) ){
                System.out.println("chegou aqui na pg inicial Adm ");
                rd = getServletContext().getRequestDispatcher("/AdministradorServlet?action=index");   
                rd.forward(request, response);
            }else if ( "listP".equalsIgnoreCase(action) ){
                //System.out.println("chegou aqui na pg inicial Parceiro ");
                int idHospedagem = Integer.parseInt(request.getParameter("idHospedagem"));
                //System.out.println("idHosp q ta vindo: "+idHospedagem);
                rd = getServletContext().getRequestDispatcher("/QuartoServlet?action=pagination&id="+idHospedagem+"&recordsPerPage=5&currentPage=1");   
                rd.forward(request, response);
            }else if ( "listU".equalsIgnoreCase(action) ){
                System.out.println("chegou aqui na pg inicial Parceiro ");
                //int idHospedagem = Integer.parseInt(request.getParameter("idHospedagem"));
                //System.out.println("idHosp q ta vindo: "+idHospedagem);
                rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=pagination&recordsPerPage=5&currentPage=1");   
                rd.forward(request, response);
            }else{
                System.out.println("Usuario já está logado!");  
                Login user = (Login)session.getAttribute("Usuario");
                 
                switch (user.getTipo()) {
                    case 'A':
                        //System.out.println("é administrador");
                        rd = getServletContext().getRequestDispatcher("/LoginServlet?action=listA");
                        rd.forward(request, response);
                        break;
                    case 'P':
                        /*
                        Login a = (Login)session.getAttribute("Usuario");
                        a.setIdHospedagem(1);
                        session.setAttribute("usuario", a);
                        */
                        //System.out.println("é parceiro: "+user.getIdHospedagem());
                        rd = getServletContext().getRequestDispatcher("/LoginServlet?action=listP&idHospedagem="+user.getIdHospedagem());
                        rd.forward(request, response);
                        break;
                    case 'U':
                        System.out.println("é usuario");
                        rd = getServletContext().getRequestDispatcher("/UsuarioServlet?action=pagination&recordsPerPage=5&currentPage=1");   
                        rd.forward(request, response);
                        //rd = getServletContext().getRequestDispatcher("/RotaHospedagem.jsp");
                        //rd.forward(request, response);
                        break;
                    default:
                        break;
                }
            }
        }catch(Exception e){
            System.out.println("deu erro login");
            rd = getServletContext().getRequestDispatcher("/erro.jsp");        
            String msg = "Usuário/senha incorretos.";
            request.setAttribute("msg", msg);
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
