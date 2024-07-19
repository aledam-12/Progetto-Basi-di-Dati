package queries;
	import java.sql.*;

import javax.swing.JOptionPane;

import grafica.OutputPane;

	public class QueryDieci extends ApriConnessione
	{
	    String titolo = "stampa annuale delle scuderie che hanno partecipato al campionato compreso il numero di finanziamenti "; //op 9
	    String SQL = "SELECT scuderia, COUNT(importo) AS numfinanziamenti "+
	                  "FROM pilota JOIN finanziamento ON pilota.vettura = finanziamento.vetturapilota "+
	                  "GROUP BY scuderia";
	    public String toString()
	        {
	            return "| scuderia \t| numfinanziamenti |";
	        }
	    public void execute(Connection conn) {
	        try {
	                if (conn == null)
	                    {
	                        conn = ApriConn();
	                    }
	                else 
	                    {
	                	JOptionPane.showMessageDialog(null,"errore nella conessione");
	                    }
	                OutputPane op = new OutputPane(titolo);
	                Statement query = conn.createStatement();
	                ResultSet conteggio = query.executeQuery(SQL);
	                op.textArea.append(toString()+"\n");
	                while(conteggio.next())
	                    {
	                        String a = conteggio.getString("scuderia");
	                        int b = conteggio.getInt("numfinanziamenti");
	                        op.textArea.append("|" + a + "\t| " + b+"\t       |\n");
	                    }
                    query.close();
                    conteggio.close();
                    conn.close();
	        }
	                catch (Exception e)
	                    {
	                	JOptionPane.showMessageDialog(null,"errore nella connessione");                      
	                    }
	            
	}

}
