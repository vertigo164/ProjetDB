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
	private JPanel pan, pan2, pan3;
	private JButton ok;
	private JButton supprimer;
	private JTable tableInstall;
	private int reponse;
	
	
	private Fenetre parent;
	
	public FormuSuppression(Fenetre fen) {
		parent = fen;
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
				pan2 = new JPanel();
				pan3 = new JPanel();
				setLayout(new BorderLayout());
				Box box = Box.createVerticalBox();
				Box box2 = Box.createHorizontalBox();
				
				Connection connection = parent.connection();	
				String select = "select * from Installation where CodeOS = ?";
				PreparedStatement prepStat = connection.prepareStatement(select);
				
				if(comboValide()) {
					prepStat.setString(1,recupCodeOS());
					ResultSet result = prepStat.executeQuery();
					TableModelGen modelInstall = AccessBDGen.creerTableModel(prepStat);
					tableInstall = new JTable(modelInstall);
					box.add(tableInstall);
					tableInstall.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				
					JScrollPane scroll = new JScrollPane(tableInstall);
					removeAll();
					add(scroll);
				
					supprimer = new JButton("Supprimer");
					box2.add(supprimer);
				
					GestionnaireAction g = new GestionnaireAction();
					supprimer.addActionListener(g);

					pan2.add(scroll);
					add(pan2, BorderLayout.CENTER);
					pan3.add(box2);	
					add(pan3, BorderLayout.SOUTH);
					repaint();
					revalidate();
				}
	
				connection.close();
					
			}catch(SQLException d) {
				d.getMessage();
			}
		
		}
		
		if(e.getSource() == supprimer) {
			try {
				
				reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette ligne?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.OK_OPTION) {
					
				Connection connection = parent.connection();
				String delete = "delete from Installation where idInstallation = ?";
				PreparedStatement prepStat = connection.prepareStatement(delete);
				prepStat.setObject(1,getId());
				
				if(prepStat.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(null, "Confirmation de suppression", "Information", JOptionPane.INFORMATION_MESSAGE);
					
					
				}
				
				connection.close();
				}
				
			}catch(SQLException d) {
				d.getMessage();
				}
		
			}
		}
	}
	
	
private Object getId() {
	Object id = tableInstall.getValueAt(tableInstall.getSelectedRow(), 0);
	return id ;
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
	    	e.getMessage();
	    }
	    return res;
	}

private boolean comboValide() {
	boolean comboVal = true;
	
	if(os.getSelectedItem ()== "Choix OS") {
        JOptionPane.showMessageDialog(null, "Veuillez Séléctionner un OS", "Erreur", JOptionPane.ERROR_MESSAGE);
        comboVal = false;}
	
	return comboVal;
}
}
	
	
	