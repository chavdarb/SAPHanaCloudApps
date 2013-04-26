package hello;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;

/**
 * Servlet implementation class HelloWorldServlet
 */
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorldServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			InitialContext ctx = new InitialContext();
			UserProvider userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");
			User user = null;
			if (request.getUserPrincipal() != null) {
				user = userProvider.getUser(request.getUserPrincipal().getName());
				if (user != null) {
					response.getWriter().println("Logged in user: "+user.getName());
					response.getWriter().println("firstName: "+user.getAttribute("fistname"));
					response.getWriter().println("lastName: "+user.getAttribute("lastname"));
					response.getWriter().println("email: "+user.getAttribute("email"));
					response.getWriter().println("roles: "+user.getRoles().toString());
				} else {
					response.getWriter().println("Hello , World !");
				}
			}
		} catch (Exception x) {
			x.printStackTrace(response.getWriter());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
