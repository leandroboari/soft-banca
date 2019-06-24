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
import java.time.LocalDate;

/**
* Classe resposável por operar listas e armazenamento de vendas.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
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

	// Estatísticas de Venda
	transient private int qtdVendasHoje;
	transient private double totVendasHoje;
	transient private int qtdVendasMes;
	transient private double totVendasMes;

	/**
	* Construtor principal da classe.
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
	* Salvar listas e operador para arquivos binários para persistência de dados.
	*/

	public void salvar() {
		salvarListaArmazenamento();
		salvarOperadorArmazenamento();
	}

	/**
	* Salvar lista de vendas para arquivo binário.
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
	* Resgata lista de vendas do arquivo binário.
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
	* Salvar operador de vendas para arquivo binário.
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
	* Resgatar operador de vendas do arquivo binário.
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
	* Inserir nova venda na lista.
	* @param venda Venda a ser inserida.
	*/

	private void inserirVenda(Venda venda) {
		lista.add(venda);
	}

	/**
	* Alterar o status da venda para finalizado.
	* @param venda Venda a ser modificada.
	* @param meioPagamento Meio de pagamento escolhido.
	*/

	public void finalizarVenda(Venda venda, String meioPagamento) {
		venda.finalizar(meioPagamento);
		inserirVenda(venda);
	}

	/**
	* Preenche tabela com os valores da lista.
	* @param tabela tabela a ser preenchida.
	*/

	public void preencherTabela(TableView<Venda> tabela) {
		tabela.getItems().clear();
		for (Venda venda: lista) {
			tabela.getItems().add(venda);
		}
	}

	/**
	* Atualiza total de acordo com as vendas listadas.
	*/

	public void atualizaTotal() {
		total = 0;
		for (Venda venda: lista) {
			total += venda.getTotal();
		}
		totalArredondado = Conversor.DoubleParaPreco(total, true);
	}

	/**
	* Atualiza estatísticas de acordo com as vendas listadas.
	*/

	public void atualizaEstatisticas() {
		qtdVendasHoje = 0;
		totVendasHoje = 0;
		qtdVendasMes = 0;
		totVendasMes = 0;
		for (Venda venda: lista) {
			if(verificaHoje(venda.getDataHora())) {
				qtdVendasHoje += venda.getItens();
				totVendasHoje += venda.getTotal();
			}
			if(verificaMes(venda.getDataHora())) {
				qtdVendasMes += venda.getItens();
				totVendasMes += venda.getTotal();
			}
		}
	}

	/**
	* Verificação se a data é o dia atual 
	* @param data Data a ser analisada
	* @return boolean true se a data representa o dia atual. 
	*/

	private boolean verificaHoje(String data) {
		LocalDate hoje = LocalDate.now();
		int anoHoje = hoje.getYear();
		int mesHoje = hoje.getMonthValue();
		int diaHoje = hoje.getDayOfMonth();
		LocalDate novaData = Conversor.StringParaDataHora(data);
		int anoData = novaData.getYear();
		int mesData = novaData.getMonthValue();
		int diaData = novaData.getDayOfMonth();
		if(anoHoje == anoData && mesHoje == mesData && diaHoje == diaData) return true;
		return false;
	}

	/**
	* Verificação se a data é o mês atual
	* @param data Data a ser analisada
	* @return boolean true se a data representa o mês atual. 
	*/

	private boolean verificaMes(String data) {
		LocalDate hoje = LocalDate.now();
		int anoHoje = hoje.getYear();
		int mesHoje = hoje.getMonthValue();
		LocalDate novaData = Conversor.StringParaDataHora(data);
		int anoData = novaData.getYear();
		int mesData = novaData.getMonthValue();
		if(anoHoje == anoData && mesHoje == mesData) return true;
		return false;
	}

	/**
	* Resgata estatística de vendas hoje.
	* @return int número de vendas.
	*/

	public int getQtdVendasHoje() {
		return qtdVendasHoje;
	}

	/**
	* Resgata estatística de vendas hoje.
	* @return double valor total de vendas.
	*/

	public double getTotVendasHoje() {
		return totVendasHoje;
	}

	/**
	* Resgata estatística de vendas neste mês.
	* @return int número de vendas.
	*/

	public int getQtdVendasMes() {
		return qtdVendasMes;
	}

	/**
	* Resgata estatística de vendas neste mês.
	* @return double valor total de vendas.
	*/

	public double getTotVendasMes() {
		return totVendasMes;
	}

	/**
	* Total de vendas arredondado e padronizado.
	* @return String
	*/

	public String getTotalArredondado() {
		return totalArredondado;
	}

	/**
	* Serialização da classe.
	* @return String valores a serem descritos.
	*/

	@Override
	public String toString() {
		return "OperadorVendas [lastId="+lastId+"]";
	}

}