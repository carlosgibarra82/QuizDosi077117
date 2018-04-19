package com.athena.quizdosi077117;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.athena.quizdosi077117.Models.User;
import com.athena.quizdosi077117.Parser.JsonUser;
import com.athena.quizdosi077117.Url.HttpManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;
    List<User> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        Task tarea = new Task();
        tarea.execute("http://jsonplaceholder.typicode.com/users");
    }

    public Boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            return true;
        }else{
            return false;
        }
    }



    public void processData(){

        for (User per : personList){
            textView.append((per.getName() + " " + per.getUsername() + " " + per.getEmail() + " ") + " " + per.getStreet() + "\n");
        }
    }

    public class Task  extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                personList = JsonUser.getDataJson(s);
                progressBar.setVisibility(View.INVISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
        }
    }
}
