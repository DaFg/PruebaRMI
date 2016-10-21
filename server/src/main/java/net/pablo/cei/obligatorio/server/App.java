package net.pablo.cei.obligatorio.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import net.pablo.cei.obligatorio.common.Server;
import net.pablo.cei.obligatorio.server.entities.Address;
import net.pablo.cei.obligatorio.server.entities.Task;
import net.pablo.cei.obligatorio.server.entities.User;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		EntityManager em = null;
		try {

			// Estas lineas son la ruta para los equipos con windows.
			String path = "C:\\java.policy";
			path = path.replace("\\", "/");

			// System.setProperty("java.security.policy",
			// "file://c:/java.policy");
			// System.setSecurityManager(new SecurityManager());

			System.setProperty("java.security.policy", "file:////java.policy");
			LocateRegistry.createRegistry(1099);
			ServerImpl obj = new ServerImpl();
			Server stub = (Server) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry(1099);
			registry.bind("server", stub);
			// JDBC
			System.out.println("Server ready");
			// new Memento();

			// JPA todo mal
			EntityManagerFactory emf;
			emf = Persistence.createEntityManagerFactory("jpaDS");
			em = (EntityManager) emf.createEntityManager();

			em.getTransaction().begin();
			User user = new User();
			Address address = new Address("la direccion");
			em.persist(address);
			em.persist(user);
			user.setAddress(address);
			em.getTransaction().commit();
			User u = em.find(User.class, user.getId());
			// u esta manejado por el EM
			em.close();
			// u deja de estar manejado por el EM
			em = (EntityManager) emf.createEntityManager();
			em.getTransaction().begin();
			u = em.merge(u);
			// u vuelve a estar manejado por un EM
			u.setName("algo");
			System.out.println(u.getTareas());
			if (u.getTareas() == null) {
				u.setTareas(new ArrayList<Task>());
			}
			Task task = new Task();
			u.getTareas().add(task);
			em.persist(task);
			em.getTransaction().commit();

			u = em.find(User.class, user.getId());

			Query query = em.createNamedQuery("findUserByName");
			query.setParameter("name", "algo");
			u = (User) query.getSingleResult();
			u.getTareas();
			System.out.println("---->2");

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
