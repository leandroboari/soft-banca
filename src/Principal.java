import java.util.*;

public class Principal {

	public static void main(String[] args) {

		ArrayList<Produto> produtos = new ArrayList<Produto>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		// Inserir
		OperadorDados.inserir(produtos, new Produto(1, "Jornal 1", 30.0, 10, "16/05/2019 12:30:40"));
		OperadorDados.inserir(produtos, new Produto(2, "Jornal 2", 30.0, 10, "16/05/2019 12:30:40"));

		// Imprimir produtos de ID 1 e 2
		OperadorDados.imprimir(produtos, 1);
		OperadorDados.imprimir(produtos, 2);

		// Editar ID 1
		OperadorDados.editar(produtos, 1, new Produto(1, "Jornal 4", 30.0, 10, "16/05/2019 12:30:40"));
		
		// Imprimir produtos ID 1
		OperadorDados.imprimir(produtos, 1);

		// Deletar produto ID 1
		OperadorDados.deletar(produtos, 1);

		// Imprimir produto ID 1
		OperadorDados.imprimir(produtos, 1);
	
	}

}
