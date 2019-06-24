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
* Descrição da classe.
*/

public class OperadorEstoque implements Serializable {

	// Arquivos de bancos de dados
	transient private final String arquivoOperador = "db/OperadorEstoque.bin";
	transient private final String arquivoLista = "db/Estoque.bin";

	// Lista de Produtos
	transient public ArrayList<Produto> lista;
	private int lastId;

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public OperadorEstoque() {
		lista = new ArrayList<Produto>();
		lastId = 0;
		resgataOperadorArmazenamento();
		new Produto(lastId);
		resgataListaArmazenamento();
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void salvar() {
		salvarListaArmazenamento();
		salvarOperadorArmazenamento();
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void adicionar(Produto produto) {
		lista.add(produto);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void remover(Produto produto) {
		lista.remove(produto);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
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
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	@Override
	public String toString() {
		return "OperadorEstoque [lastId="+lastId+"]";
	}

}