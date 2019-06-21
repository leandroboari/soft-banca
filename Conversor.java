import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
			Alerta.erro("A conversão do texto em um valor decimal falhou.");
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
			Alerta.erro("A conversão do texto em um valor inteiro falhou.");
			return 0;
		}
	}

	/**
	* Método que converte com segurança uma String para Data
	* @param valor String a ser convertida
	* @return LocalDate passado pela String
	* @author Leandro Boari Naves Silva
	*/

	public static LocalDate StringParaData(String valor) {
		try {
			return LocalDate.parse(valor, dateFormatter);
		} catch (DateTimeException nfe) {
			Alerta.erro("A conversão do texto para data falhou.");
			return null;
		}
	}

	/**
	* Método que converte com segurança uma Data para String
	* @param valor Data a ser convertida
	* @return String passado pela Data
	* @author Leandro Boari Naves Silva
	*/

	public static String DataParaString(LocalDate valor) {
		try {
			return Conversor.dateFormatter.format(valor);
		} catch (DateTimeException nfe) {
			Alerta.erro("A conversão de data para texto falhou.");
			return null;
		}
		
	}

	/**
	* Método que converte com segurança um Double para Valor de Preço com arredondamento
	* @param valor Double a ser convertido
	* @return String no formato de Preço
	* @author Leandro Boari Naves Silva
	*/

	public static String DoubleParaPreco(Double valor) {
		try {

			// Formato para 2 casas decimais
			private static DecimalFormat df = new DecimalFormat("#.##");

			// Aredondamento sempre para cima
			df.setRoundingMode(RoundingMode.UP);

			return "R$ " + String.valueOf(df.format(valor));

		} catch (NumberFormatException nfe) {
			Alerta.erro("A conversão de decimal para um valor em dinheiro falhou.");
			return 0;
		}
	}

}