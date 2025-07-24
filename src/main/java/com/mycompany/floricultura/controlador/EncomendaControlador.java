package com.mycompany.floricultura.controlador;

import com.mycompany.floricultura.modelo.dao.EncomendaDAO;
import com.mycompany.floricultura.modelo.dao.entidade.Encomenda;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet para controlar as ações relacionadas a Encomendas.
 * Mapeado para a URL /EncomendaControlador.
 */
@WebServlet(name = "EncomendaControlador", urlPatterns = {"/EncomendaControlador"})
public class EncomendaControlador extends HttpServlet {

    private EncomendaDAO encomendaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        encomendaDAO = new EncomendaDAO();
    }

    /**
     * Processa requisições para os métodos HTTP GET e POST.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se um erro específico do servlet ocorrer
     * @throws IOException se um erro de I/O ocorrer
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "listar";
        }

        try {
            switch (acao) {
                case "inserir":
                    inserirEncomenda(request, response);
                    break;
                case "editar":
                    mostrarFormularioEdicao(request, response);
                    break;
                case "atualizar":
                    atualizarEncomenda(request, response);
                    break;
                case "excluir":
                    excluirEncomenda(request, response);
                    break;
                default:
                    listarEncomendas(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarEncomendas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Encomenda> encomendas = encomendaDAO.listar();
        request.setAttribute("listaEncomendas", encomendas);
        request.getRequestDispatcher("listagemEncomendas.jsp").forward(request, response);
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codEncomenda = Integer.parseInt(request.getParameter("codEncomenda"));
        Encomenda encomenda = encomendaDAO.buscarPorId(codEncomenda);
        request.setAttribute("encomenda", encomenda);
        // Assumindo que você terá um formulário para criar/editar encomendas
        request.getRequestDispatcher("formEncomenda.jsp").forward(request, response);
    }

    private void inserirEncomenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Encomenda encomenda = new Encomenda();
        encomenda.setCodFlor(Integer.parseInt(request.getParameter("codFlor")));
        encomenda.setCodCliente(Integer.parseInt(request.getParameter("codCliente")));
        encomenda.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        
        encomendaDAO.inserir(encomenda);
        
        response.sendRedirect("EncomendaControlador?acao=listar");
    }

    private void atualizarEncomenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Encomenda encomenda = new Encomenda();
        encomenda.setCodEncomenda(Integer.parseInt(request.getParameter("codEncomenda")));
        encomenda.setCodFlor(Integer.parseInt(request.getParameter("codFlor")));
        encomenda.setCodCliente(Integer.parseInt(request.getParameter("codCliente")));
        encomenda.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

        encomendaDAO.atualizar(encomenda);

        response.sendRedirect("EncomendaControlador?acao=listar");
    }

    private void excluirEncomenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codEncomenda = Integer.parseInt(request.getParameter("codEncomenda"));
        Encomenda encomenda = new Encomenda();
        encomenda.setCodEncomenda(codEncomenda);
        
        encomendaDAO.excluir(encomenda);

        response.sendRedirect("EncomendaControlador?acao=listar");
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
        return "Controlador para gerenciar encomendas.";
    }
}
