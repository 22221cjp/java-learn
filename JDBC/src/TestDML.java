import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDML {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement=null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.103:5432/postgres",
					"postgres", "2651898");
			statement=connection.createStatement();
			String sql="insert into test1 values('d',5)";
			statement.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
}
