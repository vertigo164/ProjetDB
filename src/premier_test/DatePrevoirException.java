package premier_test;

import javax.swing.*;

public class DatePrevoirException extends Exception {

	private int jvErreur;
	private int mvErreur;
	private int avErreur;
	
    public DatePrevoirException(int aV, int mV, int jV) {
    avErreur = aV;
    jvErreur = jV;
    mvErreur = mV;
   
    }
    public String toString() {
        String message = "";
        if (jvErreur < 0 || jvErreur > 31) {
            message =  "Le jour " + jvErreur + " de date valide est invalide";}
        if (mvErreur < 0 || mvErreur > 12) {
            message = "Le mois " + mvErreur + " de date valide est invalide";}
        if (avErreur < 1000 || avErreur > 3000) {
            message = "L'année " + avErreur + " de date valide est invalide";}
        return message;

}
}