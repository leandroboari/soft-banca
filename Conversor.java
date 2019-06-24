import java.math.RoundingMode;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.util.Locale;

/**
* Classe que converte alguns tipos de dados com segurança.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class Conversor {

	// Formato padrão de data
	public static DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static DateFormat formataDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static DateTimeFormatter formataDataHora2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	/**
	* Método que converte com segurança uma String para Double
	* @param valor String a ser convertida
	* @return Double passado pela String
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
	*/

	public static LocalDate StringParaData(String valor) {
		try {
			return LocalDate.parse(valor, formataData);
		} catch (DateTimeException nfe) {
			Alerta.erro("A conversão do texto para data falhou.");
			return null;
		}
	}

	/**
	* Método que converte com segurança uma String para Data
	* @param valor String a ser convertida
	* @return LocalDate passado pela String
	*/

	public static LocalDate StringParaDataHora(String valor) {
		try {
			return LocalDate.parse(valor, formataDataHora2);
		} catch (DateTimeException nfe) {
			Alerta.erro("A conversão do texto para data falhou.");
			return null;
		}
	}


	/**
	* Método que converte com segurança uma Data para String
	* @param data Data a ser convertida
	* @return String passado pela Data
	*/

	public static String DataParaString(LocalDate data) {
		try {
			return Conversor.formataData.format(data);
		} catch (DateTimeException nfe) {
			Alerta.erro("A conversão de data para texto falhou.");
			return null;
		}
		
	}

	/**
	* Método que converte com segurança uma Data para String contendo Data e Hora
	* @param data Data a ser convertida
	* @return String passado pela Data
	*/

	public static String DataHoraParaString(Date data) {
		try {
			return Conversor.formataDataHora.format(data);
		} catch (DateTimeException nfe) {
			Alerta.erro("A conversão de data para texto falhou.");
			return null;
		}
		
	}

	/**
	* Método que converte com segurança um Double para Valor de Preço com arredondamento
	* @param valor Double a ser convertido
	* @return String no formato de Preço
	*/

	public static String DoubleParaPreco(Double valor, boolean comCifrao) {
		Locale locale  = new Locale("pt", "BR");
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(locale);
		simbolos.setDecimalSeparator(',');
		simbolos.setGroupingSeparator('.');

		// Formato padrão de 2 casas decimais
		DecimalFormat formataPreco = new DecimalFormat("###,##0.00", simbolos);

		try {
			// Aredondamento sempre para cima
			formataPreco.setRoundingMode(RoundingMode.UP);
			String cifrao = "";
			if(comCifrao) cifrao = "R$ ";
			return cifrao + formataPreco.format(valor);

		} catch (NumberFormatException nfe) {
			Alerta.erro("A conversão de decimal para um valor em dinheiro falhou.");
			return "R$ 0,00";
		}
	}
}