<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="modelo.GestionContactos,java.util.ArrayList,mx.com.gm.sga.domain.Contacto"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Gestión de Contactos</title>
    </head>
    <body>

        <c:set var="contactos" value="${requestScope.contactos}"/>

        <br/><br/><br/>

        <c:choose>

            <c:when test="${!empty contactos}">

                <table border="1">
                    <tr>
                        <th>Nombre</th>
                        <th>Email </th>
                        <th>Telefono</th>
                        <th></th>
                    </tr>

                    <c:forEach var="cont" items="${contactos}">
                        <tr><td>${cont.nombre}</td>
                            <td>${cont.email}</td>
                            <td>${cont.telefono}</td>
                            <td><a href="Controller?op=doEliminar&idContacto=${cont.idContacto}">Eliminar</a></td></tr>
                        </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h1>No hay contactos</h1>
            </c:otherwise>
        </c:choose>
        <br/>
        <br/>
        <a href="Controller?op=toIndex">Menu</a>
    </body>
</html>