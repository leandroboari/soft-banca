import java.util.*;

public class OperadorVenda {

	public ArrayList<Venda> vendas;
	public ArrayList<MeioPagamento> meiosPagamento;

	public OperadorVenda() {

		meiosPagamento = new ArrayList<MeioPagamento>();
		
		meiosPagamento.add(new MeioPagamento("Cartão de Crédito"));
		meiosPagamento.add(new MeioPagamento("Dinheiro"));
		meiosPagamento.add(new MeioPagamento("Cheque"));

		vendas = new ArrayList<Venda>();

	}

	private void inserirVenda(Venda venda) {
		vendas.add(venda);
	}

	public void finalizarVenda(Venda venda, String meioPagamento) {
		for(MeioPagamento mp: meiosPagamento) {
			if(mp.getNome().equals(meioPagamento)) {
				venda.setMeioPagamento(mp);
				return;
			}
		}
		venda.finalizar();
		inserirVenda(venda);
	}

}