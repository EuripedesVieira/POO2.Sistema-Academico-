package service;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import database.DataBase;
import models.Aluno;
import models.Curso;
import models.Municipio;
import models.Pessoa;

public class ServiceAluno {

	static PreparedStatement ps;
	
	public void salvar(Pessoa pessoa, Aluno aluno) throws Exception{

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
				command = "insert into alunos (idpessoa,idCurso, matriculaAluno, dataMatriculaAluno) values (?,?,?,?)";
				try {
					ps = DataBase.retornaConexecao().prepareStatement(command);
					ps.setInt(1, insertKey);
					ps.setInt(2, aluno.getIdCurso());
					ps.setString(3, aluno.getMatricula());
					ps.setDate(4, aluno.getDataMatricula());
					ps.executeUpdate();
				}
				catch (SQLException e) {
					e.printStackTrace();
					throw new Exception("Erro ao salvar o aluno");
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar o pessoa");

		}finally{
			ps.close();
		}
	}

	public void deletar(int idPessoa) throws Exception {

		String command = "delete from alunos where idPessoa=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1,idPessoa);
			ps.executeUpdate();
			ps.close();
			
			command = "delete from pessoas where idPessoa=?";
			try {
				ps = DataBase.retornaConexecao().prepareStatement(command);
				ps.setInt(1,idPessoa);
				ps.executeUpdate();
				ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Erro ao deletar pessoa");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao deletar aluno");
		}
	}

	public void atualizar(Pessoa pessoa, Aluno aluno) throws Exception {

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
				command = "update alunos set idCurso=?, matriculaaluno=? where idaluno=?";
				ps = DataBase.retornaConexecao().prepareStatement(command);
				ps.setInt(1, aluno.getIdCurso());
				ps.setString(2, aluno.getMatricula());
				ps.setInt(3, aluno.getIdAluno());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Erro ao atualizar dados do aluno");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao atualizar dados pessoais");
		}
	}
	
	public void buscarTabela(List<Aluno> alunos) throws Exception {

		String command = "select alunos.idAluno, alunos.idPessoa, pessoas.nome, pessoas.cpf, alunos.matriculaaluno, alunos.datamatriculaaluno, alunos.idCurso, cursos.nomeCurso from alunos inner join pessoas on alunos.idPessoa=pessoas.idPessoa inner join cursos on alunos.idCurso=cursos.idCurso order by idAluno";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();

			while(result.next()) {
			
				Aluno aluno = new Aluno();
				
				int idAluno = result.getInt(1);
				int idPessoa = result.getInt(2);
				String nomeAluno = result.getString(3);
				String cpfAluno = result.getString(4);
				String matricula = result.getString(5);
				Date dataMatricula = result.getDate(6);
				int idCurso = result.getInt(7);
				String nomeCurso = result.getString(8);
				
				aluno.setIdAluno(idAluno);
				aluno.setIdPessoa(idPessoa);
				aluno.setNomeAluno(nomeAluno);
				aluno.setCpfAluno(cpfAluno);
				aluno.setMatricula(matricula);
				aluno.setDataMatricula(dataMatricula);
				aluno.setIdCurso(idCurso);
				aluno.setNomeCurso(nomeCurso);
				
				alunos.add(aluno);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados");
		}
	};
/*
	public void buscarPessoa(Pessoa pessoa, int id)throws Exception{

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
			throw new Exception("Erro ao buscar dados pessoas");
		}
	}
	
	*/
	
	public void buscarPessoa(Pessoa pessoa, int id)throws Exception{

		String command = "select *from pessoas where idPessoa=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			while(result.next()) {
					int idPessoa = result.getInt(1);
					String cpf = result.getString(2);
					String nomePessoa = result.getString(3);
					String sexo = result.getString(4);
					Date dataNascimento = result.getDate(5);
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
					pessoa.setBairro(bairro);
					pessoa.setCep(cep);
					pessoa.setNumero(numero);
					pessoa.setComplemento(complemento);
					pessoa.setEmail(email);
					pessoa.setIdMunicipio(idMunicipio);
				}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados pessoas");
		}
	}
	
	
	public void buscarAluno(Aluno aluno, int id) throws Exception{
		
		String commnad= "select *from alunos where idaluno=?";
		try {
			ps= DataBase.retornaConexecao().prepareStatement(commnad);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				int idAluno = result.getInt(1);
				int idPessoa = result.getInt(2);
				int idCurso = result.getInt(3);
				String matricula = result.getString(4);
				Date date = result.getDate(5);
				
				aluno.setIdAluno(idAluno);
				aluno.setIdPessoa(idPessoa);
				aluno.setIdCurso(idCurso);
				aluno.setMatricula(matricula);
				aluno.setDataMatricula(date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados de aluno");
		}
		
		
	};

	public void buscarMunicipio(List<Municipio> municipios) throws Exception {

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
			throw new Exception("Erro a buscar muncípios");
		}
	}
	
	public void buscarCurso(List<Curso> cursos) throws Exception{
		
		String command = "select *from cursos";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();
			
			while(result.next()){
				Curso curso = new Curso();
				int idCurso;
				String nomeCurso;
				idCurso = result.getInt(1);
				nomeCurso = result.getString(2);
				
				curso.setIdCurso(idCurso);
				curso.setNome(nomeCurso);
				cursos.add(curso);
			};
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados de cursos");
		}		
	};

	public int buscarIdMunicipio (String municipioID) throws Exception {
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
			throw new Exception("Erro ao buscar dados de municípios");
		}
		return 0;
	}
	
	public String buscarItemMunicipio(int idMunicipio) throws Exception {

		String command ="select *from municipios where idMunicipio=?";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ps.setInt(1, idMunicipio);
			ResultSet result = ps.executeQuery();

			while(result.next()) {
				String nomeMunicipio = result.getString(2);
				return nomeMunicipio;
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados de municípios");
		}
		return null;
	}
	
	public int buscarIdCurso (String cursoID) throws Exception {
		String command ="select *from cursos";
		try {
			ps = DataBase.retornaConexecao().prepareStatement(command);
			ResultSet result = ps.executeQuery();

			while(result.next()) {
				int idCurso = result.getInt(1);
				String nomeCurso = result.getString(2);
				if(nomeCurso.equals(cursoID))
					return idCurso;
			}		
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao buscar dados de cursos");
		}
		return 0;
	}
}
