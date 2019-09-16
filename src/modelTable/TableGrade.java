package modelTable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import models.Grade;

public class TableGrade  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Grade> grades = new ArrayList<Grade>();
	String[] cabecalho = {"id","Nome","Curso"};
	
	public TableGrade (List<Grade> grades) {
		this.grades = grades;
	}
	
	public int getColumnCount() {
		return cabecalho.length;
	}
	
	public String getColumnName(int column) {
		return cabecalho[column];
	}
	
	public int getRowCount() {
		return grades.size();
	}

	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0: return grades.get(linha).getIdGrade();
			case 1: return grades.get(linha).getNomeGrade();
			case 2: return grades.get(linha).getNomeCurso();
			default: return null;
		}
	}

}
