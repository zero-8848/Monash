package com.example.movielibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movielibrary.provider.MovieDb;
import com.example.movielibrary.provider.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;


public class MainActivity extends AppCompatActivity {

    // week 10
    View gesture;
    View layout;
    float xDown,yDown,xUp,yUp,changeX,changeY;
    public static String DEBUG_TAG = "TestWeek10";
    //week10

    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ListView myListView;
    DatabaseReference mRef;
    DatabaseReference mRef2;


    //w5
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    Toolbar toolbar;

    EditText editText;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private TextView textview;
    private EditText actornumber;
    final String TAG = "lab3";

    //    final String FILE_NAME ="data.dst";
    final String KEY_TITLE = "TITLE";
    final String KEY_GENRE = "GENRE";
    final String KEY_YEAR = "YEAR";
    final String KEY_COUNTRY = "COUNTRY";
    final String KEY_COST = "COST";
    final String KEY_KEYWORDS = "KEYWORDS";
    final String KEY_NUMBER_OF_ACTORS = "NUMBER_OF_ACTORS";
    String getKEY_TITLE, getKEY_GENRE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //pairing keys.
        //? why using keys in android studio?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_main);
        editText = findViewById(R.id.editTextTextPersonName4);//title
        editText1 = findViewById(R.id.editTextTextPersonName5);//year
        editText2 = findViewById(R.id.editTextTextPersonName6);// country
        editText3 = findViewById(R.id.editTextTextPersonName7);//genre
        editText4 = findViewById(R.id.editTextTextPersonName8);//cost
        editText5 = findViewById(R.id.editTextTextPersonName9);//keywords
        actornumber = findViewById(R.id.editTextTextPersonName);//number of actors

        //week10
        gesture=findViewById(R.id.gesture_id);
        layout=findViewById(R.id.layout);
        gesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action= motionEvent.getActionMasked();

                switch(action) {
                    case (MotionEvent.ACTION_DOWN) :
                        xDown=motionEvent.getX();
                        yDown=motionEvent.getY();
                        Log.d(TAG, "onTouch: down");
                        return true;
                    case (MotionEvent.ACTION_MOVE) :
                        Log.d(TAG, "onTouch: move");
                        return true;
                    case (MotionEvent.ACTION_UP) :
                        xUp=motionEvent.getX();
                        yUp= motionEvent.getY();
                        changeX=xUp-xDown;
                        changeY=yUp-yDown;

                            if (changeX>0 && changeX>changeY){
                                addToDatabase();

                            }


                        else if (changeY>0 && changeY>changeX){
                                editText.setText("");
                                editText3.setText("");
                                editText1.setText("");
                                editText2.setText("");
                                editText4.setText("");
                                editText5.setText("");
                                actornumber.setText("");
                        }

                        Log.d(TAG, "onTouch: up");
                        return true;
                    default :
                        return false;
                }
            }
        });
        //week10


        //how to use shared perference?
        /* Request permissions to access SMS */
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);
        /* Create and instantiate the local broadcast receiver
           This class listens to messages come from class SMSReceiver
         */
        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

        /*
         * Register the broadcast handler with the intent filter that is declared in
         * class SMSReceiver @line 11
         * this clas is listen on this ferquency
         * */
        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER));

        //w5
        drawerlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());

//listener
//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        myListView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        myListView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListItem();
                addToDatabase();
                addFb();
                Snackbar.make(view, "Item added to list", Snackbar.LENGTH_LONG).setAction("Undo", undoOnClickListener).show();
            }
        });

    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        /*
         * This method 'onReceive' will get executed every time class SMSReceive sends a broadcast
         * */
        @Override
        public void onReceive(Context context, Intent intent) {
            /*
             * Retrieve the message from the intent
             * */
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);
            /*
             * String Tokenizer is used to parse the incoming message
             * The protocol is to have the account holder name and account number separate by a semicolon
             * */

            StringTokenizer sT = new StringTokenizer(msg, ";");//divide message, grab
            String MovieTitle = sT.nextToken();//???
            String MovieYear = sT.nextToken();
            String MovieCountry = sT.nextToken();
            String MovieGenre = sT.nextToken();
            String MovieCost = sT.nextToken();
            String MovieKeywords = sT.nextToken();
            String MovieActor = sT.nextToken();


            /*
             * Now, its time to update the UI
             * */
            editText.setText(MovieTitle);
            editText1.setText(MovieYear);
            editText2.setText(MovieCountry);
            editText3.setText(MovieGenre);
            editText4.setText(MovieCost);
            editText5.setText(MovieKeywords);
            actornumber.setText(MovieActor);
        }
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
        SharedPreferences sP = getSharedPreferences("sp_key", MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putString(KEY_TITLE, editText.getText().toString());
        editor.putString(KEY_GENRE, editText3.getText().toString().toLowerCase());
        editor.apply();


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SharedPreferences sP = getSharedPreferences("sp_key", MODE_PRIVATE);
        editText.setText(sP.getString(KEY_TITLE, "").toUpperCase(Locale.ROOT));
        editText3.setText(sP.getString(KEY_GENRE, ""));

    }


    //add movie
    public void displayToast(View view) {
        String movieTitle = editText.getText().toString();
        Toast.makeText(this
                , "Movie-" + movieTitle + "-has been added", Toast.LENGTH_LONG)
                .show();
        save();

    }

    public void showToast(View view) {
        String nya = "title" + editText.getText().toString() + "year" + editText1.getText().toString() + "Number" + actornumber.getText().toString();
        Toast.makeText(this
                , nya, Toast.LENGTH_LONG)
                .show();

    }

    public void save() {
        SharedPreferences sP = getSharedPreferences("sp_key", MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.putString(KEY_TITLE, editText.getText().toString());
        editor.putString(KEY_GENRE, editText3.getText().toString().toLowerCase());
        editor.putString(KEY_YEAR, editText1.getText().toString());
        editor.putString(KEY_COUNTRY, editText2.getText().toString());
        editor.putString(KEY_COST, editText4.getText().toString());
        editor.putString(KEY_KEYWORDS, editText5.getText().toString());
        editor.putString(KEY_NUMBER_OF_ACTORS, actornumber.getText().toString());
        editor.apply();

    }

    public void restore() {
        SharedPreferences sP = getSharedPreferences("sp_key", MODE_PRIVATE);
        editText.setText(sP.getString(KEY_TITLE, "").toUpperCase(Locale.ROOT));
        editText3.setText(sP.getString(KEY_GENRE, ""));
        editText1.setText(sP.getString(KEY_YEAR, ""));
        editText2.setText(sP.getString(KEY_COUNTRY, ""));
        editText4.setText(sP.getString(KEY_COST, ""));
        editText5.setText(sP.getString(KEY_KEYWORDS, ""));
        actornumber.setText(sP.getString(KEY_NUMBER_OF_ACTORS, ""));

    }

    public void clear(View view) {
        editText.setText("");
        editText3.setText("");
        editText1.setText("");
        editText2.setText("");
        editText4.setText("");
        editText5.setText("");
        actornumber.setText("");

    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            int id = item.getItemId();
            String title = editText.getText().toString();
            String year = editText1.getText().toString();
            if (id == R.id.add_movie_menu_id) {
                listItems.add(title + "|" + year);
                adapter.notifyDataSetChanged();
            } else if (id == R.id.remove_last_movie_menu_id) {
                listItems.remove(listItems.size() - 1);
                adapter.notifyDataSetChanged();
            } else if (id == R.id.remove_all_movies_menu_id) {
                listItems.clear();
                adapter.notifyDataSetChanged();
                deleteFb();


            } else if (id == R.id.double_cost) {
                int d = Integer.parseInt(editText4.getText().toString());
                String cost2 = String.valueOf(d * 2);
                editText4.setText(cost2);

            } else if (id == R.id.list_all_movies) {
                openCard();
            } else if (id == R.id.databaseNav) {
                Intent intent = new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intent);

            }
            else if(id==R.id.databaseClear){
                clearDatabase();

            }
            else if(id==R.id.delete_2020){
                deleteBefore2020();
            }
            else if(id==R.id.delete_20buck){
                delete20();
            }
            // close the drawer
            drawerlayout.closeDrawers();
            // tell the OS
            return true;
        }

    }
    public void deleteBefore2020(){
        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mMovieViewModel.deleteMovieOlderThan2020();
    }

    public void clearDatabase(){
        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mMovieViewModel.deleteAll();
    }

    public void delete20(){
        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        mMovieViewModel.deleteMovieCostAbove20();
    }

    public void openCard() {
        Intent intent = new Intent(this, CardAct.class);
        intent.putExtra("key", new Gson().toJson(movies));
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    public void clearButtonHandler() {
        editText.setText("");
        editText3.setText("");
        editText1.setText("");
        editText2.setText("");
        editText4.setText("");
        editText5.setText("");
        actornumber.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear_fields) {
            clearButtonHandler();
            Toast.makeText(this, "clear", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.lower_case) {
            String res = editText.getText().toString().toLowerCase();
            editText.setText(res);
        }

        return true;
    }

    private void addListItem() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ENGLISH);

        String title = editText.getText().toString();
        String year = editText1.getText().toString();
        String country = editText2.getText().toString();
        String genre = editText3.getText().toString();
        String cost = editText4.getText().toString();
        String keywords = editText5.getText().toString();
        String actNum = actornumber.getText().toString();
        String USD = (Integer.parseInt(cost) * 0.75) + "";
        listItems.add(title + "|" + year);
        adapter.notifyDataSetChanged();

        Movie movie = new Movie(title, year, country, genre, cost, keywords, actNum, USD);
        movies.add(movie);
    }


    private MovieViewModel mMovieViewModel;

    public void addToDatabase(){
        String title = editText.getText().toString();
        String year = editText1.getText().toString();
        int year_num=Integer.parseInt(year);
        String country = editText2.getText().toString();
        String genre = editText3.getText().toString();
        String cost = editText4.getText().toString();
        int cost_num= Integer.parseInt(cost);
        String keywords = editText5.getText().toString();
        mMovieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);


        MovieDb movie = new MovieDb(title, year_num, country, genre, cost_num, keywords);
        mMovieViewModel.insert(movie);
    }


    //    private void removeItem() {
////        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ENGLISH);
////        String temp1=editText+"|"+editText1;
//        listItems.remove(-1);
//        adapter.notifyDataSetChanged();
//    }
//    private void clearItem() {
////        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ENGLISH);
////        String temp1=editText+"|"+editText1;
//        listItems.clear();
//        adapter.notifyDataSetChanged();
//    }
    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listItems.remove(listItems.size() - 1);
            adapter.notifyDataSetChanged();
        }
    };
    public void addFb(){
        mRef=FirebaseDatabase.getInstance().getReference("/movies");
        String title = editText.getText().toString();
        String year = editText1.getText().toString();
        int year_num=Integer.parseInt(year);
        String country = editText2.getText().toString();
        String genre = editText3.getText().toString();
        String cost = editText4.getText().toString();
        int cost_num= Integer.parseInt(cost);
        String keywords = editText5.getText().toString();

        MovieDb movie = new MovieDb(title, year_num, country, genre, cost_num, keywords);

        mRef.push().setValue(movie);

    }

    public void deleteFb(){
        mRef=FirebaseDatabase.getInstance().getReference("/movies");
        mRef.setValue(null);
    }
}