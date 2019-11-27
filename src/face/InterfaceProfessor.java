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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import modelTable.TableProfessor;
import models.Municipio;
import models.Pessoa;
import models.Professor;
import service.FuncoesData;
import service.ServiceProfessor;

public class InterfaceProfessor extends JFrame {

	private JPanel containerPrincipal;
	private JPanel containerDadosPessoais;
	private JPanel containerDadosProfessores;
	
	private JTextField txfNome;
	private JTextField txfLogradouro;
	private JTextField txfBairro;
	private JTextField txfNumero;
	private JTextField txfComplemento;
	private JTextField txfEmail;
	private JTextField txfMatricula;
	private JTextField txfFormacao;
	
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
	private JLabel jlFormacao;
	private JLabel jlAno;
	
	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;
	private JLabel jlobrigatorioData;
	private JLabel jlobrigatorioEmail;
	private JLabel jlobrigatorioLogra;
	private JLabel jlobrigatorioBairro;
	private JLabel jlobrigatorioCep;
	private JLabel jlobrigatorioCodigoMatricula;
	private JLabel jlobrigatorioFormacao;
	
	private String campo = "Campo obrigatório";	
	
	private Date dataConvertida;
	private Date dataMatriculaConvertida;
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
	private int idMunucipio;
	private String codigoMatricula;
	private String formacao;
	private Date dataNascimento;
	private int idPessoa;
	private int idProfessor;

	private MaskFormatter mascaraCpf = null;
	private  MaskFormatter mascaraData= null;
	private MaskFormatter mascaraCep = null;

	private JFormattedTextField jFormattedTextCpf;
	private JFormattedTextField jFormattedTextData;
	private JFormattedTextField jFormattedTextCep;
	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
		
	private JComboBox<String> jcSexo;
	private JComboBox<String> municipios;
	
	private List<Professor> listaProfessores = new ArrayList<Professor>();
	private List<Municipio> listaMunucipios = new ArrayList<Municipio>();
	
	private TableProfessor ModelotabelaProfessor;
	private JTable tblProfessor;
	private JScrollPane scrlProfessor;
	
	Pessoa pessoa = new Pessoa();
	Professor professor = new Professor();
	ServiceProfessor professorService = new ServiceProfessor();
	FuncoesData funcoesData = new FuncoesData();
	
	private boolean clickDuplo = false;
	private boolean isDate = false;

	
	private static final long serialVersionUID = 1L;

	public InterfaceProfessor() throws IOException {
		iniciaInterface();
		
	}
	
	private void iniciaInterface() throws IOException{
		
		defineMask();
		defineJP();
		buscarTabela();
		containerPessoas();
		percorreListaMunicipio();
		containerProfessores();
		defineLb();
		defineBt();
		acoesBottons();
		modeloTabelaProfessor();
		setVisible(true);
	}
	
	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Professores");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		this.add(containerPrincipal);
	};

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
	
	void containerProfessores() {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Dados professor");
		title.setTitleColor(Color.black);
	
		containerDadosProfessores = new JPanel();
		containerDadosProfessores.setBounds(30, 230, 1245, 130);
		containerDadosProfessores.setBorder(title);
		containerPrincipal.add(containerDadosProfessores);
		containerDadosProfessores.setLayout(null);
		containerDadosProfessores.setVisible(true);
		
		txfMatricula = new JTextField();
		txfMatricula.setBounds(30, 50, 200, 30);
		containerDadosProfessores.add(txfMatricula);
		
		txfFormacao = new JTextField();
		txfFormacao .setBounds(250, 50, 200, 30);
		containerDadosProfessores.add(txfFormacao);
		
		jlMatricula = new JLabel("Codígo da matricula");
		jlMatricula.setBounds(30, 30, 150, 20);
		containerDadosProfessores.add(jlMatricula);
		
		jlFormacao = new JLabel("Formação");
		jlFormacao.setBounds(250, 30, 100, 20);
		containerDadosProfessores.add(jlFormacao);
		
		jlAno = new JLabel("Data da matrícula");
		jlAno.setBounds(470, 30, 150, 20);
		jlAno.setVisible(false);
		containerDadosProfessores.add(jlAno);
				
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		jlDataMatricula = new JLabel();
		jlDataMatricula.setBounds(470, 50, 150, 30);
		jlDataMatricula.setBorder(border);
		jlDataMatricula.setVisible(false);
		containerDadosProfessores.add(jlDataMatricula);
		
		jlobrigatorioCodigoMatricula = new JLabel(campo);
		jlobrigatorioCodigoMatricula.setForeground(Color.red);
		jlobrigatorioCodigoMatricula.setBounds(30, 80, 150, 20);
		jlobrigatorioCodigoMatricula.setVisible(false);
		containerDadosProfessores.add(jlobrigatorioCodigoMatricula);
		
		jlobrigatorioFormacao= new JLabel(campo);
		jlobrigatorioFormacao.setForeground(Color.red);
		jlobrigatorioFormacao.setBounds(250, 80, 150, 20);
		jlobrigatorioFormacao.setVisible(false);
		containerDadosProfessores.add(jlobrigatorioFormacao);
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
	
	void modeloTabelaProfessor() {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Pessoas");
		title.setTitleColor(Color.black);
		
		ModelotabelaProfessor = new TableProfessor(listaProfessores);
		tblProfessor = new JTable(ModelotabelaProfessor);
		scrlProfessor = new JScrollPane(tblProfessor);
		scrlProfessor.setBorder(title);
		scrlProfessor.setBounds(30, 420, 1245, 200);
		containerPrincipal.add(scrlProfessor);
		tblProfessor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()>=2) {
					int numeroLinha = tblProfessor.getSelectedRow();
					Professor dados = listaProfessores.get(numeroLinha);
					
					idProfessor = dados.getIdProfessor();
					idPessoa = professorService.buscarIdPessoa(idProfessor);
					professorService.buscarPessoa(pessoa, idPessoa);
										
					idPessoa = pessoa.getIdPessoa();
					idMunucipio = pessoa.getIdMunicipio();
					String nomeMunicipio = professorService.buscarNomeMunicipio(idMunucipio);
					cpf = pessoa.getCpf();
					nome = pessoa.getNome();
					sexo = pessoa.getSexo();
					dataNascimento = pessoa.getDataNascimento();
					logradouro = pessoa.getLogradouro();
					bairro =pessoa.getBairro();
					cep = pessoa.getCep();
					numero = pessoa.getNumero();
					complemento = pessoa.getComplemento();
					email = pessoa.getEmail();
					numero = pessoa.getNumero();
					complemento = pessoa.getComplemento();
	
					
					try {
						String dataNascimentoParaMostrar;
						String dataMatriculaParaMostrar;
						dataNascimentoParaMostrar = funcoesData.dataParaMostar(dataNascimento);
						dataMatriculaParaMostrar = funcoesData.dataParaMostar(dataNascimento);
						jFormattedTextData.setText(dataNascimentoParaMostrar);
						jlDataMatricula.setText(dataMatriculaParaMostrar);
						jlDataMatricula.setVisible(true);
						jlAno.setVisible(true);
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
					
					txfFormacao.setText(dados.getFormacao());
					txfMatricula.setText(dados.getMatriculaProfessor());
					clickDuplo=true;
				}
			}
		});
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
				idMunucipio=professorService.salvarIdMunicipio(nomeMunicipio);
				
				codigoMatricula = txfMatricula.getText().trim();
				formacao = txfFormacao.getText().trim();
								
				if(contdata==8) {
					String dataNascimento = jFormattedTextData.getText();

					isDate = funcoesData.isDate(dataNascimento);

					if(isDate==true) {
						try {
							dataConvertida = funcoesData.dataParaSalvar(dataNascimento);
							dataMatriculaConvertida = funcoesData.dataAtual();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Data incorreta");
					}
				}
				
				if(contcpf==11 && !nome.isEmpty() && !sexo.isEmpty() && contdata == 8
				   && !logradouro.isEmpty() && !bairro.isEmpty() && contcep == 8 && !email.isEmpty()
				   && !codigoMatricula.isEmpty() && !formacao.isEmpty()) {
			
					carregaObjetoPessoa();
					CarregaObjetoProfessor();
	
					if(clickDuplo==true) {
						professor.setIdProfessor(idProfessor);
						professorService.atualizar(pessoa,professor);
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
					}	
					else{
						try {
							professorService.salvar(pessoa,professor);
							JOptionPane.showMessageDialog(null, "Salvo com sucesso");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				
					buscarTabela();
					containerPrincipal.add(scrlProfessor);
					campusFalse();
				}
				else {
					campoValidacao(nome,contcpf,contdata,email,logradouro,bairro,contcep,codigoMatricula,formacao);
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
						professorService.deletar(idProfessor,idPessoa);
						JOptionPane.showMessageDialog(null, "Deletado com sucesso");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				campusFalse();
			}
		});
	}
	
	
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
		pessoa.setIdMunicipio(idMunucipio);
	}
	
	void CarregaObjetoProfessor() {
		professor.setMatriculaProfessor(codigoMatricula);
		professor.setDataMatricula(dataMatriculaConvertida);
		professor.setFormacao(formacao);
	}
	
	void percorreListaMunicipio() {
		professorService.buscarMunicipio(listaMunucipios);
		for(int i=0; i<listaMunucipios.size(); i++) {
			Municipio municipio;
			municipio= listaMunucipios.get(i);
			String muni =  municipio.getNomeMunicipio();
			municipios.addItem(muni);
		}
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
	 
	 void campoValidacao(String nome, int cpf, int data, String email, String logra, String bairro, int cep, String codigoMatricula, String formacao) {
			
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
			
			if(formacao.isEmpty())
				jlobrigatorioFormacao.setVisible(true);
			else
				jlobrigatorioFormacao.setVisible(false);
		 }
		
		private void campusFalse() {
			jlobrigatorio1.setVisible(false);
			jlobrigatorio2.setVisible(false);
			jlobrigatorioData.setVisible(false);
			jlobrigatorioEmail.setVisible(false);
			jlobrigatorioLogra.setVisible(false);
			jlobrigatorioBairro.setVisible(false);
			jlobrigatorioCep.setVisible(false);
			jlobrigatorioCodigoMatricula.setVisible(false);
			jlobrigatorioFormacao.setVisible(false);
			
			txfNome.setText("");
			jFormattedTextCpf.setText("");
			jFormattedTextData.setValue(null);
			txfEmail.setText("");
			txfLogradouro.setText("");
			txfBairro.setText("");
			jFormattedTextCep.setText("");
			txfNumero.setText("");
			txfComplemento.setText("");
			txfMatricula.setText("");
			txfFormacao.setText("");
			clickDuplo=false;
			
			jlDataMatricula.setVisible(false);
			jlAno.setVisible(false);
			
			txfNome.requestFocus();
		}

	void buscarTabela(){	
		listaProfessores.clear();
		professorService.buscarProfessores(listaProfessores);
	}

	public static void main(String[] args) throws IOException {
		new InterfaceProfessor();		
	}
}