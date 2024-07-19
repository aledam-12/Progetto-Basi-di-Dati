package grafica;
import javax.swing.*;
import java.awt.*;
public class Menu extends JFrame {
private static final long serialVersionUID = 1L;
public JButton pulsanteUno = new JButton("Registra Risultato");
public JButton pulsanteDue = new JButton("Stampa Costruttori");
public JButton pulsanteTre = new JButton("Registra Scuderia");
public JButton pulsanteQuattro = new JButton("Inserisci Finanziamento");
public JButton pulsanteCinque = new JButton ("Inserisci vettura con i rispettivi componenti");
public JButton pulsanteSei = new JButton ("Inserisci componente per una vettura");
public JButton pulsanteSette = new JButton ("Inserisci pilota");
public JButton pulsanteOtto = new JButton ("Iscrivi vettura a gara");
public JButton pulsanteNove = new JButton ("Visualizza la somma ottenuta da ciascuna scuderia");
public JButton pulsanteUndici = new JButton ("Visualizza piloti vincitori 'in casa' ");
public JButton pulsanteDieci = new JButton ("Visualizza quantità di finanziamenti di ciascuna scuderia");
public JButton pulsanteDodici = new JButton ("Visualizza percentuale Gentlemen driver");
public JButton pulsanteTredici = new JButton ("Classifica finale delle vetture");
public JButton pulsanteQuattordici = new JButton ("Classifica per tipo di motore");
public JButton pulsanteQuindici = new JButton("Classifica per media punti/minuto");
public JLabel a = new JLabel ("prova");
public Menu (String titolo) {
	super(titolo);
	setSize(1000,1000);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel pannello = new JPanel();
	pannello.add(pulsanteUno);
	pannello.add(pulsanteDue);
	pannello.add(pulsanteTre);
	pannello.add(pulsanteQuattro);
	pannello.add(pulsanteCinque);
	pannello.add(pulsanteSei);
	pannello.add(pulsanteSette);
	pannello.add(pulsanteOtto);
	pannello.add(pulsanteNove);
	pannello.add(pulsanteDieci);
	pannello.add(pulsanteDodici);
	pannello.add(pulsanteUndici);
	pannello.add(pulsanteTredici);
	pannello.add(pulsanteQuattordici);
	pannello.add(pulsanteQuindici);
	Container c = getContentPane();
	pannello.setLayout(new GridLayout(5,3, 100, 100));
	c.add(pannello);
}
}
