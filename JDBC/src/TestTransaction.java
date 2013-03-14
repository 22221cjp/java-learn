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
			//�����������sql����ִ�й��̣����Ա����DML����ʱ���������ڴ��жԱ������ݽ��и��ģ�Ȼ��д�ش����ϣ���������̽�����һ���ύ����Ҳ�С�һ������
			//Ĭ������£�һ�������ǡ�һ�����񡱣���Ҳ��������ύһ�Ρ���PostgreSQL�У���begin...commit֮���������䵱��һ������
			//java����Ҫ�������Զ��ύ����Ϊfalse��������Ϊfalse���ֶ��ύ֮�������SQL���ֻ�ύһ�Σ�����һ������
			connection.setAutoCommit(false);
			statement.addBatch("insert into student values(10,'xiaoyue',23)");
			statement.addBatch("insert into student values(11,'zhangwu',21)");
			statement.executeBatch();
			//�ֶ��ύ���񣬲������Զ��ύ����Ϊtrue
			connection.commit();
			connection.setAutoCommit(true);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//����ִ��sql���Ĺ����г����쳣����Ҫ�ع��������Զ��ύ����Ϊtrue
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
