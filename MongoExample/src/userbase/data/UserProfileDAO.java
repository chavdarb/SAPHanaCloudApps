package userbase.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class UserProfileDAO {

	public static final String LASTNAME = "lastname";
	public static final String FIRSTNAME = "firstname";
	public static final String EMAIL = "email";
	public static final String USER_NAME = "userName";
	public static final String PHOTO = "photo";
	public static final String PHOTO_CONTENT_TYPE = "photoContentType";
	private static final String _ID = "_id";

	public static UserProfileDAO getInstance() throws IOException {
		return new UserProfileDAO();
	}

	private DB database;

	private UserProfileDAO() throws IOException {
		this.database = MongoFactory.getInstance().getDB("geeky");
		if (!this.database.collectionExists("users")) {
			this.database.createCollection("users", null);
		}
	}

	public void upsertUser(UserProfile profile) {
       DBCollection collection = database.getCollection("users");
       BasicDBObjectBuilder userDBObject = getUserDBObject(profile);
       collection.save(userDBObject.get());
	}
	
	public List<UserProfile> getAllUsers() {
		   List<UserProfile> result = new ArrayList<UserProfile>();
	       DBCollection collection = database.getCollection("users");
	       // Execute the query
	       DBCursor cursor = collection.find();
	       while( cursor.hasNext() ) {
	            result.add( getUserProfileObject( cursor.next() ) );
	        }
	       return result;
	}
	
	private UserProfile getUserProfileObject(DBObject dbObject) {
		UserProfile userProfile = new UserProfile();
		userProfile.setUserName((String) dbObject.get(USER_NAME));
		userProfile.setEmail((String) dbObject.get(EMAIL));
		userProfile.setFirstName((String) dbObject.get(FIRSTNAME));
		userProfile.setLastName((String) dbObject.get(LASTNAME));
		userProfile.setPhoto((byte[]) dbObject.get(PHOTO));
		userProfile.setPhotoContentType((String) dbObject.get(PHOTO_CONTENT_TYPE));
		return userProfile;
	}

	private BasicDBObjectBuilder getUserDBObject(UserProfile userProfile) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start();
		addIfNotNull(_ID,userProfile.getUserName(),builder);
		addIfNotNull(USER_NAME, userProfile.getUserName(),builder);
		addIfNotNull(EMAIL, userProfile.getEmail(),builder);
		addIfNotNull(FIRSTNAME, userProfile.getFirstName(),builder);
		addIfNotNull(LASTNAME, userProfile.getLastName(),builder);
		addIfNotNull(PHOTO, userProfile.getPhoto(), builder);
		addIfNotNull(PHOTO_CONTENT_TYPE, userProfile.getPhotoContentType(), builder);
        return builder;
	}
	
	private void addIfNotNull(String key, Object value, BasicDBObjectBuilder builder) {
		if (value != null) {
			builder.add(key,value);
		}
	}

	public UserProfile getUserByName(String userName) {
		 DBCollection collection = database.getCollection("users"); 
		 BasicDBObjectBuilder dbObjectBuilder = BasicDBObjectBuilder.start();
		 dbObjectBuilder.add(_ID, userName);
		 DBCursor cursor = collection.find(dbObjectBuilder.get());
		 if (cursor.hasNext()) {
			 return getUserProfileObject(cursor.next());
		 } else {
			 return null;
		 }
	}
	
	public void removeUser(String userName) {
		 DBCollection collection = database.getCollection("users"); 
		 BasicDBObjectBuilder dbObjectBuilder = BasicDBObjectBuilder.start();
		 dbObjectBuilder.add(_ID, userName);
		 collection.remove(dbObjectBuilder.get());
	}
	
	public static void main(String[] args) throws IOException {
		UserProfile user = new UserProfile();
		user.setUserName("chavdarb");
		user.setEmail("chavdarb@yahoo.com");
		user.setFirstName("Chavdar");
		user.setLastName("Baikov");
		UserProfileDAO userDAO = UserProfileDAO.getInstance();
		List<UserProfile> users = userDAO.getAllUsers();
		System.out.println(users.size());
		/*
		userDAO.upsertUser(user);
		users = userDAO.getAllUsers();
		System.out.println(users.size());*/
		UserProfile savedUser = userDAO.getUserByName("ivan");
		//userDAO.removeUser("chavdarb");
		users = userDAO.getAllUsers();
		System.out.println(users.size());
	}

}
