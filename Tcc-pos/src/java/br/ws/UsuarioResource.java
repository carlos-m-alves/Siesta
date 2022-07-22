package br.ws;

import br.bean.Bairro;
import br.bean.Hospedagem;
import br.bean.Parceiro;
import br.bean.Usuario;
import br.facade.AdministradorFacade;
import br.facade.HospedagemlFacade;
import br.facade.UsuarioFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.validator.constraints.NotEmpty;

@Path("/usuario")
public class UsuarioResource {

    public UsuarioResource(){        
    }

    @GET 
    @Path("/{email}/{senha}") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Response buscaUsuario(@PathParam("email") String email, @PathParam("senha") String senha) { 
        System.out.println("chegou pra pega OOO Usuario: "+email);
        try{
            Usuario usuario = UsuarioFacade.getParceiro(2);             
            GenericEntity<Usuario> 
                lista = new GenericEntity<Usuario>(usuario) {};
            return Response.ok().entity(lista).build();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }        
    }
    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response inserirUsuario(Usuario usuario) { 
        // inserção no BD e obtenção do ID
        System.out.println("chegou no WS pra inserir Usuario. "+usuario.getEmail());
        //System.out.println("data: "+usuario.getDatanascimento().toString());
        try {
            //UsuarioFacade.adicionarUsuario(usuario);
            
            Date dtReservada = new SimpleDateFormat("yyyy-MM-dd").parse("1994-01-01");
            //usuario.setDatanascimento(dtReservada);
            System.out.println("chegou ate aki");
            System.out.println("senha"+usuario.getSenha());
            //System.out.println("senha"+usuario.getSenha());
            Usuario a = new Usuario();
            a.setEmail("asdas");
            
            GenericEntity<Usuario> 
                lista = new GenericEntity<Usuario>(a) {};
            return Response.ok().entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    */
    
    //teste
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response inserirCliente(Usuario usuario) { 
        // inserção no BD e obtenção do ID
        System.out.println("chegou no WS pra inserir Cliente");
        try {
            System.out.println("faz qlq coisa");
            GenericEntity<Usuario> 
                lista = new GenericEntity<Usuario>(usuario) {};
            return Response.ok().entity(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /* //quase funciona
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public Usuario inserirCliente(Usuario usuario) { 
        // inserção no BD e obtenção do ID
        System.out.println("chegou no WS pra inserir Cliente");
        try {
            System.out.println("faz qlq coisa");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario; 
    }
    */
 /*    //ESTÁ FUNCIONANDO---NUNCA MAIS MEXER E USAR COMO EXEMPLO
    @GET 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response buscarBairros() { 
        System.out.println("chegou pra pega os bairros");
        try{
            List<Bairro> listaMutantes = AdministradorFacade.getBairros();
            GenericEntity<List<Bairro>> 
                lista = new GenericEntity<List<Bairro>>(listaMutantes) {};
        return Response.ok().entity(lista).build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
 */  
    
    /*
    @GET
    @Path("/{id}") 
    //@Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public Usuario verificarUsuario(@PathParam("id") int id) { 
        //System.out.println("chegou no WS pra inserir Cliente"+email);
        try {
            return UsuarioFacade.getParceiro(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
    */
  /*  
    @POST
    @Path("/{email}/{senha}") 
    //@Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    //public Response getUsuario(@PathParam("id") int id) { 
    public Response getUsuario(@PathParam("email") String email, @PathParam("senha") String senha) { 
        try{
            
            //Parceiro parceiro = UsuarioFacade.getUsuario(email,senha);             
            //GenericEntity<Parceiro> 
            //    lista = new GenericEntity<Parceiro>(parceiro) {};
            //return Response.ok().entity(lista).build();
            return Response.ok().entity("teste").build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
*/
/*    @GET
    @Path("/parceiro") 
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public Response verificaUsuario(@PathParam("parceiro") Usuario usuario) { 
        try{
            //Usuario p = new Usuario(" email", " senha");
            String p = "qlq";
            GenericEntity<String> 
                lista = new GenericEntity<String>(p) {};
        return Response.ok().entity(lista).build();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }*/
}
