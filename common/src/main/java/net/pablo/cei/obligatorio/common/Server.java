package net.pablo.cei.obligatorio.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
	public String sayHello() throws RemoteException;
	public void senMessage(String message) throws RemoteException;
	public void addObserver(Observer observer) throws RemoteException; 
}
