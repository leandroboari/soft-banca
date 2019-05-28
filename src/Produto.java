import java.util.*;

public class Produto {
	
	private int id;
	private String titulo;
	private int qtdEstoque;
	private int qtdVendas;
	private double preco;
	private String dataEntrada;

	public Produto(int id, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = id;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		qtdVendas = 0;
		this.dataEntrada = dataEntrada;
	}

	public boolean igual(Produto produto) {
		return produto.id == id;
	}

	public boolean igual(int id) {
		if(id == this.id) return true;
		return false;
	}

	public boolean igual(String titulo) {
		if(titulo.equals(this.titulo)) return true;
		return false;
	}

	public void editar(String titulo, double preco) {
		this.titulo = titulo;
		this.preco = preco;
	}
	
	public void imprimir() {
		System.out.print("ID: " + String.valueOf(id));
		System.out.print(" - Título: " + titulo);
		System.out.print(" - Preço: " + String.valueOf(preco));
		System.out.print(" - Estoque: " + String.valueOf(qtdEstoque));
		System.out.print(" - Vendas: " + String.valueOf(qtdVendas));
		System.out.println(".");
	}

	public boolean vender() {
		if(qtdDisponivel() > 0) {
			qtdVendas++;
			return true;
		} else {
			return false;
		}
	}

	public int qtdDisponivel() {
		int qtdDisponivel = 0;
		int qtd = qtdEstoque - qtdVendas;
		if(qtd > 0) {
			qtdDisponivel = qtd;
		}
		return qtdDisponivel;
	}

}