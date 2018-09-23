package pg.grigaliunas.paulius.skatink;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {

    private static DatabaseManager instance;
    private static SQLiteOpenHelper openHelper;
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteDatabase myDb;

    public static synchronized void initialiazeInstance (SQLiteOpenHelper helper) {
        if (instance == null) {
            instance = new DatabaseManager();
            openHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance(){
            if (instance == null ){
                throw new IllegalStateException(DatabaseManager.class.getSimpleName() + "is not initialized!");
            }
        return instance;

    }

    public synchronized void OpenDatabase(){
        if (mOpenCounter.incrementAndGet() == 1){
            myDb = openHelper.getWritableDatabase();
        }
    }

    public synchronized void CloseDatabase(){
        if (mOpenCounter.decrementAndGet() == 0){
            myDb.close();
        }
    }

    public Cursor getDetails(String query){
        return myDb.rawQuery(query, null);
    }

    public boolean insert (String tablename, ContentValues values){
        long l = - 1;
        try {
            l = myDb.insert(tablename, null, values);
        }
        catch (SQLiteException e){
            e.printStackTrace();
        }
        return l != -1;
    }

    public boolean delete (String tablename){
        try {
            myDb.delete(tablename, null, null);
        }
        catch (SQLiteException e){
            e.printStackTrace();
        }
        return true;
    }


}
