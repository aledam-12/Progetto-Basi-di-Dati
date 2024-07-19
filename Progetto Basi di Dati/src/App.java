import java.sql.Connection;
import grafica.Menu;
import queries.*;

public class App {
public static void main(String[] args) {
Connection a = null;
Menu menu = new Menu("Progetto BD");
menu.setVisible(true);
PrimaQuery q1 = new PrimaQuery();
SecondaQuery q2 = new SecondaQuery();
TerzaQuery q3 = new TerzaQuery();
QuartaQuery q4 = new QuartaQuery();
QuintaQuery q5 = new QuintaQuery();
SestaQuery q6 = new SestaQuery();
SettimaQuery q7 = new SettimaQuery();
OttavaQuery q8= new OttavaQuery();
NonaQuery q9 = new NonaQuery();
QueryDieci q10 = new QueryDieci();
QueryUndici q11 = new  QueryUndici();
QueryDodici q12 = new QueryDodici();
QueryTredici q13 = new QueryTredici();
QueryQuattordici q14 = new QueryQuattordici();
QueryQuindici q15 = new QueryQuindici();
menu.pulsanteUno.addActionListener((e)->{q2.execute(a);});
menu.pulsanteDue.addActionListener((e)->{q1.execute(a);});		
menu.pulsanteTre.addActionListener((e)->{q3.execute(a);});
menu.pulsanteQuattro.addActionListener((e)->{q4.execute(a);});
menu.pulsanteCinque.addActionListener((e)->{q5.execute(a);});
menu.pulsanteSei.addActionListener((e)->{q6.execute(a);});
menu.pulsanteSette.addActionListener((e)->{q7.execute(a);});
menu.pulsanteOtto.addActionListener((e)->{q8.execute(a);});
menu.pulsanteNove.addActionListener((e)->{q9.execute(a);});
menu.pulsanteUndici.addActionListener((e)->{q11.execute(a);});
menu.pulsanteDieci.addActionListener((e)->{q10.execute(a);});
menu.pulsanteDodici.addActionListener((e)->{q12.execute(a);});
menu.pulsanteTredici.addActionListener((e)->{q13.execute(a);});
menu.pulsanteQuattordici.addActionListener((e)->q14.execute(a));
menu.pulsanteQuindici.addActionListener((e)->{q15.execute(a);});
}
}