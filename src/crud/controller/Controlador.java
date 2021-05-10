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

	@Autowired											//Inyecto el objeto de la interfaz para ser usado en el controlador. Este objeto lo necesitamos para poder usar el método getClientes() que es el que maneja la consulta 
	private ClienteDAOinterface clienteDAOinterface;			
	
	//Obtención de los clientes de la base de datos
	@RequestMapping("/generaClientes")
	public String generaClientes(Model model){
		
		//Obtener los clientes desde el DAO, para lo cual necesitamos de un objeto de la interfaz ClienteDAOinterfaz y su método getClientes()
		List<Cliente>losClientes=clienteDAOinterface.getClientes();
		
		//Agregar losClientes al modelo. Añado el contenido de losClientes a la propiedad "clientes" para poder direccionar el contenido de losClientes desde la página muestraClientes.jsp mediante JSPTags
		model.addAttribute("clientes", losClientes);
		
		//retorno la página JSP donde se listan los clientes. 
		return "muestraClientes";	
	}
	
	//Métodos para crear un cliente y insertarlo en la base de datos
	@RequestMapping("/generaFormularioRegistro")
	public String generaFormularioRegistro(Model model){
		
		//Crear un objeto de tipo Cliente que añadir al modelo
		Cliente cliente=new Cliente();
		
		//Añadir ese cliente al modelo
		model.addAttribute("clienteARegistrar", cliente);			//este atributo "clienteARegistrar" será usado como representación del objeto cliente en muestraFormularioRegistro.jsp
		
		//retorno la página JSP donde se muestra el formulario.
		return "muestraFormularioRegistro";
	}
	
	@PostMapping("generaFormularioRegistro2")														//Instrucción para obtener lo que viene del formulario de registro, donde se usó POST como método de envío.
	public String generaFormularioRegistro2(@Valid @ModelAttribute("clienteARegistrar") Cliente elCliente, BindingResult resultadoValidacion){		//Acá en ModelAttribute se debe poner el atributo que establecimos en el método generaFormularioRegistro2 y que es el mismo también en muestraFormularioRegistro.jsp
		
		if(resultadoValidacion.hasErrors())
			
			return "muestraFormularioRegistro";			//si lo introducido tiene errores de acuerdo a lo que estamos validando mando el flujo a la pagina de registro
		
		else{
			//Insertar cliente en la base de datos
			clienteDAOinterface.insertarCliente(elCliente);
			
			//Mostrar la página de confirmación de registro
			return "confirmacionRegistro";			
		}
	}
	
	//Métodos para actualizar un cliente de la base de datos
	@GetMapping("/generaFormularioActualizar")														//En este caso, desde la página muestraCliente.jsp, utilizamos la etiqueta <c:url> para enviar info desde esa página hasta este método en el controlador. Como usamos una url para mandar el id del cliente sobre el cual pulsamos en esa página jsp, pues hay que usar acá el método GET para obtener info de esa url, y para esto nos sirve la anotación @GetMapping
	public String generaFormularioActualizar(@RequestParam("clienteId") int id, Model model){		//Este método va a recibir como parámetro el Id del cliente y para esto se utiliza @RequestParam("clienteID") donde "clienteId" será un nombre identificativo que se le da que puede ser cualquiera. Luego tenemos que especificar con que campo de clase se corresponde este nombre identificativo que hemos establecido, y se corresponde con el campo de tipo int Id.
		
		//Crear un objeto de tipo Cliente cuyo id le estamos pasando por parámetro al método
		Cliente cliente=clienteDAOinterface.getCliente(id);
				
		//Añadir este cliente al modelo
		model.addAttribute("clienteAActualizar", cliente);			//este atributo "clienteAActualizar" será usado como representación del objeto cliente en muestraFormularioActualizar.jsp
				
		return "muestraFormularioActualizar";
	}
	
	@PostMapping("generaFormularioActualizar2")														//Instrucción para obtener lo que viene del formulario de registro, donde se usó POST como método de envío.
	public String generaFormularioActualizar2(@Valid @ModelAttribute("clienteAActualizar") Cliente elCliente, BindingResult resultadoValidacion){		//Acá en ModelAttribute se debe poner el atributo que establecimos en el método generaFormularioRegistro2 y que es el mismo también en muestraFormularioRegistro.jsp
		
		if(resultadoValidacion.hasErrors())
			
			return "muestraFormularioActualizar";			//si lo introducido tiene errores de acuerdo a lo que estamos validando mando el flujo a la pagina de registro
		
		else{
		
			//Actualizar el cliente escogido en la base de datos
			clienteDAOinterface.actualizaCliente(elCliente);
		
			//Mostrar la página de confirmación de actualización
			return "confirmacionActualizacion";	
		}
	}
	
	//Método para eliminar un registro de la base de datos
	@GetMapping("/eliminaRegistro")
	public String eliminaRegistro(@RequestParam("clienteId") int id, Model model){
		
		//Crear un objeto de tipo Cliente cuyo id le estamos pasando por parámetro al método contenido en la propiedad clienteID donde @RequestParam lo convierte a int en id
		Cliente cliente=clienteDAOinterface.getCliente(id);
		
		//Añadir este cliente al modelo
		model.addAttribute("clienteAEliminar", cliente);
		
		//Eliminar el cliente escogido en la base de datos
		clienteDAOinterface.eliminaCliente(cliente);
		
		//Mostrar la página de confirmación de eliminación
		return "confirmacionEliminacion";	
	}
	
	//Metodo que se encarga de resolver el problema de los espacios en blanco en los campos de texto para validacion al inicio de los mismos, cuando no se introduce texto
	@InitBinder
	public void binderMethod (WebDataBinder binder){
			
		StringTrimmerEditor trimmer=new StringTrimmerEditor(true);			//true como argumento para que tome como null los espacios en blanco
			
		binder.registerCustomEditor(String.class, trimmer);
	}
}
