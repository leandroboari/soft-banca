import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;

/**
* Classe de autenticação da senha.
* @author Leandro Boari Naves Silva
* @author Clever Oliveira
* @author João Paulo Uba
*/

public class Autenticacao {

	private String senha;
	private final String arquivo = "db/Autenticacao.bin";

	/**
	* Construtor principal da classe.
	*/
	public Autenticacao() {
		senha = "";
		resgatarSenha();
	}

	/**
	* Autentica uma senha
	* @param senha senha a ser autenticada.
	* @return boolean True se a senha for autenticada.
	*/

	public boolean autentica(String senha) {
		if(this.senha.equals(senha)) return true;
		return false;
	}

	/**
	* Alterar a senha.
	* @param senha senha a ser alterada.
	*/

	public void alterarSenha(String senha) {
		this.senha = senha;
		salvarSenha();
	}

	/**
	* Salvar senha para arquivo binário.
	*/

	public void salvarSenha() {
		try {
			FileOutputStream fout = new FileOutputStream(arquivo);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(senha);
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
	* Resgata senha do arquivo binário.
	*/

	@SuppressWarnings("unchecked")
	private void resgatarSenha() {
		try {
			FileInputStream fin = new FileInputStream(arquivo);
			ObjectInputStream ois = new ObjectInputStream(fin);
			senha = (String)ois.readObject();
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

}