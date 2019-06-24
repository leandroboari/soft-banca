import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.Serializable;

/**
* Classe resposável por operar listas e armazenamento de produtos que compõem o estoque.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class OperadorEstoque implements Serializable {

	// Arquivos de bancos de dados
	transient private final String arquivoOperador = "db/OperadorEstoque.bin";
	transient private final String arquivoLista = "db/Estoque.bin";

	// Lista de Produtos
	transient public ArrayList<Produto> lista;
	private int lastId;

	/**
	* Construtor principal da classe.
	*/

	public OperadorEstoque() {
		lista = new ArrayList<Produto>();
		lastId = 0;
		resgataOperadorArmazenamento();
		new Produto(lastId);
		resgataListaArmazenamento();
	}

	/**
	* Salvar listas e operador para arquivos binários para persistência de dados.
	*/

	public void salvar() {
		salvarListaArmazenamento();
		salvarOperadorArmazenamento();
	}

	/**
	* Salvar lista de produtos para arquivo binário.
	*/

	public void salvarListaArmazenamento() {
		try {
			FileOutputStream fout = new FileOutputStream(arquivoLista);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(lista);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException fnfex) {
			return;
		}
		catch (IOException ioex) {
			return;
		}
	}

	/**
	* Resgata lista de produtos do arquivo binário.
	*/

	@SuppressWarnings("unchecked")
	private void resgataListaArmazenamento() {
		try {
			FileInputStream fin = new FileInputStream(arquivoLista);
			ObjectInputStream ois = new ObjectInputStream(fin);
			lista = (ArrayList<Produto>)ois.readObject();
		}
		catch (FileNotFoundException fnfex) {
			return;
		}
		catch (IOException ioex) {
			return;
		} 
		catch (ClassNotFoundException ccex) {
			return;
		}
	}

	/**
	* Salvar operador de produtos para arquivo binário.
	*/

	public void salvarOperadorArmazenamento() {
		lastId = Produto.getLastId();
		try {
			FileOutputStream fout = new FileOutputStream(arquivoOperador);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(lastId);
			oos.flush();
			oos.close();
		}
		catch (FileNotFoundException fnfex) {
			return;
		}
		catch (IOException ioex) {
			return;
		}
	}

	/**
	* Resgatar operador de produtos do arquivo binário.
	*/

	@SuppressWarnings("unchecked")
	private void resgataOperadorArmazenamento() {
		try {
			FileInputStream fin = new FileInputStream(arquivoOperador);
			ObjectInputStream ois = new ObjectInputStream(fin);
			lastId = (Integer)ois.readObject();
		}
		catch (FileNotFoundException fnfex) {
			return;
		}
		catch (IOException ioex) {
			return;
		} 
		catch (ClassNotFoundException ccex) {
			return;
		}
	}

	/**
	* Adicionar um novo produto para a lista.
	*/

	public void adicionar(Produto produto) {
		lista.add(produto);
	}

	/**
	* Editar um produto.
	* @param produto Produto a ser editado.
	* @param titulo Novo título.
	* @param preco Novo preço.
	* @param estoque Nova quantidade de estoque.
	* @param dataEntrada Nova data de entrada.
	*/

	public void editar(Produto produto, String titulo, double preco, int estoque, String dataEntrada) {
		int posicao = lista.indexOf(produto);

		if(produto == null) {
			Alerta.erro("Produto inválido.");
			return;
		}
		
		lista.get(posicao).setTitulo(titulo);
		lista.get(posicao).setPreco(preco);
		lista.get(posicao).setQtdEstoque(estoque);
		lista.get(posicao).setDataEntrada(dataEntrada);
	}

	/**
	* Remover produto da lista.
	* @param produto Produto a ser removido.
	*/

	public void remover(Produto produto) {
		lista.remove(produto);
	}

	/**
	* Atualizar estoque ao ter sido realizada uma venda.
	* @param venda Venda realizada.
	*/

	public void novaVenda(Venda venda) {
		for(ProdutoVenda produtoVenda: venda.getLista()) {
			int id = produtoVenda.getId();
			for(Produto produto: lista) {
				if(produto.getId() == produtoVenda.getId()) {
					int quantidade = produtoVenda.getQuantidade();
					produto.retirarEstoque(quantidade);
				}
			}
		}
	}

	/**
	* Serialização da classe.
	* @return String valores a serem descritos.
	*/

	@Override
	public String toString() {
		return "OperadorEstoque [lastId="+lastId+"]";
	}

}