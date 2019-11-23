package models;

import java.sql.Date;

public class Aluno {
	private int idAluno;
	private int idPessoa;
	private String matricula;
	private Date dataMatricula;
	private String nomeAluno;
	private String cpfAluno;
	private String nomeCurso;
	private int idCurso;
	
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public String getCpfAluno() {
		return cpfAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	
	
	

}
