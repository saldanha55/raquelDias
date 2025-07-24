package com.mycompany.floricultura.controlador;

import com.mycompany.floricultura.modelo.dao.FuncionarioDAO;
import com.mycompany.floricultura.modelo.dao.entidade.Funcionario;
import com.mycompany.floricultura.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {

    private Funcionario objFuncionario;
    private FuncionarioDAO objFuncionarioDao;

    String nomeFuncionario = "", cpfFuncionario = "", codFuncionario = "", salarioFuncionario = "", funcao = "", enderecoFuncionario = "";

    @Override
    public void init() throws ServletException {
        objFuncionarioDao = new FuncionarioDAO();
        objFuncionario = new Funcionario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }

            nomeFuncionario = request.getParameter("nomeFuncionario");
            cpfFuncionario = request.getParameter("cpfFuncionario");
            codFuncionario = request.getParameter("codFuncionario");
            salarioFuncionario = request.getParameter("salarioFuncionario");
            funcao = request.getParameter("funcao");
            enderecoFuncionario = request.getParameter("enderecoFuncionario");

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
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

        } catch (NumberFormatException ex) {
            response.getWriter().println("Erro: parâmetro numérico inválido - " + ex.getLocalizedMessage());
        } catch (IllegalArgumentException ex) {
            response.getWriter().println("Erro: " + ex.getLocalizedMessage());
        }
    }

    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFuncionario.setNomeFuncionario(nomeFuncionario);
        objFuncionario.setCpfFuncionario(cpfFuncionario);
        objFuncionario.setSalarioFuncionario(Double.parseDouble(salarioFuncionario));
        objFuncionario.setFuncao(funcao);
        objFuncionario.setEnderecoFuncionario(enderecoFuncionario);

        objFuncionarioDao.salvar(objFuncionario);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> listaFuncionario = objFuncionarioDao.buscarTodosFuncionarios();
        request.setAttribute("listaFuncionario", listaFuncionario);
        RequestDispatcher encaminhar = request.getRequestDispatcher("/CadastroFuncionario.jsp");
        encaminhar.forward(request, response);
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codFuncionario", codFuncionario);
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("cpfFuncionario", cpfFuncionario);
        request.setAttribute("salarioFuncionario", salarioFuncionario);
        request.setAttribute("funcao", funcao);
        request.setAttribute("enderecoFuncionario", enderecoFuncionario);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        request.setAttribute("opcao", "confirmarEditar");
        encaminharParaPagina(request, response);
    }

    protected void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFuncionario.setCodFuncionario(Integer.valueOf(codFuncionario));
        objFuncionario.setNomeFuncionario(nomeFuncionario);
        objFuncionario.setCpfFuncionario(cpfFuncionario);
        objFuncionario.setSalarioFuncionario(Double.parseDouble(salarioFuncionario));
        objFuncionario.setFuncao(funcao);
        objFuncionario.setEnderecoFuncionario(enderecoFuncionario);

        objFuncionarioDao.alterar(objFuncionario);
        encaminharParaPagina(request, response);
    }

    protected void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objFuncionario.setCodFuncionario(Integer.valueOf(codFuncionario));
        objFuncionarioDao.excluir(objFuncionario);
        encaminharParaPagina(request, response);
    }

    protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codFuncionario", codFuncionario);
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("cpfFuncionario", cpfFuncionario);
        request.setAttribute("salarioFuncionario", salarioFuncionario);
        request.setAttribute("funcao", funcao);
        request.setAttribute("enderecoFuncionario", enderecoFuncionario);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        request.setAttribute("opcao", "confirmarExcluir");
        encaminharParaPagina(request, response);
    }
    protected void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("codFuncionario", "0");
        request.setAttribute("nomeFuncionario", "");
        request.setAttribute("enderecoFuncionario", "");
        request.setAttribute("cpfFuncionario", "");
        request.setAttribute("funcao", "");
        request.setAttribute("salarioFuncionario", "0");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);

    }
}
