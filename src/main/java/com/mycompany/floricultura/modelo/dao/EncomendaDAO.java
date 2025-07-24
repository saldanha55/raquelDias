package com.mycompany.floricultura.modelo.dao;

import com.mycompany.floricultura.modelo.dao.entidade.Encomenda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO para a entidade Encomenda, provendo as operações de CRUD.
 * Segue o padrão GenericoDAO para reutilização de código.
 */
public class EncomendaDAO extends GenericoDAO<Encomenda> {

    protected String getNomeTabela() {
        return "encomenda";
    }

    protected String getNomeColunaChavePrimaria() {
        return "codEncomenda";
    }

    protected String getColunasInsert() {
        return "codFlor, codCliente, quantidade";
    }

    protected Object[] getValoresInsert(Encomenda entidade) {
        return new Object[]{
            entidade.getCodFlor(),
            entidade.getCodCliente(),
            entidade.getQuantidade()
        };
    }

    protected String getColunasUpdate() {
        return "codFlor = ?, codCliente = ?, quantidade = ?";
    }

    protected Object[] getValoresUpdate(Encomenda entidade) {
        return new Object[]{
            entidade.getCodFlor(),
            entidade.getCodCliente(),
            entidade.getQuantidade(),
            entidade.getCodEncomenda()
        };
    }

    protected RowMapper<Encomenda> getRowMapper() {
        return new EncomendaRowMapper();
    }
    
    /**
     * Lista todas as encomendas da tabela.
     * @return Uma lista com todas as encomendas.
     */
    public List<Encomenda> listar() {
        String sql = "SELECT * FROM " + getNomeTabela();
        return listar(sql);
    }

    /**
     * Lista todas as encomendas de um cliente específico.
     * @param codCliente O código do cliente.
     * @return Uma lista de Encomendas.
     */
    public List<Encomenda> listarPorCliente(int codCliente) {
        String sql = "SELECT * FROM " + getNomeTabela() + " WHERE codCliente = ?";
        return listar(sql, codCliente);
    }

    /**
     * Classe interna que implementa o RowMapper para a entidade Encomenda.
     */
    private static class EncomendaRowMapper implements RowMapper<Encomenda> {
        @Override
        public Encomenda mapRow(ResultSet rs) throws SQLException {
            Encomenda encomenda = new Encomenda();
            encomenda.setCodEncomenda(rs.getInt("codEncomenda"));
            encomenda.setCodFlor(rs.getInt("codFlor"));
            encomenda.setCodCliente(rs.getInt("codCliente"));
            encomenda.setQuantidade(rs.getInt("quantidade"));
            return encomenda;
        }
    }
}
