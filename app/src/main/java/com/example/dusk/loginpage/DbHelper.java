package com.example.dusk.loginpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;


/*CREATED BY TRAJON FELTON
* 02/27/18
* Purpose: Model Class of the Database with added methods.*/

/*
* TO DO LIST
 * ADD A VERIFICATION METHOD FOR THE LOGIN PAGE
 * ADD A MODIFICATION METHOD FOR EDIT CONTACT PAGE
 * CHECK SIGN UP PAGE FOR COLUMN DATA INFORMATION
 *
 * **SAVE DATA UNDER A USERNAME FROM TASK ACTIVITIES ON MAIN PAGE**
* */


public class DbHelper extends SQLiteOpenHelper {

    //Constant Names
    private static final String DATABASE_NAME = "OrganizeMyLife.db";
    private static final String USER_TABLE = "user_table";

    private static final String UTCOL1 = "ID";
    private static final String UTCOL2 = "USERNAME";
    private static final String UTCOL3 = "EMAIL";
    private static final String UTCOL4 = "PASSWORD";
    private static final String UTCOL5 = "FULLNAME";


    private static final String TASK_TABLE = "TASK";
    private static final String TTCOL1 = "taskName";
    private static final String TTCOL2 = "taskDesc";
    private static final String TTCOL3 = "taskHour";
    private static final String TTCOL4 = "taskMin";

    //Constructor
    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    //OnCreate Method
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + USER_TABLE + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "USERNAME NOT NULL UNIQUE, " +
                "EMAIL NOT NULL, " +
                "PASSWORD NOT NULL, " +
                "FULLNAME NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(createTable);
        String createAnother = "CREATE TABLE " + TASK_TABLE + " (" +
                "USERNAME NOT NULL PRIMARY KEY," +
                "taskName NOT NULL, " +
                "taskDesc, " +
                "taskHour NOT NULL, " +
                "taskMin NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(createAnother);
    }

    //onUpgrade Method
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(sqLiteDatabase);
    }

    /*
    * addData Method
    * Purpose: Add data into the database
    * Parameters: Username, Password
    * */
    public boolean addUser(Accounts acc){
        SQLiteDatabase db = this.getWritableDatabase(); //Use this to edit the table

        //ContentValues is used ot create an object to put into the insert method
        ContentValues contentValues = new ContentValues();
        contentValues.put(UTCOL2,acc.get_username());
        contentValues.put(UTCOL3,acc.get_email());
        contentValues.put(UTCOL4,acc.get_password());
        contentValues.put(UTCOL5,acc.get_fullname());

        //Insert is a SQL Method, basically puts it into the database
        long result = db.insert(USER_TABLE, null, contentValues);

        //This Checks To See if the data was added correctly
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean loginverification(String username, String pwd){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = String.format("SELECT * FROM USER_TABLE WHERE USERNAME = \'%s\' AND PASSWORD = \'%s\'",username,pwd);
        Cursor result = db.rawQuery(Query,null);
        if(result.getCount() >= 1) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean registrationverification(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = String.format("SELECT USERNAME FROM USER_TABLE WHERE USERNAME = \'%s\'",username);
        Cursor result = db.rawQuery(Query,null);
        if(result.getCount() >= 1) {
            return false;
        }
        else {
            return true;
        }
    }
    public boolean addTask(String taskName, String taskDesc, String taskHour, String taskMin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(taskName, TTCOL1);
        cv.put(taskDesc,TTCOL2);
        cv.put(taskHour,TTCOL3);
        cv.put(taskMin,TTCOL4);

        long result = db.insert(TASK_TABLE,null,cv);
        if(result == -1){
            return false;
        }
        else return true;
    }

    public ArrayList loadTaskList(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = String.format("SELECT * FROM TASK_TABLE WHERE USERNAME = \'%s\'",username);
        ArrayList<CardsJava> eventList = new ArrayList<>();

        Cursor result = db.rawQuery(Query,null);
        while(result.moveToNext()){
            String name = result.getString(result.getColumnIndex(TTCOL2));
            String hour = result.getString(result.getColumnIndex(TTCOL3));
            String min = result.getString(result.getColumnIndex(TTCOL4));
            eventList.add(new CardsJava(name,hour,new DecimalFormat("00").format(min)));
        }
        return eventList;
    }
}

