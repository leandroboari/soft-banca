public class Produto {
	
	// Atributos
	public int id;
	public String titulo;
	private int qtdEstoque;
	private int qtdVendas;
	public double preco;
	public String dataEntrada;

	// Construtor
	public Produto(int id, String titulo, double preco, int qtdEstoque, String dataEntrada) {
		this.id = id;
		this.titulo = titulo;
		this.preco = preco;
		this.qtdEstoque = qtdEstoque;
		qtdVendas = 0;
		this.dataEntrada = dataEntrada;
	}

}