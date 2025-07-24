/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.floricultura.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 *classe ConnectionFactory é um exemplo do padrão de design 
 * Singletin aplicadi para gerenciar conexões com 
 * o banco de dados em uma alicação .
 * O objetivo da classe é encapsular a 16gica para criar c
 * onexões com o banco de dados e garantir uma intstância de 
 * connectioFactory seja criada durante o ciclo de vida
 * @author 12061351662
 */
public class ConnectionFactory {
    
    private static final String DB_URL= "jdbc:mysql://localhost:3307/floricultura?useSSL=false";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    
    //VARIÁVEL ESTÁTICA QUE MANTÉM A INSTÂNCIA ÚNICA DA CONNECTIOMFACTORY
    
    private static ConnectionFactory instance;
    // o construtor é privado para impedir a criação direta de instâncias da classe fora dela mesma
     
    private ConnectionFactory(){
        try{
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException ex){
            throw new RuntimeException("Driver do banco de dados não encontrado.",ex);
            
        }
    }
    //public static ConnectionFactory getInstance(): método estático que permite o acesso à 
    //instância única de ConnectioFactory: Padrão SingleTon -> garante que apenas uma instância seja usada em toda aplicação
    public static ConnectionFactory getInstance(){
        if(instance==null){
            instance = new ConnectionFactory();
        }
        return instance;
    }
    public Connection getConnection() throws SQLDataException, SQLException{
        return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
    }
        public PreparedStatement getPrepareStatement(String sql) throws SQLDataException, SQLException{
         Connection con = getConnection();
         return con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
         
        }
        //PreparedStatemente.Return_Genarted_Keys : Recupera o ID gerado pelo banco de dados ´pós a inserçãp de um registro
    }
     

