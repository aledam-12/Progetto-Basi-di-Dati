package queries;
import grafica.OutputPane;
import javax.swing.*;
import java.sql.*;
class ApriConnessione {
	 static Connection ApriConn() {
		try { Connection conn;
			// Carica il driver JDBC di MySQL dal percorso specificato
			Class.forName("com.mysql.cj.jdbc.Driver");
			    String url = "jdbc:mysql://localhost:3306/progettobd";
			    String username = "root"; String pwd = "pwd";
			    conn = DriverManager.getConnection(url, username, pwd);
			    return conn;
			}
			catch (Exception e) {
			e.printStackTrace();
			return null;
			}
	}
}
public class PrimaQuery extends ApriConnessione {		//op.12
public String titolo = "OP. 12 Stampa Costruttori";
public String SQL = "SELECT * FROM costruttore";
public String toString() {														//serve per le intestazioni 
	return "| nome\t| n.componenti | ragionesociale      | via\t| cap   \t| città|";
}
public void execute(Connection conn) {
	try {																//qualora la connessione non fosse attiva
		if (conn == null) { conn = ApriConn();
			if (conn== null) 
				JOptionPane.showMessageDialog(null,"errore nella conessione");
		}
		OutputPane op = new OutputPane(titolo);
		Statement pquery = conn.createStatement();
		ResultSet result = pquery.executeQuery(SQL);
		op.textArea.append(toString()+"\n");
		while (result.next()) {
			String nome = result.getString("nome");
			int numerocomponenti = result.getInt("numerocomponenti");
			String ragionesociale = result.getString("ragionesociale");
			String via = result.getString("via");
			String cap = result.getString("cap");
			String città = result.getString("città");
		op.textArea.append(nome+"\t| "+numerocomponenti+"\t| "+ragionesociale+"    | "+via+"\t| "+cap+"\t| "+città +"|"+"\n");
		}
		pquery.close();
		result.close();
		conn.close();
}
catch (Exception e) {JOptionPane.showMessageDialog(null,"errore nella connessione");}
}
}
