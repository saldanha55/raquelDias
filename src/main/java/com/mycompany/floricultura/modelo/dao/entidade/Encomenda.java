package com.mycompany.floricultura.modelo.dao.entidade;

/**
 * Representa a entidade Encomenda, que mapeia a tabela 'encomenda' no banco de dados.
 */
public class Encomenda {

    private Integer codEncomenda;
    private Integer codFlor;
    private Integer codCliente;
    private Integer quantidade;

    public Encomenda() {
    }

    public Integer getCodEncomenda() {
        return codEncomenda;
    }

    public void setCodEncomenda(Integer codEncomenda) {
        this.codEncomenda = codEncomenda;
    }

    public Integer getCodFlor() {
        return codFlor;
    }

    public void setCodFlor(Integer codFlor) {
        this.codFlor = codFlor;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
