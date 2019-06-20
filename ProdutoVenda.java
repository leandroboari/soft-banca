public class ProdutoVenda extends Produto {

	private int quantidade;

	public ProdutoVenda(int id, int quantidade, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		super(titulo, preco, qtdEstoque, dataEntrada);
		this.id = id;
		this.quantidade = quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void adicionaQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}

}