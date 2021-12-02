package gasteMenos;

import javax.swing.JOptionPane;

public class Main {

	static GastoDao gastoDao;

	public static int menu () {
		String operacoes = "";

		operacoes += "0 - Sair\n";
		operacoes += "1 - Adicionar gasto\n";
		operacoes += "2 - Alterar gasto\n";
		operacoes += "3 - Remover gasto\n";
		operacoes += "4 - Remover todos os gastos\n";
		operacoes += "5 - Listar gastos\n";
		operacoes += "6 - Ver estatísticas\n";

		int opcao = Integer.parseInt(JOptionPane.showInputDialog(operacoes));

		return opcao;
	}

	public static void adicionarGasto () {
		String nome = JOptionPane.showInputDialog("Digite o nome:");
		String categoria = JOptionPane.showInputDialog("Digite a categoria:");
		float preco = Float.parseFloat(JOptionPane.showInputDialog("Digite o pre�o:"));

		Gasto gasto = new Gasto(nome, categoria, preco);
		gastoDao.adicionar(gasto);

		JOptionPane.showMessageDialog(null, "Gasto cadastrado com sucesso!");
	}

	public static void alterarGasto () {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do gasto que deseja alterar:"));

		for (int i = 0; i < gastoDao.getLista().size(); i++) {
			if (id == gastoDao.getLista().get(i).getId()) {

				String nome = JOptionPane.showInputDialog("Digite o novo nome:");
				String categoria = JOptionPane.showInputDialog("Digite a nova categoria:");
				float preco = Float.parseFloat(JOptionPane.showInputDialog("Digite o novo preço:"));

				gastoDao.alterar(nome, categoria, preco, id);

				JOptionPane.showMessageDialog(null, "Gasto atualizado com sucesso!");
			}
			else{
				JOptionPane.showMessageDialog(null, "Id não encontrado");
				return;
			}
		}
	}

	public static void removerGasto () {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID:"));

		for (int i = 0; i < gastoDao.getLista().size(); i++) {
			if (id == gastoDao.getLista().get(i).getId()) {

				gastoDao.remover(id);

				JOptionPane.showMessageDialog(null, "Gasto removido com sucesso!");
			}else {
				JOptionPane.showMessageDialog(null, "Id não encontrado");
				return;
			}
		}
	}

	public static void removerTudo () {
		if (gastoDao.getLista().size() == 0) {
			JOptionPane.showMessageDialog(null, "Sem gastos cadastrados!");
			return;
		}

		for (int i = 0; i < gastoDao.getLista().size(); i++) {
			gastoDao.remover(gastoDao.getLista().get(i).getId());
		} 

		JOptionPane.showMessageDialog(null, "Todos os gastos removidos com sucesso!");
	}

	public static void listarGastos () {
		if (gastoDao.getLista().size() == 0) {
			JOptionPane.showMessageDialog(null, "Sem gastos cadastrados!");
			return;
		}

		for (int i = 0; i < gastoDao.getLista().size(); i++) {
			String mensagem = "";

			mensagem += "Id: " + gastoDao.getLista().get(i).getId() + "\n";
			mensagem += "Nome: " + gastoDao.getLista().get(i).getNome() + "\n";
			mensagem += "Categoria: " + gastoDao.getLista().get(i).getCategoria() + "\n";
			mensagem += String.format("Pre�o: R$ %.2f\n", gastoDao.getLista().get(i).getPreco());

			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

	public static void gastoTotal () {
		if (gastoDao.getLista().size() == 0) {
			JOptionPane.showMessageDialog(null, "Sem gastos cadastrados!");
			return;
		}

		int total = 0;

		for (int i = 0; i < gastoDao.getLista().size(); i++) {
			total += gastoDao.getLista().get(i).getPreco();
		} 

		JOptionPane.showMessageDialog(null,  String.format("Total gasto: R$ %.2f\n ", total));
	}

	public static void gastoPorCategoria () {
		if (gastoDao.listarCategorias().size() == 0) {
			JOptionPane.showMessageDialog(null, "Sem gastos cadastrados!");
			return;
		}

		String mensagem = "";

		for (int i = 0; i < gastoDao.listarCategorias().size(); i++) {
			String categoria = gastoDao.listarCategorias().get(i);
			float gasto = gastoDao.buscarPrecoPorCategoria(categoria);

			mensagem = String.format("Você gastou R$ %.2f\n ", gasto);
			mensagem += "em " + categoria;
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

	public static void main (String[] args) {
		int opcao;

		gastoDao = new GastoDao();

		do {
			opcao = menu();

			switch (opcao) {
			case 0:
				JOptionPane.showMessageDialog(null, "Fim do programa!");
				break;

			case 1:
				adicionarGasto();
				break;

			case 2:
				alterarGasto();
				break;

			case 3:
				removerGasto();
				break;

			case 4:
				removerTudo();
				break;

			case 5:
				listarGastos();
				break;

			case 6:
				gastoTotal();
				gastoPorCategoria();
				break;

			default:
				JOptionPane.showMessageDialog(null, "Op��o inv�lida!");
				break;
			}
		} while (opcao != 0);

	}

}
