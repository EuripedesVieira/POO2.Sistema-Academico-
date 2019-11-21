package models;

public class DisciplinaGrade {

	private int idDisciplinaGrade;
	private int idGrade;
	private int idDisciplina;
	private String nomeGrade;
	private String nomeDisciplina;
	
	public int getIdDisciplinaGrade() {
		return idDisciplinaGrade;
	}
	public void setIdDisciplinaGrade(int idDisciplinaGrade) {
		this.idDisciplinaGrade = idDisciplinaGrade;
	}
	public int getIdGrade() {
		return idGrade;
	}
	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}
	public int getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public String getNomeGrade() {
		return nomeGrade;
	}
	public void setNomeGrade(String nomeGrade) {
		this.nomeGrade = nomeGrade;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
}
