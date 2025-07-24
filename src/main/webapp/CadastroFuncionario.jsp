<%-- 
    Document   : CadasFuncionario
    Created on : 28 de mar de 2025, 08:59:08
    Author     : 13754639692
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Funcionario</title>
    </head>
    <body bgcolor="pink">
        <h1>Cadastro Funcionario</h1>
        <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
            <input type="hidden" name="codFuncionario" value="${codFuncionario}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            <p><label>Nome:</label><input type="text" name="nomeFuncionario" value="${nomeFuncionario}" size="5"/></p>
            <p><label>Endereço:</label><input type="text" name="enderecoFuncionario" value="${enderecoFuncionario}" size="5"/></p>
            <p><label>CPF:</label><input type="text" name="cpfFuncionario" value="${cpfFuncionario}" size="5"/></p>
            <p><label>Salario:</label><input type="text" name="salarioFuncionario" value="${salarioFuncionario}" size="5"/></p>
            <p><label>Função</label><input type="text" name="funcao" value="${funcao}" size="5" </p>
           
            
            <input type="submit" value="Salvar" name="Salvar">
        
        
        </form>
            <br>
            <h3>${mensagem}</h3>
                <c:if test="${not empty listaFuncionario}">
                     <table border="1" bgcolor="yellow">
                         <tr><td>Codigo</td><td>Nome</td><td>Endereço</td><td>CPF</td><td>Salario</td><td>Função</td><td>ALTERAR</td><td>EXCLUIR</td></tr>
                    <c:forEach var="Funcionario" items="${listaFuncionario}">
                        
                        <tr><td>${Funcionario.codFuncionario}</td>
                        <td>${Funcionario.nomeFuncionario}</td>
                        <td>${Funcionario.enderecoFuncionario}</td>
                        <td>${Funcionario.cpfFuncionario}</td>
                        <td>${Funcionario.salarioFuncionario}</td>
                        <td>${Funcionario.funcao}</td>

                        
                        <td>
               
               
                            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                                 <input type="hidden" name="codFuncionario" value="${Funcionario.codFuncionario}">
                                 <input type="hidden" name="nomeFuncionario" value="${Funcionario.nomeFuncionario}">
                                 <input type="hidden" name="enderecoFuncionario" value="${Funcionario.enderecoFuncionario}">
                                 <input type="hidden" name="cpfFuncionario" value="${Funcionario.cpfFuncionario}">
                                 <input type="hidden" name="salarioFuncionario" value="${Funcionario.salarioFuncionario}">
                                 <input type="hidden" name="funcao" value="${Funcionario.funcao}">
                                 <input type="hidden" name="opcao" value="editar">
                                 <button type="submit">Editar</button>
                                 
                            </form>
                         </td>
                         <td>
               
               
                            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                                <input type="hidden" name="codFuncionario" value="${Funcionario.codFuncionario}">
                                 <input type="hidden" name="nomeFuncionario" value="${Funcionario.nomeFuncionario}">
                                 <input type="hidden" name="enderecoFuncionario" value="${Funcionario.enderecoFuncionario}">
                                 <input type="hidden" name="cpfFuncionario" value="${Funcionario.cpfFuncionario}">
                                 <input type="hidden" name="salarioFuncionario" value="${Funcionario.salarioFuncionario}">
                                 <input type="hidden" name="funcao" value="${Funcionario.funcao}">
                                 <input type="hidden" name="opcao" value="excluir">
                                 <button type="submit">Excluir</button>
                                 
                            </form>
                         </td>
                        </tr>
                    </c:forEach>
                      </table>
                  
                </c:if>
                
         
    </body>
</html>
