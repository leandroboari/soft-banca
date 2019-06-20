import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Conversor {

	public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	* Método que converte com segurança uma String para Double
	* @param valor String a ser convertida
	* @return Double passado pela String
	* @author Leandro Boari Naves Silva
	*/
	public static double StringParaDouble(String valor) {
		try {
			return Double.parseDouble(valor.trim());
		} catch (NumberFormatException nfe) {
			Alerta.erro("Campo numérico decimal incorreto.");
			return 0.0;
		}
	}

	/**
	* Método que converte com segurança uma String para Integer
	* @param valor String a ser convertida
	* @return int Passado pela String
	* @author Leandro Boari Naves Silva
	*/
	public static int StringParaInt(String valor) {
		try {
			return Integer.parseInt(valor.trim());
		} catch (NumberFormatException nfe) {
			Alerta.erro("Campo numérico inteiro incorreto.");
			return 0;
		}
	}

	public static LocalDate StringParaData(String valor) {
		return LocalDate.parse(valor, dateFormatter);
	}

	public static String DataParaString(LocalDate valor) {
		return Conversor.dateFormatter.format(valor);
	}

}