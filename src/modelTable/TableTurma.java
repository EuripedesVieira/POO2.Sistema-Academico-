package modelTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import models.Turma;

public class TableTurma extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Turma> turmas = new ArrayList<Turma>();

	String[] cabecalho = {"Codigo","Grade","Professor","Ano","Semestre"};
	
	public TableTurma (List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public int getColumnCount() {
		return cabecalho.length;
	}
	
	public String getColumnName(int column) {
		return cabecalho[column];
	}
	
	public int getRowCount() {
		return turmas.size();
	}

	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0: return turmas.get(linha).getCodigo();
			case 1: return turmas.get(linha).getNomeGrade();
			case 2: return turmas.get(linha).getNomeProfessor();
			case 3: return turmas.get(linha).getAno();
			case 4: return turmas.get(linha).getSemestre();
			default: return null;
		}
	}
}

