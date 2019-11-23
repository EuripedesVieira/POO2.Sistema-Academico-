package face;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import modelTable.TableTurma;
import modelTable.TableTurmaAluno;
import models.AlunoTurmaTable;
import models.Curso;
import models.CursoGrade;
import models.DisciplinaGrade;
import models.Grade;
import models.Professor;
import models.Turma;
import service.FuncoesData;
import service.ServiceTurma;

public class InterfaceTurma extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	private JPanel containerDisci;
	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
	
	private JButton jbAdicionar;
	private JButton jbRemover;
	private JButton jbAddTodos;
	private JButton jbRemoverTodos;
	
	private JTextField txfCodigo;
	
	private JLabel jlCodigo;
	private JLabel jlGrades;
	private JLabel jlobrigatorioCodigo;
	private JLabel jlobrigatorio2;
	private JLabel jlAno;
	private JLabel jlAnoAtual;
	private JLabel jlSemestre;
	private JLabel jlProfessor;
	private JLabel jlcurso;
	
	private JComboBox<String>grades;
	private JComboBox<String>semestres;
	private JComboBox<String>professores;
	private JComboBox<String>cursos;
	private JComboBox<String>disciplinas;
	
	
	private String nomeGrade;
	private String codigoTurma;
	private String nomeProfessor;
	private String semestre;
	private String nomeCurso;
	private String nomeDisciplina;
	
	private int ano;
	private int idTurma;
	private int idProfessor;
	private int idGrade;
	private int idDisciplina;
	private int idDisciplinaGrade;
	private int semestreNumero;
	private int idCurso;

	
	private final String campo = "Campo obrigatório";
	private int numeroLinha;
	private boolean clickDuplo=false;
	private boolean alunoSelecionado=false;
	private boolean alunoAddSelecionado=false;
	private boolean jaselionado= false;

	private List<Turma> listaTurma = new ArrayList<Turma>();
	private List<Curso> listaCurso = new ArrayList<Curso>();
	private List<AlunoTurmaTable> listaAlunos = new ArrayList<AlunoTurmaTable>();
	private List<AlunoTurmaTable> listaAlunosAdds = new ArrayList<AlunoTurmaTable>();
	private List<Grade> listaGrade = new ArrayList<Grade>();
	private List<Professor> listaProfessores = new ArrayList<Professor>();
	private List<CursoGrade> listaCursoGrade = new ArrayList<CursoGrade>();
	private List<DisciplinaGrade> listaDisciplinaGrade = new ArrayList<DisciplinaGrade>(); 
	
	private TableTurmaAluno ModelotabelaTurmaAluno;
	private JTable tblTurmaAluno;
	private JScrollPane scrlTurmaAluno;
	
	private TableTurmaAluno ModelotabelaTurmaAlunoAdds;
	private JTable tblTurmaAlunoAdds;
	private JScrollPane scrlTurmaAlunoAdds;
	
	private TableTurma modelotabelaTurma;
	private JTable tblTurma;
	private JScrollPane scrlTurma;
	
	private ServiceTurma turmaService = new ServiceTurma();
	private FuncoesData funcoesData = new FuncoesData();
	private Turma turma = new Turma();
	

	public InterfaceTurma() throws IOException {
		iniciaInterface();
	}
	
	private void iniciaInterface() throws IOException{
		defineJP();
		containerDisciplina();
		modeloTabelaTurmaAluno();
		modeloTabelaTurmaAlunoAdds();
		defineTxf();
		buscarTabelas();
		percorreProfessores();
		percorreCursos();
		nomeCurso = (String) cursos.getSelectedItem();
		percorreGradeCurso(nomeCurso);
		nomeGrade = (String) grades.getSelectedItem();
		percorreDisciplinaGrade(nomeGrade);
		defineLb();
		defineBt();
		modeloTabelaTurma();
		action();
		setVisible(true);		
	};
	
	
	void buscarTabelas() {
		try {
			turmaService.buscarAlunos(listaAlunos);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		try {
			turmaService.buscarProfessores(listaProfessores);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			turmaService.buscarGrades(listaGrade);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			turmaService.buscarCursos(listaCurso);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			turmaService.buscarGradeCurso(listaCursoGrade);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			turmaService.buscarDisciplinaGrade(listaDisciplinaGrade);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		try {
			turmaService.buscarTurma(listaTurma);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	};
	
	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Turmas");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		this.add(containerPrincipal);
	};
	
	void containerDisciplina(){
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Alunos");
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
		jbAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(alunoSelecionado) {
					AlunoTurmaTable x = listaAlunos.get(numeroLinha);
					if(verificaAlunoAdd(x.getIdAluno())){
						listaAlunosAdds.add(x);
						containerDisci.add(scrlTurmaAlunoAdds);
					}
					else {
						JOptionPane.showMessageDialog(null, "Aluno já adicionado");
					}
					alunoSelecionado=false;
				}
			}
		});
		
		jbAddTodos = new JButton(">>");
		jbAddTodos.setBounds(540, 100, 80, 30);
		containerDisci.add(jbAddTodos);
		jbAddTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				for(int i=0; i<listaAlunos.size(); i++) {
					AlunoTurmaTable x = listaAlunos.get(i);
					if(verificaAlunoAdd(x.getIdAluno()))
						listaAlunosAdds.add(x);
				}
				containerDisci.add(scrlTurmaAlunoAdds);
			}
		});
		
		jbRemover = new JButton("<");
		jbRemover.setBounds(540, 150, 80, 30);
		containerDisci.add(jbRemover);
		jbRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(alunoAddSelecionado) {
					listaAlunosAdds.remove(numeroLinha);
					containerDisci.add(scrlTurmaAlunoAdds);
					alunoAddSelecionado=false;
					JOptionPane.showMessageDialog(null, "Aluno removido");
				}
			}
		});
		
		jbRemoverTodos = new JButton("<<");
		jbRemoverTodos.setBounds(540, 200, 80, 30);
		containerDisci.add(jbRemoverTodos);
		jbRemoverTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaAlunosAdds.clear();
				containerDisci.add(scrlTurmaAlunoAdds);
			}
		});
	}
	
	void modeloTabelaTurmaAluno(){
		
		ModelotabelaTurmaAluno = new TableTurmaAluno(listaAlunos);
		tblTurmaAluno = new JTable(ModelotabelaTurmaAluno);
		scrlTurmaAluno = new JScrollPane(tblTurmaAluno);
		scrlTurmaAluno.setBounds(30, 30, 500, 240);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Todas os alunos");
		title.setTitleColor(Color.black);
		scrlTurmaAluno.setBorder(title);
		containerDisci.add(scrlTurmaAluno);
		
		tblTurmaAluno.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				numeroLinha = tblTurmaAluno.getSelectedRow();
				if (e.getClickCount()==1){
					alunoSelecionado=true;
				}
				
				if (e.getClickCount()>=2) {
					AlunoTurmaTable aluno = listaAlunos.get(numeroLinha);
					if(verificaAlunoAdd(aluno.getIdAluno())){
						listaAlunosAdds.add(aluno);
						alunoSelecionado=false;
						containerDisci.add(scrlTurmaAlunoAdds);}
					else
						JOptionPane.showMessageDialog(null, "Aluno já foi escolhido");

				}
			}
		});
	}
	
	void modeloTabelaTurmaAlunoAdds(){
		
		ModelotabelaTurmaAlunoAdds = new TableTurmaAluno(listaAlunosAdds);
		tblTurmaAlunoAdds = new JTable(ModelotabelaTurmaAlunoAdds);
		scrlTurmaAlunoAdds = new JScrollPane(tblTurmaAlunoAdds);
		scrlTurmaAlunoAdds.setBounds(630, 30, 500, 240);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Alunos selecionados");
		title.setTitleColor(Color.black);
		scrlTurmaAlunoAdds.setBorder(title);
		containerDisci.add(scrlTurmaAlunoAdds);
		tblTurmaAlunoAdds.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					numeroLinha = tblTurmaAlunoAdds.getSelectedRow();
					listaAlunosAdds.remove(numeroLinha);
					JOptionPane.showMessageDialog(null, "Aluno removido");
					containerDisci.add(scrlTurmaAlunoAdds);
				}
				
				if(e.getClickCount()==1){
					alunoAddSelecionado=true;
					
				}
			}
		});
	}
	
	public Boolean verificaAlunoAdd(int idAluno) {
		for(int i=0; i<listaAlunosAdds.size(); i++) {
			AlunoTurmaTable x;
			x = listaAlunosAdds.get(i);
			if(x.getIdAluno()==idAluno) {
				return false;
			}
		}
		return true;
	}
	
	void defineTxf(){
		
		txfCodigo = new JTextField();
		txfCodigo.setBounds(30, 50, 200, 30);
		containerPrincipal.add(txfCodigo);
		
		cursos = new JComboBox<String>();
		cursos.setBounds(250, 50, 190, 30);
		containerPrincipal.add(cursos);
	
		grades = new JComboBox<String>();
		grades.setBounds(460, 50, 190, 30);
		containerPrincipal.add(grades);
		
		semestres = new JComboBox<String>();
		semestres.setBounds(670, 50, 150, 30);
		semestres.addItem("1º semestre");
		semestres.addItem("2º semestre");
		containerPrincipal.add(semestres);
		
		professores = new JComboBox<String>();
		professores.setBounds(840, 50, 350, 30);
		containerPrincipal.add(professores);
		
		disciplinas = new JComboBox<String>();
		disciplinas.setBounds(850, 465, 350, 30);
		containerPrincipal.add(disciplinas);

	};
	
	void defineLb() {

		jlCodigo = new JLabel("Código");
		jlCodigo.setBounds(30, 30, 150, 20);
		containerPrincipal.add(jlCodigo);
		
		jlGrades = new JLabel("Grades");
		jlGrades.setBounds(460, 30, 100, 20);
		containerPrincipal.add(jlGrades);
		
		jlAno = new JLabel("Ano");
		jlAno.setBounds(1010, 30, 100, 20);
		jlAno.setVisible(false);
		containerPrincipal.add(jlAno);
				
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		jlAnoAtual = new JLabel("2018");
		jlAnoAtual.setBounds(1010, 50, 100, 30);
		jlAnoAtual.setBorder(border);
		jlAnoAtual.setVisible(false);
		containerPrincipal.add(jlAnoAtual);
		
		jlSemestre = new JLabel("Semestre");
		jlSemestre.setBounds(670, 30, 100, 20);
		containerPrincipal.add(jlSemestre);
		
		jlProfessor = new JLabel("Professor");
		jlProfessor.setBounds(840, 30, 100, 20);
		containerPrincipal.add(jlProfessor);
		
		jlcurso = new JLabel("Curso");
		jlcurso.setBounds(250,30,100,20);
		containerPrincipal.add(jlcurso);
		
		jlobrigatorioCodigo = new JLabel(campo);
		jlobrigatorioCodigo.setForeground(Color.red);
		jlobrigatorioCodigo.setBounds(30, 80, 150, 20);
		jlobrigatorioCodigo.setVisible(false);
		containerPrincipal.add(jlobrigatorioCodigo);
		
		jlobrigatorio2 = new JLabel("Selecione ao menos uma disciplina");
		jlobrigatorio2.setForeground(Color.red);
		jlobrigatorio2.setBounds(30, 405, 250, 20);
		jlobrigatorio2.setVisible(false);
		containerPrincipal.add(jlobrigatorio2);
	};
	
	void defineBt() {
		jbSalvar = new JButton("Salvar");
		jbSalvar.setBounds(30, 425, 100, 20);
		containerPrincipal.add(jbSalvar);

		jbExcluir = new JButton("Excluir");
		jbExcluir.setBounds(140, 425, 100, 20);
		containerPrincipal.add(jbExcluir);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(250, 425, 100, 20);
	    containerPrincipal.add(jbCancelar);
	};
	
	void modeloTabelaTurma(){
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Turmas");
		
		modelotabelaTurma = new TableTurma(listaTurma);
		tblTurma = new JTable(modelotabelaTurma);
		scrlTurma = new JScrollPane(tblTurma);
		scrlTurma.setBounds(30, 465, 800, 250);
		scrlTurma.setBorder(title);
		containerPrincipal.add(scrlTurma);
		tblTurma.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					clickDuplo=true;
					numeroLinha = tblTurma.getSelectedRow();
					turma = listaTurma.get(numeroLinha);
										
					try {
						nomeCurso=turmaService.buscarCurso(turma.getIdCurso());
						nomeGrade=turmaService.buscarGrade(turma.getIdGrade());
						nomeDisciplina=turmaService.buscarDisciplina(turma.getIdDisciplina());
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					
					txfCodigo.setText(turma.getCodigo());
					cursos.setSelectedItem(nomeCurso);
					grades.setSelectedItem(nomeGrade);
					disciplinas.setSelectedItem(nomeDisciplina);
					professores.setSelectedItem(turma.getNomeProfessor());
					
					if(turma.getSemestre()==1){
						semestres.setSelectedIndex(0);
					}
					else
						semestres.setSelectedIndex(1);

					try {
						listaAlunosAdds.clear();
						turmaService.buscarAlunosAdds(listaAlunosAdds,turma.getIdTurma());
						containerDisci.add(scrlTurmaAlunoAdds);
						
					} catch (Exception e1) {
						
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}
		});
	};
	
	void action(){
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codigoTurma = txfCodigo.getText().trim();

				if(!codigoTurma.isEmpty() && !listaAlunosAdds.isEmpty()) {		
					salvar();
					if(clickDuplo==true) {
						try {
							turmaService.atualizar(turma);
							turmaService.atualizarTurmaAluno(listaAlunosAdds,turma.getIdTurma());
							atualizarTabela();
							JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
							clickDuplo=false;
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
					else {
						try {
							turmaService.salvar(turma,listaAlunosAdds);
							JOptionPane.showMessageDialog(null,"Salvo com sucesso");
							atualizarTabela();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					};
					campusFalse();
				}
				else {
					verificaCampus();
				};
			}
		});
		
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campusFalse();
				 setcombo();
				
			}
		});

		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clickDuplo==true){
					try {
						turmaService.deletar(turma.getIdTurma());
						JOptionPane.showMessageDialog(null, "Deletado com sucesso");
						clickDuplo=false;
						atualizarTabela();
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				campusFalse();
			}
		});
		
		cursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grades.removeAllItems();
				nomeCurso = (String) cursos.getSelectedItem();
				percorreGradeCurso(nomeCurso);				
				nomeGrade = (String) grades.getSelectedItem();
				disciplinas.removeAllItems();
				percorreDisciplinaGrade(nomeGrade);
			}
		});
		
		
		
		grades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				disciplinas.removeAllItems();
				nomeGrade = (String) grades.getSelectedItem();
				System.out.println(nomeGrade);
				if(nomeGrade!=null)
					percorreDisciplinaGrade(nomeGrade);
			}
		});
	}
	
	private void atualizarTabela() {
		try {
			listaTurma.clear();
			turmaService.buscarTurma(listaTurma);
			containerPrincipal.add(scrlTurma);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private void percorreCursos() {
		try {
			for(int i=0; i<listaCurso.size(); i++) {
				Curso curso;
				curso = listaCurso.get(i);
				String nome =  curso.getNome();
				cursos.addItem(nome);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	};
	
	private void percorreDisciplinaGrade(String nomeGrade) {
		try {
			for(int i=0; i<listaDisciplinaGrade.size(); i++) {
				DisciplinaGrade discplinagrade;
				discplinagrade = listaDisciplinaGrade.get(i);
				String nomeDisciplina =  discplinagrade.getNomeDisciplina();
				String nomegrade = discplinagrade.getNomeGrade();
				if(!nomeGrade.equals(nomegrade))
					continue;
				disciplinas.addItem(nomeDisciplina);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	};
	
	private void percorreGradeCurso(String nomeCurso) {
		try {
			for(int i=0; i<listaCursoGrade.size(); i++) {
				CursoGrade cursoGrade;
				cursoGrade = listaCursoGrade.get(i);
				String curso = cursoGrade.getNomeCurso();
				if(curso.equals(nomeCurso)) {
					grades.addItem(cursoGrade.getNomeGrade());}
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
	};
	
	private void percorreProfessores() {
		try {
			for(int i=0; i<listaProfessores.size(); i++) {
				Professor professor;
				professor = listaProfessores.get(i);
				String nome =  professor.getNomeProfessor();
		;		professores.addItem(nome);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	};
	
	private int idProfessor(String nome) {
		for(int i=0; i<listaProfessores.size(); i++) {
			Professor professor;
			professor = listaProfessores.get(i);
			if(nome.equals(professor.getNomeProfessor()))
				return professor.getIdProfessor();
		}
		return 0;
	};
	
	private int idCurso(String nome) {
		for(int i=0; i<listaCurso.size(); i++) {
			Curso curso;
			curso = listaCurso.get(i);
			if(nome.equals(curso.getNome()))
				return curso.getIdCurso();
		}
		return 0;
	};
	
	private int idGrade(String nomeGrade) {
		for(int i=0; i<listaGrade.size(); i++) {
			Grade grade;
			grade = listaGrade.get(i);
			if(nomeGrade.equals(grade.getNomeGrade()))
				return grade.getIdGrade();
		}
		return 0;
	};
	
	private int idDisciplina(String nomedisciplina){
		for(int i=0; i<listaDisciplinaGrade.size(); i++) {
			DisciplinaGrade discplinagrade;
			discplinagrade = listaDisciplinaGrade.get(i);
			String nomeDisciplina =  discplinagrade.getNomeDisciplina();
			int idDisciplina = discplinagrade.getIdDisciplina();
			if(nomeDisciplina.equals(nomedisciplina))
				return idDisciplina; 
		}
		return 0;
	}

	private int NumeroSemestre(String semestre) {
		if(!semestre.isEmpty()){
			int valor;
			if(semestre.equals("1º semestre")){
				valor=1;
			}
			else {
				valor=2;
			}
			return valor;
		};
		return 0;
	}
	
	private void campusFalse() {
		txfCodigo.setText("");
		txfCodigo.requestFocus();
		jlobrigatorioCodigo.setVisible(false);
		jlobrigatorio2.setVisible(false);
		
		clickDuplo=false;
		alunoAddSelecionado=false;
		alunoSelecionado=false;
		listaAlunosAdds.clear();
		containerDisci.add(scrlTurmaAlunoAdds);

		grades.setSelectedIndex(0);
		professores.setSelectedIndex(0);
		semestres.setSelectedIndex(0);
	}
	
	private void verificaCampus() {
		if(codigoTurma.isEmpty())
			jlobrigatorioCodigo.setVisible(true);
		else
			jlobrigatorioCodigo.setVisible(false);
	
		if(listaAlunosAdds.isEmpty())
			jlobrigatorio2.setVisible(true);
		else
			jlobrigatorio2.setVisible(false);
	};
	
	void salvar() {
		getAno();
		getComboBox();
		setTurma();
	};
	
	void getAno() {
		try {
			ano = funcoesData.anoAtual();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	void getComboBox() {
		nomeCurso = (String) cursos.getSelectedItem();
		nomeGrade = (String) grades.getSelectedItem();
		semestre = (String) semestres.getSelectedItem();
		nomeDisciplina = (String) disciplinas.getSelectedItem();
		nomeProfessor = (String) professores.getSelectedItem();
		
		idProfessor = idProfessor(nomeProfessor);
		idCurso=idCurso(nomeCurso);
		idGrade = idGrade(nomeGrade);
		idDisciplina=idDisciplina(nomeDisciplina);
		semestreNumero= NumeroSemestre(semestre);
		codigoTurma=txfCodigo.getText();
		
		try {
			idDisciplinaGrade = turmaService.BuscarIDDisciplinaTurna(idGrade, idDisciplina);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	};
	
	void setTurma() {
		turma.setCodigo(codigoTurma);
		turma.setSemestre(semestreNumero);
		turma.setIdGrade(idGrade);
		turma.setIdProfessor(idProfessor);
		turma.setIdCurso(idCurso);
		turma.setIdDisciplina(idDisciplina);
		turma.setAno(ano);
		turma.setCodigo(codigoTurma);
	}
	
	void setcombo() {
		cursos.setSelectedIndex(0);
		grades.setSelectedIndex(0);
		disciplinas.setSelectedIndex(0);
		professores.setSelectedIndex(0);
	}
	
	public static void main(String[] args) throws IOException {
		new InterfaceTurma();
	}
}
