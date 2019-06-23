import java.io.Serializable;

public class ProdutoVenda extends Produto implements Serializable {

	private static final long serialVersionUID = 19L;
	private int quantidade;

	public ProdutoVenda(int id, int quantidade, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		super(id, titulo, preco, qtdEstoque, dataEntrada);
		this.quantidade = quantidade;
	}
	
	public ProdutoVenda(int lastId) {
		super(lastId);
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

	public void removerQuantidade(int quantidade) {
		this.quantidade -= quantidade;
		if(quantidade < 0) quantidade = 0;
	}

	@Override
	public String toString() {
		return "ProdutoVenda [id="+id+", quantidade="+quantidade+", titulo="+titulo+", qtdEstoque="+qtdEstoque+", preco="+preco+", precoArredondado="+precoArredondado+", dataEntrada="+dataEntrada+"]";

	}

}