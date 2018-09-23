package pg.grigaliunas.paulius.skatink;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

        public static final String DARABASE_NAME = "Skatink.db";
        public static final String Table_Parent = "parent";
        public static final String Col_ID = "ID";
        public static final String Col_username = "username";
        public static final String Col_password = "password";
        public static final String Col_name = "name";
        public static final String Col_surname = "surname";
        public static final String Col_email = "email";
        public static final String Col_phone = "phone";


        public DatabaseHelper(Context context) {
                super(context,DARABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL("Create Table " + Table_Parent + " (ID INTEGER PRIMARY KEY , username, password, name, surname, email, phone )"
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("Drop Table if Exists " + Table_Parent );
                onCreate(db);
        }
}
}
