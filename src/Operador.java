import java.util.*;

public class Operador {

	private static Scanner scanner = new Scanner(System.in);

	public static String solicitaString(String pergunta) {
		System.out.print(">>> "+pergunta+": ");
		String resposta = scanner.nextLine();
		return resposta;
	}

	public static boolean verificaSair(String s) {
		if(s.toLowerCase().equals("s")) return true;
		return false;
	}

	public static boolean solicitaRetornar() {
		String resposta = solicitaString("Pressione \"enter\" para retornar");
		return true;
	}

	public static boolean vazio(ArrayList lista) {
		if(lista.size() == 0) return true;
		return false;
	}

}