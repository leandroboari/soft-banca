import java.util.*;

public class Interface {

	public static void main(String[] args) {
		OperadorProduto operadorProduto = new OperadorProduto();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			switch(menu()) {
				case 1: operadorProduto.inserir(); break;
				case 2: operadorProduto.listar(); break;
				case 3: operadorProduto.buscar(); break;
				case 4: break;
				case 5: break;
				case 6: break;
				case 7: break;
				case 8: finaliza(); break;
				default: System.out.println("-!- Comando não encontrado.");
			}
		}

	}

	public static int menu() {
		Scanner scanner = new Scanner(System.in);
		String titulo = "\n [ " + Constantes.NOME_SISTEMA + " " + Double.toString(Constantes.VERSAO_SISTEMA) + " ]\n";
		System.out.println(titulo);
		System.out.println("[1] Inserir produto");
		System.out.println("[2] Listar produtos");
		System.out.println("[3] Buscar produto");
		System.out.println("[4] Inserir funcionário");
		System.out.println("[5] Listar funcionários");
		System.out.println("[6] Buscar funcionários");
		System.out.println("[7] Configurar banca");
		System.out.println("[8] Sair");
		int comando = Integer.valueOf(Operador.solicitaString("Por favor, selecione uma operação"));
		return comando;
	}

	public static void finaliza() {
		System.out.println("\n--- Até mais!");
		System.exit(0);
	}

}
