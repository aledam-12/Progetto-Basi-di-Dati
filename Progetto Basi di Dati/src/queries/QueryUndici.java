package queries;
import java.sql.*;

import javax.swing.JOptionPane;

import grafica.OutputPane;
	public class QueryUndici extends ApriConnessione
	{
	    String titoloQuery = "Visualizzare i piloti che hanno vinto nel circuito di casa"; //op 10
	    String SQL = "SELECT distinct pilota.vettura as vett, nome, datanascita, cognome, tipo, numerolicenze, dataprimalicenza, nazionalità, categoria,gara from\r\n"
	    		+ "pilota join vettura on vettura.numerogara = pilota.vettura join competizione on competizione.vettura = vettura.numerogara join (\r\n"
	    		+ "select paese,nomegara from circuito join gara on gara.circuito = nome join competizione on competizione.gara = gara.nomegara where puntipiazzamento = 15) as tab1 on tab1.nomegara = nomegara \r\n"
	    		+ "where puntipiazzamento = 15 and pilota.nazionalità = tab1.paese";
	    public String toString()   
	        {
	            return "| vettura | nome \t| datanascita | cognome \t| tipo \t| numerolicenze | dataprimalicenza | nazionalità | categoria |";
	        }
	    public void execute(Connection conn)
	        {try {
	            if (conn == null)
	                {
	                    conn = ApriConn();
	                }            
	            else    
	                {
	            	JOptionPane.showMessageDialog(null,"errore nella conessione");
	                }
	            OutputPane op = new OutputPane(titoloQuery);
	            Statement query = conn.createStatement();
	            ResultSet risultato = query.executeQuery(SQL);
	            op.textArea.append(toString()+"\n");
	            while(risultato.next())
	                {
	                    String auto = risultato.getString("vett");
	                    String n = risultato.getString("nome");
	                    Date d = risultato.getDate("datanascita");
	                    String cog = risultato.getString("cognome");
	                    String T = risultato.getString("tipo");
	                    int nl = risultato.getInt("numerolicenze");
	                    Date dat = risultato.getDate("dataprimalicenza");
	                    String naz = risultato.getString("nazionalità");
	                    String cat = risultato.getString("categoria");
	               op.textArea.append("|" + auto + "       |    " + n + "\t|  "+ d + "|\t" + cog + "\t| " + T + "\t"+
	            		   "|" + nl + "\t  |" + dat + "\t         |" + naz + "\t     |" + cat + "           |"+"\n");
	                   
	                }
	            query.close();
                risultato.close();
                conn.close();     
	        }
	    catch(Exception e)
	        {
	    	JOptionPane.showMessageDialog(null,"errore nella connessione");
	        }
	}

}
