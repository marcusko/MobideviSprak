package ph.edu.dlsu.mobidev.marcus.broadcastreceivernewsms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by marcus on 10/26/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "school";
    public static final int VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
        //replace name with schema
        //cursors are equivalent to result sets
        //version will update the version for us
        //deleted the ones we dont use

    }

    @Override


// onupgrade and create will not be called when the table exists
    // increasing the version number always goes up if there is an upgrade
    public void onCreate(SQLiteDatabase db) {
        // our connection ^
//create tables here
        //only called once when db doesn't exist yet
        //changes happen on onCreate
// CREATE TABLE student
        //( id INTEGER PRIMARY KEY AUTOINCREMENT ,
        // name TEXT ,
        // age INTEGER,
        // course TEXT);

        // String sql = "CREATE TABLE student"    ;
        String sql = "CREATE TABLE " + Phone.TABLE
                + " ( " + Phone.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Phone.ID + " TEXT,"
                + Phone.NUM + " INTEGER, "
                + Phone.MESSAGE + " TEXT);";

        db.execSQL(sql);

// space on the name of column and data type must be made


        //notice the space          ^
        //more maintainable if placed in a variable
        //made into variable
        //sql= "SELECT * FROM" + TABLE;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//new version go to onUpgrade
        //upgrade the schema
        //drop the table if exists
        //then call onCreate
        String sql = "DROP TABLE IF EXISTS" + Phone.TABLE + ";";
        db.execSQL(sql);
        //call onCreate()
        onCreate(db);

        // CRUD = Create, retrieve, update, delete


    }

    public void insertPhone(Phone p) {
        // VALUES ("MARCUS" ,20, "ASDAS");
        // INSERT INTO student (name, age , course)
        SQLiteDatabase db = getWritableDatabase();
        //write because create

        ContentValues cv = new ContentValues();
        //put this
        cv.put(Phone.ID, p.getId());
        cv.put(Phone.NUM, p.getSenderNum());
        cv.put(Phone.MESSAGE, p.getMessage());


        db.insert(Phone.TABLE, null, cv);
        //entering 3 parameters


    }

    public void deletePhone(int id) {
        // DELETE FROM Student WHERE id = ?;
        // prepared statements
        SQLiteDatabase db = getWritableDatabase();

        db.delete(Phone.TABLE,
                Phone.ID + "=? ",
                new String[]{id + ""});


    }

    public void updatePhoe(Phone p) {
        //UPDATE student SET name= ?,
        //                      age =?,
        //                     course=?
        //          WHERE id = ?;
        //never allow the user to change the id
        SQLiteDatabase db = getWritableDatabase();
        //content values create a cv
        ContentValues cv = new ContentValues();
        cv.put(Phone.ID, p.getId());
        cv.put(Phone.NUM, p.getSenderNum());
        cv.put(Phone.MESSAGE, p.getMessage());

        db.update(Phone.TABLE, cv,
                Phone.ID + "=? ",
                new String[]{p.getId() + ""});
        //changes banking on the student, dont tell them of their id number


    }
    //resolve the error after
//sql lite is an interface
    // [1, "Juan"] selection arguments
//student attributes
    //idStudent integer
    //name text
    //age integer
    // course text

    // retrieve -> a single student
    //Student because we need to get it
    public Phone retrievePhone(String id) {
        Phone phone = new Phone();
        // SELECT * FROM student WHERE id= ?;
        SQLiteDatabase db = getReadableDatabase();
        //query // enter the parameters
        //query returns a cursor
        Cursor cursor = db.query(Phone.TABLE,
                null, // equivalent to *
                Phone.ID + " =?",
                new String[]{id + ""},
                null,
                null,
                null);

        phone = new Phone();
        // move to first gives false if empty
        if (cursor.moveToFirst()) {
            //it means that there is a match!
            // we transfer the db data to our student object
            phone.setSenderNum(
                    cursor.getString(cursor.getColumnIndex(Phone.NUM))
            );
            // assign this name to Student

            phone.setMessage(
                    cursor.getString(cursor.getColumnIndex(Phone.MESSAGE))

            );

            phone.setId(id);
        }

        return phone;


    }
    public Cursor retrieveAllStudentsCursor() {
        SQLiteDatabase db= getReadableDatabase();



        return db.query(Phone.TABLE,
                null,
                null,
                null,
                null,
                null,
                null);
    }



    //retrieve -> a list of students
    public ArrayList<Phone> retrieveAllPhones() {
        ArrayList<Phone> phoneArrayList
                = new ArrayList<>();
// SELECT * FROM student;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Phone.TABLE,
                null,// columns
                null,// selection
                null,// selection args
                null,// group by
                null,// having
                null// order by
        );

        if(cursor.moveToFirst()) {
            do {
                Phone phone = new Phone();

                phone.setSenderNum(
                        cursor.getString(cursor.getColumnIndex(Phone.NUM))
                );
                // assign this name to Student

                phone.setMessage(
                        cursor.getString(cursor.getColumnIndex(Phone.MESSAGE))

                );

                phoneArrayList.add(phone);
            }while(cursor.moveToNext());
        }


        return phoneArrayList;
    }

}

