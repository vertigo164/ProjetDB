package premier_test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import accessBD.AccessBDGen;

import java.util.*;
import java.sql.*;


public class FormuInstall extends JPanel{
	private JLabel idInstallation, dateInstall, typeInstall, commentaires,dureeInstall, refProced, validation, dateValidPrevue, codeSoft, codeOS, respReseau, facultatif;	
	private JTextField zoneIdInstall, zoneDateInstall, zoneComment, zoneDuree,  zoneRef, zoneValidation, zoneValidPrevue;
	
	
	
	private JRadioButton prevoir, cours, terminer;
	private JRadioButton boutonStandard, boutonPerso;
	
	private JButton connexion;
	private JButton boutonTerminer, boutonAnnuler;
	private ButtonGroup groupeBout, groupeBoutInstall;
	private JTextField jour, mois, annee, valJ, valM, valA;
	
	private JComboBox zoneCodeOS, zoneResp, zoneCodeSoft;
	
	private GridBagConstraints gbc;
	
	private int nmbId = 1;
	
	private Fenetre parent;
	
public FormuInstall(Fenetre fen) {
	parent = fen;
	
	this.setBounds(800,500,50,50);
	setLayout(new GridBagLayout());
	gbc = new GridBagConstraints();
	gbc.insets = new Insets (20,10,0,5);
	
	idInstallation = new JLabel ("Id Installation :");
	dateInstall = new JLabel ("Date d'installation :");
	typeInstall = new JLabel ("Type d'installation :");
	commentaires = new JLabel ("Commentaires (*) :");
	dureeInstall = new JLabel ("Dur�e de l'installation :");
	refProced = new JLabel ("Reference de la proc�dure (*):");
	validation = new JLabel ("Validation :");
	dateValidPrevue = new JLabel ("Date de validation :");
	codeSoft = new JLabel ("Code Software :");
	codeOS = new JLabel ("Code OS :");
	respReseau = new JLabel ("Responsable r�seau :");
	facultatif = new JLabel(" * =  Facultatif");
	
	
	zoneIdInstall = new JTextField(10);
	zoneIdInstall.setText(Integer.toString(nmbIdInstall()));
	zoneDateInstall = new JTextField(10);
	zoneComment = new JTextField(30);
	zoneDuree = new JTextField(10);
	zoneRef = new JTextField(10);
	zoneValidation = new JTextField(10);
	zoneValidPrevue = new JTextField(10);
	
	jour = new JTextField("Jour", 4);
    mois = new JTextField("Mois", 4);
    annee = new JTextField("Ann�e", 4);
	
    valJ = new JTextField("Jour", 4);
    valM = new JTextField("Mois", 4);
    valA = new JTextField("Ann�e", 4);
    
	zoneResp = new JComboBox();
	zoneCodeOS = new JComboBox();
	zoneCodeSoft = new JComboBox();
	
	prevoir = new JRadioButton("A pr�voir", false);
	prevoir.setActionCommand("A pr�voir");
	cours = new JRadioButton("En cours", false);
	cours.setActionCommand("En cours");
	terminer = new JRadioButton("Termin�e", true);	
	terminer.setActionCommand("Termin�e");
	
	boutonStandard = new JRadioButton("Standard", true);
	boutonPerso = new JRadioButton("Personnalis�");
	
	boutonTerminer = new JButton("Confirmer");
	
	GestionBouton ter = new GestionBouton();
    boutonTerminer.addActionListener(ter);
    boutonAnnuler = new JButton("Annuler");
    boutonAnnuler.addActionListener(ter);
    
	groupeBout = new ButtonGroup();
	groupeBout.add(prevoir);
	groupeBout.add(cours);
	groupeBout.add(terminer);
	
	prevoirBouton pb = new prevoirBouton();
	prevoir.addItemListener(pb);
	cours.addItemListener(pb);
	prevoir.addItemListener(pb);
	
	groupeBoutInstall = new ButtonGroup();
	groupeBoutInstall.add(boutonStandard);
	groupeBoutInstall.add(boutonPerso);
	
	gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    gbc.gridx = 0;
    gbc.gridy = 0;
	add(idInstallation, gbc);
	
	zoneIdInstall.setEditable(false);
	gbc.anchor = GridBagConstraints.EAST;
	gbc.gridx = 1;
	gbc.gridy = 0;
	gbc.gridwidth = 2;
	add(zoneIdInstall, gbc);
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 1;
	add(dateInstall, gbc);
	
	gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.LINE_END;
    add(mois, gbc);

    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.LINE_START;
    add(jour, gbc);

    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.LINE_START;
    add(annee, gbc);
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 2;
	add(typeInstall, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 2;
	add(boutonStandard, gbc);
	

	gbc.gridx = 3;
	gbc.gridy = 2;
	add(boutonPerso, gbc);
	
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 3;
	add(commentaires, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 3;
	add(zoneComment, gbc);
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 4;
	add(dureeInstall, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 4;
	add(zoneDuree, gbc);
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 5;
	add(refProced, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 5;
	add(zoneRef, gbc);
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 6;
	add(validation, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 6;
	add(prevoir, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 6;
	gbc.anchor = GridBagConstraints.CENTER;
	add(cours, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 6;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(terminer, gbc);
	
	dateValidPrevue.setVisible(false);
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 7;
	add(dateValidPrevue, gbc);
	
	valM.setVisible(false);
	gbc.gridx = 1;
    gbc.gridy = 7;
    gbc.anchor = GridBagConstraints.LINE_END;
    add(valM, gbc);

    valJ.setVisible(false);
    gbc.gridx = 2;
    gbc.gridy = 7;
    gbc.anchor = GridBagConstraints.LINE_START;
    add(valJ, gbc);

    valA.setVisible(false);
    gbc.gridx = 3;
    gbc.gridy = 7;
    gbc.anchor = GridBagConstraints.LINE_START;
    add(valA, gbc);
	
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 8;
	add(codeSoft, gbc);
	
	zoneCodeSoft.setPreferredSize(new Dimension(150, 20));
	zoneCodeSoft.addItem("Choix Soft");
	gbc.gridx = 2;
	gbc.gridy = 8;
	add(zoneCodeSoft, gbc);
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 9;
	add(codeOS, gbc);
	
	zoneCodeOS.setPreferredSize(new Dimension(150, 20));
	zoneCodeOS.addItem("Choix OS");
	gbc.gridx = 2;
	gbc.gridy = 9;
	add(zoneCodeOS, gbc);
	
	
	gbc.anchor = GridBagConstraints.WEST;
	gbc.gridx = 0;
	gbc.gridy = 10;
	add(respReseau, gbc);

	zoneResp.setPreferredSize(new Dimension(150, 20));
	zoneResp.addItem("Choix resp");
	gbc.gridx = 2;
	gbc.gridy = 10;
	add(zoneResp, gbc);
	
	gbc.gridx = 2;
	gbc.gridy = 11;
	add(boutonTerminer, gbc);
	
	gbc.gridx = 3;
	gbc.gridy = 11;
	add(boutonAnnuler, gbc);
	
	gbc.gridx = 0;
	gbc.gridy = 14;
	add(facultatif, gbc);
	
	gesClicEffac ge = new gesClicEffac();
	jour.addMouseListener(ge);
	mois.addMouseListener(ge);
	annee.addMouseListener(ge);
	valJ.addMouseListener(ge);
	valM.addMouseListener(ge);
	valA.addMouseListener(ge);
	
	
	try {
		Connection connection = parent.connection();
		ResultSet comboOS = connection.createStatement().executeQuery("select Libelle from OS");
		ResultSet comboSoft  = connection.createStatement().executeQuery("select Nom from Software");
		ResultSet comboResp = connection.createStatement().executeQuery("select NomPrenom from ResponsableReseaux");
			while (comboOS.next()) {
	           zoneCodeOS.addItem(comboOS.getString("Libelle"));}
			while (comboResp.next()) {
	           zoneResp.addItem(comboResp.getString("NomPrenom"));}
			while (comboSoft.next()) {
	           zoneCodeSoft.addItem(comboSoft.getString("Nom"));} 
	   
		connection.close();
	} 
	
	
	catch (SQLException e) {
		System.out.print("Impossible de se connecter � la base");
		e.getMessage();
	}
	
}


private boolean dateValide (JTextField jour, JTextField mois, JTextField annee) throws DateException{
    boolean verif = false;

    try {
        int j = Integer.parseInt(jour.getText());
        int m = Integer.parseInt(mois.getText());
        int a = Integer.parseInt(annee.getText());


        GregorianCalendar newDate = new GregorianCalendar(j, m-1, a);
        if (j < 0 || j > 31 || m < 0 || m > 12 || a < 1000 || a > 3000) {
            throw new DateException (j, m, a);
        }
        verif = true;


    }catch(java.lang.NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Date invalide", "Erreur", JOptionPane.ERROR_MESSAGE);

    }
    return verif;

}

private java.sql.Date convertisseurDate(JTextField j, JTextField m, JTextField a) {

	int jour = Integer.parseInt(j.getText());
	int mois = Integer.parseInt(m.getText());
	int annee = Integer.parseInt(a.getText());

	
	GregorianCalendar newDate = new GregorianCalendar(jour, mois-1, annee);
	java.sql.Date sqlD = new java.sql.Date(newDate.getTimeInMillis());
	

	return sqlD;

}

private boolean dureeValide() {
	boolean dureeOK = false;
	try {
		Integer.parseInt(zoneDuree.getText());
		dureeOK = true;
	}
	catch(NumberFormatException e){
		JOptionPane.showMessageDialog(null, "La dur�e n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	}
	return dureeOK;
}


private class gesClicEffac implements MouseListener, MouseMotionListener {
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jour) {
		jour.setText("");}
	
		else if (e.getSource() == mois) {
		mois.setText("");}
		
		else if (e.getSource() == annee) {
		annee.setText("");}	
	
		else if (e.getSource() == valJ){
		valJ.setText("");}
	
		else if (e.getSource() == valM) {
		valM.setText("");}
		
		else {
		valA.setText("");}
	}
	
	public void mousePressed( MouseEvent e) 
	{}
	public void mouseReleased( MouseEvent e)
	{}
	public void mouseExited( MouseEvent e)
	{}
	public void mouseEntered( MouseEvent e)
	{}
	public void mouseDragged( MouseEvent e)
	{}
	public void mouseMoved( MouseEvent e)
	{}	
}

private class prevoirBouton implements ItemListener {
	public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == prevoir && e.getStateChange() == ItemEvent.SELECTED) {
				dateValidPrevue.setVisible(true);
				valM.setVisible(true);
				valJ.setVisible(true);
				valA.setVisible(true);}
			else
			if (e.getSource() == terminer && e.getStateChange() == ItemEvent.SELECTED || e.getSource() == cours && e.getStateChange()  == ItemEvent.SELECTED) {
				dateValidPrevue.setVisible(false);
				valM.setVisible(false);
				valJ.setVisible(false);
				valA.setVisible(false);}
	}
}

private class GestionBouton implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boutonAnnuler) {
			int annul = JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment annuler?", "Annulation", JOptionPane.YES_NO_OPTION);
			if(annul == JOptionPane.OK_OPTION) {
				parent.getContInstall().removeAll();
		    	parent.getContInstall().add(new FormuInstall(parent));
		    	parent.getContInstall().repaint();
		    	parent.getContInstall().revalidate();
			}
		}else if(e.getSource() == boutonTerminer) {
		try {
			if (dateValide(jour, mois, annee)&& dureeValide() && comboValide()) {
				if(prevoir.isSelected()) {
					if(dateValide(valJ, valM, valA)) {
						int select = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment terminer", "Attention", JOptionPane.YES_NO_OPTION);
						if(select == JOptionPane.OK_OPTION) {
							envoisDonnees();
							JOptionPane.showMessageDialog(null, "Confirmation de l'envoi", "Information", JOptionPane.INFORMATION_MESSAGE);
						}	
					}
				}
				if(cours.isSelected() || terminer.isSelected()) {
					int select = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment terminer", "Attention", JOptionPane.YES_NO_OPTION);
					if(select == JOptionPane.OK_OPTION) {
						envoisDonnees();
						JOptionPane.showMessageDialog(null, "Confirmation de l'envoi", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}			
			}	
		}
		
	
			catch (DateException d) {
	        JOptionPane.showConfirmDialog(null, d, "Attention", JOptionPane.ERROR_MESSAGE);
	        d.getMessage();
			}		
		}
	}
}
private void envoisDonnees(){
	try {
		Connection connection = parent.connection();
	
		String instructionSQL = "insert into Installation (IdInstallation, DateInstallation, TypeInstallation,  Commentaires, DureeInstallation, RefProcedureInstallation, Validation, DateValidation, CodeSoftware, Matricule, CodeOS ) values (?,?,?,?,?,?,?,?,?,?,?)";
	
		String bouton = groupeBout.getSelection().getActionCommand(); 
		
		PreparedStatement prepStat = connection.prepareStatement(instructionSQL);
		if(bouton == "A pr�voir") {
			prepStat.setInt(1, nmbIdInstall());
			prepStat.setDate(2, convertisseurDate(annee, mois, jour));
			if(boutonStandard.isSelected()) {
				prepStat.setBoolean(3, true);
			}else {
				prepStat.setBoolean(3,false);
			}
			if(zoneComment.getText().isEmpty()) {
				prepStat.setNull(4, Types.VARCHAR);}
			else {
				prepStat.setString(4, zoneComment.getText());}
		
			prepStat.setInt(5, Integer.parseInt(zoneDuree.getText()));
	    
			if(zoneRef.getText().isEmpty()) {
				prepStat.setNull(6, Types.VARCHAR);}
			else {
				prepStat.setString(6,zoneRef.getText());}
			

			prepStat.setString(7, bouton);
			prepStat.setDate(8, convertisseurDate(valA, valM, valJ));
	
			prepStat.setString(9, recupCodeSoftware());
			prepStat.setString(10, recupResponsable());
			prepStat.setString(11, recupCodeOS());
			nmbId++;}
		
		else {
			prepStat.setInt(1, nmbIdInstall());
			prepStat.setDate(2, convertisseurDate(annee, mois, jour));
			if(boutonStandard.isSelected()) {
				prepStat.setBoolean(3, true);
			}else {
				prepStat.setBoolean(3,false);
			}
			if(zoneComment.getText().isEmpty()) {
				prepStat.setNull(4, Types.VARCHAR);}
			else {
				prepStat.setString(4, zoneComment.getText());}
		
			prepStat.setInt(5, Integer.parseInt(zoneDuree.getText()));
	    
			if(zoneRef.getText().isEmpty()) {
				prepStat.setNull(6, Types.VARCHAR);}
			else {
				prepStat.setString(6,zoneRef.getText());}	

			prepStat.setString(7, bouton);
			prepStat.setNull(8, Types.DATE);
	
			prepStat.setString(9, recupCodeSoftware());
			prepStat.setString(10, recupResponsable());
			prepStat.setString(11, recupCodeOS());
			nmbId++;}
	
	    if(prepStat.executeUpdate() > 0) {
	    	
	    	parent.getContInstall().removeAll();
	    	parent.getContInstall().add(new FormuInstall(parent));
	    	parent.getContInstall().repaint();
	    	parent.getContInstall().revalidate();
	    }
	    connection.close();  
	}catch(SQLException e) {
		System.out.print("Impossible de se connecter � la base");
		e.getMessage();
	}

}


private String recupCodeSoftware() {
    Connection connection = parent.connection();
    String res = "";
    try {
        String getComboString = String.valueOf(zoneCodeSoft.getSelectedItem());
        String requete = "select CodeSoftware from software where Nom = ?";
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


private String recupResponsable() {
	Connection connection = parent.connection();
	String res = null;
	try {
        String getComboString = String.valueOf(zoneResp.getSelectedItem());
        String requete = "select Matricule from responsablereseaux where NomPrenom = ?";
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

private String recupCodeOS() {
	Connection connection = parent.connection();
	String res = "";
	try {
        String getComboString = String.valueOf(zoneCodeOS.getSelectedItem());
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
	
	if(zoneCodeOS.getSelectedItem ()== "Choix OS") {
        JOptionPane.showMessageDialog(null, "Veuillez S�l�ctionner un OS", "Erreur", JOptionPane.ERROR_MESSAGE);
        comboVal = false;}
	
	if(zoneResp.getSelectedItem ()== "Choix resp") {
        JOptionPane.showMessageDialog(null, "Veuillez S�l�ctionner un responsable", "Erreur", JOptionPane.ERROR_MESSAGE);
        comboVal = false;}
	
	if(zoneCodeSoft.getSelectedItem ()== "Choix Soft") {
        JOptionPane.showMessageDialog(null, "Veuillez S�l�ctionner un software", "Erreur", JOptionPane.ERROR_MESSAGE);
        comboVal = false;}
	
	return comboVal;
}


public int nmbIdInstall() {
		Connection connection = parent.connection();
		
		try {
		ResultSet resId = connection.createStatement().executeQuery("select max(IdInstallation) from Installation");
		while(resId.next()) {
			String str = resId.getString(1);
			int nmbId = Integer.parseInt(str);
			return nmbId += 1;
			}
		connection.close();
		
		}
		
		catch (SQLException e) {
			e.getMessage();
		}
		return nmbId;
	}	

}
//fin de la classe
