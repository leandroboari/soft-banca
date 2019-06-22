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

	public void inserirVenda(Venda venda) {
		vendas.add(venda);
	}

}