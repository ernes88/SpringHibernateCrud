<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Formulario de Registro</title>
	</head>
	
	<body>
		<h3>Formulario de Registro de clientes</h3>
		
		<form:form action="generaFormularioRegistro2" modelAttribute="clienteARegistrar" method="POST"> 	<!-- Las redirección a generaFormulario2 no necesita de establecer la ruta relativa con cliente/ ya que esto ya se hizo para el origen de la cadena de redirecciones. -->
			Nombre(*): 																						<!-- El valor de la propiedad modelAttribute tiene que ser igual al establecido en el método desde donde se llamó a este formulario, o sea al establecido mediante la instrucción addAttribute en el método generaFormulario.  -->
	        <br>
			<form:input path="nombre" />
			<form:errors path="nombre" style="color:red"></form:errors>
			<br>
			<br>  
	        	
	        Apellidos(*): 
	        <br>
	        <form:input path="apellido" />  									<!-- Este path hace referencia a los getters y setters de la clase Alumnos, por tanto no podemos poner cualquier valor en path, sino exactamente el valor del campo de clase -->
	       	<form:errors path="apellido" style="color:red"></form:errors>
	        <br><br>
			
			e-mail(*):
	        <br>
	        <form:input path="email" />         
	        <form:errors path="email" style="color:red"></form:errors>
	        <br><br>
			
			<input type="submit" value="Insertar" />     	
	        <br><br>
	        
			<a href="generaClientes">Volver al listado de clientes</a>
		</form:form>	
	</body>
</html>