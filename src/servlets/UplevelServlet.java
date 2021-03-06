package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/UplevelServlet",loadOnStartup=2)
public class UplevelServlet extends HttpServlet {

    public UplevelServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String cpath=(String)request.getSession().getAttribute("currentPath");
		String upath=(String)request.getSession().getAttribute("userPath");
		if(!cpath.equals(upath))
			request.getSession().setAttribute("currentPath", cpath.substring(0,cpath.lastIndexOf("\\")));
		request.getRequestDispatcher("GetServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
