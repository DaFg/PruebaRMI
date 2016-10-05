package net.pablo.cei.obligatorio.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import net.pablo.cei.obligatorio.common.Server;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {

			// Estas lineas son la ruta para los equipos con windows.

			String path = "C:\\java.policy";
			path = path.replace("\\", "/");

			System.setProperty("java.security.policy", "file://c:/java.policy");
			System.setSecurityManager(new SecurityManager());

			// System.setProperty("java.security.policy",
			// "file:////java.policy");
			LocateRegistry.createRegistry(1099);
			ServerImpl obj = new ServerImpl();
			Server stub = (Server) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry(1099);
			registry.bind("server", stub);

			System.out.println("Server ready");
			new Memento();

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
			System.exit(1);
		}

	}
}
