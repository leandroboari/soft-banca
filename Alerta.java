import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.*;
import javafx.event.*;
import java.util.*;

public class Alerta {

	public static void erro(String texto) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");
		alert.setHeaderText("Ops! Um erro ocorreu.");
		alert.setContentText(texto);
		alert.showAndWait();
	}

	public static void listaErros(ArrayList<String> erros) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Atenção");

		String headerText = "";
		if(erros.size() == 1)
			headerText = "Ops! Ocorreu o seguinte erro:";
		if(erros.size() > 1)
			headerText = "Ops! Ocorreram os seguintes erros:";

		alert.setHeaderText(headerText);

		String contentText = "";
		for(String erro: erros) {
			contentText += " - " + erro + "\n";
		}

		alert.setContentText(contentText);
		alert.showAndWait();
	}

	public static Boolean confirmacao(String texto) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Por gentileza, confirme para prosseguir.");
		alert.setContentText(texto);
		ButtonType sim = new ButtonType("Confirmar", ButtonData.OK_DONE);
		ButtonType nao = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(nao, sim);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == sim))
			return true;
		return false;
	}

}