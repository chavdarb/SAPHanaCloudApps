package userbase;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import userbase.data.UserProfile;
import userbase.data.UserProfileDAO;

/**
 * Servlet implementation class UserUpsertServlet
 */
public class UserUpsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpsertServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		// Parse the request
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new ServletException("Exception while uploading the file");
		}
		UserProfile userProfile = new UserProfile();
		for (FileItem diskFileItem : items) {
			// Exclude the form fields
			diskFileItem.getContentType();
			if (diskFileItem.isFormField()) {				
				String fieldName = diskFileItem.getFieldName();
				String fieldValue = diskFileItem.getString();
				if (UserProfileDAO.USER_NAME.equals(fieldName)) {
					userProfile.setUserName(fieldValue);
				}
				if (UserProfileDAO.EMAIL.equals(fieldName)) {
					userProfile.setEmail(fieldValue);
				}
				if (UserProfileDAO.FIRSTNAME.equals(fieldName)) {
					userProfile.setFirstName(fieldValue);
				}
				if (UserProfileDAO.LASTNAME.equals(fieldName)) {
					userProfile.setLastName(fieldValue);
				}
			} else {
				if (UserProfileDAO.PHOTO.equals(diskFileItem.getFieldName())) {
					if (diskFileItem.getSize() != 0) {
					  userProfile.setPhotoContentType(diskFileItem.getContentType());
					  userProfile.setPhoto(diskFileItem.get());
					}
				}
			}
		}
		if (userProfile.getUserName() != null && userProfile.getUserName().trim().isEmpty() == false) {
			UserProfileDAO.getInstance().upsertUser(userProfile);
		}
		response.sendRedirect("/ListUsers.jsp");
	}

}
