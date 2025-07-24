package com.mycompany.floricultura.modelo.dao;

import com.mycompany.floricultura.modelo.dao.entidade.ItemVenda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para a entidade ItemVenda, provendo as operações de CRUD.
 * Segue o padrão GenericoDAO para reutilização de código.
 */
public class ItemVendaDAO extends GenericoDAO<ItemVenda> {

    protected String getNomeTabela() {
        return "itemvenda";
    }

    protected String getNomeColunaChavePrimaria() {
        return "codItemVenda";
    }

    protected String getColunasInsert() {
        return "codVenda, codFlor, quantidadeVendida, precoVenda";
    }

    protected Object[] getValoresInsert(ItemVenda entidade) {
        return new Object[]{
            entidade.getCodVenda(),
            entidade.getCodFlor(),
            entidade.getQuantidadeVendida(),
            entidade.getPrecoVenda()
        };
    }

    protected String getColunasUpdate() {
        return "codVenda = ?, codFlor = ?, quantidadeVendida = ?, precoVenda = ?";
    }

    protected Object[] getValoresUpdate(ItemVenda entidade) {
        return new Object[]{
            entidade.getCodVenda(),
            entidade.getCodFlor(),
            entidade.getQuantidadeVendida(),
            entidade.getPrecoVenda(),
            entidade.getCodItemVenda()
        };
    }

    protected RowMapper<ItemVenda> getRowMapper() {
        return new ItemVendaRowMapper();
    }

    /**
     * Lista todos os itens de venda da tabela.
     * @return Uma lista com todos os itens de venda.
     */
    public List<ItemVenda> listar() {
        String sql = "SELECT * FROM " + getNomeTabela();
        return listar(sql);
    }
    
    /**
     * Lista todos os itens de uma venda específica.
     * @param codVenda O código da venda.
     * @return Uma lista de Itens de Venda.
     */
    public List<ItemVenda> listarPorVenda(int codVenda) {
        String sql = "SELECT * FROM " + getNomeTabela() + " WHERE codVenda = ?";
        return listar(sql, codVenda);
    }

    /**
     * Classe interna que implementa o RowMapper para a entidade ItemVenda.
     */
    private static class ItemVendaRowMapper implements RowMapper<ItemVenda> {
        @Override
        public ItemVenda mapRow(ResultSet rs) throws SQLException {
            ItemVenda item = new ItemVenda();
            item.setCodItemVenda(rs.getInt("codItemVenda"));
            item.setCodVenda(rs.getInt("codVenda"));
            item.setCodFlor(rs.getInt("codFlor"));
            item.setQuantidadeVendida(rs.getInt("quantidadeVendida"));
            item.setPrecoVenda(rs.getDouble("precoVenda"));
            return item;
        }
    }
}
