package service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FuncoesData {

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
	
	
	public boolean isDate(String data) {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.setLenient(false);
			df.parse(data);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public Date dataAtual() throws ParseException {
		LocalDate dataAtual = LocalDate.now();
		String date =dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Date dataSalvar = dataParaSalvar(date);
		return dataSalvar;
	}

}
