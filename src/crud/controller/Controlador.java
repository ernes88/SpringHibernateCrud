package crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crud.dao.ClienteDAOinterface;
import crud.entity.Cliente;

@Controller
@RequestMapping("/cliente")
public class Controlador {

	@Autowired											//Inyecto el objeto de la interfaz para ser usado en el controlador. Este objeto lo necesitamos para poder usar el m�todo getClientes() que es el que maneja la consulta 
	private ClienteDAOinterface clienteDAOinterface;			
	
	//Obtenci�n de los clientes de la base de datos
	@RequestMapping("/generaClientes")
	public String generaClientes(Model model){
		
		//Obtener los clientes desde el DAO, para lo cual necesitamos de un objeto de la interfaz ClienteDAOinterfaz y su m�todo getClientes()
		List<Cliente>losClientes=clienteDAOinterface.getClientes();
		
		//Agregar losClientes al modelo. A�ado el contenido de losClientes a la propiedad "clientes" para poder direccionar el contenido de losClientes desde la p�gina muestraClientes.jsp mediante JSPTags
		model.addAttribute("clientes", losClientes);
		
		//retorno la p�gina JSP donde se listan los clientes. 
		return "muestraClientes";	
	}
	
	//M�todos para crear un cliente y insertarlo en la base de datos
	@RequestMapping("/generaFormularioRegistro")
	public String generaFormularioRegistro(Model model){
		
		//Crear un objeto de tipo Cliente que a�adir al modelo
		Cliente cliente=new Cliente();
		
		//A�adir ese cliente al modelo
		model.addAttribute("clienteARegistrar", cliente);			//este atributo "clienteARegistrar" ser� usado como representaci�n del objeto cliente en muestraFormularioRegistro.jsp
		
		//retorno la p�gina JSP donde se muestra el formulario.
		return "muestraFormularioRegistro";
	}
	
	@PostMapping("generaFormularioRegistro2")														//Instrucci�n para obtener lo que viene del formulario de registro, donde se us� POST como m�todo de env�o.
	public String generaFormularioRegistro2(@Valid @ModelAttribute("clienteARegistrar") Cliente elCliente, BindingResult resultadoValidacion){		//Ac� en ModelAttribute se debe poner el atributo que establecimos en el m�todo generaFormularioRegistro2 y que es el mismo tambi�n en muestraFormularioRegistro.jsp
		
		if(resultadoValidacion.hasErrors())
			
			return "muestraFormularioRegistro";			//si lo introducido tiene errores de acuerdo a lo que estamos validando mando el flujo a la pagina de registro
		
		else{
			//Insertar cliente en la base de datos
			clienteDAOinterface.insertarCliente(elCliente);
			
			//Mostrar la p�gina de confirmaci�n de registro
			return "confirmacionRegistro";			
		}
	}
	
	//M�todos para actualizar un cliente de la base de datos
	@GetMapping("/generaFormularioActualizar")														//En este caso, desde la p�gina muestraCliente.jsp, utilizamos la etiqueta <c:url> para enviar info desde esa p�gina hasta este m�todo en el controlador. Como usamos una url para mandar el id del cliente sobre el cual pulsamos en esa p�gina jsp, pues hay que usar ac� el m�todo GET para obtener info de esa url, y para esto nos sirve la anotaci�n @GetMapping
	public String generaFormularioActualizar(@RequestParam("clienteId") int id, Model model){		//Este m�todo va a recibir como par�metro el Id del cliente y para esto se utiliza @RequestParam("clienteID") donde "clienteId" ser� un nombre identificativo que se le da que puede ser cualquiera. Luego tenemos que especificar con que campo de clase se corresponde este nombre identificativo que hemos establecido, y se corresponde con el campo de tipo int Id.
		
		//Crear un objeto de tipo Cliente cuyo id le estamos pasando por par�metro al m�todo
		Cliente cliente=clienteDAOinterface.getCliente(id);
				
		//A�adir este cliente al modelo
		model.addAttribute("clienteAActualizar", cliente);			//este atributo "clienteAActualizar" ser� usado como representaci�n del objeto cliente en muestraFormularioActualizar.jsp
				
		return "muestraFormularioActualizar";
	}
	
	@PostMapping("generaFormularioActualizar2")														//Instrucci�n para obtener lo que viene del formulario de registro, donde se us� POST como m�todo de env�o.
	public String generaFormularioActualizar2(@Valid @ModelAttribute("clienteAActualizar") Cliente elCliente, BindingResult resultadoValidacion){		//Ac� en ModelAttribute se debe poner el atributo que establecimos en el m�todo generaFormularioRegistro2 y que es el mismo tambi�n en muestraFormularioRegistro.jsp
		
		if(resultadoValidacion.hasErrors())
			
			return "muestraFormularioActualizar";			//si lo introducido tiene errores de acuerdo a lo que estamos validando mando el flujo a la pagina de registro
		
		else{
		
			//Actualizar el cliente escogido en la base de datos
			clienteDAOinterface.actualizaCliente(elCliente);
		
			//Mostrar la p�gina de confirmaci�n de actualizaci�n
			return "confirmacionActualizacion";	
		}
	}
	
	//M�todo para eliminar un registro de la base de datos
	@GetMapping("/eliminaRegistro")
	public String eliminaRegistro(@RequestParam("clienteId") int id, Model model){
		
		//Crear un objeto de tipo Cliente cuyo id le estamos pasando por par�metro al m�todo contenido en la propiedad clienteID donde @RequestParam lo convierte a int en id
		Cliente cliente=clienteDAOinterface.getCliente(id);
		
		//A�adir este cliente al modelo
		model.addAttribute("clienteAEliminar", cliente);
		
		//Eliminar el cliente escogido en la base de datos
		clienteDAOinterface.eliminaCliente(cliente);
		
		//Mostrar la p�gina de confirmaci�n de eliminaci�n
		return "confirmacionEliminacion";	
	}
	
	//Metodo que se encarga de resolver el problema de los espacios en blanco en los campos de texto para validacion al inicio de los mismos, cuando no se introduce texto
	@InitBinder
	public void binderMethod (WebDataBinder binder){
			
		StringTrimmerEditor trimmer=new StringTrimmerEditor(true);			//true como argumento para que tome como null los espacios en blanco
			
		binder.registerCustomEditor(String.class, trimmer);
	}
}
