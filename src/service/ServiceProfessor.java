package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import database.DataBase;
import models.Municipio;
import models.Pessoa;
import models.Professor;

public class ServiceProfessor {
	
	static PreparedStatement ps;
	
	
	public Boolean salvar(Pessoa pessoa, Professor professor) {
		
		String command = "insert into pessoas (cpf,nome,sexo,dataNascimento,logradouro,cep,numero,complemento,email,idMunicipio,bairro) values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, pessoa.getCpf());
			ps.setString(2, pessoa.getNome());
			ps.setString(3, pessoa.getSexo());
			ps.setDate(4, pessoa.getDataNascimento());
			ps.setString(5, pessoa.getLogradouro());
			ps.setString(6, pessoa.getCep());
			ps.setString(7, pessoa.getNumero());
			ps.setString(8, pessoa.getComplemento());
			ps.setString(9, pessoa.getEmail());
			ps.setInt(10, pessoa.getIdMunicipio());
			ps.setString(11, pessoa.getBairro());
			ps.executeUpdate();
			
			ResultSet rsKey = ps.getGeneratedKeys();
			int insertKey = 0;

			if(rsKey.next()) {
			    insertKey = rsKey.getInt(1);
			    
				command = "insert into professores (matriculaProfessor,dataMatriculaProfessor,formação,idPessoa) values (?,?,?,?)";
				try {
					ps = DataBase.retornaConexecao().prepareStatement(command);
					ps.setString(1, professor.getMatriculaProfessor());
					ps.setDate(2, professor.getDataMatricula());
					ps.setString(3, professor.getFormacao());
					ps.setInt(4, insertKey);
					ps.executeUpdate();
					ps.close();
					
					return true;
				}
				catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public void buscarProfessores(List<Professor> professores) {
		String command = " select professores.idProfessor, pessoas.nome, pessoas.cpf, professores.matriculaProfessor, professores.formação from professores inner join pessoas on professores.idPessoa=pessoas.idPessoa";
	
	try {
		ps = DataBase.retornaConexecao().prepareStatement(command);
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			Professor professor = new Professor();
			
			int idProfessor = result.getInt(1);
			String nomeProfessor = result.getString(2);
			String cpfProfessor = result.getString(3);
			String codigoMatricula = result.getString(4);
			String formacao = result.getString(5);
			
			professor.setIdProfessor(idProfessor);
			professor.setNomeProfessor(nomeProfessor);
			professor.setCpfProfessor(cpfProfessor);
			professor.setMatriculaProfessor(codigoMatricula);
			professor.setFormacao(formacao);
			
			professores.add(professor);
		}
	} catch (Exception e) {
		// TODO: handle exception
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
	
	public int salvarIdMunicipio (String municipioID) {
		
		String command ="select *from municipios";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				int idMunicipio = result.getInt(1);
				String nomeMunicipio = result.getString(2);
				if(nomeMunicipio.equals(municipioID))
					return idMunicipio;
			}		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Date dataParaSalvar(String dataNascimento) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataConvertida = simpleDateFormat.parse(dataNascimento);
		java.sql.Date dataParaArmazenar = new java.sql.Date(dataConvertida.getTime());
		return dataParaArmazenar;
	}

}
