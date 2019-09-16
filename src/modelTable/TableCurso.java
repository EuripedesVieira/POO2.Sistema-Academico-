package modelTable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Curso;

public class TableCurso extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<Curso> cursos = new ArrayList<Curso>();
	String[] cabecalho = {"id", "Nome"};
	
	public TableCurso (List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	@Override
	public int getColumnCount() {
		return cabecalho.length;
	}
	
	public String getColumnName(int column) {
		return cabecalho[column];
	}

	@Override
	public int getRowCount() {
		return cursos.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0: return cursos.get(linha).getIdCurso();
			case 1: return cursos.get(linha).getNome();
			default: return null;
		}
	}
}
