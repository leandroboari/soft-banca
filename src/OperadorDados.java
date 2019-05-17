import java.util.*;

public class OperadorDados {

	public static void inserir(ArrayList arrayList, Object object) {
		arrayList.add(object);
	}

	public static void editar(ArrayList<Produto> lista, int id, Produto novo) {
		int index = 0;
		for(Produto item : lista) {
			if(item.id == id) {
				lista.set(index, novo);
				return;
			}
			index++;
		}
	}

	public static void deletar(ArrayList<Produto> lista, int id) {
		int index = 0;
		for(Produto item : lista) {
			if(item.id == id) {
				lista.remove(index);
				System.out.println("foi");
				return;
			}
			index++;
		}
	}

}

