package premier_test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.TableColumn;

import accessBD.AccessBDGen;
import accessBD.TableModelGen;

	public class ListeSoftware extends JPanel {

		private Fenetre parent;
		private JComboBox typePc;
		private JPanel pan;
		private JLabel choosePc;
		private JButton boutonRecherche;
		private GridBagConstraints gbc;
		private JTable softTable;
		
		
		public ListeSoftware(Fenetre fen) {
			parent = fen;
			choosePc = new JLabel("Choisissez un pc :");
			
			boutonRecherche = new JButton("Rechercher");
					
			typePc = new JComboBox();
			typePc.addItem("Choix typePc");
			pan = new JPanel();
			gbc = new GridBagConstraints();
			gbc.insets = new Insets (20,10,0,5);
			
			add(choosePc);
			add(typePc);
			add(boutonRecherche);
			
			setVisible(true);
			try {
				Connection connection = parent.connection();
			ResultSet res = connection.createStatement().executeQuery("select Description from TypePC");
			while (res.next()) {
		        typePc.addItem(res.getString("Description"));}
			connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
			rechercheSoft res = new rechercheSoft();
			boutonRecherche.addActionListener(res);
			add(pan);
		}
				
		private class rechercheSoft implements ActionListener {	
			public void actionPerformed(ActionEvent e) {
				try {	
					if(comboValide()) {
						Connection connection = parent.connection();
						String select = "select * from SoftwarePreinstalle where IdTypePc in (select IdTypePc from TypePc where Description = ? and CodeSoftware in (select CodeSoftware from Software where MemCarteVideoMin is null))";
						PreparedStatement prepStat = connection.prepareStatement(select);
						prepStat.setString(1, String.valueOf(typePc.getSelectedItem()));
						ResultSet result = prepStat.executeQuery();
						TableModelGen modelInstall = AccessBDGen.creerTableModel(prepStat);
						softTable = new JTable(modelInstall);
						
						TableColumn col = softTable.getColumnModel().getColumn(0);
						col.setPreferredWidth(250);
						TableColumn col2 = softTable.getColumnModel().getColumn(1);
						col2.setPreferredWidth(250);
						softTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						JScrollPane scroll = new JScrollPane(softTable);
						scroll.setPreferredSize(new Dimension(503,250));
						removeAll();
						add(scroll);

						repaint();
						revalidate();
					
						connection.close();
					}
				}
				catch(SQLException t) {
					t.getMessage();
				}
			}
		
		}
		private boolean comboValide() {
			boolean comboVal = true;
			
			if(typePc.getSelectedItem ()== "Choix typePc") {
		        JOptionPane.showMessageDialog(null, "Veuillez Séléctionner un TypePc", "Erreur", JOptionPane.ERROR_MESSAGE);
		        comboVal = false;}
			
			return comboVal;
		}	
}