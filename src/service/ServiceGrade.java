package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import database.DataBase;
import models.Curso;
import models.Disciplina;
import models.Grade;

public class ServiceGrade {

	
	
	
	//select disciplinas_grades.idgrade, disciplinas_grades.iddisciplina, disciplinas.codigoDisciplina, disciplinas.nomeDisciplina from disciplinas_grades inner join disciplinas on disciplinas_grades.idDisciplina=disciplinas.iddisciplina;
	//select disciplinas_grades.idgrade, grades.nomegrade,grades.idcurso,disciplinas_grades.iddisciplina, disciplinas.codigoDisciplina, disciplinas.nomeDisciplina from disciplinas_grades inner join disciplinas on disciplinas_grades.idDisciplina=disciplinas.iddisciplina inner join grades on disciplinas_grades.idgrade=grades.idgrade;
	PreparedStatement ps;
	
	public void salvar(Grade grade, List<Disciplina> disciplinasAdds)throws Exception {
		
		String command = "insert into grades (nomeGrade, idCurso) values (?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, grade.getNomeGrade());
			ps.setInt(2, grade.getIdCurso());
			ps.executeUpdate();

			ResultSet rsKey = ps.getGeneratedKeys();
			int insertKey = 0;

			if(rsKey.next()) {
			    insertKey = rsKey.getInt(1);
			    
				command = "insert into disciplinas_grades (idGrade, idDisciplina) values (?,?)";
				try {
					ps = DataBase.retornaConexecao().prepareStatement(command);
					
					for(int i=0; i<disciplinasAdds.size(); i++) {
						Disciplina disciplina; 
						disciplina = disciplinasAdds.get(i);
						ps.setInt(1, insertKey);
						ps.setInt(2, disciplina.getIdDisciplina());
						ps.executeUpdate();

					}
				}
				catch (SQLException e) {
					e.printStackTrace();
					throw new Exception("Erro ao salvar disciplinas-grades");
				}
			    
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro a salvar grades");
		}
		finally {
			ps.close();
		}
	}
	
	public void salvarDisciplinaGrade(int idGrade, int idDisciplina) {
		
		String command = "insert into disciplinas_grades (idGrade, idDisciplina) values (?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, idGrade);
			ps.setInt(2, idDisciplina);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletar(int idGrade) throws Exception {
		
		String command;
		try {
			deletarDisciplinasGrades(idGrade);
			try {
				
				command = "delete from grades where idGrade=?";
				ps = DataBase.retornaConexecao().prepareStatement(command);
				ps.setInt(1,idGrade);
				ps.executeUpdate();	
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Erro ao deletar grade");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao deletar disciplianas grades");
		}
		finally {
			ps.close();
		}
	
	};
	
	public void deletarDisciplinasGrades(int linha) throws Exception {
		
		String command = "delete from disciplinas_grades where idGrade=?";
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
	
	public void atualizar(Grade grade)throws Exception {
		
		String command = "update grades set nomeGrade=?, idCurso=? where idGrade=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1,grade.getNomeGrade());
			ps.setInt(2,grade.getIdCurso());
			ps.setInt(3,grade.getIdGrade());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao atualizar grades");
		}
		finally {
			ps.close();
		}
		
	}
	
	public void buscar(List<Grade> grades) throws Exception {
		
		String command = "select grades.idgrade, grades.nomegrade, grades.idcurso, cursos.nomecurso from grades inner join cursos on cursos.idcurso=grades.idcurso";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			
			while(result.next()) {
				Grade grade = new Grade();
				int idGrade = result.getInt(1);
				String nomeGrade = result.getString(2);
				int idCurso = result.getInt(3);
				String nomeCurso = result.getString(4);
		
				
				grade.setIdGrade(idGrade);
				grade.setNomeGrade(nomeGrade);
				grade.setIdCurso(idCurso);
				grade.setNomeCurso(nomeCurso);
				grades.add(grade);

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar grades");
		}
		finally {
			ps.close();
		}
	}
	
	public void buscarItem(Grade grade, int id) throws Exception {
		
		String command = "select *from grades where idgrade=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
			
				int idGrade = result.getInt(1);
				String nomeGrade = result.getString(2);
				int idCurso = result.getInt(3);
			
				grade.setIdGrade(idGrade);
				grade.setNomeGrade(nomeGrade);
				grade.setIdCurso(idCurso);
			}
			
		} catch (SQLException e) {
			throw new Exception("Erro ao buscar grade selecionada");
		}
		finally {
			ps.close();
		}
	}
	
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
	
	public void buscarDisciplinas(List<Disciplina> disciplinas) throws Exception{
		
		String command = "select *from disciplinas";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			
			while(result.next()) {

				Disciplina disciplina = new Disciplina();
				
				int idDisciplina = result.getInt(1);
				String codigoDisciplina = result.getString(2);
				String nomeDisciplina = result.getString(3);
				
				disciplina.setIdDisciplina(idDisciplina);
				disciplina.setCodigoDisciplina(codigoDisciplina);
				disciplina.setNomeDisciplina(nomeDisciplina);
			
				disciplinas.add(disciplina);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar de disciplinas");
		}
		finally {
			ps.close();
		}
	
	}
	
	public int idCursoParaGrade (String curso) {
		String command ="select *from cursos";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			String cursoReplace = curso.replace(" ","");
			cursoReplace = cursoReplace.trim();
						
			while(result.next()) {
				
				int idCurso = result.getInt(1);
				String nomeCurso = result.getString(2);
				nomeCurso = nomeCurso.replace(" ","");
				nomeCurso = nomeCurso.trim();
								
				if(nomeCurso.equals(cursoReplace))
					return idCurso;
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
	public void isInteger(String text) {
		 int cont = 0;
		 for(int i=0; i<text.length(); i++) {
			 if(Character.isLetter(text.charAt(i))) {
				 cont++;
			 }
		 }
		 System.out.println("Numeros de caracteres "+text+" : "+cont);
	 }
	

	public String nomeCursoParaGrade (int curso) {
		
		String command ="select cursos.nomecurso from cursos where idcurso=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, curso);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				String nomeCurso = result.getString(1);
				return nomeCurso;
			}		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void buscarDisciplinasSelecionadas(List<Disciplina>disciplinasSelecionadas, int idGradeSelecionada) throws Exception{
		String command = "select disciplinas_grades.idgrade, disciplinas_grades.iddisciplina, disciplinas.codigoDisciplina, disciplinas.nomeDisciplina from disciplinas_grades inner join disciplinas on disciplinas_grades.idDisciplina=disciplinas.iddisciplina";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet resutl = ps.executeQuery();
			
			while(resutl.next()) {
				int idGrade = resutl.getInt(1);
				int idDisciplina =  resutl.getInt(2);
				String codigoDisciplina = resutl.getString(3);
				String nomeDisciplina = resutl.getString(4);
				
				if(idGrade!=idGradeSelecionada)
					continue;
				
				Disciplina disciplina = new Disciplina();
				disciplina.setIdDisciplina(idDisciplina);
				disciplina.setCodigoDisciplina(codigoDisciplina);
				disciplina.setNomeDisciplina(nomeDisciplina);
				disciplinasSelecionadas.add(disciplina);	
			}
			
		} catch (Exception e) {
			throw new Exception("Erro ao buscar disciplinas selecionadas");
		}
		finally {
			ps.close();
		}
		
	}
	
	public void atualizarDisciplinaGrade(List<Disciplina> disciplinasAdds, int idGrade)throws Exception {
		
		deletarDisciplinasGrades(idGrade);
		String command = "insert into disciplinas_grades (idGrade, idDisciplina) values (?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			
			for(int i=0; i<disciplinasAdds.size(); i++) {
				Disciplina disciplina; 
				disciplina = disciplinasAdds.get(i);
				ps.setInt(1, idGrade);
				ps.setInt(2, disciplina.getIdDisciplina());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar disciplinas-grades");
		}
		finally {
			ps.close();
		}
		
	}
}

