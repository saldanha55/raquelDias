<%-- 
    Document   : Cadastrocliente
    Created on : 28 de mar de 2025, 08:59:08
    Author     : 13754639692
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cliente</title>
    </head>
    <body bgcolor="pink">
        <h1>Cadastro Cliente</h1>
        <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
            <input type="hidden" name="codCliente" value="${codCliente}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            <p><label>Nome:</label><input type="text" name="nomeCliente" value="${nomeCliente}" size="5"/></p>
            <p><label>Endereço:</label><input type="text" name="enderecoCliente" value="${enderecoCliente}" size="5"/></p>
            <p><label>CPF:</label><input type="text" name="cpfCliente" value="${cpfCliente}" size="5"/></p>
            <p><label>Email:</label><input type="text" name="emailCliente" value="${emailCliente}" size="5"/></p>
            <p><label>Telefone:</label><input type="text" name="telefoneCliente" value="${telefoneCliente}" size="5"/></p>
            <p><label>RG:</label><input type="text" name="rgCliente" value="${rgCliente}" size="5"/></p>
            
            <input type="submit" value="Salvar" name="Salvar">
        
        
        </form>
            
            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/CidadeControlador">
            <input type="submit" value="Cancelar" name="btnCancelar">
             <input type="hidden" name="opcao" value="cancelar">
            
        </form>
            
            <br>
            <h3>${mensagem}</h3>
                <c:if test="${not empty listaCliente}">
                     <table border="1" bgcolor="yellow">
                         <tr><td>Codigo</td><td>Nome</td><td>Endereço</td><td>CPF</td><td>Email</td><td>Telefone</td><td>RG</td><td>ALTERAR</td><td>EXCLUIR</td></tr>
                    <c:forEach var="cliente" items="${listaCliente}">
                        
                        <tr><td>${cliente.codCliente}</td>
                        <td>${cliente.nomeCliente}</td>
                        <td>${cliente.enderecoCliente}</td>
                        <td>${cliente.cpfCliente}</td>
                        <td>${cliente.emailCliente}</td>
                        <td>${cliente.telefoneCliente}</td>
                        <td>${cliente.rgCliente}</td>
                        
                        <td>
               
               
                            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                 <input type="hidden" name="codCliente" value="${cliente.codCliente}">
                                 <input type="hidden" name="nomeCliente" value="${cliente.nomeCliente}">
                                 <input type="hidden" name="enderecoCliente" value="${cliente.enderecoCliente}">
                                 <input type="hidden" name="cpfCliente" value="${cliente.cpfCliente}">
                                 <input type="hidden" name="emailCliente" value="${cliente.emailCliente}">
                                 <input type="hidden" name="telefoneCliente" value="${cliente.telefoneCliente}">
                                 <input type="hidden" name="rgCliente" value="${cliente.rgCliente}">
                                 <input type="hidden" name="opcao" value="editar">
                                 <button type="submit">Editar</button>
                                 
                            </form>
                         </td>
                         <td>
               
               
                            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                 <input type="hidden" name="codCliente" value="${cliente.codCliente}">
                                 <input type="hidden" name="nomeCliente" value="${cliente.nomeCliente}">
                                 <input type="hidden" name="enderecoCliente" value="${cliente.enderecoCliente}">
                                 <input type="hidden" name="cpfCliente" value="${cliente.cpfCliente}">
                                 <input type="hidden" name="emailCliente" value="${cliente.emailCliente}">
                                 <input type="hidden" name="telefoneCliente" value="${cliente.telefoneCliente}">
                                 <input type="hidden" name="rgCliente" value="${cliente.rgCliente}">
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
