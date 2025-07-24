/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.controlador;

import com.mycompany.floricultura.modelo.dao.ClienteDAO;
import com.mycompany.floricultura.modelo.dao.entidade.Cliente;
import com.mycompany.floricultura.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 13754639692
 */
//designar uma classe como servelets, mapeamento de url no servidor web
@WebServlet(WebConstantes.BASE_PATH+"/ClienteControlador")
public class ClienteControlador extends HttpServlet {
    
    
    
    
    private Cliente objCliente;
    private ClienteDAO objClienteDao;
    String nomeCliente ="", cpfCliente = "", codCliente ="", telefoneCliente ="", emailCliente ="", enderecoCliente ="", rgCliente="";

    @Override
    public void init() throws ServletException {
        objClienteDao = new ClienteDAO();
        objCliente = new Cliente ();
        
    }
    
    
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           String opcao = request.getParameter("opcao");
           if(opcao==null||opcao.isEmpty()){
               opcao="cadastrar";
           }
           nomeCliente= request.getParameter("nomeCliente");
           cpfCliente= request.getParameter("cpfCliente");
           codCliente= request.getParameter("codCliente");
           telefoneCliente= request.getParameter("telefoneCliente");
           emailCliente= request.getParameter("emailCliente");
           rgCliente= request.getParameter("rgCliente");
           enderecoCliente= request.getParameter("enderecoCliente");  
           
           switch (opcao) {
               case "cadastrar":
                   cadastrar(request,response);
                   break;
               case "editar":
                   editar(request,response);
                    break;
                    case "confirmarEditar":
                   confirmarEditar(request,response);
                    break;
                    case "excluir":
                   excluir(request,response);
                    break;

                    case "confirmarExcluir":
                      confirmarExcluir(request,response);
               default:
                   throw new IllegalArgumentException("opção invalida:"+opcao);
                   
           }
       }catch(NumberFormatException ex){
           response.getWriter().println("Erro: um ou mais parametros não são números válidos"+ex.getLocalizedMessage());
       }catch(IllegalArgumentException ex){
           response.getWriter().println("Erro:"+ex.getLocalizedMessage());
       }
    }
     protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
         objCliente.setNomeCliente(nomeCliente);
         objCliente.setCpfCliente(cpfCliente);
         objCliente.setTelefoneCliente(telefoneCliente);
         objCliente.setEmailCliente(emailCliente);
         objCliente.setRgCliente(rgCliente);
         objCliente.setEnderecoCliente(enderecoCliente);
         objClienteDao.salvar(objCliente);
         encaminharParaPagina(request, response);
     }
     
     private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         List<Cliente> listaCliente = objClienteDao.buscarTodosClientes();
         //List<Cidade> listaCidade = objCidadeDao.buscarTodasCidades();
         request.setAttribute("listaCliente",listaCliente);
         RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroCliente.jsp");
         encaminhar.forward(request, response);
     }
     
      protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("codCliente", codCliente);
    request.setAttribute("nomeCliente",nomeCliente);
    request.setAttribute("cpfCliente", cpfCliente);
    request.setAttribute("telefoneCliente", telefoneCliente);
    request.setAttribute("emailCliente", emailCliente);
    request.setAttribute("rgCliente",rgCliente);
    request.setAttribute("enderecoCliente", enderecoCliente);
    request.setAttribute("mensagem","edite os dados e clique em salvar");
    request.setAttribute("opcao", "confirmarEditar");
    encaminharParaPagina(request, response);
      }
      
       protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    objCliente.setCodCliente(Integer.valueOf(codCliente));
    objCliente.setNomeCliente(nomeCliente);
    objCliente.setCpfCliente(cpfCliente);
    objCliente.setTelefoneCliente(telefoneCliente);
    objCliente.setEmailCliente(emailCliente);
    objCliente.setRgCliente(rgCliente);
    objCliente.setEnderecoCliente(enderecoCliente);
    objClienteDao.alterar(objCliente);
    
    
    encaminharParaPagina(request, response);
       }
    
  protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       objCliente.setCodCliente(Integer.valueOf(codCliente));
       objClienteDao.excluir(objCliente);
   encaminharParaPagina(request, response);
  }
    protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("codCliente", codCliente);
    request.setAttribute("nomeCliente",nomeCliente);
    request.setAttribute("cpfCliente", cpfCliente);
    request.setAttribute("telefoneCliente", telefoneCliente);
    request.setAttribute("emailCliente", emailCliente);
    request.setAttribute("rgCliente",rgCliente);
    request.setAttribute("enderecoCliente", enderecoCliente);
    request.setAttribute("mensagem","clique em salvar para excluir");
    request.setAttribute("opcao", "confirmarExcluir");
    encaminharParaPagina(request, response);
    
}
}
