import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdatableResultSet {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.1.103:5432/postgres",
					"postgres", "2651898");
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet=statement.executeQuery("select * from student order by id");
			resultSet.next();
			System.out.println(resultSet.getString(2));
		
			resultSet.updateString(2,"jinpeng");  //���ڴ����޸�
			resultSet.updateRow();   //���µ����ݿ�
			System.out.println(resultSet.getString(2)); //jinpeng
			
			//��������
			/*resultSet.moveToInsertRow();
			resultSet.updateInt(1,12);
			resultSet.updateString(2,"chen");
			resultSet.updateInt(3,24);
			resultSet.insertRow();*/
			
			resultSet.first();
			System.out.println(resultSet.getInt(1));  //1
			
			//������Ƶ��½�����
			resultSet.moveToCurrentRow();
			System.out.println(resultSet.getInt(1));//1
			
			//ɾ����
			resultSet.absolute(5);
			resultSet.deleteRow();
			
			//ȡ������
			resultSet.absolute(1);
			resultSet.updateString(2,"chen");
			resultSet.cancelRowUpdates(); //������updateRow֮ǰ���ò�����
			resultSet.updateRow();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement!=null) {
					statement.close();
				}
				if (connection!=null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
