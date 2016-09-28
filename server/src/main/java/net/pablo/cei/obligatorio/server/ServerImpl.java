package net.pablo.cei.obligatorio.server;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import net.pablo.cei.obligatorio.common.Observer;
import net.pablo.cei.obligatorio.common.Server;

public class ServerImpl implements Server{
	private List<Observer> observers;
	@SuppressWarnings("deprecation")
	public ServerImpl() {
		String path = "C:\\Users\\Pablo\\Documents\\java.policy.txt";
		path = path.replace("\\", "/");
		System.setProperty("java.security.policy","file:///"+path);

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		this.observers= new ArrayList<Observer>();
	}

	public String sayHello() throws RemoteException {

		return  "hola remoto";
	}

	public void senMessage(String message) throws RemoteException {
		for(Observer o :this.observers){
			o.notify(message);
		}
		
	}

	public void addObserver(Observer observer) throws RemoteException {
		this.observers.add(observer);
		
	}

}
