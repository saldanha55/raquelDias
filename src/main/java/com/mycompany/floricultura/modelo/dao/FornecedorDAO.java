/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.modelo.dao;

import com.mycompany.floricultura.modelo.dao.entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author 13754639692
 */
public class FornecedorDAO extends GenericoDAO<Fornecedor>  {

    public void salvar(Fornecedor objFornecedor){
        String sql = "INSERT INTO Fornecedor (nome,endereco,telefone,email,cnpj) VALUES(?,?,?,?,?)";
        save(sql,objFornecedor.getNome(), objFornecedor.getEndereco(), objFornecedor.getTelefone(), objFornecedor.getEmail(), objFornecedor.getCnpj());
        
    }
    
   public void alterar(Fornecedor objFornecedor){
        String sql="UPDATE Fornecedor SET nome=?,endereco=?,telefone=?,email=?,cnpj=? WHERE codFornecedor=?";       
        save(sql,objFornecedor.getNome(), objFornecedor.getEndereco(), objFornecedor.getTelefone(), objFornecedor.getEmail(), objFornecedor.getCnpj());
   }
        
 
   
   
    public void excluir(Fornecedor objFornecedor)
       {
        String sql="DELETE FROM Fornecedor  WHERE codFornecedor=?";
        save(sql,objFornecedor.getCodFornecedor());
            }
    
    //implemetar o RowMapper específico para a entidade Cidade
    private static class FornecedorRowMapper implements RowMapper<Fornecedor>{

        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
         Fornecedor objFornecedor = new Fornecedor();
         objFornecedor.setCodFornecedor(rs.getInt("codFornecedor"));
         objFornecedor.setNome(rs.getString("nome"));
         objFornecedor.setCnpj(rs.getString("cnpj"));
         objFornecedor.setEmail(rs.getString("email"));
         objFornecedor.setTelefone(rs.getString("telefone"));
         objFornecedor.setEndereco(rs.getString("endereco"));
          return objFornecedor;
          
        }
        
        
    }
    public List<Fornecedor> buscarTodosFornecedores(){
    String sql = "SELECT * FROM fornecedor";
     return buscarTodos(sql, new FornecedorRowMapper());
} 
   public Fornecedor buscarFornecedorPorID(int codFornecedor){
       String sql = "SELECT * FROM fornecedor WHERE codfornecedor=?";
       return buscarPorId(sql, new FornecedorRowMapper(), codFornecedor);
   }
}
    

