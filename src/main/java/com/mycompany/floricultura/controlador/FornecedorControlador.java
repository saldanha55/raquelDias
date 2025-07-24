/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.controlador;



import com.mycompany.floricultura.modelo.dao.FornecedorDAO;
import com.mycompany.floricultura.modelo.dao.entidade.Fornecedor;
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
@WebServlet(WebConstantes.BASE_PATH+"/FornecedorControlador")
public class FornecedorControlador   extends HttpServlet{
    
        
    
    
    
    private Fornecedor objFornecedor;
    private FornecedorDAO objFornecedorDao;
    String nome ="", cnpj = "", codFornecedor ="", telefone ="", email ="", endereco ="";

    @Override
    public void init() throws ServletException {
        objFornecedorDao = new FornecedorDAO();
        objFornecedor = new Fornecedor ();
        
    }
    
    
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           String opcao = request.getParameter("opcao");
           if(opcao==null||opcao.isEmpty()){
               opcao="cadastrar";
           }
           nome= request.getParameter("nome");
           cnpj= request.getParameter("cnpj");
           codFornecedor= request.getParameter("codFornecedor");
           telefone= request.getParameter("telefone");
           email= request.getParameter("email");
           endereco= request.getParameter("endereco");  
           
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
                      case "cancelar":
                    cancelar(request, response);
                    break;
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
         objFornecedor.setNome(nome);
         objFornecedor.setCnpj(cnpj);
         objFornecedor.setEmail(email);
         objFornecedor.setEndereco(endereco);
         objFornecedor.setTelefone(telefone);
         objFornecedorDao.salvar(objFornecedor);
    
         encaminharParaPagina(request, response);
     }
     
     private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
         List<Fornecedor> listafornecedor = objFornecedorDao.buscarTodosFornecedores();
         //List<Cidade> listaCidade = objCidadeDao.buscarTodasCidades();
         request.setAttribute("listaFornecedor",listafornecedor);
         RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroFornecedor.jsp");
         encaminhar.forward(request, response);
     }
     
      protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          request.setAttribute("codFornecedor", codFornecedor);
          request.setAttribute("nome", nome);
          request.setAttribute("cnpj", cnpj);
          request.setAttribute("telefone", telefone);
          request.setAttribute("endereco", endereco);
          request.setAttribute("email", email);
          request.setAttribute("mensagem","edite os dados e clique em salvar");
          request.setAttribute("opcao", "confirmarEditar");
          encaminharParaPagina(request, response);
      }
      
       protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           objFornecedor.setCodFornecedor(Integer.parseInt(codFornecedor));
           objFornecedor.setNome(nome);
           objFornecedor.setEmail(email);
           objFornecedor.setEndereco(endereco);
           objFornecedor.setCnpj(cnpj);
           objFornecedor.setTelefone(telefone);
           objFornecedorDao.alterar(objFornecedor);
           encaminharParaPagina(request, response);
       }
    
  protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      objFornecedor.setCodFornecedor(Integer.parseInt(codFornecedor));
      objFornecedorDao.excluir(objFornecedor);
   encaminharParaPagina(request, response);
  }
    protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("nome", nome);
    request.setAttribute("codFornecedor", codFornecedor);
    request.setAttribute("cnpj", cnpj);
    request.setAttribute("telefone", telefone);
    request.setAttribute("endereco", endereco);
    request.setAttribute("email", email);   
    request.setAttribute("mensagem","clique em salvar para excluir");
    request.setAttribute("opcao", "confirmarExcluir");
    encaminharParaPagina(request, response);
    
}
   /* private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fornecedor> listaFornecedor = objFornecedorDao.buscarTodosFornecedores();
        request.setAttribute("listaFornecedor", listaFornecedor);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroFornecedor.jsp");
        encaminhar.forward(request, response);

    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fornecedor> listaFornecedor = objFornecedorDao.buscarTodosFornecedores();
        request.setAttribute("listaFornecedor", listaFornecedor);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroFornecedor.jsp");
        encaminhar.forward(request, response);
    */
    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codFornecedor", "0");
        request.setAttribute("nome", "");
        request.setAttribute("cnpj", "");
        request.setAttribute("endereco", "");
        request.setAttribute("email", "");
        request.setAttribute("telefone", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);

    }
}
