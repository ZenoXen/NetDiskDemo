package servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(value="/UploadServlet",loadOnStartup=1)
public class UploadServlet extends AbstractNetDiskServlet {
    public UploadServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //取出监听器MyProgress在session中保存的进度信息
        String progress=(String) req.getSession().getAttribute("progress");
        //响应
        resp.getWriter().print(progress);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        upload.setProgressListener(new MyProgressListener(req));
        try {
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {//普通表单
                }else{
                    String path=(String)req.getSession().getAttribute("currentPath");
                    String fileName=fileItem.getName();
                    File file=new File(path, fileName);
                    fileItem.write(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	@Override
	protected void setErrors(HttpServletRequest request) {
	}
}
