package premier_test;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class ListeSoftware extends JPanel {

	private Fenetre parent;
	private JComboBox typePc;
	private JPanel pan;
	
	
	public ListeSoftware(Fenetre fen) {
		parent = fen;
		pan = new JPanel();
		setLayout(new BorderLayout());
		
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
		pan.add(typePc);
		add(pan);
		setVisible(true);
	}
		
	
}
