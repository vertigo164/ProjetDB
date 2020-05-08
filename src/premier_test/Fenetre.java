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
private Container contInstall1;

private FormuSuppression suppr;

private ListeSoftware listeSoft;
private Connection connect;

//Fenêtre
public Fenetre() {
	super("projet ");
	setSize(800, 800);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	
//Fin Fenêtre

//Container	
	cont = getContentPane();
	cont.setLayout(new BorderLayout());

	
	
//Fin Container
		
//Menu
	setJMenuBar(barre);

    menuFonctionnalites = new JMenu("Menu");
    menuFonctionnalites.setMnemonic('M');
    barre.add(menuFonctionnalites);
    
    encodage = new JMenuItem("Installation");
    menuFonctionnalites.add(encodage);
    
    
    suppression = new JMenuItem("suppression");
	menuFonctionnalites.add(suppression);
    
    
	menuLister = new JMenu("Listing");
	barre.add(menuLister);
    
	installTable = new JMenuItem("Table Installation");
	allTable = new JMenuItem("All");
	menuLister.add(installTable);
	menuLister.add(allTable);

	menuRecherche = new JMenu("Recherche");
	barre.add(menuRecherche);
	
	parmiInstall = new JMenuItem("Parmi les Installations");
	parmiPC = new JMenuItem("Parmi les Software Préinstallé");
	menuRecherche.add(parmiInstall);
	menuRecherche.add(parmiPC);
	

	MonGestionnaireAction g = new MonGestionnaireAction();
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
		else if(e.getSource() == parmiPC) {
			cont.removeAll();   
        	listeSoft = new ListeSoftware(getFenetre());
        	cont.add(listeSoft);
        	cont.repaint();
        	cont.revalidate();
		}
    	
	}
	}

    public Connection connection() {
    	
    	try {
    		Connection connect = AccessBDGen.connecter("DbInstallations", "root", "Tigrou007=");
    		return connect;
    	
    	
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
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



