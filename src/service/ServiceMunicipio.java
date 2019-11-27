package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import database.DataBase;
import models.Municipio;

public class ServiceMunicipio {

	static PreparedStatement ps;

	public void salvar(Municipio municipio) throws Exception {
		
		String command = "insert into municipios (nomeMunicipio, uf) values (?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, municipio.getNomeMunicipio());
			ps.setString(2, municipio.getUf());
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
	
	public void deletar(int linha) throws Exception{
		
		String command = "delete from municipios where idMunicipio=?";
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
	
	public void atualizar(Municipio municipio) throws Exception{
		
		String command = "update municipios set nomeMunicipio=?, uf=? where idMunicipio=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1,municipio.getNomeMunicipio());
			ps.setString(2,municipio.getUf());
			ps.setInt(3,municipio.getIdMunicipio());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao atualizar");
		}
		finally {
			ps.close();
		}
	}
	
	
	public void buscar(List<Municipio> municipios) throws Exception{
		
		String command = "select *from municipios";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Municipio municipio = new Municipio();
				int idMunicipio = result.getInt(1);
				String nomeMunicipio = result.getString(2);
				String ufMunicipio = result.getString(3);
		
				municipio.setIdMunicipio(idMunicipio);
				municipio.setNomeMunicipio(nomeMunicipio);
				municipio.setUf(ufMunicipio);
				municipios.add(municipio);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados");
		}
	}
}
