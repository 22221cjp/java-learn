import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
/**
 * ���Ե��ô洢����
 * @author Administrator
 *
 */
public class TestProc {
	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.103:5432/postgres",
					"postgres", "2651898");
			callableStatement = connection
					.prepareCall("{call test_call_by_java(?,?,?,?)}");
			
			//��Ҫע����Щ���������������
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.INTEGER);

			//�ֱ���������ָ��ֵ
			callableStatement.setInt(1, 1);
			callableStatement.setInt(2, 2);
			callableStatement.setInt(4, 4);

			callableStatement.execute();
			
			//������������ֵ
			System.out.println(callableStatement.getInt(3)); // 2
			System.out.println(callableStatement.getInt(4)); // 5
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				callableStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
