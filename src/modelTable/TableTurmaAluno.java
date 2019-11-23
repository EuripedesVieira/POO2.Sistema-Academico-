package modelTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Aluno;
import models.AlunoTurmaTable;

public class TableTurmaAluno extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<AlunoTurmaTable> alunos = new ArrayList<AlunoTurmaTable>();

	String[] cabecalho = {"Nome","Curso"};
	
	public TableTurmaAluno (List<AlunoTurmaTable> alunos) {
		this.alunos = alunos;
	}
	
	public int getColumnCount() {
		return cabecalho.length;
	}
	
	public String getColumnName(int column) {
		return cabecalho[column];
	}
	
	public int getRowCount() {
		return alunos.size();
	}

	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		
			case 0: return alunos.get(linha).getNomeAluno();
			case 1: return alunos.get(linha).getNomeCurso();
			default: return null;
		}
	}
}
