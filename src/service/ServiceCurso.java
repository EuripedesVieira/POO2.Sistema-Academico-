package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import database.DataBase;
import models.Curso;

public class ServiceCurso {
	
	static PreparedStatement ps;
	public void salvar(Curso curso) throws Exception {
		String command = "insert into cursos (nomecurso) values (?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, curso.getNome());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar");
		}
		finally {
			ps.close();
		}
	}
	
	public void deletar(int linha)throws Exception {
		String command = "delete from cursos where idCurso=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,linha);
			ps.executeUpdate();
			System.out.println("Curso deletado com sucesso");
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao deletar");
		}
		finally{
			ps.close();
		}
	}
	
	public void atualizar(Curso curso) throws Exception {
		String command = "update cursos set nomeCurso=? where idCurso=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1,curso.getNome());
			ps.setInt(2,curso.getIdCurso());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao atualizar");
		}
		finally{
			ps.close();
		}	
	}
	
	public void buscar(List<Curso> cursos) throws Exception {	
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados");
		}
	}
}