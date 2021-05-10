package crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import crud.entity.Cliente;

@Repository										//Para que toda esta clase sea registrada en Spring como un bean.
public class ClienteDAOclase implements ClienteDAOinterface{

	@Autowired									//Permite la inyección de dependencias del sessionFactory dentro de la clase.
	private SessionFactory sessionFactory;		//Notar como no tengo que inicializar el sessionFactory, ya Spring me lo inyecta pronto para utilizar
	
	
	//Método para obtener los clientes de la base de datos
	@Override									//Sobreescribo el método de la interfaz 
	@Transactional								//Permite realizar la transacción, evitando tener que realizar el begin transaction(), el commit()
	public List<Cliente> getClientes() {
		
		//Declarar e inicializar el objeto Session.	
		Session session=sessionFactory.getCurrentSession();				
			
		//Declarar e inicializar el objeto Query. Realizo la consulta en HQL en la tabla y mapeo esos datos a la clase Cliente	
		Query<Cliente> query=session.createQuery("from Cliente",Cliente.class);		
			
		//Declarar e inicializar el objeto List donde se guardarán los objetos de tipo Cliente mapeados desde la tabla cliente de la base de datos. 	
		List<Cliente> clientes=query.getResultList();
		
		return clientes;
	}

	//Método que inserta un cliente en la base de datos 
	@Override
	@Transactional
	public void insertarCliente(Cliente elCliente) {
		
		//Declarar e inicializar el objeto Session.		
		Session session=sessionFactory.getCurrentSession();
		
		//Insertar el Cliente	
		session.save(elCliente);								//El método save realiza por detrás un Insert into
	}

	
	//Método que rescata un cliente de la base de datos
	@Override
	@Transactional
	public Cliente getCliente(int id) {
		
		//Declarar e inicializar el objeto Session.		
		Session session=sessionFactory.getCurrentSession();
		
		//Obtener el cliente utilizando el método get. Esta línea rescata de la base de datos el cliente en cuestión.
		Cliente cliente=session.get(Cliente.class,id);
		
		//Devolver el cliente
		return cliente;
	}
	
	//Método que actualiza un cliente en la base de datos
	@Override
	@Transactional
	public void actualizaCliente(Cliente elCliente) {
	
		//Declarar e inicializar el objeto Session.		
		Session session=sessionFactory.getCurrentSession();
		
		//Actualizar el cliente en la base de datos cuyo id sea el id de elCliente, que esta relacioando con clienteAActualizar
		session.update(elCliente);
	}
	
	//Método que elimina un cliente en la base de datos
	@Override
	@Transactional
	public void eliminaCliente(Cliente cliente) {
		
		//Declarar e inicializar el objeto Session.		
		Session session=sessionFactory.getCurrentSession();
		
		//Eliminar el cliente de la base de datos empleando el método delete.
		session.delete(cliente);
	}
}
