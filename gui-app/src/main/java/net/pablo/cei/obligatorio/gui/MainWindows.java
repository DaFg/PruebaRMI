package net.pablo.cei.obligatorio.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import net.pablo.cei.obligatorio.common.Server;

public class MainWindows {

	private JFrame frame;
	private JList list;
	private JTextField textField;
	private Server server;
	private DefaultListModel<String> listModel;

	/**
	 * Create the application.
	 */
	public MainWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, list, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, list, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, list, 190, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, list, 450, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		frame.getContentPane().add(list);

		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -28, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textField, 215, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.senMessage(textField.getText());
					textField.setText("");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 18, SpringLayout.EAST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -28, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

	public void addMessage(String messaje) {
		listModel.addElement(messaje);
	}

	public void setServer(Server server) {
		this.server = server;
	}

}
