package modelTable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Pessoa;

	public class TablePessoa extends AbstractTableModel{

		private static final long serialVersionUID = 1L;
		private List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String[] cabecalho = {"id","Nome","CPF","D. Nascimento","Logradouro","Bairro","CEP", "Numero", "Complemento","email","Municipio"};
		
		public TablePessoa (List<Pessoa> pessoas) {
			this.pessoas = pessoas;
		}
		
		public int getColumnCount() {
			return cabecalho.length;
		}
		
		public String getColumnName(int column) {
			return cabecalho[column];
		}
		
		public int getRowCount() {
			return pessoas.size();
		}

		public Object getValueAt(int linha, int coluna) {
			switch (coluna) {
				case 0: return pessoas.get(linha).getIdPessoa();
				case 1: return pessoas.get(linha).getNome();
				case 2: return pessoas.get(linha).getCpf();
				case 3: return pessoas.get(linha).getDataNascimento();
				case 4: return pessoas.get(linha).getLogradouro();
				case 5: return pessoas.get(linha).getBairro();
				case 6: return pessoas.get(linha).getCep();
				case 7: return pessoas.get(linha).getNumero();
				case 8: return pessoas.get(linha).getComplemento();
				case 9: return pessoas.get(linha).getEmail();
				case 10: return pessoas.get(linha).getNomeMunicipio();
				default: return null;
			}
		}

	}

