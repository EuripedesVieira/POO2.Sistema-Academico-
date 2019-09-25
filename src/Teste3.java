import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Teste3 extends JFrame {
	
	private JPanel conPrincipal;
	private JPanel cont1;
	private JPanel cont2;
	private JPanel cont3;
	
	private Boolean cont=false;
	
	private JButton but1;
	private JButton but2;
	private JButton but3;
	
	
	public Teste3() {
		iniciaInterface();
	}

	private void iniciaInterface() {
		inicaJP();
		buttons();
		setVisible(true);
		
	}

	private void inicaJP() {
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Enois");
		conPrincipal = new JPanel();
		conPrincipal.setLayout(null);
		this.add(conPrincipal);
		
		
		cont1 = new JPanel();
		cont1.setBounds(0, 0, 200, 900);
		cont1.setBorder(new LineBorder(Color.white,3,true));
		cont1.setBackground(Color.blue);
		cont1.setLayout(null);
		conPrincipal.add(cont1);
		
		cont2 = new JPanel();
		cont2.setBounds(200, 0, 1150, 900);
		cont2.setBorder(new LineBorder(Color.white,3,true));
		cont2.setBackground(Color.black);
		conPrincipal.add(cont2);
		cont2.setVisible(false);
		
		cont3 = new JPanel();
		cont3.setBounds(200, 0, 1150, 900);
		cont3.setBorder(new LineBorder(Color.red,3,true));
		cont3.setBackground(Color.red);
	
		conPrincipal.add(cont3);
		cont3.setVisible(false);
	}

	private void buttons() {
		but1 = new JButton("Cursos");
		but1.setBounds(3, 3, 194, 40);
		but1.setBackground(Color.blue);
		but1.setForeground(Color.white);
		but1.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but1);
		but1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			if(!cont) {
				cont2.setVisible(false);
				cont=true;
			}
			else {
				cont2.setVisible(true);
				cont=false;
			}
			}
		});
		

		but2 = new JButton("Disciplinas");
		but2.setBounds(3, 43, 194, 40);
		but2.setBackground(Color.blue);
		but2.setForeground(Color.white);
		but2.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but2);
		but2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			if(!cont) {
				
				cont3.setVisible(false);
				cont=true;
			}
			else {
				cont3.setVisible(true);
				cont=false;
			}
			}
		});
		
	}
	



	public static void main(String[] args) {
		new Teste3();
	}
}
