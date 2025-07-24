package com.mycompany.floricultura.controlador;

import com.mycompany.floricultura.modelo.dao.VendaDAO;
import com.mycompany.floricultura.modelo.dao.entidade.Venda;
import com.mycompany.floricultura.servico.ConverteData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para controlar as ações relacionadas a Vendas.
 * Mapeado para a URL /VendaControlador.
 */
@WebServlet(name = "VendaControlador", urlPatterns = {"/VendaControlador"})
public class VendaControlador extends HttpServlet {

    private VendaDAO vendaDAO;
    private ConverteData conversor;

    @Override
    public void init() throws ServletException {
        super.init();
        vendaDAO = new VendaDAO();
        conversor = new ConverteData();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "inserir":
                    inserirVenda(request, response);
                    break;
                case "editar":
                    mostrarFormularioEdicao(request, response);
                    break;
                case "atualizar":
                    atualizarVenda(request, response);
                    break;
                case "excluir":
                    excluirVenda(request, response);
                    break;
                default:
                    listarVendas(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarVendas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Venda> vendas = vendaDAO.listar();
        request.setAttribute("listaVendas", vendas);
        request.getRequestDispatcher("listagemVendas.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codVenda = Integer.parseInt(request.getParameter("codVenda"));
        Venda venda = vendaDAO.buscarPorId(codVenda);
        request.setAttribute("venda", venda);
        request.getRequestDispatcher("formVenda.jsp").forward(request, response);
    }

    private void inserirVenda(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Venda venda = new Venda();
        venda.setDataVenda(conversor.stringParaDate(request.getParameter("dataVenda")));
        venda.setCodFuncionario(Integer.parseInt(request.getParameter("codFuncionario")));
        venda.setCodCliente(Integer.parseInt(request.getParameter("codCliente")));
        
        vendaDAO.inserir(venda);
        
        response.sendRedirect("VendaControlador?acao=listar");
    }

    private void atualizarVenda(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Venda venda = new Venda();
        venda.setCodVenda(Integer.parseInt(request.getParameter("codVenda")));
        venda.setDataVenda(conversor.stringParaDate(request.getParameter("dataVenda")));
        venda.setCodFuncionario(Integer.parseInt(request.getParameter("codFuncionario")));
        venda.setCodCliente(Integer.parseInt(request.getParameter("codCliente")));

        vendaDAO.atualizar(venda);

        response.sendRedirect("VendaControlador?acao=listar");
    }

    private void excluirVenda(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int codVenda = Integer.parseInt(request.getParameter("codVenda"));
        Venda venda = new Venda();
        venda.setCodVenda(codVenda);
        
        vendaDAO.excluir(venda);

        response.sendRedirect("VendaControlador?acao=listar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Controlador para gerenciar vendas.";
    }
}
