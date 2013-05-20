package userbase.data;

import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoFactory {
	private static Mongo mongo;
	
	public static synchronized Mongo getInstance() throws UnknownHostException, MongoException {
		if (mongo == null) {
		  mongo = new Mongo("localhost", 27017);
		} 
		return mongo;
	}
}
