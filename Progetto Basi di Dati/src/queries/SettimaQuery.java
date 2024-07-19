package queries;
import java.sql.*;
import javax.swing.JOptionPane;
//inserimento pilota
public class SettimaQuery extends ApriConnessione {
boolean controlloGD (Statement q, String scud) { //seleziona le scuderie e il num di GM
try {String Query = "select vettura.scuderia, count(tipo) from vettura join pilota on numerogara = pilota.vettura AND tipo = 'GM' group by vettura.scuderia";
String Query1 = "select vettura.scuderia, count(*) from vettura join pilota on numerogara = pilota.vettura group by vettura.scuderia";
ResultSet rs = q.executeQuery(Query);			 //seleziona le scuderie che hanno dei piloti e conta quanti ne hanno
int numgd = 0;
int numtot = 0;
String temp="";
while (rs.next()&& !scud.equals(temp)){
	temp = rs.getString(1);
	numgd = rs.getInt(2); //ottengo numero di GM in una scuderia
} 
rs.close();
ResultSet rs1 = q.executeQuery(Query1);
if (scud.equals(temp)) {
	while (rs1.next() && !scud.equals(temp)){ 
		temp = rs1.getString(1);
		if(temp.equals(scud)) {
			numtot = rs1.getInt(2); //la scuderia ha piloti e si vede quanti GM ci sono
		} 
	} 	
	if (numgd < numtot) return true; else return false; }//la scuderia ha tutti GM
else return true;		//scuderia non ha piloti GM oppure non ha piloti
} catch (Exception e) {e.printStackTrace(); return false;} 		//problemi di connessione con SQL
}
String leggiStringa(String t) {
String s="";
s = JOptionPane.showInputDialog(t);	
return s;
}
int leggiInt(String t) {
	String s="";
	int x = 0;
	s = JOptionPane.showInputDialog(t);	
		x = Integer.parseInt(s);
return x;
}
String getScuderia(Statement q, String vettura) {
	String scud="";
	try {
	ResultSet rs2 = q.executeQuery("SELECT scuderia FROM vettura WHERE numerogara = '"+vettura+"'");
    if (rs2.next()) {
        scud = rs2.getString(1);
    }}
	catch (Exception e) {e.printStackTrace();}
	return scud;
}
public void execute(Connection conn) {
	try {																//qualora la connessione non fosse attiva
		if (conn == null) { conn = ApriConn();
			if (conn== null) 
				JOptionPane.showMessageDialog(null,"errore nella conessione");
		}
		Statement check = conn.createStatement();
		int numerolicenze = 1;
		String dataPrimaLicenza=null;
		String nome = (leggiStringa("Inserisci il nome del pilota: "));		
		String cognome = (leggiStringa("Inserisci il cognome del pilota: "));
		String dataNascita = (leggiStringa("inserisci la data di nascita (yyyy-MM-dd): "));
		String vettura = (leggiStringa("Inserisci il numero di gara della vettura: "));
		String categoria = (leggiStringa("Inserisci la categoria del pilota (PRO o AM): "));
		String nazionalità = (leggiStringa("Inserisci la nazione di provenienza: "));
		String tipo="NULL";
		String scuderia = getScuderia(check,vettura);
		if (categoria.equals("AM")) {
			tipo = (leggiStringa("Inserisci GM se il pilota è Gentleman Driver"));
			dataPrimaLicenza = (leggiStringa("Inserisci la data della prima licenza (yyyy-MM-dd)"));
			if (!tipo.equals("GM")) {tipo = "NULL";}
		}
		else {
			numerolicenze = leggiInt("inserisci il numero di licenze: ");
			if (numerolicenze < 1) numerolicenze = 1;
		}
	if (!tipo.equals("GM")||controlloGD(check,scuderia)){	
	String SQL = "INSERT INTO pilota (nome,cognome,datanascita,vettura,tipo,categoria,numerolicenze,dataprimalicenza,nazionalità)"+ 
	"VALUES(?,?,?,?,?,?,?,?,?)";
	PreparedStatement pquery = conn.prepareStatement(SQL);
	int cont = 0;
	pquery.setString(++cont,nome);
	pquery.setString(++cont,cognome);
	pquery.setString(++cont,dataNascita);
	pquery.setString(++cont,vettura);
	pquery.setString(++cont,tipo);
	pquery.setString(++cont,categoria);
	pquery.setInt(++cont,numerolicenze);
	pquery.setString(++cont,dataPrimaLicenza);
	pquery.setString(++cont,nazionalità);
	System.out.println(SQL);
	int status = pquery.executeUpdate(); if (status > 0) {JOptionPane.showMessageDialog(null,"pilota inserito correttamente \n");}
	else {JOptionPane.showMessageDialog(null,"errore nell'inserimento");}
	}
	else {JOptionPane.showMessageDialog(null,"l'equipaggio non può essere formato di soli Gentlemen Driver");}} 	//controllo RV2
catch (Exception e) {e.printStackTrace();}

}
}




