package modelTable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Disciplina;

public class TableDisciplina  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	String[] cabecalho = {"id","Código","Nome"};
	
	public TableDisciplina (List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public int getColumnCount() {
		return cabecalho.length;
	}
	
	public String getColumnName(int column) {
		return cabecalho[column];
	}
	
	public int getRowCount() {
		return disciplinas.size();
	}

	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0: return disciplinas.get(linha).getIdDisciplina();
			case 1: return disciplinas.get(linha).getCodigoDisciplina();
			case 2: return disciplinas.get(linha).getNomeDisciplina();
			default: return null;
		}
	}

}
