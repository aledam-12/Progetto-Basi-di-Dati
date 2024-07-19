package queries;
import java.sql.*;
import javax.swing.JOptionPane;
public class QuartaQuery extends ApriConnessione {
String titolo ="Inserisci Finanziamento";
String SQL;
String leggiStringa(String t) {
String s="";	
s = JOptionPane.showInputDialog(t);
return s;
}
float leggiFloat(String s) {
	String f = "";
	f = JOptionPane.showInputDialog(s);
	float x = 0.0f;
		x = Float.parseFloat(f);
return x;
}
public void execute(Connection conn) {
	String codiceFinanziamento, nome, cognome, vettura, dataFinanziamento, dataNascita;
	float importo;
nome = leggiStringa("inserisci il nome del pilota: ");
cognome = leggiStringa("inserisci il cognome del pilota: ");
dataNascita = leggiStringa("inserisci la data di nascita del pilota(yyyy-MM-dd): ");
vettura = leggiStringa("inserisci il numero di gara della vettura del pilota: ");		//le date vengono trattate come stringhe 
dataFinanziamento = leggiStringa("inserisci la data del finanziamento(yyyy-MM-dd): ");		//per un maggiore controllo	
importo = leggiFloat("inserisci importo: ");
codiceFinanziamento = leggiStringa("inserisci codice: ");
try {																//qualora la connessione non fosse attiva
	if (conn == null) { conn = ApriConn();
		if (conn== null) 			
			JOptionPane.showMessageDialog(null,"errore nella conessione");
}
	PreparedStatement query1 = conn.prepareStatement("select scuderia from pilota where vettura = "+vettura);
	ResultSet rs1 = query1.executeQuery();
	rs1.next();
	String scuderia = rs1.getString(1); //aggiorna il valore dell'attributo scuderia qualora fosse il primo finanziamento
	if (scuderia == null) {
		String q2 = "select scuderia from vettura where numerogara = "+vettura;
		rs1 = query1.executeQuery(q2);
		rs1.next();
		scuderia = rs1.getString(1);
		String updt = "UPDATE `progettobd`.`pilota` SET `scuderia` = ? WHERE nome = ? AND cognome = ? AND datanascita = ? AND vettura = ?";
		query1 = conn.prepareStatement(updt);
		query1.setString(1, scuderia);
		query1.setString(2, nome);
		query1.setString(3, cognome);
		query1.setString(4, dataNascita);
		query1.setString(5, vettura);
		query1.executeUpdate();
	}
	rs1.close();
SQL = "INSERT INTO finanziamento (nomepilota,cognomepilota,datanascitapilota,"+ 
			"vetturapilota,dataFinanziamento,importo,codicefinanziamento"+")VALUES (?,?,?,?,?,?,?)";
PreparedStatement pquery = conn.prepareStatement(SQL);
pquery.setString(1, nome);
pquery.setString(2, cognome);
pquery.setString(3, dataNascita);
pquery.setString(4, vettura);
pquery.setString(5, dataFinanziamento);
pquery.setFloat(6, importo);
pquery.setString(7, codiceFinanziamento);
int status = pquery.executeUpdate();
if(status > 0) {JOptionPane.showMessageDialog(null,"Finanziamento inserito correttamente");}
else JOptionPane.showMessageDialog(null,"errore nell'inserimento");
}
catch (Exception e) {e.printStackTrace();}	
}
}
