import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertIntoDBByInputValues {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("please input a string: ");
		String string = input.nextLine().trim();
		System.out.print("please input a number: ");
		int num = input.nextInt();
		String sql = "insert into test1 values(" + "'" + string + "'" + ","
				+ num + ")";
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.103:5432/postgres",
					"postgres", "2651898");
			statement = connection.createStatement();
			// String sql="insert into test1 values('d',5)";
			statement.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
