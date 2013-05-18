package userbase;

import static org.junit.Assert.assertNotNull;

import java.net.UnknownHostException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class ConnectionTest {
	

	
	private static Mongo mongo;
	
	@BeforeClass
	public static void init() throws UnknownHostException, MongoException {
		getMongo();
	}
	
	
	private static Mongo getMongo() throws UnknownHostException, MongoException {
		if (mongo == null) {
			mongo = new Mongo("localhost:27017");
		}
		return mongo;
	}
	
	@Test
	public void testConnectionCreate() throws UnknownHostException, MongoException{		
		assertNotNull(getMongo());
	}
	
	@Test
	public void testGetDB() throws UnknownHostException, MongoException {
		DB db = getMongo().getDB("informit");
		Set<String> collectionNames = db.getCollectionNames();
	    for (String collection: collectionNames) {
	    	System.out.println("Collection : "+collection);
	    }
	    if (db.collectionExists("users")) { // clean up database
	    	DBCollection collection = db.getCollection("users");
	    	collection.remove(new BasicDBObject());
	    }
	    DBCollection collection = db.createCollection("users", null);
	    
        BasicDBObject doc = new BasicDBObject();
        BasicDBObject tags = new BasicDBObject();
        tags.put("one",null);
        tags.put("two",null);
        doc.put( "firstName", "Steven" );
        doc.put( "lastName", "Haines" );
        doc.put( "age", 39 );
        doc.put( "picture", new byte[] {1,2,3} );
        doc.put( "tags", tags);
        collection.insert( doc );  

        // Find all objects
        DBCursor cursor = collection.find();
        while( cursor.hasNext() )
        {
            System.out.println( cursor.next() );
        }
	}

}
