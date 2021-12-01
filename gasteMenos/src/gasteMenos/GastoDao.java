package gasteMenos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastoDao {

	private Connection connection;
	final String DRIVER = "com.mysql.jdbc.Driver";

	public GastoDao() {
		this.connection = getConnection();
	}

	public Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/financas", "root", "");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void adicionar(Gasto gasto) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO gastos VALUES (?, ?, ?, ?)");

			ps.setInt(1, gasto.getId());
			ps.setString(2, gasto.getNome());
			ps.setString(3, gasto.getCategoria());
			ps.setFloat(4, gasto.getPreco());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM gastos WHERE id = ?");

			ps.setInt(1, id);

			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Gasto> getLista() {
		try {
			List<Gasto> gastos = new ArrayList<Gasto>();

			PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM gastos");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Gasto gasto = new Gasto();

				gasto.setId(rs.getInt("id"));
				gasto.setNome(rs.getString("nome"));
				gasto.setCategoria(rs.getString("categoria"));
				gasto.setPreco(rs.getFloat("preco"));

				gastos.add(gasto);
			}

			rs.close();
			ps.close();

			return gastos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
