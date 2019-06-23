import java.util.ArrayList;
import javafx.scene.control.TableView;

public class OperadorVendas {

	private double total;
	private String totalArredondado;
	private ArrayList<Venda> lista;

	public OperadorVendas() {
		lista = new ArrayList<Venda>();
		atualizaTotal();
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