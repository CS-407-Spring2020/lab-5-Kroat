package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class writenote extends AppCompatActivity {

    int noteid = -1;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writenote);
        EditText editText = (EditText) findViewById(R.id.textarea);
        noteid = getIntent().getIntExtra("noteid", -1);
        if (noteid != -1) {
            Notes note = Notepad.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void toDB(View view){
        Log.e("New note created", "test");
        Log.e("Note od", "t" + noteid);
        textField = (EditText) findViewById(R.id.textarea);
        String note = textField.getText().toString();
        SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("notes",
                Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        String content = ((EditText) findViewById(R.id.textarea)).getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        if(noteid == -1){
            title = "NOTE_" + (Notepad.notes.size() + 1);
            dbHelper.saveNotes(username, title, note, date);
        }
        else{
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, note);
        }
        Intent intent = new Intent(this, Notepad.class);
        startActivity(intent);
    }
}
