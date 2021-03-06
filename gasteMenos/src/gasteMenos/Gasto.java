package gasteMenos;

public class Gasto {

	private int id;
	private String nome, categoria;
	private float preco;

	public Gasto (String nome, String categoria, float preco) {
		this.setNome(nome);
		this.setCategoria(categoria);
		this.setPreco(preco);
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getNome () {
		return nome;
	}

	public void setNome (String nome) {
		this.nome = nome;
	}

	public String getCategoria () {
		return categoria;
	}

	public void setCategoria (String categoria) {
		this.categoria = categoria;
	}

	public float getPreco () {
		return preco;
	}

	public void setPreco (float preco) {
		this.preco = preco;
	}

}
