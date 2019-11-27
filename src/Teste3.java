import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import face.InterfaceCurso;

public class Teste3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel conPrincipal;
	private JPanel cont1;
	
	private JButton but1;
	private JButton but2;
	private JButton but3;
	private JButton but4;
	private JButton but5;
	private JButton but6;
	private JButton but7;

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
	}

	private void buttons() {
		but1 = new JButton("Aluno");
		but1.setBounds(3, 3, 194, 40);
		but1.setBackground(Color.blue);
		but1.setForeground(Color.white);
		but1.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but1);
		but1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new InterfaceCurso();
				} catch (IOException e1) {
				}
			}
		});

		but2 = new JButton("Curso");
		but2.setBounds(3, 43, 194, 40);
		but2.setBackground(Color.blue);
		but2.setForeground(Color.white);
		but2.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but2);
		but2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new InterfaceCurso();
				} catch (IOException e1) {
				}
			}
		});
		
		but3 = new JButton("Disciplina");
		but3.setBounds(3, 83, 194, 40);
		but3.setBackground(Color.blue);
		but3.setForeground(Color.white);
		but3.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but3);
		but3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new InterfaceCurso();
				} catch (IOException e1) {
				}
			}
		});
		
		
		but4 = new JButton("Grade");
		but4.setBounds(3, 123, 194, 40);
		but4.setBackground(Color.blue);
		but4.setForeground(Color.white);
		but4.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but4);
		but1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new InterfaceCurso();
				} catch (IOException e1) {
				}
			}
		});
	
		but5 = new JButton("Município");
		but5.setBounds(3, 163, 194, 40);
		but5.setBackground(Color.blue);
		but5.setForeground(Color.white);
		but5.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but5);
		but5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new InterfaceCurso();
				} catch (IOException e1) {
				}
			}
		});
		
		but6 = new JButton("Professor");
		but6.setBounds(3, 203, 194, 40);
		but6.setBackground(Color.blue);
		but6.setForeground(Color.white);
		but6.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but6);
		but6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new InterfaceCurso();
				} catch (IOException e1) {
				}
			}
		});
		
		but7 = new JButton("Turma");
		but7.setBounds(3, 243, 194, 40);
		but7.setBackground(Color.blue);
		but7.setForeground(Color.white);
		but7.setBorder(new LineBorder(Color.blue,3,true));
		cont1.add(but7);
		but7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new InterfaceCurso();
				} catch (IOException e1) {
				}
			}
		});
	}

	public static void main(String[] args) {
		new Teste3();
	}	
}