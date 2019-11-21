package models;

public class CursoGrade {

	private int idGrade;
	private String nomeGrade;
	private int idCurso;
	private String nomeCurso;
	
	public int getIdGrade() {
		return idGrade;
	}
	public String getNomeGrade() {
		return nomeGrade;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	
	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}
	public void setNomeGrade(String nomeGrade) {
		this.nomeGrade = nomeGrade;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
}