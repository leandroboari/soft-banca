import java.util.*;

public class OperadorVendas {

	public ArrayList<Venda> lista;

	public OperadorVendas() {
		lista = new ArrayList<Venda>();
	}

	private void inserirVenda(Venda venda) {
		lista.add(venda);
	}

	public void finalizarVenda(Venda venda, String meioPagamento) {
		venda.finalizar(meioPagamento);
		inserirVenda(venda);
	}

}