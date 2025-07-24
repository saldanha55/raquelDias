/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.modelo.dao.entidade;

/**
 *
 * @author 13754639692
 */
public class Flor {
    
    private int codFlor, quntidadeEst;
    private String nome;
    private Double preco;
     private Fornecedor objFornecedor = new Fornecedor();

    public int getCodFlor() {
        return codFlor;
    }

    public void setCodFlor(int codFlor) {
        this.codFlor = codFlor;
    }

    public int getQuntidadeEst() {
        return quntidadeEst;
    }

    public void setQuntidadeEst(int quntidadeEst) {
        this.quntidadeEst = quntidadeEst;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Fornecedor getObjFornecedor() {
        return objFornecedor;
    }

    public void setObjFornecedor(Fornecedor objFornecedor) {
        this.objFornecedor = objFornecedor;
    }
     
     
    
}
