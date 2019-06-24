import java.io.Serializable;

/**
* Classe que caracteriza os atributos em métodos de um Produto de Venda.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class ProdutoVenda extends Produto implements Serializable {

	private static final long serialVersionUID = 19L;
	private int quantidade;

	/**
	* Construtor principal da classe.
	* @param id ID que representa a identidade do produto.
	* @param quantidade quantidade de itens a serem vendidos.
	* @param titulo Título que representa o produto.
	* @param preco Preço do produto.
	* @param qtdEstoque Quantidade em estoque do produto.
	* @param dataEntrada Data em que foi feito a última inserção em estoque.
	*/

	public ProdutoVenda(int id, int quantidade, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		super(id, titulo, preco, qtdEstoque, dataEntrada);
		this.quantidade = quantidade;
	}

	/**
	* Construtor para referenciar a última ID estatitcamente.
	* @param lastId
	*/
	
	public ProdutoVenda(int lastId) {
		super(lastId);
	}

	/**
	* Altera a quantidade de itens a serem vendidos.
	* @param quantidade Quantidade de itens a serem vendidos.
	*/

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	* Acessa a quantidade de itens a serem vendidos.
	* @return int
	*/

	public int getQuantidade() {
		return quantidade;
	}

	/**
	* Insere a quantidade de itens a serem vendidos.
	* @param quantidade
	*/

	public void inserirQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}

	/**
	* Altera a quantidade de itens a serem vendidos.
	* @param quantidade
	*/

	public void removerQuantidade(int quantidade) {
		this.quantidade -= quantidade;
		if(quantidade < 0) quantidade = 0;
	}

	/**
	* Serialização da classe.
	* @return String valores a serem descritos.
	*/

	@Override
	public String toString() {
		return "ProdutoVenda [id="+id+", quantidade="+quantidade+", titulo="+titulo+", qtdEstoque="+qtdEstoque+", preco="+preco+", precoArredondado="+precoArredondado+", dataEntrada="+dataEntrada+"]";

	}

}