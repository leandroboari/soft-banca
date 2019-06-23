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

	public Produto(int id, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = id;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.dataEntrada = dataEntrada;
		precoArredondado = Conversor.DoubleParaPreco(preco, false);
	}

	public Produto(String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = ++lastId;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.dataEntrada = dataEntrada;
		precoArredondado = Conversor.DoubleParaPreco(preco, false);
	}

	public Produto(int lastId) {
		this.lastId = lastId;
	}

	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
		precoArredondado = Conversor.DoubleParaPreco(this.preco, false);
	}

	public String getPrecoArredondado() {
		return precoArredondado;
	}
	public void setPrecoArredondado(String precoArredondado) {
		this.precoArredondado = precoArredondado;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void inserirEstoque(int quantidade) {
		qtdEstoque += quantidade;
	}

	public void retirarEstoque(int quantidade) {
		qtdEstoque -= quantidade;
	}

	public static int getLastId() {
		return lastId;
	}

	@Override
	public String toString() {
		return "Produto [id="+id+", titulo="+titulo+", qtdEstoque="+qtdEstoque+", preco="+preco+", precoArredondado="+precoArredondado+", dataEntrada="+dataEntrada+"]";
	}

}
