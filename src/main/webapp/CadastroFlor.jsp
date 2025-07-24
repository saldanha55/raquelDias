<%-- 
    Document   : CadastroFuncionario
    Created on : 28 de mar. de 2025, 08:58:56
    Author     : tulio
--%>
<%@include file="menu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Flor</title>
    </head>
    <body>
        <h1>Cadastro Flor</h1>
        <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/FlorControlador">
            <input type="hidden" name="codFlor" value="${codFlor}"/>
            <input type="hidden" name="opcao" value="${opcao}"/>
            <p><label>Flor:</label><input type="text" required="" name="nome" value="${nome}" size="40"/></p>
            <p><label>preco:</label><input type="number" required="" name="preco" value="${preco}" size="15"/></p>
            <p><label>Quantidade Estoque:</label><input type="number" required="" name="quantidadeEst" value="${quantidadeEst}"</p>
             
            
             
            <p><label>Fornecedor:</label>
                <select name="codFornecedor">
                    <c:forEach var="fornecedor" items="${listaFornecedor}">
                        <c:choose>
                            <c:when test="${fornecedor.codFornecedor eq codFornecedor}">
                                <option selected value="${fornecedor.codFornecedor}">${fornecedor.nome}</option> 
                            </c:when>
                            <c:otherwise>
                                <option value="${fornecedor.codFornecedor}">${fornecedor.nome}</option> 
                            </c:otherwise>
                        </c:choose>
                   </c:forEach>
                    
                </select>
            
            </p>
            
            <input type="submit" value="Salvar" name="btnSalvar" style="float: left; margin-right: 3px" >

        </form>
            
            <form id="cadastroForm" name="cadastro" method="get" action="${pageContext.request.contextPath}${URL_BASE}/FlorControlador">
            <input type="submit" value="Cancelar" name="btnCancelar">
             <input type="hidden" name="opcao" value="cancelar">
            
        </form>
            <br>
            <h3>${mensagem}</h3>
        <c:if test="${not empty listaFlor}"> 
            <table border="1">
                <tr><td>CÓDIGO</td><td>NOME</td><td>PREÇO</td><td>QUANTIDADE ESTOQUE</td><td>FORNECEDOR</td><td>ALTERAR</td><td>EXCLUIR</td></tr>  
                <c:forEach var="flor" items="${listaFlor}">
                    <tr>
                    <td>${flor.codFlor}</td>
                    <td>${flor.nome}</td>
                    <td>${flor.preco}</td>
                    <td>${flor.quantidadeEst}</td>
                    <td>${flor.objFornecedor.nome}</td>
                    <td> <form id="cadastroForm" name="cadastro" method="get" 
                        action="${pageContext.request.contextPath}${URL_BASE}/FlorControlador">
                            <input type="hidden" name="codFlor" value="${flor.codFlor}">
                            <input type="hidden" name="nome" value="${flor.nome}">
                            <input type="hidden" name="preco" value="${flor.preco}">
                            <input type="hidden" name="quantidadeEst" value="${flor.quantidadeEst}">
                            <input type="hidden" name="codFornecedor" value="${flor.objFornecedor.codFornecedor}">
                            <input type="hidden" name="opcao" value="editar">
                            <button type="submit">Editar</button>
                            
                        </form>
                    </td>
                    <td> <form id="cadastroForm" name="cadastro" method="get" 
              action="${pageContext.request.contextPath}${URL_BASE}/FlorControlador">
                            <input type="hidden" name="codFlor" value="${flor.codFlor}">
                            <input type="hidden" name="nome" value="${flor.nome}">
                            <input type="hidden" name="preco" value="${flor.preco}">
                            <input type="hidden" name="quantidadeEst" value="${flor.quantidadeEst}">
                            <input type="hidden" name="codFornecedor" value="${flor.objFornecedor.codFornecedor}">
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