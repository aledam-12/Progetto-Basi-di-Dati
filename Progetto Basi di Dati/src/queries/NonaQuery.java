package queries;
import java.sql.*;

import javax.swing.JOptionPane;

import grafica.OutputPane;
	public class NonaQuery extends ApriConnessione {
	    String operazione = "per ciascuna scuderia, stampare la somma totale dei finanziamenti ricevuti"; //op 8
	    String SQL = "SELECT scuderia, SUM(importo) AS importo_totale FROM pilota JOIN finanziamento ON pilota.vettura = finanziamento.vetturapilota GROUP BY scuderia";
	    public String toString()
	        {
	            return "| scuderia\t| importo totale |";
	        }
	    
	    public void execute(Connection conn) {
	       try  {
	                if (conn == null)
	                    {
	                        conn = ApriConn();
	                    }
	                else
	                    {
	                	JOptionPane.showMessageDialog(null,"errore nella conessione"); 
	                    }
	                OutputPane op = new OutputPane(operazione);
	                Statement query = conn.createStatement();
	                ResultSet risultato = query.executeQuery(SQL);
	                op.textArea.append(toString()+"\n");
	                while(risultato.next())
	                    {
	                        String nome = risultato.getString("scuderia");
	                        float fintot = risultato.getFloat("importo_totale");
	                        op.textArea.append("|" + nome + "\t|" + fintot + "€"+"\t |\n");
	                    }
	                query.close();
	                risultato.close();
	                conn.close();
	            }
	    catch (Exception e)
	        {
	    	JOptionPane.showMessageDialog(null,"errore nella connessione");
	        } }
}
