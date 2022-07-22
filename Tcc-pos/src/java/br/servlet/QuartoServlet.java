package br.servlet;

import br.bean.Adicional;
import br.bean.Horario;
import br.bean.Login;
import br.bean.Quarto;
import br.facade.AdicionalFacade;
import br.facade.HorarioFacade;
import br.facade.HospedagemlFacade;
import br.facade.ImagemFacade;
import br.facade.QuartoFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name="QuartoServlet", urlPatterns={"/QuartoServlet"})
public class QuartoServlet extends HttpServlet {
   
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
                if ( "pagination".equalsIgnoreCase(action) ){
                    int idHospedagem = Integer.valueOf(request.getParameter("idHospedagem"));
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

                    List<Quarto> listaQuartos = QuartoFacade.getQuartosByIdHotels(idHospedagem, currentPage, recordsPerPage, 0);
                    request.setAttribute("listaQuartos", listaQuartos);

                    int rows = QuartoFacade.quantidadeTotalQuartosPorHotel(idHospedagem, 0);
                    int nOfPages = rows / recordsPerPage;

                    if ((nOfPages % recordsPerPage) > 0) {
                        nOfPages++;
                    }
                    //System.out.println("res da conta: "+(nOfPages % recordsPerPage));
                    //System.out.println("qtd de paginas: "+nOfPages);    
                    //System.out.println("qtd de recordsPerPage: "+recordsPerPage);    
                    
                    
                    //request.setAttribute("idHospedagem", listaQuartos.get(0).getIdHospedagem());
                    request.setAttribute("idHospedagem", idHospedagem);
                    request.setAttribute("qtdQuartos", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);

                    rd = getServletContext().getRequestDispatcher("/lista_quartos_hospedagem.jsp");   
                    rd.forward(request, response);     
                }else if ( "inf".equalsIgnoreCase(action) ){
                    int id = Integer.valueOf(request.getParameter("idQuarto"));

                    Quarto quarto = QuartoFacade.getQuartoById(id);
                    List<Horario> listaHorarios = HorarioFacade.getHorarioByQuartoId(id);
                    //System.out.println("nome imagem 1: "+quarto.getListaImagemFirebase().get(0));
                    request.setAttribute("quarto", quarto);
                    request.setAttribute("listaHorarios", listaHorarios);
                    rd = getServletContext().getRequestDispatcher("/quarto.jsp");   
                    rd.forward(request, response);
                }else if ( "editar".equalsIgnoreCase(action) ){
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                     System.out.println("chegou aqui na servlet EDITAR. "+idQuarto);
                    Quarto quarto = QuartoFacade.getQuartoByIdWithComp(idQuarto);
  
                    request.setAttribute("quarto", quarto);                  
                    rd = getServletContext().getRequestDispatcher("/edt_quarto.jsp");   
                    rd.forward(request, response);
                }else if ( "add".equalsIgnoreCase(action) ){                    
                    rd = getServletContext().getRequestDispatcher("/add_quarto.jsp");   
                    rd.forward(request, response);
                }else if ( "insere".equalsIgnoreCase(action) ){
                    System.out.println("chegou para inserir quarto");
                    Login login = (Login)session.getAttribute("Usuario");                  
                    String nomeQuartoServ = request.getParameter("nomeQuartoServ");
                    System.out.println(""+nomeQuartoServ);
                    String descricaoServ = request.getParameter("descricaoServ");
                    System.out.println(""+descricaoServ);
                    Double precoServ = Double.valueOf(request.getParameter("precoServ").replace(",", "."));  
                    System.out.println(""+precoServ);
                    //Double classificacao = Double.valueOf(request.getParameter("classificacaoServ")); 
                    //System.out.println(""+classificacao);
                    String[] nomesImagens =  request.getParameterValues("nomeUidServ")[0].split(","); 
                    System.out.println(""+nomesImagens[0]);
                    System.out.println(""+nomesImagens[1]);
                    System.out.println(""+nomesImagens[2]);
                    String[] addArray =  request.getParameterValues("adicionaisServ")[0].split(",");                   
                    for(String a : addArray){
                        System.out.println("add: "+a);
                    }
                    Quarto quarto = new Quarto();
                    quarto.setIdHospedagem(login.getIdHospedagem());
                    quarto.setNome(nomeQuartoServ);
                    quarto.setDescricao(descricaoServ);
                    quarto.setPreco(precoServ);
                    //quarto.setClassificacao(classificacao);
                    
                    //lista auxiliar para identificar o id do item
                    List<Adicional> listaAddEscolhidaPeloUsuario = new ArrayList<>();
                    //procura id dos itens adicionais                    
                    //transforma array de string em array de int
                    if( !addArray[0].equals("") ){
                        System.out.println("entrou aqui no if");
                        for(String item : addArray){
                            listaAddEscolhidaPeloUsuario.add(new Adicional(Integer.parseInt(item)));
                        }                    
                        quarto.setListaAdicionais(listaAddEscolhidaPeloUsuario);
                    }
                    /////////////////////////////////////////////////////////////
                    //cria lista com o nome das imagens
                    List<String> listaImagens = new ArrayList<>();                
                    //transforma array de string em lista
                    for(String item : nomesImagens){
                        listaImagens.add(item);
                    } 
                    quarto.setListaImagemFirebase(listaImagens);
                    
                    System.out.println("inseriu a lista de imagens no vetor");
                    /////////////////////////////////////////////////////////////
                    //insert na tabela QUARTO
                    int idQuartoAdicionado = QuartoFacade.adicionaQuarto(quarto);
                    //System.out.println("id do quarto: "+idQuartoAdicionado);
                    quarto.setId(idQuartoAdicionado); //pega retorno da funcao acima
                    //insert na tabela QUARTO
                    //a funcao abaixo só insere os adicionais
                    //se for nulo nao precisa chamar
                    if( !addArray[0].equals("") ){
                        AdicionalFacade.adicionaQuartoEItemAdicional(quarto);
                    }
                    //insert na tabela IMAGEM
                    ImagemFacade.adicionaImagemQuarto(quarto);
                    
                    //redireciona para página de lista
                    
                    rd = getServletContext().getRequestDispatcher("/LoginServlet?action=listF&idHotel="+login.getIdHospedagem());   
                    rd.forward(request, response);
                    
                }else if ( "edtForm".equalsIgnoreCase(action) ){ 
                    Login login = (Login)session.getAttribute("Usuario");  
                    
                    int idQuarto = Integer.valueOf(request.getParameter("idQuartoServ"));
                    //System.out.println("add vindo da pagina: "+idQuarto);
                    String nomeQuartoServ = request.getParameter("nomeQuartoServ");
                    //System.out.println("add vindo da pagina: "+nomeQuartoServ);
                    String descricaoServ = request.getParameter("descricaoServ");
                    //System.out.println("add vindo da pagina: "+descricaoServ);
                    Double precoServ = Double.valueOf(request.getParameter("precoServ").replace(",", "."));  
                    //System.out.println("add vindo da pagina: "+precoServ);
                    String[] addArray =  request.getParameterValues("adicionaisServ")[0].split(",");    
                                   
                    Quarto quarto = new Quarto();
                    quarto.setId(idQuarto);
                    List<Adicional> listaAddEscolhidaPeloUsuario = new ArrayList<>();
                    //procura id dos itens adicionais                    
                    //transforma array de string em array de int
                    for(String item : addArray){
                        listaAddEscolhidaPeloUsuario.add(new Adicional(Integer.parseInt(item)));
                    }                    
                    quarto.setListaAdicionais(listaAddEscolhidaPeloUsuario);
                    
                    for(Adicional a : quarto.getListaAdicionais()){
                        System.out.println("add vindo da pagina: "+a.getId());
                    }
                    
                    
                    AdicionalFacade.deletaParceiro(idQuarto);
                    AdicionalFacade.adicionaQuartoEItemAdicional(quarto);
                    
                    //redireciona para página de lista 
                    request.setAttribute("msg", "Quarto editado!");  
                    rd = getServletContext().getRequestDispatcher("/LoginServlet?action=listF&idHotel="+login.getIdHospedagem());   
                    rd.forward(request, response);
                    
                }else if ( "delQuarto".equalsIgnoreCase(action) ){ 
                    Login login = (Login)session.getAttribute("Usuario");  
                    //System.out.println("chegou para deletar quarto"+request.getParameter("idQuarto"));
                    int idQuarto = Integer.valueOf(request.getParameter("idQuarto"));
                    System.out.println("id del: "+idQuarto);
                    QuartoFacade.deleteQuarto(idQuarto);
                    
                    request.setAttribute("msg", "Quarto deletado com sucesso!");
                    rd = request.getRequestDispatcher("QuartoServlet?action=pagination&currentPage=1&recordsPerPage=5&idHospedagem="+login.getIdHospedagem());
                    rd.forward(request, response); 
                }
            }
        }catch(Exception e){
            System.out.println("erro: "+ e.getMessage());
            System.out.println("erro: "+ e.toString());
            
            rd = getServletContext().getRequestDispatcher("/erro.jsp");        
            String msg = e.getMessage();
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
