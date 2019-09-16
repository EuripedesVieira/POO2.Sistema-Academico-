package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DataBase;
import models.Curso;
import models.Disciplina;
import models.Grade;

public class ServiceGrade {

	PreparedStatement ps;
	
	public void salvar(Grade grade) {
		
		String command = "insert into grades (nomeGrade, idCurso) values (?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, grade.getNomeGrade());
			ps.setInt(2, grade.getIdCurso());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletar(int linha) {
		
		String command = "delete from grades where idGrade=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,linha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Grade grade) {
		
		String command = "update grades set nomeGrade=?, idCurso=? where idGrade=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1,grade.getNomeGrade());
			ps.setInt(2,grade.getIdCurso());
			ps.setInt(3,grade.getIdGrade());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscar(List<Grade> grades) {
		
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
		}
	}
	
	public void buscarItem(Grade grade, int id) {
		
		String command = "select *from grades";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				if(result.getInt(1)!=id) {
					continue;
				}
				
				int idGrade = result.getInt(1);
				String nomeGrade = result.getString(2);
				int idCurso = result.getInt(3);
			
				grade.setIdGrade(idGrade);
				grade.setNomeGrade(nomeGrade);
				grade.setIdCurso(idCurso);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarCursos(List<Curso> cursos) {
		
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
			e.printStackTrace();
		}
	}
	
	public void buscarDisciplinas(List<Disciplina> disciplinas) {
		
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
		}
	}
/*
	public static int idCursoParaGrade (String curso) {
		String command ="select *from cursos";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				int idCurso = result.getInt(1);
				String nomeCurso = result.getString(2);
				nomeCurso = nomeCurso.replace(" ","");
				if(nomeCurso==curso)
					return idCurso;
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
*/
	
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
		
		String command ="select *from cursos";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				int idCurso = result.getInt(1);
				String nomeCurso = result.getString(2);
				
				if(idCurso!=curso)
					continue;
				
				return nomeCurso;
			}		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

