<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Confirmación de Actualización de Cliente</title>
	</head>
	<body>
		Ha actualizado con éxito al cliente:
		<br>
		Nombre:${clienteAActualizar.nombre}<br>
		Apellido:${clienteAActualizar.apellido}<br>
		E-mail:${clienteAActualizar.email}<br>
		<br><br>
		<a href="generaClientes">Volver al listado de clientes</a>
	</body>
</html>