package servlets;
import java.io.*;
import beans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.*;
@WebServlet(value="/RegisterServlet",loadOnStartup=4)
public class RegisterServlet extends AbstractNetDiskServlet {
    public RegisterServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		User user=new User(uname,upwd);
		setErrors(request);
		boolean procceed=true;
		if(ValidityUtil.userEmpty(user)) {
			setError(request,"EmptyError",true);
			procceed=false;
		}
		else if(ValidityUtil.nameFormatFalse(user.getUname())) {
			setError(request,"NameError",true);
			procceed=false;
		}
		else if(ValidityUtil.pwdFormatFalse(user.getUpwd())) {
			setError(request,"PwdError",true);
			procceed=false;
		}
		if(!procceed)
			request.getRequestDispatcher("register.jsp").forward(request, response);
		else {
			QueryUnameDao qud=new QueryUnameDao();
			InsertUserDao iud=new InsertUserDao();
			if(qud.queryUname(uname)) {
				setError(request,"DuplicateError",true);
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			int result=iud.insertUser(user);
			if(result<=0) {
				setError(request,"RegisterError",true);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			else {
				File newDir=new File("D:\\NetDiskUploads\\"+uname);
				newDir.mkdir();
				request.getRequestDispatcher("LoginServlet?uname="+uname+"&upwd="+upwd).forward(request, response);
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void setErrors(HttpServletRequest request) {
		setError(request,"EmptyError",false);
		setError(request,"NameError",false);
		setError(request,"PwdError",false);
		setError(request,"RegisterError",false);
	}
}