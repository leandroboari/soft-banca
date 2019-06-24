import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;

/**
* Classe que valida com segurança os formulários da interface.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class ValidaFormulario {
	public static DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	* Verifica se um campo de texto é nulo.
	* @param campo
	* @return boolean
	*/

	public static boolean nulo(TextField campo) {
		if (campo.getText() == null || campo.getText().isEmpty())
			return true;
		return false;
	}

	/**
	* Veririca se um campo de data é nulo.
	* @param campo
	* @return boolean
	*/

	public static boolean nulo(DatePicker campo) {
		if (campo.getValue() == null)
			return true;
		return false;
	}

	/**
	* Verifica se um campo tem um número inteiro.
	* @param campo
	* @return boolean
	*/

	public static boolean inteiro(TextField campo) {
		if(!nulo(campo)) {
			try {
				int numero = Integer.parseInt(campo.getText());
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return true;
	}

	/**
	* Verifica se o campo é um número decimal.
	* @param campo
	* @return boolean
	*/

	public static boolean decimal(TextField campo) {
		if(!nulo(campo)) {
			try {
				double numero = Double.parseDouble(campo.getText());
				return true;
			} catch (NumberFormatException nfe) {
				return false;
			}
		}
		return true;
	}

}