import java.util.ArrayList;
import javafx.scene.control.TableView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.Serializable;
import java.util.Date;

/**
* Descrição da classe.
*/

public class OperadorVendas implements Serializable {

	// Arquivos de bancos de dados
	transient private final String arquivoOperador = "db/OperadorVendas.bin";
	transient private final String arquivoLista = "db/Vendas.bin";

	// Total de vendas
	transient private double total;
	transient private String totalArredondado;

	// Lista de Vendas
	transient private ArrayList<Venda> lista;
	private int lastId;

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public OperadorVendas() {
		lista = new ArrayList<Venda>();
		lastId = 0;
		resgataOperadorArmazenamento();
		new Venda(lastId);
		resgataListaArmazenamento();
		atualizaTotal();
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
			lista = (ArrayList<Venda>)ois.readObject();
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
		lastId = Venda.getLastId();
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

	private void inserirVenda(Venda venda) {
		lista.add(venda);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void finalizarVenda(Venda venda, String meioPagamento) {
		venda.finalizar(meioPagamento);
		inserirVenda(venda);
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void preencherTabela(TableView<Venda> tabela) {
		tabela.getItems().clear();
		for (Venda venda: lista) {
			tabela.getItems().add(venda);
		}
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public void atualizaTotal() {
		total = 0;
		for (Venda venda: lista) {
			total += venda.getTotal();
		}
		totalArredondado = Conversor.DoubleParaPreco(total, true);
	}


	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	public String getTotalArredondado() {
		return totalArredondado;
	}

	/**
	* Descrição do método
	* @param
	* @return
	* @author Leandro Boari Naves Silva
	*/

	@Override
	public String toString() {
		return "OperadorVendas [lastId="+lastId+"]";
	}

}