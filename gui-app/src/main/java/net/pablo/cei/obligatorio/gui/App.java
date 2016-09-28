package net.pablo.cei.obligatorio.gui;

import java.awt.EventQueue;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import net.pablo.cei.obligatorio.common.Observer;
import net.pablo.cei.obligatorio.common.Server;

/**
 * Hello world!
 *
 */
public class App extends UnicastRemoteObject implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Server server;
	private MainWindows window;

	public App() throws RemoteException, NotBoundException {
		System.out.println(System.currentTimeMillis());

		/*
		 * Estas lineas son la ruta para los equipos con windows.
		 *
		 * String path = "C:\\Users\\Pablo\\Documents\\java.policy.txt"; path =
		 * path.replace("\\", "/"); System.setProperty("java.security.policy",
		 * "file:///"+path);
		 */

		System.setProperty("java.security.policy",
				"file:///home/david/Documentos/AP/DDA/RmiPrueba/PruebaRMI/java.policy");
		final App app = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainWindows();
					System.out.println("Hello World");
					Registry registry = LocateRegistry.getRegistry(1099);
					server = (Server) registry.lookup("server");
					window.setServer(server);
					String response = server.sayHello();
					System.out.println("response: " + response);
					server.addObserver(app);
					app.sendMessage("hola");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) throws RemoteException, NotBoundException {
		App app = new App();
	}

	public void sendMessage(String message) throws RemoteException {
		this.server.senMessage(message);
	}

	public void notify(String message) throws RemoteException {
		this.window.addMessage(message);

	}

}
