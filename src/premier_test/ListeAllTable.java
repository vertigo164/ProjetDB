package premier_test;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;



public class ListeAllTable extends JPanel{

	
	
	
	private JTable tables;
	private JScrollPane scroll, scrollGen;
	private Fenetre fenParent;
	
	private String requetes[] = {"select * from AnneeEtude", "select * from Editeur","select * from FamilleSoftware","select * from Fournisseur", "select * from Installation","select * from OS","select * from Professeur", "select * from ResponsableReseaux","select * from Section","select * from Software", "select * from SoftwarePreinstalle", "select * from TypePC","select * from UtilisationSoftware" };
	
	public ListeAllTable(Fenetre fen) {
		fenParent = fen;
		Box box = Box.createVerticalBox();
		
		
		
		
		int hauteurPanneau = 0;
		 
		try {
			Connection connect = fen.connection();
	
			for (String requete : requetes) {
				
				PreparedStatement prepStat = connect.prepareStatement(requete);
				TableModelGen model = AccessBDGen.creerTableModel(prepStat);
				tables = new JTable(model);
				tables.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				scroll = new JScrollPane(tables);
				int hauteur = tables.getPreferredSize().height;
				hauteurPanneau += hauteur + 50;
				scroll.setPreferredSize(new Dimension(600,hauteur +50));
				box.add(scroll);
				
			}
			
			setPreferredSize(new Dimension(700, hauteurPanneau));
			add(box);
			connect.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		setVisible(true);
	}
	 
}

