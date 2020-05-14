package premier_test;

import javax.swing.*;

public class DateException extends Exception {


	private int jErreur;
	private int mErreur;
	private int aErreur;
	
    public DateException(int j, int m, int a) {
    jErreur = j;
    mErreur = m;
    aErreur = a;

    }
    public String toString() {
        String message = "";
        if (jErreur < 0 || jErreur > 31) {
            message =  "Le jour " + jErreur + " est invalide";}
        if (mErreur < 0 || mErreur > 12) {
            message = "Le mois " + mErreur + " est invalide";}
        if (aErreur < 1000 || aErreur > 3000) {
            message = "L'année " + aErreur + " est invalide";}
        
        return message;

}
}