package com.mycompany.floricultura.modelo.dao;

import com.mycompany.floricultura.modelo.dao.entidade.Venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para a entidade Venda, provendo as operações de CRUD.
 * Segue o padrão GenericoDAO para reutilização de código.
 */
public class VendaDAO extends GenericoDAO<Venda> {

    protected String getNomeTabela() {
        return "venda";
    }

    protected String getNomeColunaChavePrimaria() {
        return "codVenda";
    }

    protected String getColunasInsert() {
        return "dataVenda, codFuncionario, codCliente";
    }

    protected Object[] getValoresInsert(Venda entidade) {
        return new Object[]{
            entidade.getDataVenda(),
            entidade.getCodFuncionario(),
            entidade.getCodCliente()
        };
    }

    protected String getColunasUpdate() {
        return "dataVenda = ?, codFuncionario = ?, codCliente = ?";
    }

    protected Object[] getValoresUpdate(Venda entidade) {
        return new Object[]{
            entidade.getDataVenda(),
            entidade.getCodFuncionario(),
            entidade.getCodCliente(),
            entidade.getCodVenda()
        };
    }

    protected RowMapper<Venda> getRowMapper() {
        return new VendaRowMapper();
    }

    /**
     * Lista todas as vendas da tabela.
     * @return Uma lista com todas as vendas.
     */
    public List<Venda> listar() {
        String sql = "SELECT * FROM " + getNomeTabela();
        return listar(sql);
    }

    /**
     * Classe interna que implementa o RowMapper para a entidade Venda.
     */
    private static class VendaRowMapper implements RowMapper<Venda> {
        @Override
        public Venda mapRow(ResultSet rs) throws SQLException {
            Venda venda = new Venda();
            venda.setCodVenda(rs.getInt("codVenda"));
            venda.setDataVenda(rs.getDate("dataVenda"));
            venda.setCodFuncionario(rs.getInt("codFuncionario"));
            venda.setCodCliente(rs.getInt("codCliente"));
            return venda;
        }
    }
}
