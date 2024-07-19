package queries;
import java.sql.*;

import javax.swing.JOptionPane;

//op.4     //registrazione di una vettura a una gara 
public class OttavaQuery extends ApriConnessione {
//Si assume che la gara e la vettura siano già esistenti
String leggiStringa(String t) {
String s = JOptionPane.showInputDialog(t);
return s;
}	//metodi per l'input
int leggiInt(String s) {
String f = JOptionPane.showInputDialog(s);
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
	String nomeGara = "";
	String vettura="";
	nomeGara= (leggiStringa("inserisci il nome della gara"));
	vettura=(leggiStringa("inserisci il numero di gara della vettura partecipante"));
	String SQL="INSERT INTO competizione(gara,vettura,puntipiazzamento,motivoritiro) values(?,?,0,null)";
	PreparedStatement pquery = conn.prepareStatement(SQL);
	pquery.setString(1,nomeGara);
	pquery.setString(2,vettura);
	int status = pquery.executeUpdate();
		if(status>0) {JOptionPane.showMessageDialog(null,"vettura iscritta correttamente \n");}
			else {JOptionPane.showMessageDialog(null,"errore nell'iscrizione \n");}
	}
catch(Exception e) {
	JOptionPane.showMessageDialog(null,"vettura o gara non presenti oppure vettura già iscritta");}
	}
}