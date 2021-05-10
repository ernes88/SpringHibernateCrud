<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Confirmación de Registro de Cliente</title>
	</head>
	<body>
		<h3>Usted se ha registrado correctamente con los datos</h3>
		<br>
		Nombre:${clienteARegistrar.nombre}<br>
		Apellido:${clienteARegistrar.apellido}<br>
		E-mail:${clienteARegistrar.email}<br>
		
		<br><br>
		<a href="generaClientes">Volver al listado de clientes</a>
	</body>
</html>