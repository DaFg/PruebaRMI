package net.pablo.cei.obligatorio.gui;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import net.pablo.cei.obligatorio.common.Server;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class VentanaObserver {

	private JFrame frame;
	private JTextField textField;
	private Server server;
	private DefaultListModel<String> listModel;

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VentanaObserver window = new
	 * VentanaObserver(); window.frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the application.
	 */
	public VentanaObserver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 93, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -89, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.senMessage(textField.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -95, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -51, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);

		JList list = new JList();
		springLayout.putConstraint(SpringLayout.NORTH, list, 51, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, list, 52, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(list);
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void addMessage(String messaje) {
		listModel.addElement(messaje);
	}
}
