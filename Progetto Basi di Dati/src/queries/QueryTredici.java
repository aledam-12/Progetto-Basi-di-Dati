package queries;
	import java.sql.*;

import javax.swing.JOptionPane;

import grafica.OutputPane;

	public class QueryTredici extends ApriConnessione
	{
	    String titolo = "stampa della classifica finale dei punti conseguiti da tutte le vetture";
	    String SQL = "SELECT vettura, SUM(puntipiazzamento) AS totpunti FROM competizione GROUP BY vettura ORDER BY totpunti DESC";
	    public String toString()
	        {
	            return "pos.\t | numero di gara | punteggio totale | \n";
	        }
	    public void execute(Connection conn) {
	     try{
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
	            ResultSet classifica = query.executeQuery(SQL);
	            op.textArea.append(toString());
	            int piazz = 0;
	            while(classifica.next())
	                {
	                    int numerogara = classifica.getInt("vettura");
	                    int punti = classifica.getInt("totpunti");
	                    op.textArea.append(++piazz + "° \t |" + numerogara + " \t    | " + punti +"\t         |\n");
	                }
	            query.close();
	            classifica.close();
	            conn.close();
	        }
	     	catch(Exception e)
	        {
	     		JOptionPane.showMessageDialog(null,"errore nella connessione");
	        }
	}

}
