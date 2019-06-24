import java.util.*;
import java.io.Serializable;

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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public Produto(int lastId) {
		this.lastId = lastId;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public int getId() {
		return id;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	private void setId(int id) {
		this.id = id;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public String getTitulo() {
		return titulo;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public double getPreco() {
		return preco;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/
	public void setPreco(double preco) {
		this.preco = preco;
		precoArredondado = Conversor.DoubleParaPreco(this.preco, false);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public String getPrecoArredondado() {
		return precoArredondado;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void setPrecoArredondado(String precoArredondado) {
		this.precoArredondado = precoArredondado;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public String getDataEntrada() {
		return dataEntrada;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/
	
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void inserirEstoque(int quantidade) {
		qtdEstoque += quantidade;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void retirarEstoque(int quantidade) {
		qtdEstoque -= quantidade;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public static int getLastId() {
		return lastId;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	@Override
	public String toString() {
		return "Produto [id="+id+", titulo="+titulo+", qtdEstoque="+qtdEstoque+", preco="+preco+", precoArredondado="+precoArredondado+", dataEntrada="+dataEntrada+"]";
	}

}
