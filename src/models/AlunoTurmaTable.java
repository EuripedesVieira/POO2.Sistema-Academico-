package models;

public class AlunoTurmaTable {
	private int idTurmaAluno;
	private int idturma;
	private int idAluno;
	private String nomeAluno;
	private String nomeCurso;
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public int getIdTurmaAluno() {
		return idTurmaAluno;
	}
	public int getIdturma() {
		return idturma;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setIdTurmaAluno(int idTurmaAluno) {
		this.idTurmaAluno = idTurmaAluno;
	}
	public void setIdturma(int idturma) {
		this.idturma = idturma;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
}
