package queries;
import java.sql.*;

import grafica.OutputPane;
import javax.swing.JOptionPane;


public class QueryQuindici extends ApriConnessione
{
    String titolo = "stampa una classifica sulla base del rapporto punti/minuti di gara di ciascuna scuderia";
    String SQL = "SELECT tab1.scuderia, AVG(tab1.pti) AS \"pti/min\"\r\n"
    		+ "FROM (\r\n"
    		+ "    SELECT vettura.scuderia, competizione.vettura, SUM(competizione.puntipiazzamento) / (SUM(gara.durata) * 60) AS pti\r\n"
    		+ "    FROM competizione\r\n"
    		+ "    JOIN gara ON competizione.gara = gara.nomegara\r\n"
    		+ "    JOIN vettura ON vettura.numerogara = competizione.vettura\r\n"
    		+ "    GROUP BY vettura.scuderia, competizione.vettura\r\n"
    		+ ") AS tab1\r\n"
    		+ "GROUP BY tab1.scuderia\r\n"
    		+ "ORDER BY \"pti/min\"";
    public String toString()
        {
            return "piaz. | nome \t      | pti/min |";
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
            ResultSet rapporto = query.executeQuery(SQL);
            op.textArea.append(toString()+"\n");
            int cont = 0;
            while(rapporto.next())
                {
                    String n = rapporto.getString(1);
                    Float c = rapporto.getFloat(2);
                    op.textArea.append(++cont +"°     |" + n + "  |" + c + "|"+"\n");
                }    
            query.close();
            rapporto.close();
            conn.close();
        }
    catch(Exception e)
        {
    	JOptionPane.showMessageDialog(null,"errore nella connessione");
        }
}
}
