import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPreparedStatement {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Parameter Error..");
			System.exit(-1);
		}

		String string = args[0];
		int num = Integer.parseInt(args[1]);
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.103:5432/postgres", "postgres", "2651898");
			preparedStatement = connection.prepareStatement("insert into test1 values(?,?)");
			preparedStatement.setString(1, string);
			preparedStatement.setInt(2, num);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
