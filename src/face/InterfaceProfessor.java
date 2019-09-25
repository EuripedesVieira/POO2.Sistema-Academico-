package face;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

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
	
	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;
	private JLabel jlobrigatorioData;
	private JLabel jlobrigatorioEmail;
	private JLabel jlobrigatorioLogra;
	private JLabel jlobrigatorioBairro;
	private JLabel jlobrigatorioCep;
	
	private String campo = "Campo obrigatório";
	
	private MaskFormatter mascaraCpf = null;
	private  MaskFormatter mascaraData= null;
	private MaskFormatter mascaraCep = null;
	

	private JFormattedTextField jFormattedTextCpf;
	private JFormattedTextField jFormattedTextData;
	private JFormattedTextField jFormattedTextCep;
	private JFormattedTextField jFormattedTextDataMatricula;

	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
		
	private JComboBox<String> jcSexo;
	private JComboBox<String> municipios;
	
	private static final long serialVersionUID = 1L;

	public InterfaceProfessor() throws IOException {
		iniciaInterface();
		
	}
	
	private void iniciaInterface() throws IOException{
		defineJP();
		defineMask();
		containerPessoas();
		containerProfessores();
		defineLb();
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
		containerDadosPessoais.setBounds(30, 10, 1245, 260);
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
		txfLogradouro.setBounds(480, 130, 200, 30);
		containerDadosPessoais.add(txfLogradouro);		

		txfBairro = new JTextField();
		txfBairro.setBounds(700, 130, 150, 30);
		containerDadosPessoais.add(txfBairro);	
		
	    jFormattedTextCep = new JFormattedTextField(mascaraCep);
		jFormattedTextCep.setBounds(870, 130, 150, 30);
		containerDadosPessoais.add(jFormattedTextCep);
		
		txfNumero = new JTextField();
		txfNumero.setBounds(1040, 130, 100, 30);
		containerDadosPessoais.add(txfNumero);
		
		txfComplemento = new JTextField();
		txfComplemento.setBounds(30, 210, 430, 30);
		containerDadosPessoais.add(txfComplemento);
		
		jcSexo = new JComboBox<String>();
		jcSexo.addItem("Feminino");
		jcSexo.addItem("Masculino");
		jcSexo.setBounds(520, 50, 150, 30);
		containerDadosPessoais.add(jcSexo);

		municipios = new JComboBox<String>();
		municipios.setBounds(480, 210, 200, 30);
		containerDadosPessoais.add(municipios);
	}
	
	void containerProfessores() {
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Dados professor");
		title.setTitleColor(Color.black);
	
		containerDadosProfessores = new JPanel();
		containerDadosProfessores.setBounds(30, 280, 1245, 130);
		containerDadosProfessores.setBorder(title);
		containerPrincipal.add(containerDadosProfessores);
		containerDadosProfessores.setLayout(null);
		containerDadosProfessores.setVisible(true);
		
		txfMatricula = new JTextField();
		txfMatricula.setBounds(30, 50, 300, 30);
		containerDadosProfessores.add(txfMatricula);
		
	    jFormattedTextDataMatricula = new JFormattedTextField(mascaraData);
		jFormattedTextDataMatricula.setBounds(350, 50, 150, 30);
		containerDadosProfessores.add(jFormattedTextDataMatricula);
		
		txfFormacao = new JTextField();
		txfFormacao .setBounds(520, 50, 300, 30);
		containerDadosProfessores.add(txfFormacao);
		
		jlMatricula = new JLabel("Codígo da matricula");
		jlMatricula.setBounds(30, 30, 150, 20);
		containerDadosProfessores.add(jlMatricula);
		
		jlDataMatricula = new JLabel("Data");
		jlDataMatricula.setBounds(350, 30, 100, 20);
		containerDadosProfessores.add(jlDataMatricula);
		
		jlFormacao = new JLabel("Formação");
		jlFormacao.setBounds(520, 30, 100, 20);
		containerDadosProfessores.add(jlFormacao);
		
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
		jlEmail.setBounds(30, 110, 150, 20);
		containerDadosPessoais.add(jlEmail);

		jlLogradouro = new JLabel("Longradouro");
		jlLogradouro.setBounds(480, 110, 150, 20);
		containerDadosPessoais.add(jlLogradouro);

		jlBairro = new JLabel("Bairro");
		jlBairro.setBounds(700, 110, 100, 20);
		containerDadosPessoais.add(jlBairro);
		
		jlCep = new JLabel("Cep");
		jlCep.setBounds(870, 110, 100, 20);
		containerDadosPessoais.add(jlCep);
		
		jlNumero = new JLabel("Numero");
		jlNumero.setBounds(1040, 110, 100, 20);
		containerDadosPessoais.add(jlNumero);
		
		jlComplemento = new JLabel("Complemento");
		jlComplemento.setBounds(30, 190, 100, 20);
		containerDadosPessoais.add(jlComplemento);

		jlMunicipio = new JLabel("Municipio");
		jlMunicipio.setBounds(480, 190, 100, 20);
		containerDadosPessoais.add(jlMunicipio);
		
		jlobrigatorio1 = new JLabel(campo);
		jlobrigatorio1.setForeground(Color.red);
		jlobrigatorio1.setBounds(30, 80, 150, 20);
		jlobrigatorio1.setVisible(true);
		containerDadosPessoais.add(jlobrigatorio1);
		
		jlobrigatorio2 = new JLabel(campo);
		jlobrigatorio2.setForeground(Color.red);
		jlobrigatorio2.setBounds(350, 80, 300, 20);
		jlobrigatorio2.setVisible(true);
		containerDadosPessoais.add(jlobrigatorio2);
	
	
		jlobrigatorioData = new JLabel(campo);
		jlobrigatorioData.setForeground(Color.red);
		jlobrigatorioData.setBounds(840, 80, 300, 20);
		jlobrigatorioData.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioData);
		
		jlobrigatorioEmail = new JLabel(campo);
		jlobrigatorioEmail.setForeground(Color.red);
		jlobrigatorioEmail.setBounds(30, 160, 300, 20);
		jlobrigatorioEmail.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioEmail);
		
		jlobrigatorioLogra = new JLabel(campo);
		jlobrigatorioLogra.setForeground(Color.red);
		jlobrigatorioLogra.setBounds(480, 160, 300, 20);
		jlobrigatorioLogra.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioLogra);
		
		jlobrigatorioBairro = new JLabel(campo);
		jlobrigatorioBairro.setForeground(Color.red);
		jlobrigatorioBairro.setBounds(700, 160, 300, 20);
		jlobrigatorioBairro.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioBairro);
		
		jlobrigatorioCep = new JLabel(campo);
		jlobrigatorioCep.setForeground(Color.red);
		jlobrigatorioCep.setBounds(870, 160, 300, 20);
		jlobrigatorioCep.setVisible(false);
		containerDadosPessoais.add(jlobrigatorioCep);
	};
	
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
		jbSalvar.setBounds(30, 350, 100, 20);
		containerPrincipal.add(jbSalvar);

		jbExcluir = new JButton("Excluir");
		jbExcluir.setBounds(140, 350, 100, 20);
		containerPrincipal.add(jbExcluir);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(250, 350, 100, 20);
		containerPrincipal.add(jbCancelar);
	};
	
	
	public static void main(String[] args) throws IOException {
		new InterfaceProfessor();
		
	}
}
