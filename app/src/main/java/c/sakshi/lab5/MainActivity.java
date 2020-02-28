package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        if(!username.equals("")){
            Intent intent = new Intent(this, Notepad.class);
            intent.putExtra("message", username);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        if(!username.equals("")){
            Intent intent = new Intent(this, Notepad.class);
            intent.putExtra("message", username);
            startActivity(intent);
        }
    }

    public void toNotes(View view){
        EditText textField = (EditText) findViewById(R.id.editText);
        String name = textField.getText().toString();
        Intent intent = new Intent(this, Notepad.class);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", name).apply();
        startActivity(intent);
    }

}
