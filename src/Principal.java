import java.util.*;

public class Principal {
	public static void main(String[] args) {
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		int n = (int)Math.floor(Math.random()*100000+1);
		System.out.println(n);

		OperadorDados.inserir(produtos, new Produto(1, "Jornal 1", 30.0, 10, "16/05/2019 12:30:40"));
		OperadorDados.inserir(produtos, new Produto(2, "Jornal 2", 30.0, 10, "16/05/2019 12:30:40"));

		System.out.println("Mostra pos 0... ");
		System.out.println(produtos.get(0).titulo);

		System.out.println("Editar... ");
		OperadorDados.editar(produtos, 1, new Produto(1, "Jornal 4", 30.0, 10, "16/05/2019 12:30:40"));
		
		System.out.println("Mostra pos 0... ");
		System.out.println(produtos.get(0).titulo);

		System.out.println("Deletar... ");
		OperadorDados.deletar(produtos, 1);

		System.out.println("Mostra pos 0... ");
		System.out.println(produtos.get(0).titulo);
	}
}
