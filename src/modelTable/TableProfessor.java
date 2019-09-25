package modelTable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Professor;

public class TableProfessor extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Professor> professores = new ArrayList<Professor>();
	String[] cabecalho = {"id","Nome","CPF","Matricula","Formação"};
	
	public TableProfessor (List<Professor> professores) {
		this.professores = professores;
	}
	
	public int getColumnCount() {
		return cabecalho.length;
	}
	
	public String getColumnName(int column) {
		return cabecalho[column];
	}
	
	public int getRowCount() {
		return professores.size();
	}

	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		
			case 0: return professores.get(linha).getIdProfessor();
			case 1: return professores.get(linha).getNomeProfessor();
			case 2: return professores.get(linha).getCpfProfessor();
			case 3: return professores.get(linha).getMatriculaProfessor();
			case 4: return professores.get(linha).getFormacao();
			default: return null;
		}
	}
}


