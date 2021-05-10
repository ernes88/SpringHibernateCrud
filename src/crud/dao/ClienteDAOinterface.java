//Esta interfaz me debe devolver una lista con todos los clientes. Utilizando inyección de dependencias se inyectará donde lo necesitemos el objeto ClienteDAO.
package crud.dao;

import java.util.List;

import crud.entity.Cliente;

public interface ClienteDAOinterface {
	
	public List<Cliente> getClientes();

	public void insertarCliente(Cliente elCliente);

	public Cliente getCliente(int id);

	public void actualizaCliente(Cliente elCliente);

	public void eliminaCliente(Cliente cliente);
	
}
