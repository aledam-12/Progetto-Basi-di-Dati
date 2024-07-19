package queries;
import grafica.OutputPane;
import java.sql.*;

import javax.swing.JOptionPane;

public class QueryDodici extends ApriConnessione
{	
    String titolo = "per ciascuna scuderia, visualizzare la percentiale do gentleman driver di cui si compone l'equipaggio"; //op 11
    String SQL = "select scuderia, numGM/numTot*100 as percentualeGD from "
    		+"(select vettura.scuderia, count(*) as numGM from pilota join vettura ON pilota.vettura = vettura.numerogara AND tipo = 'GM' group by vettura.scuderia) as tab "
    		+"NATURAL JOIN "
    		+"(select vettura.scuderia, count(*) as numTot from pilota join vettura ON pilota.vettura = vettura.numerogara group by vettura.scuderia) as tab1";
    String SQL1 = "select nome from scuderia where nome not in (select vettura.scuderia from pilota join vettura on pilota.vettura = vettura.numerogara AND tipo = 'GM' group by vettura.scuderia)";
    public String toString()
        {
            return "| scuderia \t| percentualeGD |";
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
            ResultSet percentuale = query.executeQuery(SQL);
            op.textArea.append(toString()+"\n");
            while(percentuale.next())						//esecuzione query per piloti GM e calcolo della relativa percentuale
                {
                    String scu = percentuale.getString("scuderia");
                    int p = percentuale.getInt("percentualeGD");
                    op.textArea.append("|" + scu + "\t|" + p + "%\t   |"+"\n");
                }
            ResultSet perc0 = query.executeQuery(SQL1);		//esecuzione query per scuderie senza piloti GM, la cui 
            while (perc0.next()) {							//percentuale sarà 0
            	String scu = perc0.getString("nome");
            	op.textArea.append("|" + scu + "\t|" + 0 + "%\t   |"+"\n");
            }
            query.close();
            percentuale.close();
            perc0.close();
            conn.close();
        }
    catch(Exception e)
        {
    	JOptionPane.showMessageDialog(null,"errore nella connessione");
        }
}
}
