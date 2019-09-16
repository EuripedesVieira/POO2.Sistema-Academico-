package models;

public class Turma {

	private int idTurma;
	private int idProfessor;
	private int idDisciplina_grade;
	private String codigo;
	private String ano;
	private String semestre;
	
	public int getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	public int getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}
	public int getIdDisciplina_grade() {
		return idDisciplina_grade;
	}
	public void setIdDisciplina_grade(int idDisciplina_grade) {
		this.idDisciplina_grade = idDisciplina_grade;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	
	
}
