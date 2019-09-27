import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Teste extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	private JPanel panel2;
	private JPanel panel3;
	private JMenuBar menuBar;
	private JMenu menu1;
	private JMenu menu2;

	private JButton bot;
	private int isso=0;
	
	public Teste() {
		iniciaInterface();
	}
	
	private void iniciaInterface() {
		defineJP();
		
		setVisible(true);
		
	}
	
	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Teste");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		add(containerPrincipal);
		
		
		menuBar = new JMenuBar(); 
		menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
		menuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		
		menu1 = new JMenu("Curso");
		menu1.setMnemonic(KeyEvent.VK_F);
		
		menu2 = new JMenu("Prova");
		menu2.setMnemonic(KeyEvent.VK_F);
		
		menuBar.add(menu1);
		menuBar.add(menu2);

	//	containerPrincipal.add(menuBar);
		this.setJMenuBar(menuBar);
		
		panel2 =new JPanel();
		panel2.setBounds(0, 0, 100, 100);
		panel2.setBorder(new LineBorder(Color.black,3,true));
		containerPrincipal.add(panel2);
		panel2.setVisible(false);
		
		panel3 =new JPanel();
		panel3.setBounds(0, 0, 100, 100);
		panel3.setBorder(new LineBorder(Color.BLUE,3,true));
		containerPrincipal.add(panel3);
		panel3.setVisible(false);
		
		bot =new JButton("Teste");
		bot.setBounds(50, 250, 100, 100);
		containerPrincipal.add(bot);
		bot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel2.setVisible(false);
			///	panel3.setVisible(true);
			}
		});
	};
	
	void menu() {
		
	}
	
	
	public static void main(String[] args) {
		new Teste();
	}
	
	
	
	
	
	
	
	
}
