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
import modelTable.TableDisciplina;
import models.Disciplina;
import service.ServiceDisciplina;

public class InterfaceDisciplina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	private JTextField txfNome;
	private JTextField txfCodigo;

	private JLabel jlNome;
	private JLabel jlCodigo;
	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;

	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
	
	private List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
	private TableDisciplina ModelotabelaDisciplina;
	private JTable tblDisciplina;
	private JScrollPane scrlDisciplina;
	
	private String nomeCurso;
	private String codigoDisciplina;

	private String campo = "Campo obrigatório";
	private int numeroLinha;
	private int id;
	
	private boolean clickDuplo = false; 
	
	Disciplina disciplina = new Disciplina();
	DataBase database = new DataBase();
	ServiceDisciplina disciplinaService = new ServiceDisciplina();

	public InterfaceDisciplina() throws IOException {
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
		containerPrincipal.add(scrlDisciplina);
		setVisible(true);
	}
	
	void buscarTabela(){
		listaDisciplina.clear();
		try {
			disciplinaService.buscar(listaDisciplina);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
	}

	
	void modeloTabelaM() {
		ModelotabelaDisciplina = new TableDisciplina(listaDisciplina);
		tblDisciplina = new JTable(ModelotabelaDisciplina);
		scrlDisciplina = new JScrollPane(tblDisciplina);
		scrlDisciplina.setBounds(30, 200, 620, 200);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Dsiciplinas");
		title.setTitleColor(Color.black);
		scrlDisciplina.setBorder(title);
		tblDisciplina.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					numeroLinha = tblDisciplina.getSelectedRow();
					Disciplina disciplina = listaDisciplina.get(numeroLinha);
					
					id= disciplina.getIdDisciplina();
					String codigo = disciplina.getCodigoDisciplina();
					String nome = disciplina.getNomeDisciplina();

					txfCodigo.setText(codigo);
					txfNome.setText(nome);
					
					clickDuplo = true;
					jlobrigatorio1.setVisible(false);
				}
			}
		});
	}

	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Disciplinas");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		add(containerPrincipal);
	};

	void defineTxf(){

		txfCodigo = new JTextField();
		txfCodigo.setBounds(30, 50, 300, 30);
		containerPrincipal.add(txfCodigo);
		
		txfNome = new JTextField();
		txfNome.setBounds(350, 50, 300, 30);
		containerPrincipal.add(txfNome);

	};

	void defineLb() {

		jlNome = new JLabel("Nome");
		jlNome.setBounds(350, 30, 150, 20);
		containerPrincipal.add(jlNome);
		
		jlCodigo = new JLabel("Codigo");
		jlCodigo.setBounds(30, 30, 100, 20);
		containerPrincipal.add(jlCodigo);
		
		jlobrigatorio1 = new JLabel(campo);
		jlobrigatorio1.setForeground(Color.red);
		jlobrigatorio1.setBounds(30, 80, 150, 20);
		jlobrigatorio1.setVisible(false);
		containerPrincipal.add(jlobrigatorio1);
		
		jlobrigatorio2 = new JLabel(campo);
		jlobrigatorio2.setForeground(Color.red);
		jlobrigatorio2.setBounds(350, 80, 150, 20);
		jlobrigatorio2.setVisible(false);
		containerPrincipal.add(jlobrigatorio2);
	};

	void defineBt() {
		jbSalvar = new JButton("Salvar");
		jbSalvar.setBounds(30, 150, 100, 20);
		containerPrincipal.add(jbSalvar);

		jbExcluir = new JButton("Excluir");
		jbExcluir.setBounds(140, 150, 100, 20);
		containerPrincipal.add(jbExcluir);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(250, 150, 100, 20);
		containerPrincipal.add(jbCancelar);

	};


	
	
	private void limpaCampos() {
		txfNome.setText("");
		txfCodigo.setText("");
	}
	
	private void campusFalse() {
		jlobrigatorio1.setVisible(false);
		jlobrigatorio2.setVisible(false);

	
	}
	
	 void campoValidacao(String nomeCurso, String codigoDisciplina) {
		
		if(codigoDisciplina.isEmpty())
			jlobrigatorio1.setVisible(true);
		else
			jlobrigatorio1.setVisible(false);
		
		if(nomeCurso.isEmpty())
			jlobrigatorio2.setVisible(true);
		else
			jlobrigatorio2.setVisible(false);
	}

	void action() {
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nomeCurso = txfNome.getText().trim();
				codigoDisciplina = txfCodigo.getText().trim();
				
				disciplina.setCodigoDisciplina(codigoDisciplina);
				disciplina.setNomeDisciplina(nomeCurso);
				
				if(!nomeCurso.isEmpty() && !codigoDisciplina.isEmpty()) {
					if(clickDuplo==true) {
						disciplina.setIdDisciplina(id);
						try {
							disciplinaService.atualizar(disciplina);
							JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
							atulizarFrame();
						}catch(Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						
					}
					else{		
						try {
							disciplinaService.salvar(disciplina);
							JOptionPane.showMessageDialog(null, "Salvo com sucesso");
							atulizarFrame();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				else {
					campoValidacao(nomeCurso,codigoDisciplina);
				}
			}
		});

		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				campusFalse();
				if(clickDuplo==true) 
					clickDuplo=false;
			}
		});

		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clickDuplo==true) {
					try {
						disciplinaService.deletar(id);
						JOptionPane.showMessageDialog(null, "Deletado com sucesso");
						atulizarFrame();
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				limpaCampos();
				campusFalse();
				clickDuplo=false;
				txfCodigo.requestFocus();
			}
		});
	};
	
	private void atulizarFrame() {
		buscarTabela();
		limpaCampos();
		containerPrincipal.add(scrlDisciplina);
		campusFalse();
		txfCodigo.requestFocus();
		clickDuplo=false;
	}
	
	public static void main(String[] args) throws IOException{
		new InterfaceDisciplina();
	};
};
