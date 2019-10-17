package modelTable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import models.Aluno;
import models.Professor;

public class TableAluno extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Aluno> alunos = new ArrayList<Aluno>();

	String[] cabecalho = {"id","Nome","CPF","codigo matricula","data matricula", "Curso"};
	
	public TableAluno (List<Aluno> alunos) {
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
		
			case 0: return alunos.get(linha).getIdAluno();
			case 1: return alunos.get(linha).getNomeAluno();
			case 2: return alunos.get(linha).getCpfAluno();
			case 3: return alunos.get(linha).getMatricula();
			case 4: return alunos.get(linha).getDataMatricula();
			case 5: return alunos.get(linha).getNomeCurso();
			default: return null;
		}
	}
}


