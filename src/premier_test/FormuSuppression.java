package premier_test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

public class FormuSuppression extends JPanel{
	private JComboBox os;
	private JPanel pan;
	private JButton ok;
	private JButton supprimer;
	private JTable tableInstall;
	private int reponse;
	
	
	private Fenetre parent;
	
	public FormuSuppression(Fenetre fen) {
		parent = fen;
		setLayout(new BorderLayout());
		os = new JComboBox();
		os.addItem("Choix OS");
		pan = new JPanel();
		
		ok = new JButton("OK");
		
		GestionnaireAction g = new GestionnaireAction();
		ok.addActionListener(g);
	try {
		Connection connection = parent.connection();
	ResultSet comboOS = connection.createStatement().executeQuery("select Libelle from OS");
	while (comboOS.next()) {
        os.addItem(comboOS.getString("Libelle"));}
	connection.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	pan.add(os, BorderLayout.CENTER);
	pan.add(ok);
	add(pan);
	
	setVisible(true);
	}
	
	

			
		
			
	
private class GestionnaireAction implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		
		
		
		if(e.getSource() == ok) {
			try {
				removeAll();
				Connection connection = parent.connection();
			
				String select = "select * from Installation where CodeOS = ?";
				PreparedStatement prepStat = connection.prepareStatement(select);
				prepStat.setString(1,recupCodeOS());
				ResultSet result = prepStat.executeQuery();
			
				TableModelGen modelInstall = AccessBDGen.creerTableModel(prepStat);
				tableInstall = new JTable(modelInstall);
				tableInstall.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
			
			
				JScrollPane scroll = new JScrollPane(tableInstall);
				add(scroll);
				
				supprimer = new JButton("Supprimer");
				GestionnaireAction g = new GestionnaireAction();
				supprimer.addActionListener(g);
				add(supprimer, BorderLayout.SOUTH);
				
				connection.close();
			}catch(SQLException d) {
				d.printStackTrace();
			}
			repaint();
			revalidate();
		
		}
		
		if(e.getSource() == supprimer) {
			try {
				
				reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette ligne?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.OK_OPTION) {
					
				Connection connection = parent.connection();
				String delete = "delete from Installation where idInstallation = ?";
				PreparedStatement prepStat = connection.prepareStatement(delete);
				prepStat.setInt(1,getId());
				prepStat.executeUpdate();
				
				connection.close();
				}
				
			}catch(SQLException d) {
				d.printStackTrace();
			}
		
		}
}
	
private int getId() {
	tableInstall.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	int indiceLigneSelect = tableInstall.getSelectedRow();
	int id =  (int)tableInstall.getModel().getValueAt(indiceLigneSelect, 1 );
	System.out.println(id);
	return id ;
}
}






private String recupCodeOS() {
		Connection connection = parent.connection();
		String res = "";
		try {
	        String getComboString = String.valueOf(os.getSelectedItem());
	        String requete = "select CodeOS from os where Libelle = ?";
	        PreparedStatement prepStat = connection.prepareStatement(requete);
	        prepStat.setString(1,getComboString);
	        ResultSet result = prepStat.executeQuery();
	            while(result.next()) {
	                res = result.getString(1);
	        }
	            connection.close();
	            
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return res;
	}
	
	
	
	
}