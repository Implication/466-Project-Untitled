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

    //Constant Database Name
    private static final String DATABASE_NAME = "OrganizeMyLife.db";

    //USER TABLE COLUMN NAMES
    private static final String USER_TABLE = "user_table";
    private static final String UTCOL1 = "ID";
    private static final String UTCOL2 = "USERNAME";
    private static final String UTCOL3 = "EMAIL";
    private static final String UTCOL4 = "PASSWORD";
    private static final String UTCOL5 = "FULLNAME";

    //TASK TABLE COLUMN NAMES
    private static final String TASK_TABLE = "TASK";
    private static final String TTCOL1 = "taskName";
    private static final String TTCOL2 = "taskDesc";
    private static final String TTCOL3 = "taskHour";
    private static final String TTCOL4 = "taskMin";

    //Constructor
    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    //OnCreate Method, Create the tables
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
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "USERNAME NOT NULL, " +
                "taskName NOT NULL, " +
                "taskDesc, " +
                "taskHour NOT NULL, " +
                "taskMin NOT NULL, " +
                "FOREIGN KEY(USERNAME) REFERENCES user_table(USERNAME)" +
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

    /*
    * loginverifcation method
    * purpose: Validate the login credentials
    * paramaters: Username, password
    * returns false if credentials do not match in the database, otherwise returns true
    * */
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

    /*
    * registrationverification
    * Purpose: Verifies a username is not in the database to use for registration
    * paramaters: username
    * returns false if in the  database, otherwise returns true
    * */
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

    /**
     * addTask method
     * Purpose: Add tasks into the TASK table
     * Paramaters: taskName, taskDesc, taskHour, taskMin
     */
    
    public boolean addTask(String username,String taskName, String taskDesc, String taskHour, String taskMin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(UTCOL2, username);
        cv.put(TTCOL1,taskName);
        cv.put(TTCOL2,taskDesc);
        cv.put(TTCOL3,taskHour);
        cv.put(TTCOL4,taskMin);

        long result = db.insert(TASK_TABLE,null,cv);
        if(result == -1){
            return false;
        }
        else return true;
    }


    /*
    * loadTaskList
    * purpose: Loads the tasks into the main activity
    * paramaters: username
    * Returns an arraylist of all the tasks associated with a username in the task table
    * */
    public ArrayList loadTaskList(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = String.format("SELECT * FROM TASK WHERE USERNAME = \'%s\'",username);
        ArrayList<CardsJava> eventList = new ArrayList<>();

        Cursor result = db.rawQuery(Query,null);
        if(result != null) {
            while (result.moveToNext()) {
                String name = result.getString(result.getColumnIndex(TTCOL1));
                String hour = result.getString(result.getColumnIndex(TTCOL3));
                String min = result.getString(result.getColumnIndex(TTCOL4));
                eventList.add(new CardsJava(name, hour, new DecimalFormat("00").format(Integer.parseInt(min))));
            }
        }
            return eventList;
    }

    /*
    * updateDatabase
    * purpose: Updates the database based on user input in the settings activity
    * paramaters: un, email, pwd, fullname
    * */
    public void updateDatabase(String un, String email, String pwd, String fullname){
        SQLiteDatabase db = this.getWritableDatabase();

        String Query = "UPDATE USER_TABLE " +
                "SET FULLNAME = \'" + fullname + "\', " +
                "EMAIL = \'" + email + "\', " +
                "PASSWORD = \'" + pwd + "\' " +
                "WHERE USERNAME = '" + un + "\'";
        db.execSQL(Query);
    }


    /*
    * SettingsValues
    * Purpose: Loads the settings activity with values in the database
    * paramaters: un
    * Returns an arraylist containing the fullname, email, and password associated with a username
    * in the user table
    * */
    public ArrayList SettingsValues(String un) {
        SQLiteDatabase db = this.getReadableDatabase();

        String Query = String.format("SELECT * FROM USER_TABLE WHERE USERNAME = \'%s\'", un);
        Cursor result = db.rawQuery(Query, null);
        ArrayList list = new ArrayList();
        if (result != null) {
            while (result.moveToNext()) {
                list.add(result.getString((result.getColumnIndex(UTCOL5))));
                list.add(result.getString((result.getColumnIndex(UTCOL3))));
                list.add(result.getString((result.getColumnIndex(UTCOL4))));
            }
        }
        return list;
    }
}

