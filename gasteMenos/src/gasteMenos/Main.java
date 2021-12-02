package gasteMenos;

import javax.swing.JOptionPane;

public class Main {

	static GastoDao gastoDao;
	
	public static int menu () {
		String operacoes = "";
		
		operacoes += "0 - Sair\n";
		operacoes += "1 - Adicionar gasto\n";
		operacoes += "2 - Listar gastos\n";
		
		int opcao = Integer.parseInt(JOptionPane.showInputDialog(operacoes));
		
		return opcao;
	}
	
	public static void adicionarGasto () {
		String nome = JOptionPane.showInputDialog("Digite o nome:");
		String categoria = JOptionPane.showInputDialog("Digite a categoria:");
		float preco = Float.parseFloat(JOptionPane.showInputDialog("Digite o preço:"));
		
		Gasto gasto = new Gasto(nome, categoria, preco);
		gastoDao.adicionar(gasto);
		
		JOptionPane.showMessageDialog(null, "Gasto cadastrado com sucesso!");
	}
	
	public static void listarGastos () {
		if (gastoDao.getLista().size() == 0) {
			JOptionPane.showMessageDialog(null, "Sem gastos cadastrados!");
			return;
		}
		
		for (int i = 0; i < gastoDao.getLista().size(); i++) {
			String mensagem = "";
			
			mensagem += "Nome: " + gastoDao.getLista().get(i).getNome() + "\n";
			mensagem += "Categoria: " + gastoDao.getLista().get(i).getCategoria() + "\n";
			mensagem += String.format("Preço: R$ %.2f\n", gastoDao.getLista().get(i).getPreco());
			
			JOptionPane.showMessageDialog(null, mensagem);
		}
	}

	public static void main(String[] args) {
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
					listarGastos();
					break;
					
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida!");
					break;
			}
		} while (opcao != 0);

	}

}
