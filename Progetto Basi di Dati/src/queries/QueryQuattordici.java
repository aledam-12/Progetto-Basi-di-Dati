package queries;
import java.sql.*;

import javax.swing.JOptionPane;

import grafica.OutputPane;

public class QueryQuattordici extends ApriConnessione
{
    String titolo = "stampa delle classifiche finali dei punti per tipo di motore";
    String SQL = "SELECT competizione.vettura, SUM(puntipiazzamento) AS totpunti, componente.tipomotore FROM competizione join componente on tipo='motore' AND componente.vettura = competizione.vettura GROUP BY vettura ORDER BY totpunti DESC, tipomotore";
    public String toString()
        {
            return "  pos. | vettura | totpunti | tipo      |";
        }
    public void execute(Connection conn) {
    try{
            if(conn == null)
                {
                    conn = ApriConn();
                }
            else
                {
            	JOptionPane.showMessageDialog(null,"errore nella conessione");
                }
            OutputPane op = new OutputPane(titolo);
            Statement query = conn.createStatement();
            ResultSet pmotore = query.executeQuery(SQL);
            op.textArea.append(toString()+"\n");
            int piazz = 0;
            while(pmotore.next())
                {	
                    String v = pmotore.getString(1);
                    int punti = pmotore.getInt(2);
                    String tipo = pmotore.getString(3);
                    op.textArea.append(++piazz + "°      |" + v + "       |" + punti + "          |" + tipo +"|"+"\n");
                }
            query.close();
            pmotore.close();
            conn.close();
        }
    
    catch(Exception e)
        {
    	JOptionPane.showMessageDialog(null,"errore nella connessione");
        }

}
}
