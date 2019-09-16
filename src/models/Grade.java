package models;

public class Grade {
	private int idGrade;
	private int idCurso;
	private String nomeGrade;
	private String nomeCurso;

	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	public int getIdGrade() {
		return idGrade;
	}
	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}
	public String getNomeGrade() {
		return nomeGrade;
	}
	public void setNomeGrade(String nomeGrade) {
		this.nomeGrade = nomeGrade;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	
	
}
