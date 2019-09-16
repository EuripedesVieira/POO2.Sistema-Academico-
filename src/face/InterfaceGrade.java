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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import database.DataBase;
import modelTable.TableGrade;
import models.Curso;
import models.Disciplina;
import models.Grade;
import service.ServiceGrade;

public class InterfaceGrade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	private JTextField txfNome;

	private JLabel jlNome;
	private JLabel jlCurso;
	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;

	private JComboBox<String> cursos;
	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
	
	private JCheckBox[] disciplinas = new JCheckBox[100];

	
	private List<Grade> listaGrade = new ArrayList<Grade>();
	private List<Curso> listaCurso = new ArrayList<Curso>();
	private List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();

	
	private TableGrade ModelotabelaGrade;
	private JTable tblGrade;
	private JScrollPane scrlGrade;
	
	private String nomeGrade;
	private String curso;

	private String campo = "Campo obrigatório";
	private int numeroLinha;
	private int id;
	private int idCurso;		
	private boolean clickDuplo= false; 
	
	
	Grade grade = new Grade();
	DataBase database = new DataBase();
	ServiceGrade gradeService = new ServiceGrade();
	

	public InterfaceGrade() throws IOException {
		InciaInterface();
	}

	private void InciaInterface() throws IOException {

		defineJP();
		defineTxf();
		defineLb();
		defineBt();
		percorreDisciplina();
		action();
		buscarTabela();
		modeloTabelaM();
		percorre();
		containerPrincipal.add(scrlGrade);
		setVisible(true);
	}
	
	void buscarTabela(){
		listaGrade.clear();
		gradeService.buscar(listaGrade);
		gradeService.buscarCursos(listaCurso);
	
		}

	void percorre() {
		for(int i=0; i<listaCurso.size(); i++) {
			Curso curso;
			curso = listaCurso.get(i);
			String muni =  curso.getNome();
			cursos.addItem(muni);
		}
	}
		
	void percorreDisciplina() {
		int x=30;
		int y=100;
		int a=0;
	
		
		gradeService.buscarDisciplinas(listaDisciplina);
		for(int i=0; i<listaDisciplina.size(); i++) {
			Disciplina disciplina;
			disciplina = listaDisciplina.get(i);
			String nomeDisciplina = disciplina.getNomeDisciplina(); 
			
			//JCheckBox primary = new JCheckBox(nomeDisciplina);
			disciplinas[i]= new JCheckBox(nomeDisciplina);
			disciplinas[i].setBounds(x, y, 300, 20);
			containerPrincipal.add(disciplinas[i]);
			x+=330;
			a+=1;
			if(a==4) {
				y=y+20;
				a=0;
				x=30;
			}
		
		}
		
	}
	
	void modeloTabelaM() {
		ModelotabelaGrade = new TableGrade(listaGrade);
		tblGrade = new JTable(ModelotabelaGrade);
		scrlGrade = new JScrollPane(tblGrade);
		scrlGrade.setBounds(30, 300, 620, 200);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Grades");
		title.setTitleColor(Color.black);
		scrlGrade.setBorder(title);
		tblGrade.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					numeroLinha = tblGrade.getSelectedRow();
					Grade grade = listaGrade.get(numeroLinha);
					
					id = grade.getIdGrade();
					String nomeGrade = grade.getNomeGrade();
					int idCurso = grade.getIdCurso();
					String curso = gradeService.nomeCursoParaGrade(idCurso);
					
					txfNome.setText(nomeGrade);
					cursos.setSelectedItem(curso);
					
					clickDuplo = true;
					jlobrigatorio1.setVisible(false);
				}
			}
		});
	}
	
	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Grades");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		add(containerPrincipal);
	};

	void defineTxf(){
		
		txfNome = new JTextField();
		txfNome.setBounds(30, 50, 300, 30);
		containerPrincipal.add(txfNome);
		
		
		cursos = new JComboBox<String>();
		cursos.setBounds(350, 50, 300, 30);
		containerPrincipal.add(cursos);

	};

	void defineLb() {

		jlNome = new JLabel("Nome");
		jlNome.setBounds(30, 30, 150, 20);
		containerPrincipal.add(jlNome);
		
		jlCurso = new JLabel("Curso");
		jlCurso.setBounds(350, 30, 100, 20);
		containerPrincipal.add(jlCurso);
		
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
		jbSalvar.setBounds(30, 250, 100, 20);
		containerPrincipal.add(jbSalvar);

		jbExcluir = new JButton("Excluir");
		jbExcluir.setBounds(140, 250, 100, 20);
		containerPrincipal.add(jbExcluir);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(250, 250, 100, 20);
	containerPrincipal.add(jbCancelar);
	};

	private void limpaCampos() {
		txfNome.setText("");
	}
	
	private void campusFalse() {
		jlobrigatorio1.setVisible(false);
		jlobrigatorio2.setVisible(false);
		txfNome.requestFocus();
		clickDuplo=false;
	}
	
	 void campoValidacao(String nomeGrade) {
		
		if(nomeGrade.isEmpty())
			jlobrigatorio1.setVisible(true);
		else
			jlobrigatorio1.setVisible(false);
	}

	void action() {
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeGrade = txfNome.getText();
				curso = (String) cursos.getSelectedItem();
				idCurso=gradeService.idCursoParaGrade(curso);
				
				if(clickDuplo==true) {
					if(!nomeGrade.isEmpty() && !curso.isEmpty()) {
									
						grade.setIdGrade(id);
						grade.setNomeGrade(nomeGrade);
						grade.setIdCurso(idCurso);
						
						gradeService.atualizar(grade);
						buscarTabela();
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
						limpaCampos();
						
						containerPrincipal.add(scrlGrade);
						campusFalse();
					}
					else {
						 campoValidacao(nomeGrade);
					}
				}
				
				else{	
					if(!nomeGrade.isEmpty() && !curso.isEmpty()) {

						grade.setNomeGrade(nomeGrade);
						grade.setIdCurso(idCurso);
						grade.setNomeCurso(curso);
						
						gradeService.salvar(grade);
						buscarTabela();
						JOptionPane.showMessageDialog(null, "Salvo com sucesso");
						limpaCampos();
						containerPrincipal.add(scrlGrade);
						campusFalse();

					}
					else {
						 campoValidacao(nomeGrade);
					}
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
				if(clickDuplo==true){
					gradeService.deletar(id);
					buscarTabela();
					JOptionPane.showMessageDialog(null, "Deletado com sucesso");
					containerPrincipal.add(scrlGrade);
					clickDuplo=false;
					limpaCampos();
					campusFalse();
				}
				limpaCampos();
				campusFalse();
			}
		});
	};
	public static void main(String[] args) throws IOException{
		new InterfaceGrade();		
	};
};