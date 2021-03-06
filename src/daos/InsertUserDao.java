package daos;
import beans.*;
import java.sql.*;
import java.io.*;
import java.util.*;
public class InsertUserDao extends AbstractDatabaseDao{
	public InsertUserDao() {
		super("insert into users values(null,?,password(?))");
	}
	public int insertUser(User user) {
		int ret=-1;
		try {
			ps.setString(1,user.getUname());
			ps.setString(2, user.getUpwd());
			ret=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeStmt();
		return ret;
	}
}