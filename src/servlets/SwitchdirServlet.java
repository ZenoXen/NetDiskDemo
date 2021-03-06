package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/SwitchdirServlet",loadOnStartup=3)
public class SwitchdirServlet extends HttpServlet {

    public SwitchdirServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String dir=request.getParameter("targetDir");
		String ori=(String)request.getSession().getAttribute("currentPath");
		request.getSession().setAttribute("currentPath", ori+"\\"+dir);
		request.getRequestDispatcher("GetServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
