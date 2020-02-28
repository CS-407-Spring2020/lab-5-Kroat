package c.sakshi.lab5;

import android.database.sqlite.SQLiteDatabase;
import java.util.*;
import android.database.*;

public class DBHelper{

    SQLiteDatabase sqLiteDatabase;
    public DBHelper (SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void createTable(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes " + "(id INTEGER PRIMARY KEY, username TEXT, date TEXT, title TEXT, content TEXT, src TEXT)");
    }

    public ArrayList<Notes> readNotes(String username){
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * from notes where username like '%s'", username), null);
        int dateIndex = c.getColumnIndex("date");
        int titleIndex = c.getColumnIndex("title");
        int contentIndex = c.getColumnIndex("content");

        c.moveToFirst();

        ArrayList<Notes> notesList = new ArrayList<>();

        while (!c.isAfterLast()){
            String title = c.getString(titleIndex);
            String date = c.getString(dateIndex);
            String content = c.getString(contentIndex);

            Notes note = new Notes(date, username, title, content);
            notesList.add(note);
            c.moveToNext();
        }

        c.close();
        sqLiteDatabase.close();

        return notesList;

    }

    public void saveNotes(String username, String title, String content, String date){
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO notes (username, date, title, content) VALUES ('%s', '%s', '%s', '%s')", username, date, title, content));
    }

    public void updateNote(String title, String date, String content){
        createTable();
        sqLiteDatabase.execSQL(String.format("UPDATE notes set content = '%s', date = '%s' where title = '%s'", content, date, title));
    }
}
