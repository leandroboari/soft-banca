import java.util.*;
import java.io.Serializable;

/**
* Classe que caracteriza os atributos em métodos de um Produto.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class Produto implements Serializable {

	private static final long serialVersionUID = 19L;
	transient private static int lastId = 0;
	protected int id;
	protected String titulo;
	protected int qtdEstoque;
	protected double preco;
	protected String precoArredondado;
	protected String dataEntrada;

	/**
	* Construtor para caso o produto já contenha uma ID.
	* @param id ID que representa a identidade do produto.
	* @param titulo Título que representa o produto.
	* @param preco Preço do produto.
	* @param qtdEstoque Quantidade em estoque do produto.
	* @param dataEntrada Data em que foi feito a última inserção em estoque.
	*/

	public Produto(int id, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = id;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.dataEntrada = dataEntrada;
		precoArredondado = Conversor.DoubleParaPreco(preco, false);
	}

	/**
	* Construtor para um novo produto.
	* @param titulo Título que representa o produto.
	* @param preco Preço do produto.
	* @param qtdEstoque Quantidade em estoque do produto.
	* @param dataEntrada Data em que foi feito a última inserção em estoque.
	*/

	public Produto(String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = ++lastId;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.dataEntrada = dataEntrada;
		precoArredondado = Conversor.DoubleParaPreco(preco, false);
	}

	/**
	* Construtor para referenciar a última ID estatitcamente.
	* @param lastId
	*/

	public Produto(int lastId) {
		this.lastId = lastId;
	}

	/**
	* ID do produto.
	* @return int Identificação do produto.
	*/

	public int getId() {
		return id;
	}

	/**
	* Altera ID do produto.
	* @param id Identificação do produto.
	*/

	private void setId(int id) {
		this.id = id;
	}

	/**
	* Acessa Título do produto.
	* @return String titulo
	*/

	public String getTitulo() {
		return titulo;
	}

	/**
	* Altera Título do produto.
	* @param titulo Título do produto.
	*/

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	* Acessa Quantidade em Estoque
	* @return int qtdEstoque
	*/

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	/**
	* Altera Quantidade em Estoque.
	* @param qtdEstoque Quantidade em Estoque.
	*/

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	/**
	* Acessa Preço do produto.
	* @return double Preço do produto.
	*/

	public double getPreco() {
		return preco;
	}

	/**
	* Altera Preço do produto.
	* @param preco Preço do produto.
	*/
	public void setPreco(double preco) {
		this.preco = preco;
		precoArredondado = Conversor.DoubleParaPreco(this.preco, false);
	}

	/**
	* Acessa Preço do produto arredondado.
	* @return String Preço do produto arredondado.
	*/

	public String getPrecoArredondado() {
		return precoArredondado;
	}

	/**
	* Altera Preço do produto arredondado.
	* @param precoArredondado Preço do produto arredondado.
	*/

	public void setPrecoArredondado(String precoArredondado) {
		this.precoArredondado = precoArredondado;
	}

	/**
	* Acessa Data de Entrada do produto.
	* @return String Data de Entrada do produto.
	*/

	public String getDataEntrada() {
		return dataEntrada;
	}

	/**
	* Altera Data de Entrada do produto.
	* @param dataEntrada Data de Entrada do produto.
	*/
	
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	/**
	* Inserir estoque.
	* @param quantidade Quantidade a ser inserida em estoque.
	*/

	public void inserirEstoque(int quantidade) {
		qtdEstoque += quantidade;
	}

	/**
	* Retirar estoque.
	* @param quantidade uantidade a ser removeida em estoque.
	*/

	public void retirarEstoque(int quantidade) {
		qtdEstoque -= quantidade;
	}

	/**
	* Acessa última ID
	* @return int última ID
	*/

	public static int getLastId() {
		return lastId;
	}

	/**
	* Serialização da classe.
	* @return String valores a serem descritos.
	*/

	@Override
	public String toString() {
		return "Produto [id="+id+", titulo="+titulo+", qtdEstoque="+qtdEstoque+", preco="+preco+", precoArredondado="+precoArredondado+", dataEntrada="+dataEntrada+"]";
	}

}
