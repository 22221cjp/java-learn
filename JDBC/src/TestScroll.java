import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestScroll {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.103:5432/postgres", "postgres", "2651898");
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = statement.executeQuery("select * from student order by id");

			resultSet.next();
			System.out.println(resultSet.isFirst()); // true
			System.out.println(resultSet.getString(2)); // chen

			resultSet.last();
			System.out.println(resultSet.getRow()); // 11
			System.out.println(resultSet.isAfterLast()); // false

			resultSet.absolute(5);
			System.out.println(resultSet.getString(2)); // wangwu

			resultSet.previous();
			System.out.println(resultSet.getInt(1)); // 4

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
