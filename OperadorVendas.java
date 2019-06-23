import java.util.ArrayList;
import javafx.scene.control.TableView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;

public class OperadorVendas {

	transient private final String arquivoOperador = "OperadorVendas.bin";
	transient private final String arquivoLista = "Vendas.bin";

	transient private double total;
	transient private String totalArredondado;

	transient private ArrayList<Venda> lista;
	private int lastId;

	transient private Venda venda;

	public OperadorVendas() {
		lista = new ArrayList<Venda>();
		lastId = 0;
		resgataOperadorArmazenamento();
		venda = new Venda(lastId);
		resgataListaArmazenamento();
		atualizaTotal();
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

		// for(Venda venda: lista){
		// 	System.out.println(venda);
		// }
	}

	public void salvaOperadorArmazenamento() {
		lastId = venda.getLastId();
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

	private void inserirVenda(Venda venda) {
		lista.add(venda);
	}

	public void finalizarVenda(Venda venda, String meioPagamento) {
		venda.finalizar(meioPagamento);
		inserirVenda(venda);
	}

	public void preencherTabela(TableView<Venda> tabela) {
		tabela.getItems().clear();
		for (Venda venda: lista) {
			tabela.getItems().add(venda);
		}
	}

	public void atualizaTotal() {
		total = 0;
		for (Venda venda: lista) {
			total += venda.getTotal();
		}
		totalArredondado = Conversor.DoubleParaPreco(total, true);
	}

	public String getTotalArredondado() {
		return totalArredondado;
	}

}