package queries;
import java.sql.*;

import javax.swing.JOptionPane;

public class SestaQuery extends QuintaQuery{
public void execute(Connection conn) { try {		
if (conn == null) { conn = ApriConn();
if (conn== null) 
	JOptionPane.showMessageDialog(null,"errore nella conessione");
}
String numeroGara = leggiStringa("inserisci il numero di gara");
boolean res = inserisciComponenti(conn, numeroGara);
if (res) JOptionPane.showMessageDialog(null,"componenete registrato");
else JOptionPane.showMessageDialog(null,"errore nell'inserimento del componenete"); 
}
catch (Exception e) {e.printStackTrace();}
}
}
