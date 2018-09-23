package pg.grigaliunas.paulius.skatink;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

        public static final String DATABASE_NAME = "Skatink.db";
        public static final String Table_Parent = "parent";
        public static final String Table_Child = "child";
        public static final String Col_ID = "ID";
        public static final String Col_username = "username";
        public static final String Col_password = "password";
        public static final String Col_name = "name";
        public static final String Col_surname = "surname";
        public static final String Col_email = "email";
        public static final String Col_phone = "phone";
        public static final String Col_parent_ID = "parent_ID";
        public static final String Col_points = "points";


        public DatabaseHelper(Context context) {
                super(context,DATABASE_NAME, null, 1);
            SQLiteDatabase db = this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL("Create Table " + Table_Parent + " (" +
                        "ID INTEGER PRIMARY KEY ," +
                        "username, " +
                        "password, " +
                        "name, " +
                        "surname, " +
                        "email, " +
                        "phone )");

                db.execSQL("Create Table " + Table_Child + " (" +

                        "ID INTEGER PRIMARY KEY ," +
                        "parent_ID, " +
                        "username, " +
                        "password, " +
                        "name, " +
                        "points, " +
                        "FOREIGN KEY(parent_ID) REFERENCES Table_Parent(ID) )");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("Drop Table if Exists " + Table_Parent );
                db.execSQL("Drop Table if Exists " + Table_Child );
                onCreate(db);
        }
        public boolean insertData (String userName, String password, String name, String surname, String email, String phone){
                SQLiteDatabase db = this.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(Col_username, userName);
                contentValues.put(Col_password, password);
                contentValues.put(Col_name, name);
                contentValues.put(Col_surname, surname );
                contentValues.put(Col_email, email);
                contentValues.put(Col_phone, phone );
                long result = db.insert(Table_Parent, null, contentValues);
                return (result == -1 )? false: true;
        }
}

