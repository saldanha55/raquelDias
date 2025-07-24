package com.mycompany.floricultura.modelo.dao;

import com.mycompany.floricultura.modelo.dao.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDAO extends GenericoDAO<Funcionario> {

    public void salvar(Funcionario objFuncionario) {
        String sql = "INSERT INTO funcionario (nomeFuncionario, enderecoFuncionario, cpfFuncionario, salarioFuncionario, funcao) VALUES (?, ?, ?, ?, ?)";
        save(sql,
             objFuncionario.getNomeFuncionario(),
             objFuncionario.getEnderecoFuncionario(),
             objFuncionario.getCpfFuncionario(),
             objFuncionario.getSalarioFuncionario(),
             objFuncionario.getFuncao());
    }

    public void alterar(Funcionario objFuncionario) {
        String sql = "UPDATE funcionario SET nomeFuncionario=?, enderecoFuncionario=?, cpfFuncionario=?, salarioFuncionario=?, funcao=? WHERE codFuncionario=?";
        save(sql,
             objFuncionario.getNomeFuncionario(),
             objFuncionario.getEnderecoFuncionario(),
             objFuncionario.getCpfFuncionario(),
             objFuncionario.getSalarioFuncionario(),
             objFuncionario.getFuncao(),
             objFuncionario.getCodFuncionario());
    }

    public void excluir(Funcionario objFuncionario) {
        String sql = "DELETE FROM funcionario WHERE codFuncionario=?";
        save(sql, objFuncionario.getCodFuncionario());
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        String sql = "SELECT * FROM funcionario";
        return buscarTodos(sql, new FuncionarioRowMapper());
    }

    public Funcionario buscarFuncionarioPorID(int codFuncionario) {
        String sql = "SELECT * FROM funcionario WHERE codFuncionario=?";
        return buscarPorId(sql, new FuncionarioRowMapper(), codFuncionario);
    }

    private static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario objFuncionario = new Funcionario();
            objFuncionario.setCodFuncionario(rs.getInt("codFuncionario"));
            objFuncionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
            objFuncionario.setEnderecoFuncionario(rs.getString("enderecoFuncionario"));
            objFuncionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
            objFuncionario.setSalarioFuncionario(rs.getDouble("salarioFuncionario"));
            objFuncionario.setFuncao(rs.getString("funcao"));
            return objFuncionario;
        }
    }
}
