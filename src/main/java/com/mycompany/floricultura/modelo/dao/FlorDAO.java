/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.modelo.dao;

import com.mycompany.floricultura.modelo.dao.entidade.Flor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 13754639692
 */
public class FlorDAO extends GenericoDAO<Flor> {
    
    public void salvar(Flor objFlor) {
        String sql = "INSERT INTO Flor (nome,preco,quntidadeEst,codFornecedor) VALUES(?,?,?,?)";
        save(sql, objFlor.getNome(), objFlor.getQuntidadeEst() , objFlor.getPreco(), objFlor.getObjFornecedor().getCodFornecedor());

    }

    public void alterar(Flor objFlor) {
        String sql = "UPDATE Flor SET nome=?,preco=?, quantidadeEst=?,codFornecedor=? WHERE codFlor=?";
        save(sql, objFlor.getNome(), objFlor.getPreco(), objFlor.getQuntidadeEst(), objFlor.getObjFornecedor().getCodFornecedor(),objFlor.getCodFlor());
    }

    public void excluir(Flor objFlor) {
        String sql = "DELETE FROM flor WHERE codFlor=?";
        save(sql, objFlor.getCodFlor());
    }

    // implementa o RowMapper específico para a entidade Cidade
    private static class FlorRowMapper implements RowMapper<Flor> {
      //  ConverteData converte = new ConverteData();
        FornecedorDAO objFornecedorDAO = new FornecedorDAO();
        @Override
        public Flor mapRow(ResultSet rs) throws SQLException {
            Flor objFlor = new Flor();
            objFlor.setCodFlor(rs.getInt("codFlor"));
            objFlor.setNome(rs.getString("nome"));
            objFlor.setPreco(rs.getDouble("preco"));
            objFlor.setQuntidadeEst(rs.getInt("quantidadeEst"));
            objFlor.setObjFornecedor(objFornecedorDAO.buscarFornecedorPorID(rs.getInt("codFornecedor")));
            return objFlor;

        }

    }
    
    public List<Flor> buscarTodosFlor(){
        String sql = "SELECT * FROM Flor";
        return buscarTodos(sql, new FlorRowMapper());
    }
    
    public Flor buscarFlorPorId(int codFlor){
        String sql = "SELECT * FROM Flor WHERE codFlor=?";
        return buscarPorId(sql, new FlorRowMapper(), codFlor);
    }
    
    
}
