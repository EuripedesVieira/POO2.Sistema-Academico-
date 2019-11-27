package models;

public class Turma {

	private int idTurma;
	private int idProfessor;
	private int idGrade;
	private int idCurso;
	private int idDisciplina;
	private int idDisciplinaGrade;
	private String nomeGrade;
	private String nomeProfessor;
	private String codigo;
	private int ano;
	private int semestre;
	
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
	public int getIdGrade() {
		return idGrade;
	}
	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public int getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public int getIdDisciplinaGrade() {
		return idDisciplinaGrade;
	}
	public void setIdDisciplinaGrade(int idDisciplinaGrade) {
		this.idDisciplinaGrade = idDisciplinaGrade;
	}
}
