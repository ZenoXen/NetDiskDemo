package beans;
import java.sql.*;
import java.io.*;
public class ConnectUtil {
	private static Connection conn=null;
	private static void initConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://47.97.206.64:3306/netdisk?"
					+ "useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
			conn=DriverManager.getConnection(url,"root","123456");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn() {
		if(conn==null) initConn();
		return conn;
	}
}