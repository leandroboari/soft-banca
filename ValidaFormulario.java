import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.*;

public class ValidaFormulario {
	public static DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static boolean nulo(TextField campo) {
		if (campo.getText() == null || campo.getText().isEmpty())
			return true;
		return false;
	}

	public static boolean nulo(DatePicker campo) {
		if (campo.getValue() == null)
			return true;
		return false;
	}

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