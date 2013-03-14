import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTransaction {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.103:5432/postgres",
					"postgres", "2651898");
			statement = connection.createStatement();
			//可以这里理解sql语句的执行过程，当对表进行DML操作时，先是在内存中对表格的数据进行更改，然后写回磁盘上，把这个过程叫做“一次提交”，也叫“一次事务”
			//默认情况下，一条语句就是“一次事务”，即也就向磁盘提交一次。在PostgreSQL中，将begin...commit之间的所有语句当成一次事务
			//java中需要先设置自动提交属性为false，在设置为false和手动提交之间的所有SQL语句只提交一次，当成一个事务。
			connection.setAutoCommit(false);
			statement.addBatch("insert into student values(10,'xiaoyue',23)");
			statement.addBatch("insert into student values(11,'zhangwu',21)");
			statement.executeBatch();
			//手动提交事务，并设置自动提交属性为true
			connection.commit();
			connection.setAutoCommit(true);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//若在执行sql语句的过程中出现异常，需要回滚并设置自动提交属性为true
				if (connection != null) {
					connection.rollback();
					connection.setAutoCommit(true);
				}
			} catch (Exception e2) {
			}
		} finally {
			try {
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
