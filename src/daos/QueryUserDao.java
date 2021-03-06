package daos;
import java.sql.*;
import java.io.*;
import java.util.*;
import beans.*;
public class QueryUserDao extends AbstractDatabaseDao{
	public QueryUserDao() {
		super("select * from users where uname=? and upwd=password(?)");
	}
	public boolean queryUser(User user) {
		ResultSet rs=null;
		boolean ret=false;
		try {
			ps.setString(1,user.getUname());
			ps.setString(2, user.getUpwd());
			rs=ps.executeQuery();
			ret=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStmt();
		return ret;
	}
}