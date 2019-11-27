package face;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import database.DataBase;
import modelTable.TableCurso;
import models.Curso;
import service.ServiceCurso;

public class InterfaceCurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	private JTextField txfNome;

	private JLabel jlNome;
	private JLabel jlobrigatorio1;
	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
	
	private List<Curso> listaCurso = new ArrayList<Curso>();
	private TableCurso ModelotabelaCurso;
	private JTable tblCurso;
	private JScrollPane scrlCurso;
	
	private String nomeCurso;
	private String campo = "Campo obrigatório";
	private int numeroLinha;
	private int idParaDeletar;
	
	private boolean click_duplo = false; 
	
	Curso curso = new Curso();
	DataBase database = new DataBase();
	ServiceCurso cursoService = new ServiceCurso();

	public InterfaceCurso() throws IOException {
		InciaInterface();
	}

	private void InciaInterface() throws IOException {

		defineJP();
		defineTxf();
		defineLb();
		defineBt();
		action();
		buscarTabela();
		modeloTabelaM();
		containerPrincipal.add(scrlCurso);
		setVisible(true);
	}
	
	void buscarTabela(){
		listaCurso.clear();
		try {
			cursoService.buscar(listaCurso);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	
	void modeloTabelaM() {
		ModelotabelaCurso = new TableCurso(listaCurso);
		tblCurso = new JTable(ModelotabelaCurso);
		scrlCurso = new JScrollPane(tblCurso);
		scrlCurso.setBounds(30, 250, 620, 200);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Cursos");
		title.setTitleColor(Color.black);
		scrlCurso.setBorder(title);
		tblCurso.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					numeroLinha = tblCurso.getSelectedRow();
					Curso curso = listaCurso.get(numeroLinha);
					String nome = curso.getNome();
					idParaDeletar = curso.getIdCurso();
					txfNome.setText(nome);
					click_duplo = true;
					jlobrigatorio1.setVisible(false);
				}
			}
		});
	}

	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Cursos");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		add(containerPrincipal);
	};

	void defineTxf(){

		txfNome = new JTextField();
		txfNome.setBounds(30, 50, 300, 30);
		containerPrincipal.add(txfNome);

	};

	void defineLb() {

		jlNome = new JLabel("Nome");
		jlNome.setBounds(30, 30, 100, 20);
		containerPrincipal.add(jlNome);
		
		jlobrigatorio1 = new JLabel(campo);
		jlobrigatorio1.setForeground(Color.red);
		jlobrigatorio1.setBounds(30, 80, 150, 20);
		jlobrigatorio1.setVisible(false);
		containerPrincipal.add(jlobrigatorio1);
	};

	void defineBt() {
		jbSalvar = new JButton("Salvar");
		jbSalvar.setBounds(30, 200, 100, 20);
		containerPrincipal.add(jbSalvar);

		jbExcluir = new JButton("Excluir");
		jbExcluir.setBounds(140, 200, 100, 20);
		containerPrincipal.add(jbExcluir);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(250, 200, 100, 20);
		containerPrincipal.add(jbCancelar);

	};


	
	
	private void limpaCampos() {
		txfNome.setText("");
	}

	void action() {
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeCurso = txfNome.getText().trim();
				curso.setNome(nomeCurso);

				
				if(!nomeCurso.isEmpty()) {
					if(click_duplo==true) {
						curso.setIdCurso(idParaDeletar);
						try {
							cursoService.atualizar(curso);
							JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
							atualizarTabela();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						
					}
					else{	
						try {
							cursoService.salvar(curso);
							JOptionPane.showMessageDialog(null, "Salvo com sucesso");
							atualizarTabela();
						}catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				else{
					jlobrigatorio1.setVisible(true);
				}
			}
		});
		
		
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				jlobrigatorio1.setVisible(false);
				if(click_duplo==true) 
						click_duplo=false;
			}
		});

		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(click_duplo==true) {
					try {
						cursoService.deletar(idParaDeletar);
						JOptionPane.showMessageDialog(null, "Deletado com sucesso");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					atualizarTabela();
				}
				limpaCampos();
				txfNome.requestFocus();
				jlobrigatorio1.setVisible(false);
			}
		});
	};
	
	void atualizarTabela() {
		buscarTabela();
		limpaCampos();
		containerPrincipal.add(scrlCurso);
		jlobrigatorio1.setVisible(false);
		txfNome.requestFocus();
		click_duplo=false;
	}

	
	public static void main(String[] args) throws IOException{
		new InterfaceCurso();
	};
};

