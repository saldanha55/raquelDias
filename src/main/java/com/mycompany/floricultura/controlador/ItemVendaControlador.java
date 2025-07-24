package com.mycompany.floricultura.controlador;

import com.mycompany.floricultura.modelo.dao.ItemVendaDAO;
import com.mycompany.floricultura.modelo.dao.entidade.ItemVenda;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para controlar as ações relacionadas a Itens de Venda.
 * Mapeado para a URL /ItemVendaControlador.
 */
@WebServlet(name = "ItemVendaControlador", urlPatterns = {"/ItemVendaControlador"})
public class ItemVendaControlador extends HttpServlet {

    private ItemVendaDAO itemVendaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        itemVendaDAO = new ItemVendaDAO();
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
                    inserirItemVenda(request, response);
                    break;
                case "editar":
                    mostrarFormularioEdicao(request, response);
                    break;
                case "atualizar":
                    atualizarItemVenda(request, response);
                    break;
                case "excluir":
                    excluirItemVenda(request, response);
                    break;
                default:
                    listarItensVenda(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarItensVenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Geralmente, os itens são listados no contexto de uma venda específica.
        // Este método pode ser adaptado para receber um codVenda.
        List<ItemVenda> itens = itemVendaDAO.listar();
        request.setAttribute("listaItensVenda", itens);
        request.getRequestDispatcher("listagemItensVenda.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codItemVenda = Integer.parseInt(request.getParameter("codItemVenda"));
        ItemVenda item = itemVendaDAO.buscarPorId(codItemVenda);
        request.setAttribute("itemVenda", item);
        request.getRequestDispatcher("formItemVenda.jsp").forward(request, response);
    }

    private void inserirItemVenda(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ItemVenda item = new ItemVenda();
        item.setCodVenda(Integer.parseInt(request.getParameter("codVenda")));
        item.setCodFlor(Integer.parseInt(request.getParameter("codFlor")));
        item.setQuantidadeVendida(Integer.parseInt(request.getParameter("quantidadeVendida")));
        item.setPrecoVenda(Double.parseDouble(request.getParameter("precoVenda")));
        
        itemVendaDAO.inserir(item);
        
        // Redireciona para a página da venda ou para a listagem geral
        response.sendRedirect("ItemVendaControlador?acao=listar");
    }

    private void atualizarItemVenda(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ItemVenda item = new ItemVenda();
        item.setCodItemVenda(Integer.parseInt(request.getParameter("codItemVenda")));
        item.setCodVenda(Integer.parseInt(request.getParameter("codVenda")));
        item.setCodFlor(Integer.parseInt(request.getParameter("codFlor")));
        item.setQuantidadeVendida(Integer.parseInt(request.getParameter("quantidadeVendida")));
        item.setPrecoVenda(Double.parseDouble(request.getParameter("precoVenda")));

        itemVendaDAO.atualizar(item);

        response.sendRedirect("ItemVendaControlador?acao=listar");
    }

    private void excluirItemVenda(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int codItemVenda = Integer.parseInt(request.getParameter("codItemVenda"));
        ItemVenda item = new ItemVenda();
        item.setCodItemVenda(codItemVenda);
        
        itemVendaDAO.excluir(item);

        response.sendRedirect("ItemVendaControlador?acao=listar");
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
        return "Controlador para gerenciar itens de uma venda.";
    }
}
