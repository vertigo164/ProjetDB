package premier_test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import accessBD.AccessBDGen;
import accessBD.TableModelGen;

import java.util.*;
import java.sql.*;


public class Fenetre extends JFrame {
	
private JMenuBar barre = new JMenuBar();
private JMenu menuFonctionnalites, menuLister, menuRecherche ;
private JMenuItem encodage,suppression;
private JMenuItem installTable, allTable;
private JMenuItem parmiInstall, parmiPC;

private FormuInstall formuInstall;

private ListeTableInstallation tableInstall;

private ListeAllTable tables;

private Container cont;

private JPanel panConnexion;
private JLabel indicationConnexion, identifiant, password;
private JTextField champID, champMDP;
private JButton connexion;

private GridBagConstraints gbc;

private FormuSuppression suppr;

private ListeRecheDate dateRecherche;
private ListeSoftware listeSoft;

private Connection connect;


//Fenêtre
	public Fenetre() {
		super("Projet JAVA");
		setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		MonGestionnaireAction g = new MonGestionnaireAction();
//Fin Fenêtre

		indicationConnexion = new JLabel("Cliquez sur connexion pour vous connecter à la base de données");	
		identifiant = new JLabel("Identifiant :");
		password = new JLabel("Mot de Passe :");
		champID = new JTextField(10);
		champMDP = new JTextField(10);
	
		connexion = new JButton("Connexion");
		connexion.addActionListener(g);
		
	
		panConnexion = new JPanel();
		panConnexion.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets (10,10,0,5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panConnexion.add(identifiant, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panConnexion.add(password, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panConnexion.add(champID, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panConnexion.add(champMDP, gbc);
	
	
	
	
//Container	
		cont = getContentPane();
		cont.setLayout(new GridBagLayout());
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		cont.add(indicationConnexion, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		cont.add(connexion, gbc);
	
	
//Fin Container
		
//Menu
		setJMenuBar(barre);

		menuFonctionnalites = new JMenu("Menu");
		menuFonctionnalites.setEnabled(false);
		barre.add(menuFonctionnalites);
    
		encodage = new JMenuItem("Installation");
		menuFonctionnalites.add(encodage);
    
    
		suppression = new JMenuItem("suppression");
		menuFonctionnalites.add(suppression);
    
    
		menuLister = new JMenu("Listing");
		menuLister.setEnabled(false);
		barre.add(menuLister);
   	
		installTable = new JMenuItem("Table Installation");
		allTable = new JMenuItem("All");
		menuLister.add(installTable);
		menuLister.add(allTable);

		menuRecherche = new JMenu("Recherche");
		menuRecherche.setEnabled(false);
		barre.add(menuRecherche);
	
		parmiInstall = new JMenuItem("Parmi les Installations");
		parmiPC = new JMenuItem("Parmi les Software Préinstallé");
		menuRecherche.add(parmiInstall);
		menuRecherche.add(parmiPC);
	

	
		encodage.addActionListener(g);
		installTable.addActionListener(g);
		allTable.addActionListener(g);
		suppression.addActionListener(g);
		parmiInstall.addActionListener(g);
		parmiPC.addActionListener(g);
	
		setVisible(true);


	}

	private class MonGestionnaireAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == encodage) {
				cont.removeAll();
				formuInstall = new FormuInstall(getFenetre());
				cont.add(formuInstall);
				cont.repaint();
				cont.revalidate();
			}
			else if(e.getSource() == installTable) {
				cont.removeAll();   
				tableInstall = new ListeTableInstallation(getFenetre());       	
				cont.add(tableInstall);
				cont.repaint();
				cont.revalidate();
			}
			else if (e.getSource() == allTable) {
				cont.removeAll();   
				tables = new ListeAllTable(getFenetre());  
				JScrollPane scroll = new JScrollPane(tables);
				cont.setLayout(new BorderLayout());
				cont.add(scroll);
				cont.repaint();
				cont.revalidate();
			}
			else if(e.getSource() == suppression) {
				cont.removeAll();   
        		suppr = new FormuSuppression(getFenetre());
        		cont.add(suppr);
        		cont.repaint();
        		cont.revalidate();
			}	
			
			else if(e.getSource() == parmiInstall) {
				cont.removeAll();   
				dateRecherche = new ListeRecheDate(getFenetre());
				cont.add(dateRecherche);
				cont.repaint();
				cont.revalidate();
			}
			else if(e.getSource() == parmiPC) {
				cont.removeAll();   
				listeSoft = new ListeSoftware(getFenetre());
				cont.add(listeSoft);
				cont.repaint();
				cont.revalidate();
			}
			if(e.getSource() == connexion) {
				int confirm = JOptionPane.showConfirmDialog(null,panConnexion,"Connexion", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.OK_OPTION) {
					if(connection() != null){
						connection();
						JOptionPane.showMessageDialog(null, "Connecté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
						menuFonctionnalites.setEnabled(true);
						menuLister.setEnabled(true);
						menuRecherche.setEnabled(true);
						connexion.setEnabled(false);
					}else {
						JOptionPane.showMessageDialog(null, "Erreur, l'identifiant et/ou le mot de passe est incorrect","Erreur Connexion", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

    public Connection connection() {
    	
    	try {
    		connect = AccessBDGen.connecter("DbInstallations", champID.getText(), champMDP.getText());
    		//connect = AccessBDGen.connecter("DbInstallations", "root", "Tigrou007=");
    		return connect;
    	
    	
    	}
    	catch(SQLException e) {
    		e.getMessage();
    	}
    	return null;
    }

    
	
    public Fenetre getFenetre() {
    	return this;
    }

	public Container getContInstall() {
		return cont;
	}
    
    
    
    

    
}



