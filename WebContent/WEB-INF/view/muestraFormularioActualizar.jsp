<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Formulario de Actualizar Registro</title>
	</head>
	<body>
		<h3>Usted ha elegido actualizar el cliente:</h3>
		<br>
		${clienteAActualizar.nombre} <br>
		${clienteAActualizar.apellido} <br>
		${clienteAActualizar.email}	<br>
			
		<h4>Modifique los parámetros que desee en los campos de texto y pulse Actualizar para guardar los cambios</h4>
		<br>
		
		<form:form action="generaFormularioActualizar2" modelAttribute="clienteAActualizar" method="POST"> 	<!-- Las redirección a generaFormulario2 no necesita de establecer la ruta relativa con cliente/ ya que esto ya se hizo para el origen de la cadena de redirecciones. -->
			
			<form:hidden path="id"/>						<!-- Este campo oculto que no lo ve el usuario es para poder obtener la id del cliente que el usuario está actualizando, para poder luego utilizar este dato para actualizar en la base de datos con los valores tecleados por el usuario el registro correspondiente con el id. El id se cargará en este campo justo como se cargan los otros campos cuando se abre este formulario de actualizar, al dar al botón Actualizar se manda toda la info incluyendo el id al método generaFormularioActualizar2-->
			
			Nombre(*): 																						<!-- El valor de la propiedad modelAttribute tiene que ser igual al establecido en el método desde donde se llamó a este formulario, o sea al establecido mediante la instrucción addAttribute en el método generaFormulario.  -->
	        <br>
			<form:input path="nombre" />					<!-- Esta JSP tag me permite depositar en el cuadro de texto Nombre(*) el valor que haya en el campo de clase nombre, que como en el controlador se obtuvo un cliente previamente será el nombre de ese cliente, el cual está relacionado con el id del cliente escogido para actualizar. -->
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
			
			<input type="submit" value="Actualizar" />     	
	        <br><br>
		</form:form>
			
		<br><br>
		<a href="generaClientes">Volver al listado de clientes</a>
	</body>
</html>