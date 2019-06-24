import java.io.Serializable;

/**
* Descrição da classe.
*/

public class ProdutoVenda extends Produto implements Serializable {

	private static final long serialVersionUID = 19L;
	private int quantidade;

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public ProdutoVenda(int id, int quantidade, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		super(id, titulo, preco, qtdEstoque, dataEntrada);
		this.quantidade = quantidade;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/
	
	public ProdutoVenda(int lastId) {
		super(lastId);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public int getQuantidade() {
		return quantidade;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void inserirQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void removerQuantidade(int quantidade) {
		this.quantidade -= quantidade;
		if(quantidade < 0) quantidade = 0;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	@Override
	public String toString() {
		return "ProdutoVenda [id="+id+", quantidade="+quantidade+", titulo="+titulo+", qtdEstoque="+qtdEstoque+", preco="+preco+", precoArredondado="+precoArredondado+", dataEntrada="+dataEntrada+"]";

	}

}