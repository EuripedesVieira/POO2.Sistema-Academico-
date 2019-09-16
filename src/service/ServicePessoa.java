package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import database.DataBase;
import models.Municipio;
import models.Pessoa;

public class ServicePessoa {

	static PreparedStatement ps;
	
	public void salvar(Pessoa pessoa) {
		
		String command = "insert into pessoas (cpf,nome,sexo,dataNascimento,logradouro,cep,numero,complemento,email,idMunicipio,bairro) values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, pessoa.getCpf());
			ps.setString(2, pessoa.getNome());
			ps.setString(3, pessoa.getSexo());
			ps.setDate(4, pessoa.getDataNascimento());
			ps.setString(5, pessoa.getLogradouro());
			ps.setString(6, pessoa.getBairro());
			ps.setString(7, pessoa.getCep());
			ps.setString(8, pessoa.getNumero());
			ps.setString(9, pessoa.getComplemento());
			ps.setString(10, pessoa.getEmail());
			ps.setInt(11, pessoa.getIdMunicipio());

			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletar(int linha) {
		
		String command = "delete from pessoas where idPessoa=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,linha);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Pessoa pessoa) {
		
		String command = "update pessoas set cpf=?, nome=?, sexo=?, dataNascimento=?, logradouro=?,cep=?, numero=?, complemento=?, email=?, idMunicipio=?, bairro=? where idPessoa=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setString(1, pessoa.getCpf());
			ps.setString(2, pessoa.getNome());
			ps.setString(3, pessoa.getSexo());
			ps.setDate(4,(Date) pessoa.getDataNascimento());
			ps.setString(5, pessoa.getLogradouro());
			ps.setString(6, pessoa.getCep());
			ps.setString(7, pessoa.getNumero());
			ps.setString(8, pessoa.getComplemento());
			ps.setString(9, pessoa.getEmail());
			ps.setInt(10, pessoa.getIdMunicipio());
			ps.setString(11, pessoa.getBairro());
			ps.setInt(12, pessoa.getIdPessoa());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void buscar(List<Pessoa> pessoas) {
		
		String command = "select *from pessoas";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			
			while(result.next()) {
				
				Pessoa pessoa = new Pessoa();
				
				int idPessoa = result.getInt(1);
				String cpf = result.getString(2);
				String nomePessoa = result.getString(3);
				String sexo = result.getString(4);
				String dataNascimento = result.getString(5);
				String logradouro = result.getString(6);
				String cep = result.getString(7);
				String numero = result.getString(9);
				String complemento = result.getString(9);
				String email = result.getString(10);
				int idMunicipio = result.getInt(11);
				String bairro = result.getString(12);

				
				pessoa.setIdPessoa(idPessoa);
				pessoa.setCpf(cpf);
				pessoa.setNome(nomePessoa);
				pessoa.setSexo(sexo);
				pessoa.setDataNascimento(dataNascimento);
				pessoa.setLogradouro(logradouro);
				pessoa.setLogradouro(bairro);
				pessoa.setCep(cep);
				pessoa.setNumero(numero);
				pessoa.setComplemento(complemento);
				pessoa.setEmail(email);
				pessoa.setIdMunicipio(idMunicipio);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarItem(Pessoa pessoa, int id) {
		
		String command = "select *from pessoas";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				
				if(result.getInt(1)==id) {
					int idPessoa = result.getInt(1);
					String cpf = result.getString(2);
					String nomePessoa = result.getString(3);
					String sexo = result.getString(4);
					String dataNascimento = result.getString(5);
					String logradouro = result.getString(6);
					String cep = result.getString(7);
					String numero = result.getString(9);
					String complemento = result.getString(9);
					String email = result.getString(10);
					int idMunicipio = result.getInt(11);
					String bairro = result.getString(12);
					
					pessoa.setIdPessoa(idPessoa);
					pessoa.setCpf(cpf);
					pessoa.setNome(nomePessoa);
					pessoa.setSexo(sexo);
					pessoa.setDataNascimento(dataNascimento);
					pessoa.setLogradouro(logradouro);
					pessoa.setLogradouro(bairro);
					pessoa.setCep(cep);
					pessoa.setNumero(numero);
					pessoa.setComplemento(complemento);
					pessoa.setEmail(email);
					pessoa.setIdMunicipio(idMunicipio);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarMunicipio(List<Municipio> municipios) {
		
		String command ="select *from municipios";
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
		
		}
		 catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public int salvarMunicipio (String municipioID) {
		
		String command ="select *from municipios";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				int idMunicipio = result.getInt(1);
				String nomeMunicipio = result.getString(2);
				if(nomeMunicipio==municipioID)
					return idMunicipio;
			}		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String converteData(String data) {

		String array[] = new String[3];
		array = data.split("/");
		String dia = array[0];
		String mes = array[1];
		String ano = array[2];
		String convertido = ano.concat("-"+mes+"-"+dia);
		
		return convertido;
	}
	
	public Date dataParaSalvar(String dataNascimento) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataConvertida = simpleDateFormat.parse(dataNascimento);
		java.sql.Date dataParaArmazenar = new java.sql.Date(dataConvertida.getTime());
		return dataParaArmazenar;
	}
	
	
	
}