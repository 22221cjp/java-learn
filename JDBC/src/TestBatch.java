import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBatch {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.103:5432/postgres",
					"postgres", "2651898");
			
			/*
			statement = connection.createStatement();
			statement.addBatch("insert into student values(6,'tom',21)");
			statement.addBatch("insert into student values(7,'jack',23)");
			statement.executeBatch();
			*/
			
			preparedStatement=connection.prepareStatement("insert into student values(?,?,?)");
			
			preparedStatement.setInt(1,8);
			preparedStatement.setString(2,"liudehua");
			preparedStatement.setInt(3,43);
			preparedStatement.addBatch();
			
			preparedStatement.setInt(1,9);
			preparedStatement.setString(2,"linjunjie");
			preparedStatement.setInt(3,32);
			preparedStatement.addBatch();
			
			preparedStatement.executeBatch();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

	}
}
