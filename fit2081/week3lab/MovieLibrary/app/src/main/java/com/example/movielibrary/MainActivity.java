package com.example.movielibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private TextView textview;
    private EditText actornumber;
    final String TAG="lab3";

//    final String FILE_NAME ="data.dst";
    final String KEY_TITLE ="TITLE";
    final String KEY_GENRE ="GENRE";
    final String KEY_YEAR ="YEAR";
    final String KEY_COUNTRY ="COUNTRY";
    final String KEY_COST ="COST";
    final String KEY_KEYWORDS ="KEYWORDS";
    final String KEY_NUMBER_OF_ACTORS ="NUMBER_OF_ACTORS";
    String getKEY_TITLE,getKEY_GENRE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //pairing keys.
        //? why using keys in android studio?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName4);//title
        editText1 = findViewById(R.id.editTextTextPersonName5);//year
        editText2 = findViewById(R.id.editTextTextPersonName6);// country
        editText3 = findViewById(R.id.editTextTextPersonName7);//genre
        editText4 = findViewById(R.id.editTextTextPersonName8);//cost
        editText5 = findViewById(R.id.editTextTextPersonName9);//keywords
        actornumber=findViewById(R.id.editTextTextPersonName);//number of actors
//         sP=getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//         this.getKEY_TITLE=sP.getString(KEY_TITLE,"DEFAULT");//
//        this.getKEY_GENRE=sP.getString(KEY_GENRE,"DEFAULT");
//        editText.setText(this.getKEY_TITLE);
//        editText3.setText(this.getKEY_GENRE);
//what do set text for ? set default
// go back and look at hte output video
        //how to use shared perference?
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        restore();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences sP=getSharedPreferences("sp_key",MODE_PRIVATE);
        SharedPreferences.Editor editor=sP.edit();
        editor.putString(KEY_TITLE,editText.getText().toString());
        editor.putString(KEY_GENRE,editText3.getText().toString().toLowerCase());
        editor.apply();


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SharedPreferences sP=getSharedPreferences("sp_key",MODE_PRIVATE);
        editText.setText(sP.getString(KEY_TITLE,"").toUpperCase(Locale.ROOT));
        editText3.setText(sP.getString(KEY_GENRE,""));

    }


//add movie
    public void displayToast(View view){
        String movieTitle = editText.getText().toString();
        Toast.makeText(this
                ,"Movie-"+movieTitle+"-has been added",Toast.LENGTH_LONG)
                .show();
        save();

    }

    public void showToast(View view){
        String nya = "title"+editText.getText().toString()+"year"+editText1.getText().toString()+"Number"+actornumber.getText().toString();
        Toast.makeText(this
                ,nya,Toast.LENGTH_LONG)
                .show();

    }

    public void save(){
        SharedPreferences sP=getSharedPreferences("sp_key",MODE_PRIVATE);
        SharedPreferences.Editor editor =sP.edit();
        editor.putString(KEY_TITLE,editText.getText().toString());
        editor.putString(KEY_GENRE,editText3.getText().toString().toLowerCase());
        editor.putString(KEY_YEAR,editText1.getText().toString());
        editor.putString(KEY_COUNTRY,editText2.getText().toString());
        editor.putString(KEY_COST,editText4.getText().toString());
        editor.putString(KEY_KEYWORDS,editText5.getText().toString());
        editor.putString(KEY_NUMBER_OF_ACTORS,actornumber.getText().toString());
        editor.apply();

    }
    public void restore(){
        SharedPreferences sP=getSharedPreferences("sp_key",MODE_PRIVATE);
        editText.setText(sP.getString(KEY_TITLE,"").toUpperCase(Locale.ROOT));
        editText3.setText(sP.getString(KEY_GENRE,""));
        editText1.setText(sP.getString(KEY_YEAR,""));
        editText2.setText(sP.getString(KEY_COUNTRY,""));
        editText4.setText(sP.getString(KEY_COST,""));
        editText5.setText(sP.getString(KEY_KEYWORDS,""));
        actornumber.setText(sP.getString(KEY_NUMBER_OF_ACTORS,""));

    }

    public void clear(View view){
        editText.setText("");
        editText3.setText("");
        editText1.setText("");
        editText2.setText("");
        editText4.setText("");
        editText5.setText("");
        actornumber.setText("");

    }
}