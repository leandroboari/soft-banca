import java.util.*;

public class Produto {
	private static int lastId = 0;
	protected int id;
	protected String titulo;
	protected int qtdEstoque;
	protected int qtdVendas;
	protected double preco;
	protected String precoArredondado;
	protected String dataEntrada;

	public Produto(int id, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = id;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.dataEntrada = dataEntrada;
		qtdVendas = 0;
		precoArredondado = Conversor.DoubleParaPreco(preco, false);
	}

	public Produto(String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = ++lastId;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		this.dataEntrada = dataEntrada;
		qtdVendas = 0;
		precoArredondado = Conversor.DoubleParaPreco(preco, false);
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

	public int getQtdVendas() {
		return qtdVendas;
	}
	public void setQtdVendas(int qtdVendas) {
		this.qtdVendas = qtdVendas;
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

	public int qtdDisponivel() {
		int qtdDisponivel = 0;
		int qtd = qtdEstoque - qtdVendas;
		if(qtd > 0) {
			qtdDisponivel = qtd;
		}
		return qtdDisponivel;
	}

	public void inserirEstoque(int quantidade) {
		qtdEstoque += quantidade;
	}

}