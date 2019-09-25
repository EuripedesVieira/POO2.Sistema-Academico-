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
import modelTable.TableDisciplina;
import modelTable.TableGrade;
import models.Curso;
import models.Disciplina;
import models.Grade;
import service.ServiceGrade;

public class InterfaceGrade extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	private JPanel containerDisci;
	private JTextField txfNome;

	private JLabel jlNome;
	private JLabel jlCurso;
	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;

	private JComboBox<String> cursos;
	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
	
	private JButton jbAdicionar;
	private JButton jbRemover;
	private JButton jbAddTodos;
	private JButton jbRemoverTodos;
	
	private List<Grade> listaGrade = new ArrayList<Grade>();
	private List<Curso> listaCurso = new ArrayList<Curso>();
	private List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
	private List<Disciplina> listaDisciplinaAdds = new ArrayList<Disciplina>();

	
	private TableGrade ModelotabelaGrade;
	private JTable tblGrade;
	private JScrollPane scrlGrade;
	
	private TableDisciplina ModelotabelaDisciplina;
	private JTable tblDisciplina;
	private JScrollPane scrlDisciplina;
	
	private TableDisciplina ModelotabelaDisciplinaAdds;
	private JTable tblDisciplinaAdds;
	private JScrollPane scrlDisciplinaAdds;
	
	private String nomeGrade;
	private String curso;

	private String campo = "Campo obrigatório";
	private int numeroLinha;
	private int id;
	private int idCurso;		
	private boolean clickDuplo= false;
	private boolean disciplinaSelecionada=false;
	private boolean disciplinaAddSelecionada=false;


	
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
		action();
		containerDisciplina();
		buscarTabela();
		modeloTabelaM();
		ModeloTabelaDisciplina();
		ModeloTabelaDisciplinaAdds();
		percorre();
		setVisible(true);
	}
	
	void buscarTabela(){
		listaGrade.clear();
		gradeService.buscar(listaGrade);
		gradeService.buscarCursos(listaCurso);
		gradeService.buscarDisciplinas(listaDisciplina);

		}

	void percorre() {
		for(int i=0; i<listaCurso.size(); i++) {
			Curso curso;
			curso = listaCurso.get(i);
			String muni =  curso.getNome();
			cursos.addItem(muni);
		}
	}

	void containerDisciplina(){
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Disciplinas");
		title.setTitleColor(Color.black);
	
		containerDisci = new JPanel();
		containerDisci.setBounds(30, 105, 1160, 300);
		containerDisci.setBorder(title);
		containerPrincipal.add(containerDisci);
		containerDisci.setLayout(null);
		containerDisci.setVisible(true);
		
		jbAdicionar =new JButton(">");
		jbAdicionar.setBounds(540,50,80,30);
		containerDisci.add(jbAdicionar);
		
		jbAddTodos = new JButton(">>");
		jbAddTodos.setBounds(540, 100, 80, 30);
		containerDisci.add(jbAddTodos);
		jbAddTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				for(int i=0; i<listaDisciplina.size(); i++) {
					Disciplina x = listaDisciplina.get(i);
					if(percorrelistaDisciplina(x.getIdDisciplina()))
						listaDisciplinaAdds.add(x);
				}
				containerDisci.add(scrlDisciplinaAdds);
			}
		});
		
		jbRemover = new JButton("<");
		jbRemover.setBounds(540, 150, 80, 30);
		containerDisci.add(jbRemover);
		jbRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(disciplinaAddSelecionada) {
					listaDisciplinaAdds.remove(numeroLinha);
					containerDisci.add(scrlDisciplinaAdds);
					disciplinaAddSelecionada=false;
					JOptionPane.showMessageDialog(null, "Disciplina removida");
				}
			}
		});
		
		jbRemoverTodos = new JButton("<<");
		jbRemoverTodos.setBounds(540, 200, 80, 30);
		containerDisci.add(jbRemoverTodos);
		jbRemoverTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaDisciplinaAdds.clear();
				containerDisci.add(scrlDisciplinaAdds);
			}
		});

	}
	
	void modeloTabelaM() {
		ModelotabelaGrade = new TableGrade(listaGrade);
		tblGrade = new JTable(ModelotabelaGrade);
		scrlGrade = new JScrollPane(tblGrade);
		scrlGrade.setBounds(30, 480, 500, 240);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Grades");
		title.setTitleColor(Color.black);
		scrlGrade.setBorder(title);
		containerPrincipal.add(scrlGrade);
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
					
					listaDisciplinaAdds.clear();
					gradeService.buscarDisciplinasSelecionadas(listaDisciplinaAdds,id);
					containerDisci.add(scrlDisciplinaAdds);
					
					clickDuplo = true;
					jlobrigatorio1.setVisible(false);
				}
			}
		});
	}
	
	void ModeloTabelaDisciplina(){
		
		ModelotabelaDisciplina = new TableDisciplina(listaDisciplina);
		tblDisciplina = new JTable(ModelotabelaDisciplina);
		scrlDisciplina = new JScrollPane(tblDisciplina);
		scrlDisciplina.setBounds(30, 30, 500, 240);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Todas as disciplinas");
		title.setTitleColor(Color.black);
		scrlDisciplina.setBorder(title);
		containerDisci.add(scrlDisciplina);
		
		tblDisciplina.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				numeroLinha = tblDisciplina.getSelectedRow();
				
				if (e.getClickCount()==1){
					disciplinaSelecionada=true;
				}
				
				
				if (e.getClickCount() >= 2) {
					Disciplina disciplina = listaDisciplina.get(numeroLinha);
					if(percorrelistaDisciplina(disciplina.getIdDisciplina())){
						listaDisciplinaAdds.add(disciplina);
						disciplinaSelecionada=false;
						containerDisci.add(scrlDisciplinaAdds);}
					else
						JOptionPane.showMessageDialog(null, "Discicplina já foi escolhida");

				}
			}
		});
	}
	
	void ModeloTabelaDisciplinaAdds(){
		
		ModelotabelaDisciplinaAdds = new TableDisciplina(listaDisciplinaAdds);
		tblDisciplinaAdds = new JTable(ModelotabelaDisciplinaAdds);
		scrlDisciplinaAdds = new JScrollPane(tblDisciplinaAdds);
		scrlDisciplinaAdds.setBounds(630, 30, 500, 240);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Disciplinas escolhidas");
		title.setTitleColor(Color.black);
		scrlDisciplinaAdds.setBorder(title);
		containerDisci.add(scrlDisciplinaAdds);
		tblDisciplinaAdds.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					numeroLinha = tblDisciplinaAdds.getSelectedRow();
					listaDisciplinaAdds.remove(numeroLinha);
					JOptionPane.showMessageDialog(null, "Discicplina removida");
					containerDisci.add(scrlDisciplinaAdds);
				}
				
				if(e.getClickCount()==1){
					disciplinaAddSelecionada=true;
					
				}
			}
		});
	}
	
	public Boolean percorrelistaDisciplina(int idDisciplina) {
		for(int i=0; i<listaDisciplinaAdds.size(); i++) {
			Disciplina x;
			x = listaDisciplinaAdds.get(i);
			if(x.getIdDisciplina()==idDisciplina) {
				return false;
			}
		}
		return true;
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
		
		jlobrigatorio2 = new JLabel("Selecione ao menos uma disciplina");
		jlobrigatorio2.setForeground(Color.red);
		jlobrigatorio2.setBounds(30, 405, 250, 20);
		jlobrigatorio2.setVisible(false);
		containerPrincipal.add(jlobrigatorio2);
	};

	void defineBt() {
		jbSalvar = new JButton("Salvar");
		jbSalvar.setBounds(30, 440, 100, 20);
		containerPrincipal.add(jbSalvar);

		jbExcluir = new JButton("Excluir");
		jbExcluir.setBounds(140, 440, 100, 20);
		containerPrincipal.add(jbExcluir);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(250, 440, 100, 20);
	    containerPrincipal.add(jbCancelar);
	};

	private void limpaCampos() {
		txfNome.setText("");
		listaDisciplinaAdds.clear();
		containerDisci.add(scrlDisciplinaAdds);
	}
	
	private void campusFalse() {
		jlobrigatorio1.setVisible(false);
		jlobrigatorio2.setVisible(false);
		txfNome.requestFocus();
		clickDuplo=false;
	}
	
	 void campoValidacao(String nomeGrade, List<Disciplina> disciplinas) {
		
		if(nomeGrade.isEmpty())
			jlobrigatorio1.setVisible(true);
		else
			jlobrigatorio1.setVisible(false);
		
		if(disciplinas.isEmpty())
			jlobrigatorio2.setVisible(true);
		else
			jlobrigatorio2.setVisible(false);
	}
	 
	void salvalistaDisciplina(int idGrade) {
			for(int i=0; i<listaDisciplinaAdds.size(); i++) {
				Disciplina disciplina = listaDisciplinaAdds.get(i);
				gradeService.salvarDisciplinaGrade(idGrade, disciplina.getIdDisciplina());
			}
		}

	void action() {
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeGrade = txfNome.getText();
				curso = (String) cursos.getSelectedItem();
				idCurso=gradeService.idCursoParaGrade(curso);
				
				if(clickDuplo==true) {
					if(!nomeGrade.isEmpty() && !curso.isEmpty() && !listaDisciplinaAdds.isEmpty()) {
									
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
						 campoValidacao(nomeGrade,listaDisciplinaAdds);
					}
				}
				
				else{	
					if(!nomeGrade.isEmpty() && !curso.isEmpty() && !listaDisciplinaAdds.isEmpty()) {

						grade.setNomeGrade(nomeGrade);
						grade.setIdCurso(idCurso);
						grade.setNomeCurso(curso);
						
						gradeService.salvar(grade,listaDisciplinaAdds);
						buscarTabela();
						JOptionPane.showMessageDialog(null,"Salvo com sucesso");
						limpaCampos();
						containerPrincipal.add(scrlGrade);
						campusFalse();

					}
					else {
						 campoValidacao(nomeGrade,listaDisciplinaAdds);
					}
				}
			}
		});

		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				campusFalse();

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
