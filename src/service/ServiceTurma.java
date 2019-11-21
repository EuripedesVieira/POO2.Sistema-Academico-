package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EmptyStackException;
import java.util.List;

import database.DataBase;
import models.Aluno;
import models.Curso;
import models.CursoGrade;
import models.Disciplina;
import models.DisciplinaGrade;
import models.Grade;
import models.Professor;
import models.Turma;

public class ServiceTurma {
	
	PreparedStatement ps;
	
	public void buscarGrades(List<Grade> grades) throws Exception {
		
		String command = "select *from grades";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				Grade grade = new Grade();
				int idGrade = result.getInt(1);
				String nomeGrade = result.getString(2);
				int idCurso = result.getInt(3);
			
				grade.setIdGrade(idGrade);
				grade.setNomeGrade(nomeGrade);
				grade.setIdCurso(idCurso);
				grades.add(grade);
			}
			
		} catch (SQLException e) {
			throw new Exception("Erro ao buscar grades");
		}
		finally {
			ps.close();
		}
	}
	
	public void buscarProfessores(List<Professor> professores) throws Exception {
		
		String command = "select professores.idProfessor, pessoas.nome, professores.matriculaProfessor from professores inner join pessoas on  professores.idPessoa=pessoas.idPessoa";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				Professor professor = new Professor();
				int idProfessor = result.getInt(1);
				String nomeProfessor = result.getString(2);
				String matriculaProfessor = result.getString(3);
				
				professor.setIdProfessor(idProfessor);
				professor.setNomeProfessor(nomeProfessor);
				professor.setMatriculaProfessor(matriculaProfessor);
				professores.add(professor);
			}
			
		} catch (SQLException e) {
			throw new Exception("Erro ao buscar professores");
		}
		finally {
			ps.close();
		}
	}
	
	public void buscarAlunos (List<Aluno> alunos) throws Exception{

		String command = "select alunos.idAluno, pessoas.nome, cursos.nomeCurso from alunos inner join pessoas on alunos.idPessoa=pessoas.idPessoa inner join cursos on alunos.idCurso=cursos.idCurso";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Aluno aluno = new Aluno();
				
				int idAluno = result.getInt(1);
				String nomeAluno = result.getString(2);
				String nomeCurso = result.getString(3);
				
				aluno.setIdAluno(idAluno);
				aluno.setNomeAluno(nomeAluno);
				aluno.setNomeCurso(nomeCurso);
				alunos.add(aluno);
			}
			
		} catch (Exception e) {
			throw new Exception("Erro ao buscar alunos");
		}
		finally {
			ps.close();
		}
	
	};
	
	public void buscarCursos(List<Curso> cursos) throws Exception{
		
		String command = "select *from cursos";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
		
			while(result.next()) {
				Curso curso = new Curso();

				int idCurso = result.getInt(1);
				String nomeCurso = result.getString(2);
		
				
				curso.setIdCurso(idCurso);
				curso.setNome(nomeCurso);
				cursos.add(curso);
			}
		}
		catch (SQLException e) {
			throw new Exception("Erro ao buscar cursos");
		}
		finally {
			ps.close();
		}
	}
	
	public void buscarGradeCurso(List<CursoGrade> cursogrades) throws Exception{
		
		String command = "select grades.idgrade, grades.nomegrade, grades.idcurso, cursos.nomecurso from grades inner join cursos on grades.idcurso=cursos.idcurso";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				
				CursoGrade cursograde = new CursoGrade();

				int idGrade = result.getInt(1);
				String nomeGrade = result.getString(2);
				int idCurso = result.getInt(3);
				String nomeCurso = result.getString(4);
		
				cursograde.setIdGrade(idGrade);
				cursograde.setNomeGrade(nomeGrade);
				cursograde.setIdCurso(idCurso);
				cursograde.setNomeCurso(nomeCurso);
				
				cursogrades.add(cursograde);
			}
		}
		catch (SQLException e) {
			throw new Exception("Erro ao buscar cursos");
		}
		finally {
			ps.close();
		}
	};
	
	public void salvar(Turma turma, List<Aluno> alunosAdds)throws Exception {
		
		String command = "insert into turmas (codigoturma,idprofessor,ano,semestre,idgrade) values (?,?,?,?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, turma.getCodigo());
			ps.setInt(2, turma.getIdProfessor());
			ps.setInt(3, turma.getAno());
			ps.setInt(4, turma.getSemestre());
			ps.setInt(5, turma.getIdGrade());

			ps.executeUpdate();

			ResultSet rsKey = ps.getGeneratedKeys();
			int insertKey = 0;

			if(rsKey.next()) {
			    insertKey = rsKey.getInt(1);
			    
				command = "insert into turmas_alunos (idturma, idaluno) values (?,?)";
				try {
					ps = DataBase.retornaConexecao().prepareStatement(command);
					
					for(int i=0; i<alunosAdds.size(); i++) {
						Aluno aluno;
						aluno = alunosAdds.get(i);
						ps.setInt(1, insertKey);
						ps.setInt(2, aluno.getIdAluno());
						ps.executeUpdate();
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
					throw new Exception("Erro ao salvar turma-alunos");
				}
			    
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro a salvar turma");
		}
		finally {
			ps.close();
		}
	};
	
	public void deletar(int idTurma) throws Exception {
		
		String command;
		try {
			deletarAlunosTurma(idTurma);
			try {
				
				command = "delete from turmas where idTurma=?";
				ps = DataBase.retornaConexecao().prepareStatement(command);
				ps.setInt(1,idTurma);
				ps.executeUpdate();	
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Erro ao deletar Turma");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao deletar turma-alunos");
		}
		finally {
			ps.close();
		}
	
	};
	
	public void deletarAlunosTurma(int linha) throws Exception {
		
		String command = "delete from turmas_alunos where idTurma=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,linha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao deletar " + e.getMessage());
		}
		finally {
			ps.close();
		}
	}
	
	public void buscarDisciplinaGrade(List<DisciplinaGrade> disciplinasGrades) throws Exception {
		
		String command = "select disciplinas_grades.id_disciplina_grade, disciplinas_grades.idgrade, "
				+ "grades.nomegrade, disciplinas_grades.iddisciplina, disciplinas.nomedisciplina "
				+ "from disciplinas_grades inner join grades on disciplinas_grades.idgrade=grades.idgrade"
				+ " inner join disciplinas on disciplinas_grades.iddisciplina=disciplinas.iddisciplina";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				DisciplinaGrade disciplinaGrade = new DisciplinaGrade(); 
				
				int idDisciplinaGrade= result.getInt(1);
				int idGrade = result.getInt(2);
				String nomeGrade = result.getString(3);
				int idDisciplina = result.getInt(4);
				String nomeDisciplina= result.getString(5);
			
				disciplinaGrade.setIdDisciplinaGrade(idDisciplinaGrade);
				disciplinaGrade.setIdGrade(idGrade);
				disciplinaGrade.setNomeGrade(nomeGrade);
				disciplinaGrade.setIdDisciplina(idDisciplina);
				disciplinaGrade.setNomeDisciplina(nomeDisciplina);
				
				disciplinasGrades.add(disciplinaGrade);
			}
			
		} catch (SQLException e) {
			throw new Exception("Erro ao buscar Disciplinas");
		}
		finally {
			ps.close();
		}
	}
}