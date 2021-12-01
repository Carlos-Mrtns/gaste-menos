package gasteMenos;

public class Main {

	static Gasto gasto;
	static GastoDao gastoDao;

	public static void main(String[] args) {
		gasto = new Gasto();
		gastoDao = new GastoDao();

		gasto.setNome("The Legend of Zelda");
		gasto.setCategoria("Jogo");
		gasto.setPreco(100);

		gastoDao.adicionar(gasto);
		//gastoDao.remover();

		for(int i = 0; i < gastoDao.getLista().size(); i++) {
			System.out.println("Nome: " + gastoDao.getLista().get(i).getNome());
			System.out.println("Categoria " + gastoDao.getLista().get(i).getCategoria());
			System.out.println("R$ " + gastoDao.getLista().get(i).getPreco());

			System.out.println();
		}
	}

}
