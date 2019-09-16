package modelTable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Municipio;

public class TableMunicipio  extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Municipio> municipios = new ArrayList<Municipio>();
	String[] cabecalho = {"id","Nome","UF"};
	
	public TableMunicipio (List<Municipio> municipios) {
		this.municipios = municipios;
	}
	
	public int getColumnCount() {
		return cabecalho.length;
	}
	
	public String getColumnName(int column) {
		return cabecalho[column];
	}
	
	public int getRowCount() {
		return municipios.size();
	}

	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
			case 0: return municipios.get(linha).getIdMunicipio();
			case 1: return municipios.get(linha).getNomeMunicipio();
			case 2: return municipios.get(linha).getUf();
			default: return null;
		}
	}

}

