package servlets;
import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/DeleteServlet",loadOnStartup=10)
public class DeleteServlet extends HttpServlet {
	private void deleteRecursively(File dir) {
		File[] all=dir.listFiles();
		for(File f:all) {
			if(f.isDirectory())
				deleteRecursively(f);
			f.delete();
		}
		dir.delete();
	}
    public DeleteServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String fileName=request.getParameter("fileName");
		String path=(String)request.getSession().getAttribute("currentPath");
		File tar=new File(path+"\\"+fileName);
		if(tar.exists()&&!tar.isDirectory())
			tar.delete();
		else if(tar.isDirectory())
			deleteRecursively(tar);
		request.getRequestDispatcher("GetServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
