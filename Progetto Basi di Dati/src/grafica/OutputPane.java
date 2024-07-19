package grafica;
import java.awt.BorderLayout;

import javax.swing.*;

public class OutputPane extends JFrame{
	private static final long serialVersionUID = 1L;
public JTextArea textArea;
public OutputPane(String s){
	super(s);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setSize(800,1000);
	textArea = new JTextArea(100, 200);
	JPanel p = new JPanel();
	add(textArea);
	add(p);
	setLayout(new BorderLayout());
	add(textArea, BorderLayout.CENTER);
	textArea.setEditable(false);
	textArea.setVisible(true);
	setVisible(true);	
	JButton ind = new JButton ("Esci");
	add(p, BorderLayout.SOUTH);
	ind.addActionListener ((e)->setVisible(false));
	p.add(ind);
}
}
