import java.util.*;

public class Banca {
	public String nome;
        public String gerente;
        public String endereco;
        public String telefone;
        public String email;
	private int numFuncionario;
	public double faturamento;

    public Banca(String nome, String gerente, String endereco, String dataEntrada, String telefone, String email, int numFuncionario, double faturamento) {
	this.nome = nome;
	this.gerente = gerente;
	this.endereco = endereco;
	this.telefone = telefone;
        this.email = email;
	numFuncionario = 0;
	faturamento = 0;
    }
    
    // Métodos Set
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setGerente(String gerente){
        this.gerente = gerente;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
      public void setNumFuncionario(int numFuncionario){
        this.numFuncionario =+ 1;
    }
      
      public void setFaturamento(double faturamento){
        this.faturamento = faturamento + getPreco() ;
    }
    
    // Métodos Get
    
    public String getNome(){
        return nome;
    }
    
    public String getGerente(){
        return gerente;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getNumFuncionario(){
        return numFuncionario;
    }
    
    public double getFaturamento(){
        return faturamento;
    }
}