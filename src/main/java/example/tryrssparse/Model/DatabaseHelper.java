package example.tryrssparse.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by redhwan on 3/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String dbName = "TimesNews";
    public static final String tblname = "news";
    public static final String colTitle = "title";
    public static final String colDetails = "details";
    public static final String colDate = "date";
    public static final String colink = "link";
    public static final String colId = "id";


    public DatabaseHelper (Context context) {
        super(context,dbName,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tblname+"("+colId+" VARCHAR,"+colTitle+" VARCHAR,"+colDetails+" VARCHAR,"+colDate+" DATE,"+colink+" TIME);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS expenses");
    }

    public Cursor getDataById (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From "+tblname+" where "+colId+"= "+id,null);

        return cursor;
    }

    public void fnExecuteSql(String strSql,Context appContext){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(strSql);
        } catch (Exception e) {
            Log.d("Unnable To Runn Query", "Error!");
        }
    }

    public  int fnTotalRow(){
        int intRow;
        SQLiteDatabase db = this.getReadableDatabase();
        intRow = (int) DatabaseUtils.queryNumEntries(db,tblname);

        return intRow;
    }
}
