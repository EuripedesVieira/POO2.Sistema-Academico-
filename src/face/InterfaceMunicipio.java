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
import modelTable.TableMunicipio;
import models.Municipio;
import service.ServiceMunicipio;

public class InterfaceMunicipio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel containerPrincipal;
	private JTextField txfNome;

	private JLabel jlNome;
	private JLabel jlCodigo;
	private JLabel jlobrigatorio1;
	private JLabel jlobrigatorio2;

	private JComboBox<String> ufs;
	
	private JButton jbSalvar;
	private JButton jbExcluir;
	private JButton jbCancelar;
	
	private List<Municipio> listaMunicipio = new ArrayList<Municipio>();
	private TableMunicipio ModelotabelaMunicipio;
	private JTable tblMunicipio;
	private JScrollPane scrlMunicipio;
	
	private String nomeMunicipio;
	private String uf;

	private String campo = "Campo obrigatório";
	private int numeroLinha;
	private int id;
	
	private boolean clickDuplo = false; 
	
	
	Municipio municipio = new Municipio();
	DataBase database = new DataBase();
	ServiceMunicipio municipioService = new ServiceMunicipio();

	public InterfaceMunicipio() throws IOException {
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
		containerPrincipal.add(scrlMunicipio);
		setVisible(true);
	}
	
	void buscarTabela(){
		listaMunicipio.clear();
		try {
			municipioService.buscar(listaMunicipio);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	
	void modeloTabelaM() {
		ModelotabelaMunicipio = new TableMunicipio(listaMunicipio);
		tblMunicipio = new JTable(ModelotabelaMunicipio);
		scrlMunicipio = new JScrollPane(tblMunicipio);
		scrlMunicipio.setBounds(30, 200, 620, 200);

		TitledBorder title;
		title = BorderFactory.createTitledBorder("Municpios");
		title.setTitleColor(Color.black);
		scrlMunicipio.setBorder(title);
		tblMunicipio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					numeroLinha = tblMunicipio.getSelectedRow();
					Municipio municipio = listaMunicipio.get(numeroLinha);
					
					id= municipio.getIdMunicipio();
					String nomeMunicipio = municipio.getNomeMunicipio();
					String uf = municipio.getUf();
					
					txfNome.setText(nomeMunicipio);
					ufs.setSelectedItem(uf);
					
					clickDuplo = true;
					jlobrigatorio1.setVisible(false);
				}
			}
		});
	}

	
	void defineJP() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Municipios");
		containerPrincipal = new JPanel();
		containerPrincipal.setLayout(null);
		add(containerPrincipal);
	};

	void defineTxf(){
		
		txfNome = new JTextField();
		txfNome.setBounds(30, 50, 300, 30);
		containerPrincipal.add(txfNome);
		
		ufs = new JComboBox<String>();
		ufs.addItem("GO");
		ufs.addItem("AC");
		ufs.addItem("AM");
		ufs.addItem("RR");
		ufs.addItem("PA");
		ufs.addItem("AP");
		ufs.addItem("TO");
		ufs.addItem("MA");
		ufs.addItem("PI");
		ufs.addItem("CE");
		ufs.addItem("RN");
		ufs.addItem("PB");
		ufs.addItem("PE");
		ufs.addItem("AL");
		ufs.addItem("SE");
		ufs.addItem("BA");
		ufs.addItem("MG");
		ufs.addItem("ES");
		ufs.addItem("RJ");
		ufs.addItem("SP");
		ufs.addItem("PR");
		ufs.addItem("SC");
		ufs.addItem("RS");
		ufs.addItem("MS");
		ufs.addItem("MT");
		ufs.addItem("RO");
		ufs.addItem("DF");
		ufs.setBounds(350, 50, 300, 30);
		containerPrincipal.add(ufs);

	};

	void defineLb() {

		jlNome = new JLabel("Nome");
		jlNome.setBounds(30, 30, 150, 20);
		containerPrincipal.add(jlNome);
		
		jlCodigo = new JLabel("UF");
		jlCodigo.setBounds(350, 30, 100, 20);
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
	}
	
	private void campusFalse() {
		jlobrigatorio1.setVisible(false);
		jlobrigatorio2.setVisible(false);
	}
	
	 void campoValidacao(String nomeMunicipio, String uf) {
		
		if(nomeMunicipio.isEmpty())
			jlobrigatorio1.setVisible(true);
		else
			jlobrigatorio1.setVisible(false);
		
		if(uf.isEmpty())
			jlobrigatorio2.setVisible(true);
		else
			jlobrigatorio2.setVisible(false);
	}

	void action() {
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeMunicipio = txfNome.getText().trim();
				uf = (String) ufs.getSelectedItem();

				municipio.setNomeMunicipio(nomeMunicipio);
				municipio.setUf(uf);
				
				if(!nomeMunicipio.isEmpty() && !uf.isEmpty()) {
					if(clickDuplo==true){
						municipio.setIdMunicipio(id);
						try {
							municipioService.atualizar(municipio);
							atualizarPainel();
							JOptionPane.showMessageDialog(null, "Atualizado com sucesso");

						} catch (Exception e1){
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
					else{	
						try {
							municipioService.salvar(municipio);
							JOptionPane.showMessageDialog(null, "Salvo com sucesso");
							atualizarPainel();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
				else {
					 campoValidacao(nomeMunicipio,uf);
				}
			}
		});

		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				campusFalse();
				if(clickDuplo==true) 
					clickDuplo=false;
				ufs.setSelectedIndex(0);
			}
		});

		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clickDuplo==true){
					try {
						municipioService.deletar(id);
						atualizarPainel();
						JOptionPane.showMessageDialog(null, "Deletado com sucesso");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					clickDuplo=false;
				}
				limpaCampos();
				campusFalse();
				txfNome.requestFocus();
			}
		});
	};
	
	void atualizarPainel() {
		buscarTabela();
		containerPrincipal.add(scrlMunicipio);
		clickDuplo=false;
		limpaCampos();
		txfNome.requestFocus();
		campusFalse();
		ufs.setSelectedIndex(0);
	}
	
	public static void main(String[] args) throws IOException{
		new InterfaceMunicipio();		
	};
};
