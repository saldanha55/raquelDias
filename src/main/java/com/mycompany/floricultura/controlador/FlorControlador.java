/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.controlador;

import com.mycompany.floricultura.modelo.dao.FlorDAO;
import com.mycompany.floricultura.modelo.dao.FornecedorDAO;
import com.mycompany.floricultura.modelo.dao.entidade.Flor;
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

@WebServlet(WebConstantes.BASE_PATH+"/FlorControlador")
public class FlorControlador extends HttpServlet {
    

    private Fornecedor objFornecedor;
    private FornecedorDAO objFornecedorDAO;
    private Flor objFlor;
    private FlorDAO objFlorDAO;

    String nome= "", codFlor = "", codFornecedor = "", preco = "", quantidadeEst="",
            opcao;

    @Override
    public void init() throws ServletException {
        objFornecedorDAO = new FornecedorDAO();
        objFornecedor = new Fornecedor();
        objFlor = new Flor();
        objFlorDAO = new FlorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            codFlor = request.getParameter("codFlor");
            nome= request.getParameter("nome");
            preco = request.getParameter("preco");
            quantidadeEst = request.getParameter("quantidadeEst");         
            codFornecedor = request.getParameter("codFornecedor");
            
            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }

        } catch (NumberFormatException ex) { // lida com erros de conversão de tipo numérico
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            response.getWriter().println("Erro: " + ex.getMessage());

        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFlor.setNome(nome);
        objFlor.setPreco(Double.valueOf(preco));
        objFlor.setQuntidadeEst(Integer.parseInt(quantidadeEst));
        objFlor.getObjFornecedor().setCodFornecedor(Integer.parseInt(codFornecedor));
        objFlorDAO.salvar(objFlor);
        encaminharParaPagina(request, response);

    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codFlor", codFlor);
        request.setAttribute("nome", nome);
        request.setAttribute("preco", preco);
        request.setAttribute("codFornecedor", codFornecedor);
        request.setAttribute("quantidadeEst", quantidadeEst);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);

    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codFlor", codFlor);
        request.setAttribute("nome", nome);
        request.setAttribute("preco", preco);
        request.setAttribute("quantidadeEst", quantidadeEst);
        request.setAttribute("codFornecedor", codFornecedor);
        request.setAttribute("mensagem", "Clique no botão salvar para excluir");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);

    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFlor.setCodFlor(Integer.parseInt(codFlor));
        objFlor.setNome(nome);
        objFlor.setQuntidadeEst(Integer.parseInt(quantidadeEst));
        objFlor.setPreco(Double.valueOf(preco));
        objFlor.getObjFornecedor().setCodFornecedor(Integer.parseInt(codFornecedor));
        objFlorDAO.alterar(objFlor);
        encaminharParaPagina(request, response);

    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFlor.setCodFlor(Integer.parseInt(codFlor));
        objFlorDAO.excluir(objFlor);
        encaminharParaPagina(request, response);

    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fornecedor> listaFornecedor = objFornecedorDAO.buscarTodosFornecedores();
        request.setAttribute("listaFornecedor", listaFornecedor);
        List<Flor> listaFlor = objFlorDAO.buscarTodosFlor();
        request.setAttribute("listaFlor", listaFlor);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroFlor.jsp");
        encaminhar.forward(request, response);

    }

    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codFlor", "0");
        request.setAttribute("nome", "");
        request.setAttribute("preco", "");
        request.setAttribute("quantidadeEst", "");        
        request.setAttribute("codFornecedor", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);

    }
    
    
    
    
}
