package userbase;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userbase.data.UserProfile;
import userbase.data.UserProfileDAO;

/**
 * Servlet implementation class UserPhoto
 */
public class UserPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("user");
		UserProfile userProfile = null;
		if (userName != null) {
			userProfile = UserProfileDAO.getInstance().getUserByName(userName);
		}
		if (userProfile != null && userProfile.getPhoto() != null) {
			response.setStatus(200);
			response.setContentType(userProfile.getPhotoContentType());
			response.setContentLength(userProfile.getPhoto().length);
			response.getOutputStream().write(userProfile.getPhoto());
			response.getOutputStream().flush();	
		}
	}

}
