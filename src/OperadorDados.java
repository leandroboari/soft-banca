import java.util.*;

public class OperadorDados {

	// 1. Métodos

	// 1.1. Inserir
	public static void inserir(ArrayList arrayList, Object object) {
		arrayList.add(object);
	}

	// 1.2. Editar
	public static void editar(ArrayList<Produto> lista, int id, Produto novo) {
		int posicao = 0;
		for(Produto item : lista) {
			if(item.id == id) {
				lista.set(posicao, novo);
				return;
			}
			posicao++;
		}
		System.out.println("O produto com a ID informada não existe.");
	}

	// 1.3. Deletar
	public static void deletar(ArrayList<Produto> lista, int id) {
		int posicao = 0;
		for(Produto item : lista) {
			if(item.id == id) {
				lista.remove(posicao);
				return;
			}
			posicao++;
		}
		System.out.println("O produto com a ID informada não existe.");
	}

	// 1.3. Imprimir
	public static void imprimir(ArrayList<Produto> lista, int id) {
		int posicao = 0;
		for(Produto item : lista) {
			if(item.id == id) {
				System.out.println("Título: " + item.titulo + " - Preço: " + item.preco + ".");
				return;
			}
			posicao++;
		}
		System.out.println("O produto com a ID informada não existe.");
	}

}

