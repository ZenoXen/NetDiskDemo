package servlets;
import java.io.*;
import java.util.*;
import beans.*;
import daos.*;
import servlets.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/GetServlet",loadOnStartup=8)
public class GetServlet extends HttpServlet {
    public GetServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String path=(String)request.getSession().getAttribute("currentPath");
		File cp=new File(path);
		if(!cp.exists()) cp.mkdirs();
		ArrayList<File> files=new ArrayList<File>(Arrays.asList(cp.listFiles()));
		request.setAttribute("files", files);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}