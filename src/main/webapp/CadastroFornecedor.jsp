<%-- 
    Document   : Cadastr
    Created on : 28 de mar de 2025, 08:59:08
    Author     : 13754639692
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Fornecedor</title>
    </head>
    <body bgcolor="pink">
        <h1>Cadastro Fornecedor</h1>
        <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
            <input type="hidden" name="codFornecedor" value="${codFornecedor}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            <p><label>Nome:</label><input type="text" name="nome" value="${nome}" size="5"/></p>
            <p><label>Endereço:</label><input type="text" name="endereco" value="${endereco}" size="5"/></p>
            <p><label>CNPJ:</label><input type="text" name="cnpj" value="${cnpj}" size="5"/></p>
            <p><label>Email:</label><input type="text" name="email" value="${email}" size="5"/></p>
            <p><label>Telefone:</label><input type="text" name="telefone" value="${telefone}" size="5"/></p>
            
            <input type="submit" value="Salvar" name="Salvar">
        
        
        </form>
            
            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
            <input type="submit" value="Cancelar" name="btnCancelar">
             <input type="hidden" name="opcao" value="cancelar">
            
        </form>
            
            <br>
            <h3>${mensagem}</h3>
                <c:if test="${not empty listaFornecedor}">
                     <table border="1" bgcolor="yellow">
                         <tr><td>Codigo</td><td>Nome</td><td>Endereço</td><td>CNPJ</td><td>Email</td><td>Telefone</td><td>ALTERAR</td><td>EXCLUIR</td></tr>
                    <c:forEach var="fornecedor" items="${listaFornecedor}">
                        
                        <tr><td>${fornecedor.codFornecedor}</td>
                        <td>${fornecedor.nome}</td>
                        <td>${fornecedor.endereco}</td>
                        <td>${fornecedor.cnpj}</td>
                        <td>${fornecedor.email}</td>
                        <td>${fornecedor.telefone}</td>
                        
                        
                        <td>
               
               
                            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
                                 <input type="hidden" name="codFornecedor" value="${fornecedor.codFornecedor}">
                                 <input type="hidden" name="nome" value="${fornecedor.nome}">
                                 <input type="hidden" name="endereco" value="${fornecedor.endereco}">
                                 <input type="hidden" name="cnpj" value="${fornecedor.cnpj}">
                                 <input type="hidden" name="email" value="${fornecedor.email}">
                                 <input type="hidden" name="telefone" value="${fornecedor.telefone}">                             
                                 <input type="hidden" name="opcao" value="editar">
                                 <button type="submit">Editar</button>
                                 
                            </form>
                         </td>
                         <td>
               
               
                            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
                                 <input type="hidden" name="codFornecedor" value="${fornecedor.codFornecedor}">
                                 <input type="hidden" name="nome" value="${fornecedor.nome}">
                                 <input type="hidden" name="endereco" value="${fornecedor.endereco}">
                                 <input type="hidden" name="cnpj" value="${fornecedor.cnpj}">
                                 <input type="hidden" name="email" value="${fornecedor.email}">
                                 <input type="hidden" name="telefone" value="${fornecedor.telefone}">  
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
