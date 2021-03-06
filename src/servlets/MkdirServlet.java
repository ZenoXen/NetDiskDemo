package servlets;
import java.io.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/MkdirServlet",loadOnStartup=5)
public class MkdirServlet extends HttpServlet {

    public MkdirServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String dirName=request.getParameter("dirName");
		String path=(String)request.getSession().getAttribute("currentPath");
		File f=new File(path+"\\"+dirName);
		if(!f.exists()&&!f.isDirectory())
			f.mkdir();
		request.getRequestDispatcher("GetServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
