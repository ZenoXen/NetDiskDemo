package daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class QueryUnameDao extends AbstractDatabaseDao{
	public QueryUnameDao() {
		super("select * from users where uname=?");
	}
	public boolean queryUname(String uname) {
		ResultSet rs=null;
		boolean ret=false;
		try {
			ps.setString(1,uname);
			rs=ps.executeQuery();
			ret=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStmt();
		return ret;
	}
}
