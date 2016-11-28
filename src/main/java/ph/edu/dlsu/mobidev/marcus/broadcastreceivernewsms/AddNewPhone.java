package ph.edu.dlsu.mobidev.marcus.broadcastreceivernewsms;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidexample.broadcastreceiver.R;

public class AddNewPhone extends AppCompatActivity {
    Button addDB;
    EditText etPhone;
    EditText etMessage;

    //PhoneAdapter studentAdapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_phone);

        addDB= (Button) findViewById (R.id.button_add);
        etPhone= (EditText) findViewById(R.id.et_phone);
        etMessage= (EditText) findViewById(R.id.et_message);

        final Phone p = new Phone();





        addDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), MainActivity.class);
                startActivityForResult(i, 0);

                //s.setName("Marcus Ko");
//        s.setAge(20);
//        s.setCourse("BS IS");
//
//        dbHelper.insertStudent(s);
                String senderNum = etPhone.getText().toString();
                String message = etMessage.getText().toString();

                //sending the data back create an intent renamed intent to data
                // Intent data = new Intent();
                Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_LONG).show();
                //set extras to intent
                DatabaseHelper dbHelper = new DatabaseHelper(getBaseContext());
                i.putExtra(MainActivity.EXTRA_PHONE, senderNum);
                i.putExtra(MainActivity.EXTRA_MESSAGE, message);

                //send intent back result code is ok or cancel
                //        Student s = new Student();
                p.setSenderNum(senderNum);
                p.setMessage(message);

                dbHelper.insertPhone(p);
                setResult(RESULT_OK,i);
                finish() ;
                //        s.setName("Pikachu");
                //  s.setAge(20);
                //    s.setCourse("BS IS");

//      dbHelper.insertStudent(s);



            }

        });

//        addDB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //get  the input of the user as favorite fruit
//                String name = etName.getText().toString();
//                String age = etAge.getText().toString();
//                String course = etCourse.getText().toString();
//                //sending the data back create an intent renamed intent to data
//                Intent data = new Intent();
//                //set extras to intent
//                data.putExtra(MainActivity.EXTRA_NAME, name);
//                data.putExtra(MainActivity.EXTRA_AGE, age);
//                data.putExtra(MainActivity.EXTRA_COURSE, course);
//
//                //send intent back result code is ok or cancel
//                setResult(RESULT_OK,data);
//                finish();
//            }
//        });









        //we can add it because we can use it anytime
        // do not start activity for result do it in add Student Activity Homework
        //create the edit text + button
        //when user clicks on submit/done
        /*
        * get inputs from user
        *
        * create a new Student from inputs
        *
        * create a DatabaseHelper object to addStudent






         * */

    }
}
