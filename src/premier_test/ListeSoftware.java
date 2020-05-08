package premier_test;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class ListeSoftware extends JPanel {

	private Fenetre parent;
	private JComboBox typePc;
	private JPanel pan;
	private JLabel choosePc;
	private JButton ok;
	private GridBagConstraints gbc;
	
	
	public ListeSoftware(Fenetre fen) {
		parent = fen;
		pan = new JPanel();
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets (20,10,0,5);
		choosePc = new JLabel("Choisissez un pc :");
		ok = new JButton("OK");
		typePc = new JComboBox();
		try {
			Connection connection = parent.connection();
		ResultSet res = connection.createStatement().executeQuery("select Description from TypePC");
		while (res.next()) {
	        typePc.addItem(res.getString("Description"));}
		connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(choosePc, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(typePc, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.EAST;
		add(ok, gbc);
		
		setVisible(true);
	}
	
	
}
