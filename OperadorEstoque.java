import java.util.*;

public class OperadorEstoque {
	
	// Lista de Produtos
	public ArrayList<Produto> lista = new ArrayList<Produto>();

	public OperadorEstoque() {
		lista.add(new Produto("Jornal de Lavras", 2.30, 100, "18/06/2019"));
		lista.add(new Produto("Estadão", 2.00, 500, "12/04/2019"));
	}

	public void adicionar(Produto produto) {
		lista.add(produto);
	}

	public void editar(Produto produto, String titulo, double preco, int estoque, String dataEntrada) {
		int posicao = lista.indexOf(produto);

		if(produto == null) {
			Alerta.erro("Produto inválido.");
			return;
		}
		
		lista.get(posicao).setTitulo(titulo);
		lista.get(posicao).setPreco(preco);
		lista.get(posicao).setQtdEstoque(estoque);
		lista.get(posicao).setDataEntrada(dataEntrada);
	}

	public void remover(Produto produto) {
		lista.remove(produto);
	}

	public void novaVenda(Venda venda) {
		for(ProdutoVenda produtoVenda: venda.getLista()) {
			int id = produtoVenda.getId();
			for(Produto produto: lista) {
				if(produto.getId() == produtoVenda.getId()) {
					int quantidade = produtoVenda.getQuantidade();
					produto.retirarEstoque(quantidade);
				}
			}
		}
	}

}