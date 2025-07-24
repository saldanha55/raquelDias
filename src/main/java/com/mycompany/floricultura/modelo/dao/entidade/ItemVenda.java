package com.mycompany.floricultura.modelo.dao.entidade;

/**
 * Representa a entidade ItemVenda, que mapeia a tabela 'itemvenda' no banco de dados.
 * Cada item representa uma flor dentro de uma venda.
 */
public class ItemVenda {

    private Integer codItemVenda;
    private Integer codVenda;
    private Integer codFlor;
    private Integer quantidadeVendida;
    private Double precoVenda;

    public ItemVenda() {
    }

    public Integer getCodItemVenda() {
        return codItemVenda;
    }

    public void setCodItemVenda(Integer codItemVenda) {
        this.codItemVenda = codItemVenda;
    }

    public Integer getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(Integer codVenda) {
        this.codVenda = codVenda;
    }

    public Integer getCodFlor() {
        return codFlor;
    }

    public void setCodFlor(Integer codFlor) {
        this.codFlor = codFlor;
    }

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Integer quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }
}
