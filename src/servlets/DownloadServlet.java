package servlets;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/DownloadServlet",loadOnStartup=9)
public class DownloadServlet extends HttpServlet {
    public DownloadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		String path=(String)request.getSession().getAttribute("currentPath");
		String fileName=request.getParameter("fileName");
		response.addHeader("content-type","application/x-msdownload"); 
		response.addHeader("content-Disposition", "attachment;filename="+fileName);
		FileInputStream fis=new FileInputStream(path+"\\"+fileName);
		byte[] buf=new byte[1024];
		int len=-1;
		OutputStream os=response.getOutputStream();
		while((len=fis.read(buf))!=-1)
			os.write(buf,0,len);
		fis.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
