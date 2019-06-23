public class BancoDados {

	public void save(String fileName) throws FileNotFoundException {
		FileOutputStream fout= new FileOutputStream (fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(clubs);
		fout.close();
	}

	public void read(String fileName) throws FileNotFoundException {
		FileInputStream fin= new FileInputStream (fileName);
		ObjectInputStream ois = new ObjectInputStream(fin);
		clubs= (ArrayList<Clubs>)ois.readObject();
		fin.close();
	}

}