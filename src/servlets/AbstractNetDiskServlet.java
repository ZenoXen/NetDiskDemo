package servlets;
import beans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/BaseNetDiskServlet")
public abstract class AbstractNetDiskServlet extends HttpServlet {
    protected AbstractNetDiskServlet() {
        super();
    }
	protected void setError(HttpServletRequest req, String err, boolean val) {
		req.setAttribute(err, val);
	}
	abstract protected void setErrors(HttpServletRequest request);
}