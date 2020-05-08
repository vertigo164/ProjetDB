package premier_test;


import java.awt.*;
import java.awt.FlowLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;


public class ListeTableInstallation extends JPanel{
	
	
	private JTable tableInstall;
	private JScrollPane scroll;
	private Fenetre fenParent;
	
	public ListeTableInstallation(Fenetre fen) {
		
		fenParent = fen;
		setLayout(new FlowLayout());
		
		try {
			Connection connect = fen.connection();
	
			String select = "select * from Installation";
			PreparedStatement requete = connect.prepareStatement(select);
			TableModelGen modelInstall = AccessBDGen.creerTableModel(requete);
			tableInstall = new JTable(modelInstall);
			tableInstall.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scroll = new JScrollPane(tableInstall);
			connect.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		add(scroll);
		
	}
	 
}

