package face;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import modelTable.TableAluno;
import models.Aluno;
import models.Curso;
import models.Municipio;
import models.Pessoa;
import service.FuncoesData;
import service.ServiceAluno;

public class InterfaceAluno extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel containerPrincipal;
	private JPanel containerDadosPessoais;
	private JPanel containerDadosAlunos;
	
	private JTextField txfNome;
	private JTextField txfLogradouro;
	private JTextField txfBairro;
	private JTextField txfNumero;
	private JTextField txfComplemento;
	private JTextField txfEmail;
	
	private JTextField txfMatricula;

	private JComboBox<String> jcSexo;
	private JComboBox<String> municipios;
	private JComboBox<String> cursos;
	
	private JLabel jlNome;
	private JLabel jlCodigo;
	private JLabel jlSexo;
	private JLabel jlDataNascimento;
	private JLabel jlLogradouro;
	private JLabel jlBairro;
	private JLabel jlCep;
	private JLabel jlNumero;
	private JLabel jlEmail;
	private JLabel jlComplemento;
	private JLabel jlMunicipio;
	
	private JLabel jlMatricula;
	private JLabel jlDataMatricula;
	private JLabel jlData;
	private JLabel jNomeCurso;
	
	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;
	private JLabel jlobrigatorioData;
	private JLabel jlobrigatorioEmail;
	private JLabel jlobrigatorioLogra;
	private JLabel jlobrigatorioBairro;
	private JLabel jlobrigatorioCep;
	private JLabel jlobrigatorioCodigoMatricula;
	
	private MaskFormatter mascaraCpf = null;
	private  MaskFormatter mascaraData= null;
	private MaskFormatter mascaraCep = null;

	private JFormattedTextField jFormattedTextCpf;
	private JFormattedTextField jFormattedTextData;
	private JFormattedTextField jFormattedTextCep;
	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;

	private String campo = "Campo obrigatório";	

	private Date dataConvertida;
	private String nome;
	private String cpf;
	private String sexo;
	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String email;
	private String nomeMunicipio;
	private int idMunicipio;
	private String codigoMatricula;
	private String dataMatricula;
	private String dataNascimento;
	private int idPessoa;
	private int idCurso;
	private String nomeCurso;

	private boolean clickDuplo = false; 
	
	private TableAluno ModelotabelaAluno;
	private JTable tblAluno;
	private JScrollPane scrlAluno;
	
	private List<Aluno> listaAlunos = new ArrayList<Aluno>();
	private List<Municipio> listaMunucipios = new ArrayList<Municipio>();
	private List<Curso> listaCursos = new ArrayList<Curso>();
	
	Pessoa pessoa = new Pessoa();
	Aluno aluno = new Aluno();
	ServiceAluno alunoService = new ServiceAluno();
	FuncoesData funcoesData = new FuncoesData();
		
	public InterfaceAluno() throws IOException{
		iniciaInterface();
	}
	private void iniciaInterface() throws IOException{
		buscarTabelaInicio();
		defineJP();
		defineMask();
		containerPessoas();
		percorreListaMunicipio();
		containerAluno();
		percorreListaCurso();
		defineLb();
		defineBt();
		acoesBottons();
		modeloTabelaAluno();
		setVisible(true);
	}
	
	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alunos");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		this.add(containerPrincipal);
	}
	
	void containerPessoas() {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Dados pessoais");
		title.setTitleColor(Color.black);
	
		containerDadosPessoais = new JPanel();
		containerDadosPessoais.setBounds(30, 10, 1245, 210);
		containerDadosPessoais.setBorder(title);
		containerPrincipal.add(containerDadosPessoais);
		containerDadosPessoais.setLayout(null);
		containerDadosPessoais.setVisible(true);

		txfNome = new JTextField();
		txfNome.setBounds(30, 50, 300, 30);
		containerDadosPessoais.add(txfNome);
		
       jFormattedTextCpf = new JFormattedTextField(mascaraCpf);
       jFormattedTextCpf.setBounds(350, 50, 150, 30);
       containerDadosPessoais.add(jFormattedTextCpf);
		
	    jFormattedTextData = new JFormattedTextField(mascaraData);
	    jFormattedTextData.setBounds(690, 50, 150, 30);
	    containerDadosPessoais.add(jFormattedTextData);
		
		txfEmail = new JTextField();
		txfEmail.setBounds(860, 50, 355, 30);
		containerDadosPessoais.add(txfEmail);
		
		txfLogradouro = new JTextField();
		txfLogradouro.setBounds(30, 130, 200, 30);
		containerDadosPessoais.add(txfLogradouro);		

		txfBairro = new JTextField();
		txfBairro.setBounds(250, 130, 150, 30);
		containerDadosPessoais.add(txfBairro);	
		
	    jFormattedTextCep = new JFormattedTextField(mascaraCep);
		jFormattedTextCep.setBounds(420, 130, 150, 30);
		containerDadosPessoais.add(jFormattedTextCep);
		
		txfNumero = new JTextField();
		txfNumero.setBounds(590, 130, 100, 30);
		containerDadosPessoais.add(txfNumero);
		
		txfComplemento = new JTextField();
		txfComplemento.setBounds(710, 130, 285, 30);
		containerDadosPessoais.add(txfComplemento);
		
		jcSexo = new JComboBox<String>();
		jcSexo.addItem("Feminino");
		jcSexo.addItem("Masculino");
		jcSexo.setBounds(520, 50, 150, 30);
		containerDadosPessoais.add(jcSexo);

		municipios = new JComboBox<String>();
		municipios.setBounds(1015, 130, 200, 30);
		containerDadosPessoais.add(municipios);
	}
	
	void defineMask() {
		 try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraCep = new MaskFormatter("#####-###");
			mascaraData.setPlaceholderCharacter('_');
			mascaraCpf.setPlaceholderCharacter('_');
			mascaraCep.setPlaceholderCharacter('_');

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	void defineLb() {

		jlNome = new JLabel("Nome");
		jlNome.setBounds(30, 30, 150, 20);
		containerDadosPessoais.add(jlNome);
		
		jlCodigo = new JLabel("CPF");
		jlCodigo.setBounds(350, 30, 100, 20);
		containerDadosPessoais.add(jlCodigo);
		
		jlSexo = new JLabel("Sexo");
		jlSexo.setBounds(520, 30, 100, 20);
		containerDadosPessoais.add(jlSexo);

		jlDataNascimento = new JLabel("Data Nascimento");
		jlDataNascimento.setBounds(690, 30, 150, 20);
		containerDadosPessoais.add(jlDataNascimento);

		jlEmail = new JLabel("Email");
		jlEmail.setBounds(860, 30, 150, 20);
		containerDadosPessoais.add(jlEmail);

		jlLogradouro = new JLabel("Longradouro");
		jlLogradouro.setBounds(30, 110, 150, 20);
		containerDadosPessoais.add(jlLogradouro);

		jlBairro = new JLabel("Bairro");
		jlBairro.setBounds(250, 110, 100, 20);
		containerDadosPessoais.add(jlBairro);
		
		jlCep = new JLabel("Cep");
		jlCep.setBounds(420, 110, 100, 20);
		containerDadosPessoais.add(jlCep);
		
		jlNumero = new JLabel("Numero");
		jlNumero.setBounds(590, 110, 100, 20);
		containerDadosPessoais.add(jlNumero);
		
		jlComplemento = new JLabel("Complemento");
		jlComplemento.setBounds(710, 110, 100, 20);
		containerDadosPessoais.add(jlComplemento);

		jlMunicipio = new JLabel("Municipio");
		jlMunicipio.setBounds(1015, 110, 100, 20);
		containerDadosPessoais.add(jlMunicipio);
		
		jlobrigatorio1 = new JLabel(campo);
		jlobrigatorio1.setForeground(Color.red);
		jlobrigatorio1.setBounds(30, 80, 150, 20);
		jlobrigatorio1.setVisible(false);
		containerDadosPessoais.add(jlobrigatorio1);
		
		jlobrigatorio2 = new JLabel(campo);
		jlobrigatorio2.setForeground(Color.red);
		jlobrigatorio2.setBounds(350, 80, 300, 20);
		jlobrigatorio2.setVisible(false);
		containerDadosPessoais.add(jlobrigatorio2);
	
		jlobrigatorioData = new JLabel(campo);
		jlobrigatorioData.setForeground(Color.red);
		jlobrigatorioData.setBounds(690, 80, 300, 20);
		jlobrigatorioData.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioData);
		
		jlobrigatorioEmail = new JLabel(campo);
		jlobrigatorioEmail.setForeground(Color.red);
		jlobrigatorioEmail.setBounds(860, 80, 300, 20);
		jlobrigatorioEmail.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioEmail);
		
		jlobrigatorioLogra = new JLabel(campo);
		jlobrigatorioLogra.setForeground(Color.red);
		jlobrigatorioLogra.setBounds(30, 160, 300, 20);
		jlobrigatorioLogra.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioLogra);
		
		jlobrigatorioBairro = new JLabel(campo);
		jlobrigatorioBairro.setForeground(Color.red);
		jlobrigatorioBairro.setBounds(250,160, 150, 20);
		jlobrigatorioBairro.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioBairro);
		
		jlobrigatorioCep = new JLabel(campo);
		jlobrigatorioCep.setForeground(Color.red);
		jlobrigatorioCep.setBounds(420, 160, 150, 20);
		jlobrigatorioCep.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioCep);
	};
	void containerAluno() {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Dados aluno");
		title.setTitleColor(Color.black);
	
		containerDadosAlunos = new JPanel();
		containerDadosAlunos.setBounds(30, 230, 1245, 130);
		containerDadosAlunos.setBorder(title);
		containerPrincipal.add(containerDadosAlunos);
		containerDadosAlunos.setLayout(null);
		containerDadosAlunos.setVisible(true);
		
		txfMatricula = new JTextField();
		txfMatricula.setBounds(30, 50, 200, 30);
		containerDadosAlunos.add(txfMatricula);
		
		cursos = new JComboBox<String>();
		cursos.setBounds(250, 50, 200, 30);
		containerDadosAlunos.add(cursos);
		
		jlData = new JLabel();
		jlData.setBounds(470, 50, 150, 30);
		containerDadosAlunos.add(jlData);
		jlData.setVisible(false);
		
		
		jlMatricula = new JLabel("Codígo da matricula");
		jlMatricula.setBounds(30, 30, 150, 20);
		containerDadosAlunos.add(jlMatricula);
		
		jlDataMatricula = new JLabel("Data da Matricula");
		jlDataMatricula.setBounds(470, 30, 150, 20);
		jlDataMatricula.setVisible(false);
		containerDadosAlunos.add(jlDataMatricula);
		
		jNomeCurso = new JLabel("Curso");
		jNomeCurso.setBounds(250, 30, 150, 20);
		jNomeCurso.setVisible(true);
		containerDadosAlunos.add(jNomeCurso);
		
		jlobrigatorioCodigoMatricula = new JLabel(campo);
		jlobrigatorioCodigoMatricula.setForeground(Color.red);
		jlobrigatorioCodigoMatricula.setBounds(30, 80, 150, 20);
		jlobrigatorioCodigoMatricula.setVisible(false);
		containerDadosAlunos.add(jlobrigatorioCodigoMatricula);
	}
	
	void defineBt() {
		jbSalvar = new JButton("Salvar");
		jbSalvar.setBounds(30, 380, 100, 20);
		containerPrincipal.add(jbSalvar);

		jbExcluir = new JButton("Excluir");
		jbExcluir.setBounds(140, 380, 100, 20);
		containerPrincipal.add(jbExcluir);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(250, 380, 100, 20);
		containerPrincipal.add(jbCancelar);
	};
	
	void modeloTabelaAluno() {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Pessoas");
		title.setTitleColor(Color.black);
		
		ModelotabelaAluno = new TableAluno(listaAlunos);
		tblAluno = new JTable(ModelotabelaAluno);
		scrlAluno = new JScrollPane(tblAluno);
		scrlAluno.setBorder(title);
		scrlAluno.setBounds(30, 420, 1245, 200);
		containerPrincipal.add(scrlAluno);
		tblAluno.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()>=2) {
					int numeroLinha = tblAluno.getSelectedRow();
					aluno = listaAlunos.get(numeroLinha);					
					idPessoa=aluno.getIdPessoa();
					
					try {
						alunoService.buscarPessoa(pessoa, idPessoa);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					
					
					idPessoa = pessoa.getIdPessoa();
					idMunicipio = pessoa.getIdMunicipio();
					cpf = pessoa.getCpf();
					nome = pessoa.getNome();
					sexo = pessoa.getSexo();
					Date date = pessoa.getDataNascimento();
					logradouro = pessoa.getLogradouro();
					bairro =pessoa.getBairro();
					cep = pessoa.getCep();
					numero = pessoa.getNumero();
					complemento = pessoa.getComplemento();
					email = pessoa.getEmail();
					numero = pessoa.getNumero();
					complemento = pessoa.getComplemento();
					
					codigoMatricula = aluno.getMatricula();
					Date dateMatricula = aluno.getDataMatricula();
					nomeCurso = aluno.getNomeCurso();
					
					try {
						nomeMunicipio=alunoService.buscarItemMunicipio(idMunicipio);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				
					try {					
						dataNascimento = funcoesData.dataParaMostar(date);
						dataMatricula = funcoesData.dataParaMostar(dateMatricula);
						jFormattedTextData.setText(dataNascimento);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}

					txfNome.setText(nome);
					jFormattedTextCpf.setText(cpf);
					jcSexo.setSelectedItem(sexo);
					txfLogradouro.setText(logradouro);
					txfBairro.setText(bairro);
					jFormattedTextCep.setText(cep);
					txfNumero.setText(numero);
					txfComplemento.setText(complemento);
					txfEmail.setText(email);
					txfLogradouro.setText(logradouro);
					txfNumero.setText(numero);
					txfComplemento.setText(complemento);
					municipios.setSelectedItem(nomeMunicipio);
					
					txfMatricula.setText(codigoMatricula);
					cursos.setSelectedItem(nomeCurso);
					jlData.setText(dataMatricula);
					jlData.setVisible(true);
					jlDataMatricula.setVisible(true);
					clickDuplo=true;
					}
				}
		});
	}
	
	 int isInteger(String text) {
		 int cont = 0;
		 for(int i=0; i<text.length(); i++) {
			 if(Character.isDigit(text.charAt(i))) {
				 cont++;
			 }
		 }
		 return cont;
	 }
	 
	void acoesBottons() {
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contdata = isInteger(jFormattedTextData.getText());
				int contcpf = isInteger(jFormattedTextCpf.getText());
				int contcep = isInteger(jFormattedTextCep.getText());
				
				nome = txfNome.getText().trim();
				cpf = jFormattedTextCpf.getText();
				sexo = (String) jcSexo.getSelectedItem();
				email = txfEmail.getText().trim();
				logradouro = txfLogradouro.getText().trim();
				bairro = txfBairro.getText().trim();
				cep = jFormattedTextCep.getText();
				numero = txfNumero.getText();
				complemento = txfComplemento.getText();
				nomeMunicipio = (String) municipios.getSelectedItem();
				codigoMatricula = txfMatricula.getText().trim();
				nomeCurso = (String) cursos.getSelectedItem();
			
				try {
					idCurso = alunoService.buscarIdCurso(nomeCurso);
					idMunicipio= alunoService.buscarIdMunicipio(nomeMunicipio);
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage());
				}
				
				
				if(contdata==8) {
					dataNascimento = jFormattedTextData.getText();
					try {
						dataConvertida = funcoesData.dataParaSalvar(dataNascimento);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				
				if(contcpf==11 && !nome.isEmpty() && !sexo.isEmpty() && contdata == 8
				   && !logradouro.isEmpty() && !bairro.isEmpty() && contcep == 8 && !email.isEmpty()
				   && !codigoMatricula.isEmpty()){
				
					carregaObjetoPessoa();
					CarregaObjetoAluno();
	
					if(clickDuplo==true) {
						try {
							alunoService.atualizar(pessoa,aluno);
							JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}	
					else{
						try {
							alunoService.salvar(pessoa, aluno);
							JOptionPane.showMessageDialog(null, "Salvo com sucesso");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
					campusFalse();
					buscarTabela();
				}
				else {
					campoValidacao(nome,contcpf,contdata,email,logradouro,bairro,contcep,codigoMatricula);
				}
			}
		});
		
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campusFalse();
			}
		});
		
		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clickDuplo==true) {
					try {
						alunoService.deletar(idPessoa);
						JOptionPane.showMessageDialog(null, "Deletado com sucesso");
						buscarTabela();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				campusFalse();
			}
		});
	}
	
	void buscarTabela() {
		try {
			if(!listaAlunos.isEmpty())
				listaAlunos.clear();
			alunoService.buscarTabela(listaAlunos);
			containerPrincipal.add(scrlAluno);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	};
	
	void buscarTabelaInicio() {
		try {
			alunoService.buscarTabela(listaAlunos);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	};


	
	void carregaObjetoPessoa() {
		pessoa.setCpf(cpf);
		pessoa.setNome(nome);
		pessoa.setSexo(sexo);
		pessoa.setDataNascimento(dataConvertida);
		pessoa.setLogradouro(logradouro);
		pessoa.setBairro(bairro);
		pessoa.setCep(cep);
		pessoa.setNumero(numero);
		pessoa.setComplemento(complemento);
		pessoa.setEmail(email);
		pessoa.setIdMunicipio(idMunicipio);
	}
	
	void CarregaObjetoAluno() {
		aluno.setMatricula(codigoMatricula);
		aluno.setIdCurso(idCurso);
		try {
			Date dataMatricula;
			dataMatricula = funcoesData.dataAtual();
			aluno.setDataMatricula(dataMatricula);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	void percorreListaMunicipio() {
		try {
			alunoService.buscarMunicipio(listaMunucipios);
			for(int i=0; i<listaMunucipios.size(); i++) {
				Municipio municipio;
				municipio= listaMunucipios.get(i);
				String muni =  municipio.getNomeMunicipio();
				municipios.addItem(muni);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	};
	
	void percorreListaCurso(){
		try {
			alunoService.buscarCurso(listaCursos);
			for(int i=0; i<listaCursos.size(); i++){
				Curso curso = listaCursos.get(i);
				String curs = curso.getNome();
				cursos.addItem(curs);	
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	};
	
	
	void campoValidacao(String nome, int cpf, int data, String email, String logra, String bairro, int cep, String codigoMatricula) {
			if(nome.isEmpty())
				jlobrigatorio1.setVisible(true);
			else
				jlobrigatorio1.setVisible(false);
			
			if(cpf!=11)
				jlobrigatorio2.setVisible(true);
			else
				jlobrigatorio2.setVisible(false);
			
			if(data != 8)
				jlobrigatorioData.setVisible(true);
			else
				jlobrigatorioData.setVisible(false);
			
			if(email.isEmpty())
				jlobrigatorioEmail.setVisible(true);
			else
				jlobrigatorioEmail.setVisible(false);
			
			if(logra.isEmpty())
				jlobrigatorioLogra.setVisible(true);
			else
				jlobrigatorioLogra.setVisible(false);
			
			if(bairro.isEmpty())
				jlobrigatorioBairro.setVisible(true);
			else
				jlobrigatorioBairro.setVisible(false);
		 
			if(cep != 8)
				jlobrigatorioCep.setVisible(true);
			else
				jlobrigatorioCep.setVisible(false);
			
			if(codigoMatricula.isEmpty())
				jlobrigatorioCodigoMatricula.setVisible(true);
			else
				jlobrigatorioCodigoMatricula.setVisible(false);				
		 }
		
		private void campusFalse() {
			jlobrigatorio1.setVisible(false);
			jlobrigatorio2.setVisible(false);
			jlobrigatorioData.setVisible(false);
			jlobrigatorioEmail.setVisible(false);
			jlobrigatorioLogra.setVisible(false);
			jlobrigatorioBairro.setVisible(false);
			jlobrigatorioCep.setVisible(false);
			
			txfNome.setText("");
			jFormattedTextCpf.setText("");
			jFormattedTextData.setText("");
			txfEmail.setText("");
			txfLogradouro.setText("");
			txfBairro.setText("");
			jFormattedTextCep.setText("");
			txfNumero.setText("");
			txfComplemento.setText("");
			txfMatricula.setText("");
			jlData.setText("");
			jlData.setVisible(false);
			jlDataMatricula.setVisible(false);

			clickDuplo=false;
			txfNome.requestFocus();
			cursos.setSelectedIndex(0);
		}
	public static void main(String args[]) throws IOException {
		new InterfaceAluno();
	}
}
