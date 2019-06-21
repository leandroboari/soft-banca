public class ProdutoVenda extends Produto {

	private int quantidade;

	public ProdutoVenda(int id, int quantidade, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		super(id, titulo, preco, qtdEstoque, dataEntrada);
		this.quantidade = quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void inserirQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}

}