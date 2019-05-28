import java.util.*;

public class OperadorProduto {

	private ArrayList<Produto> lista = new ArrayList<Produto>();

	public void inserir() {
		System.out.println("\n--- Inserir produto (\"s\" para sair)");
		
		int id = lista.size() + 1;
		
		String titulo = Operador.solicitaString("Título");
		if(Operador.verificaSair(titulo)) return;
		
		String preco = Operador.solicitaString("Preço");
		if(Operador.verificaSair(preco)) return;

		String qtdEstoque = Operador.solicitaString("Quantidade em estoque");
		if(Operador.verificaSair(qtdEstoque)) return;

		String data = "hoje";

		lista.add(new Produto(id, titulo, Double.valueOf(preco), Integer.valueOf(qtdEstoque), data));
	
		if(Operador.solicitaRetornar()) return;
	}

	public void listar() {
		System.out.println("\n--- Listar produtos");
		if(Operador.vazio(lista)) {
			System.out.println("-!- Não há produtos inseridos.");
		} else {
			for(Produto item : lista) {
				item.imprimir();
			}
		}
		if(Operador.solicitaRetornar()) return;
	}

	public void buscar() {
		System.out.println("\n--- Buscar produtos");
		
		if(Operador.vazio(lista)) {
			System.out.println("-!- Não há produtos inseridos.");
			if(Operador.solicitaRetornar()) return;
		}
		
		int id = 0;
		String titulo = null;
		boolean parametro = false;

		while(!parametro) {
			System.out.println("[1] Por ID");
			System.out.println("[2] Por Título");
			System.out.println("[3] Voltar");
			int comando = Integer.valueOf(Operador.solicitaString("Por favor, selecione um parâmetro de busca"));

			switch(comando) {
				case 1:
					id = Integer.valueOf(Operador.solicitaString("ID"));
					parametro = true;
				break;
				case 2:
					titulo = Operador.solicitaString("Título");
					parametro = true;
				break;
				case 3: return;
				default:
					System.out.println("-!- Opção inválida.");
					Operador.solicitaRetornar();
				break;
			}
		}
		
		Produto produto = null;
		for(Produto item : lista) {
			if(id != 0 && item.igual(id)) {
				produto = item;
				break;
			} else if(titulo != null && item.igual(titulo)) {
				produto = item;
				break;
			}
		}

		if(produto != null) {
			produto.imprimir();

			parametro = false;
			while(!parametro) {
				System.out.println("\n--- Operações para o produto");
				System.out.println("[1] Editar Produto");
				System.out.println("[2] Deletar Produto");
				System.out.println("[3] Voltar");
				int comando = Integer.valueOf(Operador.solicitaString("Por favor, selecione uma operação"));

				switch(comando) {
					case 1:
						editar(produto);
						parametro = true;
					break;
					case 2:
						deletar(produto);
						parametro = true;
					break;
					case 3: return;
					default:
						System.out.println("-!- Opção inválida.");
						Operador.solicitaRetornar();
					break;
				}
			}
		} else {
			System.out.println("-!- O produto não foi encontrado.");
			Operador.solicitaRetornar();
		}

	}

	public void editar(Produto produto) {
		System.out.println("\n--- Editar produto (\"s\" para sair)");

		String titulo = Operador.solicitaString("Título");
		if(Operador.verificaSair(titulo)) return;
		
		String preco = Operador.solicitaString("Preço");
		if(Operador.verificaSair(preco)) return;

		produto.editar(titulo, Double.valueOf(preco));

		Operador.solicitaRetornar();
	}

	public void deletar(Produto produto) {
		System.out.println("\n--- Deletar produto");

		String confirma = Operador.solicitaString("Tem certeza que deseja deletar (\"s\" ou \"n\")");
		if(confirma.toLowerCase().equals("s")) lista.remove(produto);
	
		Operador.solicitaRetornar();
	}

}

