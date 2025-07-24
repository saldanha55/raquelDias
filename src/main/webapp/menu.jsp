<%-- 
    Document   : menu
    Created on : 21 de mar de 2025, 10:28:33
    Author     : 12061351662
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <ul>
       
       
                <li><a href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">CLIENTE</a></li>                        
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar">FUNCIONÁRIO</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador?opcao=cancelar">FORNECEDOR</a></li>
                <li><a href="${pageContext.request.contextPath}/login.jsp">LOGIN</a></li>
                <li><a href="${pageContext.request.contextPath}/logout.jsp">LOGOUT</a></li>
            
            </ul>
        </nav>
    </body>
</html>