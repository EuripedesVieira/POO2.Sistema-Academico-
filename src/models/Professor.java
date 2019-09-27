package models;

import java.sql.Date;

public class Professor {

	private int idProfessor;
	private String matriculaProfessor;
	private String formacao;
	private String nomeProfessor;
	private String cpfProfessor;
	private Date dataMatricula;
	
	public int getIdProfessor() {
		return idProfessor;
	}
	public void setIdProfessor(int idProfessor) {
		this.idProfessor = idProfessor;
	}
	public String getMatriculaProfessor() {
		return matriculaProfessor;
	}
	public void setMatriculaProfessor(String matriculaProfessor) {
		this.matriculaProfessor = matriculaProfessor;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public String getCpfProfessor() {
		return cpfProfessor;
	}
	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
}
	


