package servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.*;
import daos.*;
@WebServlet(value="/LoginServlet",loadOnStartup=7)
public class LoginServlet extends AbstractNetDiskServlet{
    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		User user=new User(uname,upwd);
		if(ValidityUtil.userEmpty(user)) {
			setError(request,"EmptyError",true);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			QueryUserDao qud=new QueryUserDao();
			if(qud.queryUser(user)) {
				Cookie cookie=new Cookie("uname",uname);
				cookie.setMaxAge(86400);
				response.addCookie(cookie);
				request.getSession().setAttribute("uname", uname);
				request.getSession().setAttribute("currentPath", "D:\\NetDiskUploads\\"+uname);
				request.getSession().setAttribute("userPath", "D:\\NetDiskUploads\\"+uname);
				request.getRequestDispatcher("GetServlet").forward(request, response);
			}
			else {
				setError(request,"LoginError",true);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void setErrors(HttpServletRequest request) {
		setError(request,"EmptyError",false);
		setError(request,"LoginError",false);
	}
}