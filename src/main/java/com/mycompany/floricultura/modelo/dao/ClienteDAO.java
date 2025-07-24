/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.modelo.dao;

import com.mycompany.floricultura.modelo.dao.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 12061351662
 */
public class ClienteDAO extends GenericoDAO<Cliente>{
    public void salvar(Cliente objCliente){
        String sql = "INSERT INTO CLIENTE (nomeCliente,enderecoCliente,telefoneCliente,emailCliente,rgCliente,cpfCliente) VALUES(?,?,?,?,?,?)";
        save(sql,objCliente.getNomeCliente(),objCliente.getEnderecoCliente(),objCliente.getTelefoneCliente(),objCliente.getEmailCliente(),objCliente.getRgCliente(),objCliente.getCpfCliente());
        
    }
    
   public void alterar(Cliente objCliente){
        String sql="UPDATE CLIENTE SET nomeCliente=?,enderecoCliente=?,telefoneCliente=?,emailCliente=?,rgCliente=?,cpfCliente=? WHERE codCliente=?";       
      save(sql,objCliente.getNomeCliente(),objCliente.getEnderecoCliente(),objCliente.getTelefoneCliente(),objCliente.getEmailCliente(),objCliente.getRgCliente(),objCliente.getCpfCliente(),objCliente.getCodCliente());  
}
   
       public void excluir(Cliente objCliente){
        String sql="DELETE FROM Cliente WHERE codCliente=?";
        save(sql,objCliente.getCodCliente());
            }
    //implemetar o RowMapper específico para a entidade Cidade
    private static class ClienteRowMapper implements RowMapper<Cliente>{

        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
         Cliente objCliente = new Cliente();
         objCliente.setCodCliente(rs.getInt("codCliente"));
         objCliente.setEnderecoCliente(rs.getString("enderecoCliente"));
         objCliente.setCpfCliente(rs.getString("cpfCliente"));
         objCliente.setTelefoneCliente(rs.getString("telefoneCliente"));
         objCliente.setRgCliente(rs.getString("rgCliente"));
         objCliente.setEmailCliente(rs.getString("emailCliente"));
         objCliente.setNomeCliente(rs.getString("nomeCliente"));
        
          return objCliente;
          
        }
        
        
    }
    public List<Cliente> buscarTodosClientes(){
    String sql = "SELECT * FROM cliente";
     return buscarTodos(sql, new ClienteRowMapper());
} 
   public Cliente buscarClientePorID(int codCliente){
       String sql = "SELECT * FROM cliente WHERE codCliente=?";
       return buscarPorId(sql, new ClienteRowMapper(), codCliente);
     
   }
}