package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EmptyStackException;
import java.util.List;

import database.DataBase;
import modelTable.TableTurmaAluno;
import models.Aluno;
import models.AlunoTurmaTable;
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
	
	public String buscarGrade(int idgrade) throws Exception {
		
		String command = "select *from grades where idGrade=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, idgrade);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				return result.getString(2);
			}
			
		} catch (SQLException e) {
			throw new Exception("Erro ao buscar grade");
		}
		finally {
			ps.close();
		}
		return null;
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
	
	public void buscarAlunos (List<AlunoTurmaTable> alunos) throws Exception{

		String command = "select alunos.idAluno, pessoas.nome, cursos.nomeCurso from alunos inner join pessoas on alunos.idPessoa=pessoas.idPessoa inner join cursos on alunos.idCurso=cursos.idCurso";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				
				AlunoTurmaTable aluno = new AlunoTurmaTable();
				
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
	
	public String buscarCurso(int idcurso) throws Exception {
		
		String command = "select *from cursos where idCurso=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, idcurso);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				return result.getString(2);
			}
			
		} catch (SQLException e) {
			throw new Exception("Erro ao buscar curso");
		}
		finally {
			ps.close();
		}
		return null;
	}
	
	public String buscarDisciplina(int iddisciplina) throws Exception {
		
		String command = "select *from disciplinas where idDisciplina=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, iddisciplina);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				return result.getString(3);
			}
			
		} catch (SQLException e) {
			throw new Exception("Erro ao buscar disciplina");
		}
		finally {
			ps.close();
		}
		return null;
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
	
	public void salvar(Turma turma, List<AlunoTurmaTable> alunosAdds)throws Exception {
		
		String command = "insert into turmas (codigoturma,idprofessor,ano,semestre,idcurso,idgrade,iddisciplina) values (?,?,?,?,?,?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, turma.getCodigo());
			ps.setInt(2, turma.getIdProfessor());
			ps.setInt(3, turma.getAno());
			ps.setInt(4, turma.getSemestre());
			ps.setInt(5, turma.getIdCurso());
			ps.setInt(6, turma.getIdGrade());
			ps.setInt(7, turma.getIdDisciplina());
			ps.executeUpdate();

			ResultSet rsKey = ps.getGeneratedKeys();
			int insertKey = 0;

			if(rsKey.next()) {
			    insertKey = rsKey.getInt(1);
			    
				command = "insert into turmas_alunos (idturma, idaluno) values (?,?)";
				try {
					ps = DataBase.retornaConexecao().prepareStatement(command);
					
					for(int i=0; i<alunosAdds.size(); i++) {
						AlunoTurmaTable aluno;
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
	
	public void atualizar(Turma turma) throws Exception {
		String command="update turmas set "
		+ "codigoturma=?,idprofessor=?,ano=?,semestre=?,idcurso=?,idgrade=?,iddisciplina=? where idturma=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, turma.getCodigo());
			ps.setInt(2, turma.getIdProfessor());
			ps.setInt(3, turma.getAno());
			ps.setInt(4,turma.getSemestre());
			ps.setInt(5, turma.getIdCurso());
			ps.setInt(6, turma.getIdGrade());
			ps.setInt(7, turma.getIdDisciplina());
			ps.setInt(8,turma.getIdTurma());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao atualizar turmas");
		}
		finally {
			ps.close();
		}	
	}
	
	public void atualizarTurmaAluno(List<AlunoTurmaTable> alunosAdds, int idTurma)throws Exception {
		try {
			deletarAlunosTurma(idTurma);
			try {
				String command = "insert into turmas_alunos (idturma, idaluno) values (?,?)";
				ps = DataBase.retornaConexecao().prepareStatement(command);
				
				for(int i=0; i<alunosAdds.size(); i++) {
					AlunoTurmaTable aluno;
					aluno = alunosAdds.get(i);
					System.out.println("ID aluno: "+aluno.getIdAluno());
					ps.setInt(1, idTurma);
					ps.setInt(2, aluno.getIdAluno());
					ps.executeUpdate();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Erro ao atualizar turma-alunos");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao atualizar turma-alunos delete");
		}
	}
	
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
	
	public int BuscarIDDisciplinaTurna(int idgrade, int iddisciplina) throws Exception {
		
		String command = "select *from disciplinas_grades where idGrade=? and idDisciplina=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,idgrade);
			ps.setInt(2,iddisciplina);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {			
				int id = result.getInt(1);
				return id;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar ID disciplina-grade " + e.getMessage());
		}
		finally {
			ps.close();
		}
		return 0;
	};
	
	
	
	
public void buscarTurma(List<Turma> turmas) throws Exception{
		

	String command = "select turmas.idturma,turmas.codigoturma,turmas.ano, turmas.semestre, pessoas.nome,"
			+ " grades.nomegrade,turmas.idcurso,turmas.idgrade, turmas.iddisciplina"
			+ " from turmas inner join professores on turmas.idprofessor=professores.idprofessor"
			+ " inner join pessoas on professores.idpessoa=pessoas.idpessoa"
			+ " inner join grades on turmas.idgrade=grades.idgrade"; 
	try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				
				Turma turma = new Turma();
				
				int idTurma = result.getInt(1);
				String codigo = result.getString(2);
				int ano = result.getInt(3);
				int semestre = result.getInt(4);
				String nomeProfessor = result.getString(5);
				String nomeGrade = result.getString(6);
				int idcurso = result.getInt(7);
				int idgrade = result.getInt(8);
				int iddisciplina = result.getInt(9);
				
				turma.setIdTurma(idTurma);
				turma.setCodigo(codigo);
				turma.setAno(ano);
				turma.setSemestre(semestre);
				turma.setNomeProfessor(nomeProfessor);
				turma.setNomeGrade(nomeGrade);
				turma.setIdCurso(idcurso);
				turma.setIdGrade(idgrade);
				turma.setIdDisciplina(iddisciplina);

				turmas.add(turma);
			}
		}
		catch (SQLException e) {
			throw new Exception("Erro ao buscar turmas");
		}
		finally {
			ps.close();
		}
	};
	
	
	public void buscarAlunosAdds(List<AlunoTurmaTable> alunosadds, int idTurma) throws Exception {
		
		String command="select turmas_alunos.idturma_aluno, turmas_alunos.idturma, "
				+ "turmas_alunos.idaluno, pessoas.nome, alunos.idcurso, cursos.nomecurso "
				+ "from turmas_alunos inner join alunos on turmas_alunos.idaluno=alunos.idaluno "
				+ "inner join pessoas on alunos.idpessoa=pessoas.idpessoa inner join cursos on "
				+ "alunos.idcurso=cursos.idcurso where idTurma=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, idTurma);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				AlunoTurmaTable aluno = new AlunoTurmaTable();
				
				int idTurmaAluno = result.getInt(1);
				int idturma = result.getInt(2);
				int idAluno = result.getInt(3);
				String nomeAluno = result.getString(4);
				int idcurso = result.getInt(5);
				String nomeCurso = result.getString(6);
				
				aluno.setIdTurmaAluno(idTurmaAluno);
				aluno.setIdturma(idturma);
				aluno.setNomeAluno(nomeAluno);
				aluno.setNomeCurso(nomeCurso);
				aluno.setIdAluno(idAluno);
				alunosadds.add(aluno);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar disciplinas adicionadas");
		}
	};
	
}