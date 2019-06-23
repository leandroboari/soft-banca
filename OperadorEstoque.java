import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class OperadorEstoque {

	transient private final String arquivoOperador = "OperadorEstoque.bin";
	transient private final String arquivoLista = "Estoque.bin";

	// Lista de Produtos
	transient public ArrayList<Produto> lista;
	private int lastId;

	Produto produto;

	public OperadorEstoque() {
		lista = new ArrayList<Produto>();
		lastId = 0;
		resgataOperadorArmazenamento();
		produto = new Produto(lastId);
		resgataListaArmazenamento();
	}

	public void salvar() {
		salvaListaArmazenamento();
		salvaOperadorArmazenamento();
	}

	public void salvaListaArmazenamento() {
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

		for(Produto produto: lista){
			System.out.println(produto);
		}
	}

	public void salvaOperadorArmazenamento() {
		lastId = produto.getLastId();
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

	public void adicionar(Produto produto) {
		lista.add(produto);
	}

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

	public void remover(Produto produto) {
		lista.remove(produto);
	}

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

}