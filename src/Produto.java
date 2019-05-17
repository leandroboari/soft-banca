public class Produto {
	
	public int id;
	public String titulo;
	private int numEstoque;
	private int numVendas;
	public double preco;
	public String dataEntrada;

	public Produto(String titulo, double preco, int numEstoque, String dataEntrada) {
		this.id = id;
		this.titulo = titulo;
		this.preco = preco;
		this.numEstoque = numEstoque;
		numVendas = 0;
		this.dataEntrada = dataEntrada;
	}

}