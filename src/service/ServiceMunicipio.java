package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import database.DataBase;
import models.Municipio;

public class ServiceMunicipio {

	static PreparedStatement ps;

	public void salvar(Municipio municipio) {
		
		String command = "insert into municipios (nomeMunicipio, uf) values (?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, municipio.getNomeMunicipio());
			ps.setString(2, municipio.getUf());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletar(int linha) {
		
		String command = "delete from municipios where idMunicipio=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,linha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Municipio municipio) {
		
		String command = "update municipios set nomeMunicipio=?, uf=? where idMunicipio=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1,municipio.getNomeMunicipio());
			ps.setString(2,municipio.getUf());
			ps.setInt(3,municipio.getIdMunicipio());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void buscar(List<Municipio> municipios) {
		
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
		}
	}
	
	public void buscarItem(Municipio municipio, int id) {
		
		String command = "select *from municipios";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				
				if(result.getInt(1)==id) {
					int idMunicipio = result.getInt(1);
					String nomeMunicipio = result.getString(2);
					String ufMunicipio = result.getString(3);
				
					municipio.setIdMunicipio(idMunicipio);
					municipio.setNomeMunicipio(nomeMunicipio);
					municipio.setUf(ufMunicipio);
					System.out.println(municipio.getNomeMunicipio());
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
