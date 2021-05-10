<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Confirmación de eliminación de Cliente</title>
	</head>
	<body>
		Ha eliminado con éxito al cliente:
		<br>
		Nombre:${clienteAEliminar.nombre}<br>
		Apellido:${clienteAEliminar.apellido}<br>
		E-mail:${clienteAEliminar.email}<br>
		<br><br>
		<a href="generaClientes">Volver al listado de clientes</a>		
	</body>
</html>