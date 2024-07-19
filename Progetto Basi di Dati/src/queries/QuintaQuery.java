package queries;
import java.sql.*;

import javax.swing.JOptionPane;

//inserimento vettura e controllo dei componenti 
public class QuintaQuery extends ApriConnessione {
String SQL;
String numeroGara;
String leggiStringa(String t) {
String s="";	
s = JOptionPane.showInputDialog(t);
return s;
}
int leggiInt(String s) {
	String f = "";
	f = JOptionPane.showInputDialog(s);
	int x = 0;
		x = Integer.parseInt(f);
return x;
}
float leggiFloat(String s) {
	String f = "";
	f = JOptionPane.showInputDialog(s);
	float x = 0.0f;
	x = Float.parseFloat(f);
return x;
}
boolean inserisciVettura(Connection a) {
String scuderia = leggiStringa ("inserisci la scuderia : ");
String modello = leggiStringa ("inserisci il modello: ");
numeroGara = leggiStringa ("inserisci il numero di gara: ");
String result = "INSERT INTO vettura(scuderia,numerogara,modello) VALUES (?,?,?)";
try {
PreparedStatement ps = a.prepareStatement(result);
ps.setString(1,scuderia);
ps.setString(2,numeroGara);
ps.setString(3,modello);
int status = ps.executeUpdate();
if (status > 0) return true; else return false;
}
catch (Exception e){e.printStackTrace();
return false;}
}
void aggiornaNumComponenti(Connection a, String costruttore) {
	try {
	PreparedStatement num = a.prepareStatement("select numerocomponenti from costruttore where ragionesociale = ?");
	num.setString(1,costruttore);
	ResultSet rs1 = num.executeQuery();
	rs1.next();
	int numcomp = rs1.getInt(1);
	JOptionPane.showMessageDialog(null,"Il costruttore "+costruttore+" ha fornito "+numcomp+"componenti \n");
	JOptionPane.showMessageDialog(null,"Aggiornamento numero di componenti");
	String updt = ("UPDATE costruttore SET numerocomponenti = ? WHERE ragionesociale = ?");
	PreparedStatement ps3 = a.prepareStatement(updt);
	int comp = leggiInt("Inserisci il numero di componenti aggiornato ");
	ps3.setString(2,costruttore);
	ps3.setInt(1,comp);
	int status = ps3.executeUpdate();
	if (status > 0) {JOptionPane.showMessageDialog(null,"Aggiornamento completato \n");} 
	else {JOptionPane.showMessageDialog(null,"Errore nell'aggiornamento \n");}
	}
	catch (Exception e) {e.printStackTrace();}
}
boolean inserisciComponenti (Connection a, String numgara) {
String tipo="",tipomotore="",materiale="",codice="",vettura="",costruttore="";
float costospecifico=0.0f; int peso=0,numerocilindri=0,cilindrata=0,numeromarce=0;
tipo = leggiStringa("inserisci il tipo di componente: ");
codice = leggiStringa("inserisci codice del componente: ");
costruttore = leggiStringa("inserisci la ragione sociale del costruttore: ");
costospecifico = leggiFloat("inserisci prezzo del componente: ");
vettura = numgara;
if (tipo.equals("motore")) {
tipomotore = leggiStringa("inserisci il tipo di motore: ");
cilindrata = leggiInt("inserisci la cilindrata: ");
numerocilindri = leggiInt("inserisci numero di cilindri: ");
}
else if (tipo.equals("cambio")) {
	numeromarce = leggiInt ("inserisci numero delle marce: ");
}
else {
	materiale = leggiStringa ("inserisci il materiale: ");
	peso = leggiInt("inserisci il peso: ");
}
String result = "INSERT INTO componente (tipo, costospecifico, peso, numerocilindri, tipomotore, materiale,"+
"codice, vettura, cilindrata, numeromarce, costruttore)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
try {
PreparedStatement ps2 = a.prepareStatement(result);
ps2.setString(1, tipo);
ps2.setFloat(2,costospecifico);
ps2.setInt(3, peso);
ps2.setInt(4,numerocilindri);
ps2.setString(5,tipomotore);
ps2.setString(6,materiale);
ps2.setString(7,codice);
ps2.setString(8,vettura);
ps2.setInt(9,cilindrata);
ps2.setInt(10,numeromarce);
ps2.setString(11,costruttore);
int status = ps2.executeUpdate();
aggiornaNumComponenti(a,costruttore);
if (status > 0) return true; else return false;
}
catch (Exception e) {e.printStackTrace(); return false;}
}
public void execute(Connection conn) {
	try {																//qualora la connessione non fosse attiva
		if (conn == null) { conn = ApriConn();
			if (conn== null) 
				JOptionPane.showMessageDialog(null,"errore nella conessione");
		}
	boolean vettStatus = inserisciVettura(conn);
	if (vettStatus){
		JOptionPane.showMessageDialog(null,"Vettura registrata correttamente \n\n Registrazione componenti della vettura"); 
		int num = 0;
		do { 
			num = leggiInt("inserisci quanti componenti registrare (max 3): ");
		}
			while (num < 0 && num > 4);
		for (int i=0;i<num;i++) {boolean CompStatus = inserisciComponenti(conn, numeroGara);
		if (CompStatus) JOptionPane.showMessageDialog(null,"componente inserito correttamente \n");
		else JOptionPane.showMessageDialog(null,"errore nell'inserimento del componente \n");
	} }
	else JOptionPane.showMessageDialog(null,"errore nell'inserimento della vettura \n");
	}
	catch(Exception e) {e.printStackTrace();}
}
}