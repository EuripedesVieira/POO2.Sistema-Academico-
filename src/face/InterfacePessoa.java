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
import database.DataBase;
import modelTable.TablePessoa;
import models.Municipio;
import models.Pessoa;
import service.ServicePessoa;

public class InterfacePessoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	
	private String nome;
	private String cpf;
	private String sexo;
	private Date dataNascimento;
	private Date dataConvertida;
	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String email;
	private String nomeMunicipio;
	private int idMunucipio;
	
	private JTextField txfNome;
	private JTextField txfCpf;
	private JTextField txfSexo;
	private JTextField txfLogradouro;
	private JTextField txfBairro;
	private JTextField txfCep;
	private JTextField txfNumero;
	private JTextField txfComplemento;
	private JTextField txfEmail;

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


	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;
	private JLabel jlobrigatorioData;
	private JLabel jlobrigatorioEmail;
	private JLabel jlobrigatorioLogra;
	private JLabel jlobrigatorioBairro;
	private JLabel jlobrigatorioCep;

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

	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	private List<Municipio> listaMunucipios = new ArrayList<Municipio>();

	private TablePessoa ModelotabelaPessoa;
	private JTable tblPessoa;
	private JScrollPane scrlPessoa;
		
	private String campo = "Campo obrigatório";
	private int numeroLinha;
	private int id;
	
	private boolean click_duplo = false; 
		
	Pessoa pessoa = new Pessoa();
	DataBase database = new DataBase();
	ServicePessoa pessoaService = new ServicePessoa();
	
	public InterfacePessoa() throws IOException {
		InciaInterface();
	}

	private void InciaInterface() throws IOException {

		buscarTabela();
		defineJP();
		defineMask();
		defineTxf();
		defineLb();
		defineBt();
		action();
		modeloTabelaM();
		percorre(listaMunucipios,municipios);
		containerPrincipal.add(scrlPessoa);
		setVisible(true);
	}
	
	void buscarTabela(){
		listaPessoa.clear();
		pessoaService.buscar(listaPessoa);
		pessoaService.buscarMunicipio(listaMunucipios);
	}

	
	void modeloTabelaM() {
		ModelotabelaPessoa = new TablePessoa(listaPessoa);
		tblPessoa = new JTable(ModelotabelaPessoa);
		scrlPessoa = new JScrollPane(tblPessoa);
		scrlPessoa.setBounds(30, 400, 1200, 200);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Pessoas");
		title.setTitleColor(Color.black);
		scrlPessoa.setBorder(title);
		tblPessoa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					numeroLinha = tblPessoa.getSelectedRow();
					Pessoa pessoa = listaPessoa.get(numeroLinha);
					
					id = pessoa.getIdMunicipio();
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
					//String data = dataString(dataNascimento); 
				
					txfNome.setText(nome);
					txfCpf.setText(cpf);
					txfSexo.setText(sexo);
				
					//txfDataNascimento.setText(data);
					txfLogradouro.setText(logradouro);
					txfBairro.setText(bairro);
					txfCep.setText(cep);
					txfNumero.setText(numero);
					txfComplemento.setText(complemento);
					txfEmail.setText(email);
					//municipio
					
					click_duplo = true;
					jlobrigatorio1.setVisible(false);
				}
			}
		});
	}

	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Municipios");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		add(containerPrincipal);
	};

	void defineTxf(){
		
		txfNome = new JTextField();
		txfNome.setBounds(30, 50, 300, 30);
		containerPrincipal.add(txfNome);
		
       jFormattedTextCpf = new JFormattedTextField(mascaraCpf);
       jFormattedTextCpf.setBounds(350, 50, 300, 30);
       containerPrincipal.add(jFormattedTextCpf);
		
		txfCpf = new JTextField();
		txfCpf.setBounds(350, 50, 300, 30);
		//containerPrincipal.add(txfCpf);
		
		//txfDataNascimento = new JTextField();
		//txfDataNascimento.setBounds(840, 50, 300, 30);
		
		//DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    jFormattedTextData = new JFormattedTextField(mascaraData);
	    jFormattedTextData.setBounds(840, 50, 300, 30);
	    containerPrincipal.add(jFormattedTextData);
		
		txfEmail = new JTextField();
		txfEmail.setBounds(30, 130, 430, 30);
		containerPrincipal.add(txfEmail);
		
		txfLogradouro = new JTextField();
		txfLogradouro.setBounds(480, 130, 200, 30);
		containerPrincipal.add(txfLogradouro);		

		txfBairro = new JTextField();
		txfBairro.setBounds(700, 130, 150, 30);
		containerPrincipal.add(txfBairro);		

		txfCep = new JTextField();
	    jFormattedTextCep = new JFormattedTextField(mascaraCep);
		jFormattedTextCep.setBounds(870, 130, 150, 30);
		containerPrincipal.add(jFormattedTextCep);
		
		txfNumero = new JTextField();
		txfNumero.setBounds(1040, 130, 100, 30);
		containerPrincipal.add(txfNumero);
		
		txfComplemento = new JTextField();
		txfComplemento.setBounds(30, 210, 430, 30);
		containerPrincipal.add(txfComplemento);
		
	};

	void defineLb() {

		jlNome = new JLabel("Nome");
		jlNome.setBounds(30, 30, 150, 20);
		containerPrincipal.add(jlNome);
		
		jlCodigo = new JLabel("CPF");
		jlCodigo.setBounds(350, 30, 100, 20);
		containerPrincipal.add(jlCodigo);
		
		jlSexo = new JLabel("Sexo");
		jlSexo.setBounds(670, 30, 100, 20);
		containerPrincipal.add(jlSexo);

		jlDataNascimento = new JLabel("Data Nascimento");
		jlDataNascimento.setBounds(840, 30, 150, 20);
		containerPrincipal.add(jlDataNascimento);
		
		jlEmail = new JLabel("Email");
		jlEmail.setBounds(30, 110, 150, 20);
		containerPrincipal.add(jlEmail);

		jlLogradouro = new JLabel("Longradouro");
		jlLogradouro.setBounds(480, 110, 150, 20);
		containerPrincipal.add(jlLogradouro);

		jlBairro = new JLabel("Bairro");
		jlBairro.setBounds(700, 110, 100, 20);
		containerPrincipal.add(jlBairro);
		
		jlCep = new JLabel("Cep");
		jlCep.setBounds(870, 110, 100, 20);
		containerPrincipal.add(jlCep);
		
		jlNumero = new JLabel("Numero");
		jlNumero.setBounds(1040, 110, 100, 20);
		containerPrincipal.add(jlNumero);
		
		jlComplemento = new JLabel("Complemento");
		jlComplemento.setBounds(30, 190, 100, 20);
		containerPrincipal.add(jlComplemento);

		jlMunicipio = new JLabel("Municipio");
		jlMunicipio.setBounds(480, 190, 100, 20);
		containerPrincipal.add(jlMunicipio);
		
		jlobrigatorio1 = new JLabel(campo);
		jlobrigatorio1.setForeground(Color.red);
		jlobrigatorio1.setBounds(30, 80, 150, 20);
		jlobrigatorio1.setVisible(false);
		containerPrincipal.add(jlobrigatorio1);
		
		jlobrigatorio2 = new JLabel(campo);
		jlobrigatorio2.setForeground(Color.red);
		jlobrigatorio2.setBounds(350, 80, 300, 20);
		jlobrigatorio2.setVisible(false);
		containerPrincipal.add(jlobrigatorio2);
	
	
		jlobrigatorioData = new JLabel(campo);
		jlobrigatorioData.setForeground(Color.red);
		jlobrigatorioData.setBounds(840, 80, 300, 20);
		jlobrigatorioData.setVisible(false);
		containerPrincipal.add(jlobrigatorioData);
		
		jlobrigatorioEmail = new JLabel(campo);
		jlobrigatorioEmail.setForeground(Color.red);
		jlobrigatorioEmail.setBounds(30, 160, 300, 20);
		jlobrigatorioEmail.setVisible(false);
		containerPrincipal.add(jlobrigatorioEmail);
		
		jlobrigatorioLogra = new JLabel(campo);
		jlobrigatorioLogra.setForeground(Color.red);
		jlobrigatorioLogra.setBounds(480, 160, 300, 20);
		jlobrigatorioLogra.setVisible(false);
		containerPrincipal.add(jlobrigatorioLogra);
		
		jlobrigatorioBairro = new JLabel(campo);
		jlobrigatorioBairro.setForeground(Color.red);
		jlobrigatorioBairro.setBounds(700, 160, 300, 20);
		jlobrigatorioBairro.setVisible(false);
		containerPrincipal.add(jlobrigatorioBairro);
		
		jlobrigatorioCep = new JLabel(campo);
		jlobrigatorioCep.setForeground(Color.red);
		jlobrigatorioCep.setBounds(870, 160, 300, 20);
		jlobrigatorioCep.setVisible(false);
		containerPrincipal.add(jlobrigatorioCep);
	};
	

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
		
		jcSexo = new JComboBox<String>();
		jcSexo.addItem("Feminino");
		jcSexo.addItem("Masculino");
		jcSexo.setBounds(670, 50, 150, 30);
		containerPrincipal.add(jcSexo);

		municipios = new JComboBox<String>();
		municipios.setBounds(480, 210, 200, 30);
		containerPrincipal.add(municipios);
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
	
	
	void percorre(List<Municipio> lista, JComboBox<String> municipios) {
		for(int i=0; i<lista.size(); i++) {
			Municipio municipio;
			municipio= lista.get(i);
			String muni =  municipio.getNomeMunicipio();
			municipios.addItem(muni);
		}
	}
	
	void salvarMunicipio(String municipio){
		
	}
	
	
	private void limpaCampos() {
		txfNome.setText("");
		jFormattedTextCpf.setText("");
		jFormattedTextData.setText("");
		txfEmail.setText("");
		txfLogradouro.setText("");
		txfBairro.setText("");
		jFormattedTextCep.setText("");
		txfNumero.setText("");
		txfComplemento.setText("");
		
		
		
	}
	
	private void campusFalse() {
		jlobrigatorio1.setVisible(false);
		jlobrigatorio2.setVisible(false);
		jlobrigatorioData.setVisible(false);
		jlobrigatorioEmail.setVisible(false);
		jlobrigatorioLogra.setVisible(false);
		jlobrigatorioBairro.setVisible(false);
		jlobrigatorioCep.setVisible(false);
		
	}
	
	 void campoValidacao(String nome, int cpf, int data, String email, String logra, String bairro, int cep) {
		
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

	 
	 
	void action() {
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contdata = isInteger(jFormattedTextData.getText());
				 int contcpf = isInteger(jFormattedTextCpf.getText());
				 int contcep = isInteger(jFormattedTextCep.getText());
				
				nome = txfNome.getText();
				cpf = jFormattedTextCpf.getText();
				sexo = (String) jcSexo.getSelectedItem();
				String data = jFormattedTextData.getText();
				email = txfEmail.getText();
				logradouro = txfLogradouro.getText();
				bairro = txfBairro.getText();
				cep = jFormattedTextCep.getText();
				numero = txfNumero.getText();
				complemento = txfComplemento.getText();
				nomeMunicipio = (String) municipios.getSelectedItem();
				idMunucipio=pessoaService.salvarMunicipio(nomeMunicipio);
				System.out.println(idMunucipio);
				try {
					dataConvertida = pessoaService.dataParaSalvar(data);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			 

			

				if(click_duplo==true) {
					if(contcpf==11 && !nome.isEmpty() && !sexo.isEmpty() && contdata == 8
					   && !logradouro.isEmpty() && !bairro.isEmpty() && contcep == 8 && !email.isEmpty()){
						
						pessoa.setCpf(cpf);
						pessoa.setNome(nome);
						pessoa.setSexo(sexo);
						pessoa.setDataNascimento(dataConvertida);
						pessoa.setLogradouro(logradouro);
						pessoa.setLogradouro(bairro);
						pessoa.setCep(cep);
						pessoa.setNumero(numero);
						pessoa.setComplemento(complemento);
						pessoa.setEmail(email); 
						pessoa.setIdMunicipio(idMunucipio);
						
						pessoaService.atualizar(pessoa);
						buscarTabela();
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
						limpaCampos();
						containerPrincipal.add(scrlPessoa);
						campusFalse();
						txfNome.requestFocus();
						click_duplo=false;
					}
					else {
						campoValidacao(nome,contcpf,contdata,email,logradouro,bairro,contcep);
					}
				}
				
				else{	
					
				
					if(contcpf == 11 && !nome.isEmpty() && !sexo.isEmpty() && contdata == 8
					&& !logradouro.isEmpty() && !bairro.isEmpty() && contcep == 8 && !email.isEmpty()){
					
						pessoa.setCpf(cpf);
						pessoa.setNome(nome);
						pessoa.setSexo(sexo);
						pessoa.setDataNascimento(dataConvertida);
						pessoa.setLogradouro(logradouro);
						pessoa.setLogradouro(bairro);
						pessoa.setCep(cep);
						pessoa.setNumero(numero);
						pessoa.setComplemento(complemento);
						pessoa.setEmail(email);
						pessoa.setIdMunicipio(idMunucipio);
						
					//	pessoaService.salvar(pessoa);
						buscarTabela();
						JOptionPane.showMessageDialog(null, "Salvo com sucesso");
						limpaCampos();
						containerPrincipal.add(scrlPessoa);
						campusFalse();
						click_duplo=false;
						txfNome.requestFocus();
						
					}
					else {
						campoValidacao(nome,contcpf,contdata,email,logradouro,bairro,contcep);						
					}
				}
			}
		});

		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				campusFalse();
				if(click_duplo==true) 
					click_duplo=false;
			}
		});
		
		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(click_duplo==true) {
					pessoaService.deletar(id);
					buscarTabela();
					JOptionPane.showMessageDialog(null, "Deletado com sucesso");
					containerPrincipal.add(scrlPessoa);
					click_duplo=false;
					limpaCampos();
					txfCpf.requestFocus();
					campusFalse();
				}
				limpaCampos();
				txfCpf.requestFocus();
			}
		});
	};

	public static void main(String[] args) throws IOException{
		new InterfacePessoa();		
	};
};
