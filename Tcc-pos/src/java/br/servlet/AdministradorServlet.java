package br.servlet;

import br.bean.Bairro;
import br.bean.Hospedagem;
import br.bean.Parceiro;
import br.bean.Relatorio;
import br.bean.Usuario;
import br.facade.AdministradorFacade;
import br.facade.HospedagemlFacade;
import br.facade.ReservaFacade;
import br.facade.UsuarioFacade;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
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

@WebServlet(name="AdministradorServlet", urlPatterns={"/AdministradorServlet"})
public class AdministradorServlet extends HttpServlet {
   
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
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

                    List<Hospedagem> listaHospedagens = AdministradorFacade.getHospedagens(currentPage, recordsPerPage);
                    request.setAttribute("listaHospedagens", listaHospedagens);

                    int rows = AdministradorFacade.quantidadeTotalHotel();
                    int nOfPages = rows / recordsPerPage;

                    if (nOfPages % recordsPerPage > 0) {
                        nOfPages++;
                    }

                    request.setAttribute("qtdHospedagem", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);

                    rd = request.getRequestDispatcher("lista_hospedagem.jsp");
                    rd.forward(request, response);      
                }else if ( "index".equalsIgnoreCase(action) ){
                    rd = request.getRequestDispatcher("relatorios.jsp");
                    rd.forward(request, response);     
                }else if ( "paginationFunc".equalsIgnoreCase(action) ){   
                    int currentPage = Integer.valueOf(request.getParameter("currentPage"));
                    int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

                    List<Parceiro> listaParceiros = AdministradorFacade.getParceiros(currentPage, recordsPerPage);
                    request.setAttribute("listaParceiros", listaParceiros);
                    
                    int rows = AdministradorFacade.quantidadeTotalParceiros();
                    int nOfPages = rows / recordsPerPage;

                    if (nOfPages % recordsPerPage > 0) {
                        nOfPages++;
                    }

                    request.setAttribute("qtdParceiros", rows);
                    request.setAttribute("noOfPages", nOfPages);
                    request.setAttribute("currentPage", currentPage);
                    request.setAttribute("recordsPerPage", recordsPerPage);

                    rd = request.getRequestDispatcher("lista_parceiros.jsp");
                    rd.forward(request, response);      
                }else if ( "relatorios".equalsIgnoreCase(action) ){ 
                    /*List<Relatorio> listaRelatorios = ReservaFacade.getLucroPorMes();
                    List<String> listaLucro = new ArrayList<>();
                    List<String> listaLMes = new ArrayList<>();
                    for(Relatorio r : listaRelatorios){
                        listaLucro.add(r.getLucro().toString());
                        listaLMes.add(r.getData().toString());
                    }        
                    request.setAttribute("listaLucro", listaLucro);
                    request.setAttribute("listaLMes", listaLMes);
                    */rd = request.getRequestDispatcher("relatorios.jsp");
                    rd.forward(request, response); 
                }else if ( "infFunc".equalsIgnoreCase(action) ){
                    int idFunc = Integer.valueOf(request.getParameter("idFunc"));
                     
                    Parceiro parceiro = UsuarioFacade.getParceiro(idFunc);
                    System.out.println("data: "+parceiro.getDatanascimento());
                    
                    request.setAttribute("parceiro", parceiro);                    
                    rd = request.getRequestDispatcher("edt_funcionario.jsp");
                    rd.forward(request, response); 
                }else if ( "infHospedagem".equalsIgnoreCase(action) ){
                    int idHospedagem = Integer.valueOf(request.getParameter("idHospedagem"));
                     
                    Hospedagem hospedagem = HospedagemlFacade.getHospedagemById(idHospedagem);
                    request.setAttribute("hospedagem", hospedagem);          
                    
                    List<Bairro> listaBairros = AdministradorFacade.getBairros();
                    request.setAttribute("listaBairros", listaBairros);
                    
                    for(Bairro bairro : listaBairros){
                        if( bairro.getIdBairro() == hospedagem.getIdBairro() ){
                            hospedagem.setBairro(bairro.getDescricao());
                            break;
                        }
                    }
                    //System.out.println("id: "+hospedagem.getId());
                    //System.out.println("id ba: "+hospedagem.getIdBairro());
                    //System.out.println("id end: "+hospedagem.getIdEndereco());
                    //System.out.println("rua: "+hospedagem.getRua());
                    rd = request.getRequestDispatcher("edt_hotel.jsp");
                    rd.forward(request, response); 
                }else if ( "edtHospedagem".equalsIgnoreCase(action) ){
                    int idHospedagem = Integer.valueOf(request.getParameter("idHotelServ"));
                    String nomeHotelServ = request.getParameter("nomeHotelServ");
                    String rua = request.getParameter("enderecoServ");
                    int idBairroServ = Integer.valueOf(request.getParameter("bairroServ"));
                    int numeroServ = Integer.valueOf(request.getParameter("numeroServ"));
                    String complementoServ = request.getParameter("complementoServ");
                    String cepServ = request.getParameter("cepServ");

                    Hospedagem hospedagem = new Hospedagem();
                    hospedagem.setId(idHospedagem);
                    hospedagem.setNome(nomeHotelServ);
                    hospedagem.setRua(rua);
                    hospedagem.setIdBairro(idBairroServ);
                    hospedagem.setNumero(numeroServ);
                    hospedagem.setComplemento(complementoServ);
                    hospedagem.setCep(cepServ);
                    
                    HospedagemlFacade.atualizaHospedagem(hospedagem);
                    
                    request.setAttribute("msg", "Informações da Hospedagem atualizadas!");
                    rd = request.getRequestDispatcher("AdministradorServlet?action=pagination&recordsPerPage=5&currentPage=1");
                    rd.forward(request, response); 
                }else if ( "edtParceiro".equalsIgnoreCase(action) ){
                    int idParceiro = Integer.valueOf(request.getParameter("parceiroIdServ"));
                    String nome = request.getParameter("nomeServ");
                    String cpf = request.getParameter("cpfServ");
                    String dataNasc = request.getParameter("dataNascServ");                    
                    Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(dataNasc);
                    
                    Parceiro parceiro = new Parceiro();
                    parceiro.setId(idParceiro);
                    parceiro.setNome(nome);
                    parceiro.setCpf(cpf.replace(".", "").replace("-", ""));
                    parceiro.setDatanascimento(dt);
                    
                    UsuarioFacade.atualizaParceiro(parceiro);
                    request.setAttribute("msg", "Informações do Parceiro atualizadas!");
                    rd = request.getRequestDispatcher("AdministradorServlet?action=paginationFunc&recordsPerPage=5&currentPage=1");
                    rd.forward(request, response); 
                }else if ( "addFunc".equalsIgnoreCase(action) ){
                    //List<Bairro> listaBairros = AdministradorFacade.getBairros();
                    //request.setAttribute("listaBairros", listaBairros);
                    
                    rd = request.getRequestDispatcher("add_funcionario.jsp");
                    rd.forward(request, response); 
                }else if ( "addHospedagem".equalsIgnoreCase(action) ){
                    List<Bairro> listaBairros = AdministradorFacade.getBairros();
                    List<Parceiro> listaParceiros = AdministradorFacade.getParceiros();
         
                    request.setAttribute("listaParceiros", listaParceiros);                                        
                    request.setAttribute("listaBairros", listaBairros);
                    
                    rd = request.getRequestDispatcher("add_hotel.jsp");
                    rd.forward(request, response); 
                }else if ( "insereHotel".equalsIgnoreCase(action) ){                    
                     int parceiroServ = Integer.parseInt(request.getParameter("parceiroServ"));
                     String nomeHotelServ = request.getParameter("nomeHotelServ");
                     String enderecoServ = request.getParameter("enderecoServ");
                     int bairroServ = Integer.parseInt(request.getParameter("bairroServ"));
                     int numeroServ = Integer.parseInt(request.getParameter("numeroServ"));
                     System.out.println("num: "+numeroServ);
                     String complementoServ = request.getParameter("complementoServ");
                     System.out.println("comp: "+complementoServ);
                     String cepServ = request.getParameter("cepServ");
                     
                     System.out.println("bairro: "+bairroServ);
                     System.out.println("parceiro: "+parceiroServ);
                                         
                     Hospedagem hospedagem = new Hospedagem();
                     hospedagem.setNome(nomeHotelServ);
                     hospedagem.setRua(enderecoServ);
                     hospedagem.setIdParceiro(parceiroServ);
                     hospedagem.setIdBairro(bairroServ);
                     hospedagem.setNumero(numeroServ);
                     hospedagem.setComplemento(complementoServ);
                     hospedagem.setCep(cepServ.replace("-", ""));
                     
                     HospedagemlFacade.adicionarHospedagem(hospedagem);
                     
                     //enviar msg para mostrar na tela??
                     request.setAttribute("msg", "Hotel cadastrado com sucesso!");
                     rd = request.getRequestDispatcher("AdministradorServlet?action=pagination&currentPage=1&recordsPerPage=5");
                     rd.forward(request, response); 
                }else if ( "insereParc".equalsIgnoreCase(action) ){
                    String nomeServ = request.getParameter("nomeServ");
                    String cpfServ = request.getParameter("cpfServ");
                    String dataNascServ = request.getParameter("dataNascServ");
                    Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascServ);
                    
                    String emailServ = request.getParameter("emailServ");
                    System.out.println("chegou aqui: "+nomeServ);
                    System.out.println("chegou aqui: "+cpfServ);
                    System.out.println("chegou aqui: "+dataNascServ);
                    System.out.println("chegou aqui: "+emailServ);
                    
                    /////// SENHA CRIPTOGRAFADA ////////////////////
                    MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                    byte messageDigest[] = algorithm.digest(("123456").getBytes("UTF-8"));

                    StringBuilder hexString = new StringBuilder();
                    for (byte b : messageDigest) {
                      hexString.append(String.format("%02X", 0xFF & b));
                    }
                    String senhaCriptografada = hexString.toString();
                    ///////////////////////////
                    
                    Usuario u = new Usuario();
                    u.setNome(nomeServ);
                    u.setCpf(cpfServ.replace(".", "").replace("-", ""));
                    u.setDatanascimento(dt);
                    u.setEmail(emailServ);
                    u.setSenha(senhaCriptografada);
                    u.setTipo('P');
                    UsuarioFacade.adicionarParceiro(u);
                    
                    //enviar msg para mostrar na tela??
                    request.setAttribute("msg", "Parceiro cadastrado com sucesso!");
                    rd = request.getRequestDispatcher("AdministradorServlet?action=paginationFunc&currentPage=1&recordsPerPage=5");
                    rd.forward(request, response); 
                }else if ( "delHospedagem".equalsIgnoreCase(action) ){
                    int idHospedagem = Integer.valueOf(request.getParameter("idHospedagem"));
                    
                    HospedagemlFacade.deletaHospedagem(idHospedagem);
                    
                    request.setAttribute("msg", "Hotel deletado com sucesso!");
                    rd = request.getRequestDispatcher("AdministradorServlet?action=pagination&currentPage=1&recordsPerPage=5");
                    rd.forward(request, response); 
                }else if ( "delParceiro".equalsIgnoreCase(action) ){
                    int idParceiro = Integer.valueOf(request.getParameter("idParceiro"));
                    System.out.println("id parceiro para excluir: "+idParceiro);
                    
                    UsuarioFacade.deletaParceiro(idParceiro);
                    
                    request.setAttribute("msg", "Parceiro deletado com sucesso!");
                    rd = request.getRequestDispatcher("AdministradorServlet?action=paginationFunc&currentPage=1&recordsPerPage=5");
                    rd.forward(request, response); 
                }                                 
            }else if ( request.getParameter("action").equals("insereUsu") ){
                String nomeServ = request.getParameter("nomeServ");
                String cpfServ = request.getParameter("cpfServ");
                String dataNascServ = request.getParameter("dataNascServ");
                Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascServ);                    
                String emailServ = request.getParameter("emailServ");
                String senhaServ = request.getParameter("senhaServ");
                String confSenhaServ = request.getParameter("confSenhaServ");
                System.out.println("chegou aqui: "+nomeServ);
                System.out.println("chegou aqui: "+cpfServ);
                System.out.println("chegou aqui: "+dataNascServ);
                System.out.println("chegou aqui: "+emailServ);
                System.out.println("chegou aqui: "+senhaServ);
                System.out.println("chegou aqui: "+confSenhaServ);

                /////// SENHA CRIPTOGRAFADA ////////////////////
                MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = algorithm.digest((senhaServ).getBytes("UTF-8"));

                StringBuilder hexString = new StringBuilder();
                for (byte b : messageDigest) {
                  hexString.append(String.format("%02X", 0xFF & b));
                }
                String senhaCriptografada = hexString.toString();
                ///////////////////////////

                Usuario u = new Usuario();
                u.setNome(nomeServ);
                u.setCpf(cpfServ.replace(".", "").replace("-", ""));
                u.setDatanascimento(dt);
                u.setEmail(emailServ);
                u.setSenha(senhaCriptografada);
                u.setTipo('U');
                u.setSenha(senhaCriptografada);
                UsuarioFacade.adicionarUsuario(u);

                //enviar msg para mostrar na tela??
                request.setAttribute("msg", "Cadastrado com sucesso!");
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response); 
            }
        }catch(Exception e){
            System.out.println("deu erro admServlet");
            rd = getServletContext().getRequestDispatcher("/erro.jsp");        
            System.out.println("erro: "+e.getMessage());
            String msg = "Erro no servidor. ";
            //request.setAttribute("msg", msg+e.getMessage());
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
