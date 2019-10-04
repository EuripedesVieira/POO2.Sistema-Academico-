package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import database.DataBase;
import models.Disciplina;

public class ServiceDisciplina {
static PreparedStatement ps;
	
	public void salvar(Disciplina disciplina) throws Exception {
		
		String command = "insert into disciplinas (codigoDisciplina, nomeDisciplina) values (?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, disciplina.getCodigoDisciplina());
			ps.setString(2, disciplina.getNomeDisciplina());
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
	
	public void deletar(int linha) throws Exception {
		
		String command = "delete from disciplinas where idDisciplina=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,linha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao deletar");
		}
		finally {
			ps.close();
		}
	}
	
	public void atualizar(Disciplina disciplina) throws Exception {
		
		String command = "update disciplinas set codigoDisciplina=?, nomeDisciplina=? where idDisciplina=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1,disciplina.getCodigoDisciplina());
			ps.setString(2,disciplina.getNomeDisciplina());
			ps.setInt(3,disciplina.getIdDisciplina());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao atualizar");
		}
		finally {
			ps.close();
		}
	}
	
	
	public void buscar(List<Disciplina> disciplinas) throws Exception {
		
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados");
		}
		finally {
			ps.close();
		}
	}
 }
