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
	
	
	public void salvar(Pessoa pessoa, Professor professor) throws Exception{
		
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
				}
				catch (SQLException e) {
					e.printStackTrace();
					throw new Exception("Erro ao salvar o professor");
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar o professor");
			
		}finally {
			ps.close();
		}
	}
	
	public void deletar(int idProfessor, int idPessoa) throws Exception {

		String command = "delete from professores where idProfessor=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,idProfessor);
			ps.executeUpdate();
			
			try {
				command = "delete from pessoas where idPessoa=?";
				ps = DataBase.retornaConexecao().prepareStatement(command);
				ps.setInt(1,idPessoa);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Erro ao deletar pessoa");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao deletar professor");
		}
		finally {
			ps.close();
		}
	}
	
	public void atualizar(Pessoa pessoa,Professor professor) {
		
		String command = "update pessoas set cpf=?, nome=?, sexo=?, dataNascimento=?, logradouro=?,cep=?, numero=?, complemento=?, email=?, idMunicipio=?, bairro=? where idPessoa=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
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
			ps.setInt(12, pessoa.getIdPessoa());
			ps.executeUpdate();
			
			try {
				command = "update professores set matriculaProfessor=?,dataMatriculaProfessor=?,formação=? where idProfessor=?";
				ps = DataBase.retornaConexecao().prepareStatement(command);
				ps.setString(1, professor.getMatriculaProfessor());
				ps.setDate(2, professor.getDataMatricula());
				ps.setString(3, professor.getFormacao());
				ps.setInt(4, professor.getIdProfessor());
				ps.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public void buscarItem(Professor professor, int id) {
		
		String command = "select *from professores";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {
				
				if(result.getInt(1)==id) {
					int idProfessor = result.getInt(1);
					String matricula = result.getString(2);
					Date dataMatricula = result.getDate(3);
					String formacao= result.getString(4);
					int idPessoa = result.getInt(5);
					
					professor.setIdProfessor(idProfessor);
					professor.setMatriculaProfessor(matricula);
					professor.setDataMatricula(dataMatricula);
					professor.setFormacao(formacao);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int buscarIdPessoa(int idProfe) {
		
		String command = "select *from professores";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
						
			while(result.next()) {

				int idProfessor = result.getInt(1);
				int idPessoa = result.getInt(5);
				
				if(idProfessor!=idProfe)
					continue;
				return idPessoa;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void buscarPessoa(Pessoa pessoa, int id) {
		
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
					Date dataNascimento = result.getDate(5);
					String logradouro = result.getString(6);
					String cep = result.getString(7);
					String numero = result.getString(8);
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
					pessoa.setBairro(bairro);
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
	
	public Date dataParaSalvar(String dataNascimento) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dataConvertida = simpleDateFormat.parse(dataNascimento);
		java.sql.Date dataParaArmazenar = new java.sql.Date(dataConvertida.getTime());
		return dataParaArmazenar;
	}
	
	public String dataParaMostar(Date data) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataParaInterface = simpleDateFormat.format(data); 
		return dataParaInterface;
	}

}
