package queries;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class SecondaQuery extends ApriConnessione{
public String titolo = "Registrazione risultato";
String SQL = "UPDATE progettobd.competizione SET motivoritiro = ?, puntipiazzamento = ? WHERE gara = ? AND vettura = ?";
String leggiStringa(String t) {
String s="";	
s =	JOptionPane.showInputDialog(t);
return s;
}
int leggiInt(String s) {
	String f = "";
	f = JOptionPane.showInputDialog(s);
	int x = 0;
		x = Integer.parseInt(f);
return x;
}
public void execute(Connection conn) {
	try {																//qualora la connessione non fosse attiva
		if (conn == null) { conn = ApriConn();
			if (conn== null) 
				JOptionPane.showMessageDialog(null,"errore nella conessione");
		}
String motivoRit=null;
String vettura = leggiStringa("Inserisci il numero di gara della vettura: ");
String gara = leggiStringa("Inserisci la gara: ");
int punteggio = leggiInt("inserisci il punteggio ottenuto: ");
if (punteggio == 0) {motivoRit = leggiStringa("inserisci il motivo del ritiro: ");}
PreparedStatement ins = conn.prepareStatement(SQL);
ins.setString(1,motivoRit);
ins.setInt(2,punteggio);
ins.setString(3,gara);
ins.setString(4,vettura);
int status = ins.executeUpdate(); if (status > 0) JOptionPane.showMessageDialog(null,"risultato registrato correttamente \n"); 
else JOptionPane.showMessageDialog(null,"errore nella registrazione \n");
	}

	
	catch (Exception e) {e.printStackTrace();}
	}
}