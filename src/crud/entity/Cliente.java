package crud.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity									//Anotacion que le permite a Hibernate transformar nuestra clase Java en una entidad para poder realizar el mapeo
@Table(name="cliente")	
public class Cliente {
	
	//campos de clase que se deben corresponder con las columnas de la tabla Clientes de la base de datos pruebasHibernate
	@Id														//Anotacion que especifica cual va a ser el campo de clase que mapea el campo clave de la tabla de la BD
	@GeneratedValue(strategy=GenerationType.IDENTITY)		//Creacion de una clave principal en Hibernate, para poder rescatar la info de la tabla
	@Column(name="id")										//Anotacion que especifica el campo de clase mapeado a la columna id de la tabla. Tiene que coincidir exacto con nombre de la columna en la tabla en la base de datos
	private int id;
	
	@Size(min=2,max=20,message="Debe ingresar al menos dos caracteres")				//indicamos que tampoco puede ingresarse una cantidad de caracteres menores a 2 letras. Se incluye un mensaje que le saldrá al usuario en caso de no cumplir este criterio.
	@NotEmpty(message="nombre requerido")
	@Column(name="nombre")															//Tiene que coincidir exacto con nombre de la columna en la tabla en la base de datos
	private String nombre;
	
	@Size(min=2,max=20,message="Debe ingresar al menos dos caracteres")
	@NotEmpty(message="apellido requerido")
	@Column(name="apellido")														//Tiene que coincidir exacto con nombre de la columna en la tabla en la base de datos
	private String apellido;
	
	@Email(message="Ingresa un e-mail valido")
	@NotEmpty(message="e-mail requerido")
	@Column(name="email")															//Tiene que coincidir exacto con nombre de la columna en la tabla en la base de datos
	private String email;
	
	public Cliente(){}

	//Metodos getter y setters de la clase
	//Para los getters y setters si creo los mismos para el campo id pues es muy probable se utilicen estos metodos en consultas de tipo CRUD
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}
}
