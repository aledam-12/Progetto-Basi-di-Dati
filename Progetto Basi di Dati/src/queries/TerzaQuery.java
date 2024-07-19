package queries;
import java.sql.*;

import javax.swing.JOptionPane;
public class TerzaQuery extends ApriConnessione{
String titolo = "Inserisci Scuderie";
String SQL = "";
String nome;	String paese;	/*attributi della tabella scuderia */
public String getNome () {
String s = "";
s = JOptionPane.showInputDialog("Inserisci il nome della scuderia");
return s;
}
public String getPaese() {
String	s = "";
s = JOptionPane.showInputDialog("Inserisci la nazione della scuderia");
return s;
}
public void execute(Connection conn) {
	try {					//qualora la connessione non fosse attiva
		if (conn == null) { conn = ApriConn();
			if (conn== null) 
				JOptionPane.showMessageDialog(null,"errore nella conessione");
		}
String a = getNome();
String b = getPaese();
SQL = "INSERT INTO scuderia (nome, paesesede) VALUES (?,?)";
PreparedStatement pquery = conn.prepareStatement(SQL);
pquery.setString(1,a);
pquery.setString(2,b);
int status = pquery.executeUpdate();
if (status > 0) {JOptionPane.showMessageDialog(null,"colonna inserita correttamente \n");}
else {JOptionPane.showMessageDialog(null,"errore nell'inserimento \n");}
conn.close();
pquery.close();
	}
catch (Exception e) {e.printStackTrace();}
}
}