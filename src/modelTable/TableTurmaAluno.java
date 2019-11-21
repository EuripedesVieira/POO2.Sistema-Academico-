package modelTable;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Aluno;

public class TableTurmaAluno extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Aluno> alunos = new ArrayList<Aluno>();

	String[] cabecalho = {"Nome","Curso"};
	
	public TableTurmaAluno (List<Aluno> alunos) {
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
