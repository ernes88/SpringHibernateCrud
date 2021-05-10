<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Listado de clientes</title>
	</head>
	<body>
		<h3>Listado de Clientes</h3>
		<!-- Construcción de una tabla para representar en la página Web los datos de la BD -->
		<table>
			<!-- Construcción de una fila de cabecera -->
			<tr>
				<!-- Construcción de las celdas para esta primera fila, se construyen con un formato de cabecera  -->
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Email</th>
				<th>Actualizar</th>
				<th>Eliminar</th>
			<tr/>
				
			<!-- Construcción de un bucle for:each que permite recorren la cantidad de elementos guardados en la lista losClientes, la cual se accede a través de la propiedad clientes -->
			<c:forEach var = "clienteTemp" items="${clientes}">
					
				<!-- Con esta jsp tag logro construir un link al método generaFormularioActualizar que se llegará relativamente a través de cliente, y el nombre del link será linkActualizar -->
				<c:url var="linkActualizar" value="generaFormularioActualizar">
						
					<!-- EL próximo paso es pasarle a la url definida arriba el id del link que hayamos pinchado, para lo cual le pasamos un parámetro a esa url  -->
					<c:param name="clienteId" value="${clienteTemp.id}"/>
				</c:url>
				
				<!-- Con esta jsp tag logro construir un link al método eliminaRegistro que se llegará relativamente a través de cliente, y el nombre del link será linkEliminar -->
				<c:url var="linkEliminar" value="eliminaRegistro">
						
					<!-- EL próximo paso es pasarle a la url definida arriba el id del link que hayamos pinchado, para lo cual le pasamos un parámetro a esa url  -->
					<c:param name="clienteId" value="${clienteTemp.id}"/>
				</c:url>
				
				<!-- Construimos la fila -->
				<tr>
					<!-- Construimos las tres celdas comunes para la fila actual -->
					<td>${clienteTemp.nombre}</td>			
					<td>${clienteTemp.apellido}</td>					<!-- A través de la propiedad clienteTemp puedo acceder a los campos de clase de Cliente -->
					<td>${clienteTemp.email}</td>
					<td>													
						<a href="${linkActualizar}"> 					<!-- Acá estamos incluuyendo en la nueva columna Actualizar, un botón con el texto Modificar -->
						<input type="button" value="Modificar"></a>
					</td>
					<td>													
						<a href="${linkEliminar}"> 						<!-- Acá estamos incluuyendo en la nueva columna Eliminar, un botón con el texto Eliminar -->
						<input type="button" value="Eliminar" 
						onclick="if(!(confirm('Desea realmente eliminar este registro?'))) return false"></a>		<!-- código de javascript que saca una ventana emergente con dos botones -->
					</td>				
				</tr>	
			</c:forEach>	
		</table>		
		<br>
		<input type="button" value="Añadir nuevo cliente" onclick="window.location.href='generaFormularioRegistro';return false;"/>
		<br><br>		
	</body>
</html>