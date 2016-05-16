package mongo.Insert;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mongo.MongoUtils;
import mongo.TestCollectionUtils;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by seb on 16.05.16.
 */
public class InsertBulkComplexObject {

    private final static Logger log = Logger.getLogger(SimpleInsert.class.getName());
    private final static int K = 1000;
    private final static int HK = 100000;
    private final static int M = 1000000;
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> collection;


    @Before
    public void insertDatas () {
        client = MongoUtils.getMongoClient();
        db = MongoUtils.getDatabase(client);

        collection = MongoUtils.getCollectionFor(db, MongoUtils.Collections.TEST);
        collection.drop();

        TestCollectionUtils.insertBulkComplexUser(HK, collection);
    }

    @Test
    public void testDatasInsert () {

        long size = collection.count();
        assertTrue(size == HK);

    }


    @After
    public void clean () {
        client.close();
    }
}
