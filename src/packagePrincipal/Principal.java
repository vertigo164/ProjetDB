package packagePrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import accessBD.AccessBDGen;

public class Principal {

	public static void main(String[]args) {
		try {
			//Etablir la connexion("le cable qui relie le programme java a la BD")
			Connection connection = AccessBDGen.connecter("DbInstallations", "root", "Tigrou007=");
			
			//creer instruction SQL
			String sqlInstruction = "insert into FamilleSoftware (IdFamSoft, Libelle) values (?,?)";
			
			//Créer le PreparedStatement à partir de cette instruction SQL("Chariot sur le cable")
			PreparedStatement  myPrepStat  = connection.prepareStatement(sqlInstruction); 
			
			// remplacer  les  ?  par  valeurs  introduites  par  user  (pour  éviter  les injections SQL) 
			myPrepStat.setInt(1,202); 
			myPrepStat.setString(2,"Mafamille Software ");
			
			// Exécuter ("envoyer le chariot à la BD et demander d'exécuter l'instruction") 
			// Récupérer le nombre de lignes modifiées et l'afficher
			
			int  nbUpdatedLines = myPrepStat.executeUpdate();
			System.out.println("Nombre de lignes modifiées: " + nbUpdatedLines); 
		}
		catch (SQLException e) {
			System.out.println(e.getMessage()); 
		}
	}
}
