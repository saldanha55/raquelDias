package com.mycompany.floricultura.modelo.dao.entidade;

import java.util.Date;

/**
 * Representa a entidade Venda, que mapeia a tabela 'venda' no banco de dados.
 */
public class Venda {

    private Integer codVenda;
    private Date dataVenda;
    private Integer codFuncionario;
    private Integer codCliente;

    public Venda() {
    }

    public Integer getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(Integer codVenda) {
        this.codVenda = codVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Integer getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(Integer codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }
}
