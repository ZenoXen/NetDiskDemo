package daos;
import java.sql.*;

import beans.ConnectUtil;
public abstract class AbstractDatabaseDao {
	protected PreparedStatement ps;
	protected AbstractDatabaseDao(String sql) {
		Connection conn=ConnectUtil.getConn();
		try {
			ps=conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void closeStmt() {
		try {
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}