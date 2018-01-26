package com.example.aqil.database;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aqil on 21/10/2017.
 */

public class Addinfo extends Activity {
    EditText Name, URL1, Description;
    String name, url, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinfo);
        Name = (EditText) findViewById(R.id.e1);
        URL1 = (EditText) findViewById(R.id.e2);
        Description = (EditText) findViewById(R.id.e3);
    }

    public void saveinfo(View view) {
        name = Name.getText().toString();
        url = URL1.getText().toString();
        description = Description.getText().toString();
        AsyncHttpTask asyncHttpTask= new AsyncHttpTask();
        asyncHttpTask.execute(name,url,description);
    }

    class AsyncHttpTask extends AsyncTask<String, Void, String> {
         String add_info_url;


        @Override
        protected void onPreExecute() {
            add_info_url="http://10.20.19.119/android/create_product.php";


        }

        @Override
        protected String doInBackground(String... args) {
            String name,url,description;
            name=args[0];
            url=args[1];
            description=args[2];
            try {
                // Create Apache HttpClient
                URL url1 =new URL(add_info_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("url","UTF-8")+"="+URLEncoder.encode(url,"UTF-8")+"&"+
                        URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(description,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "One Row is Inserted.....";
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
               // Toast.makeText(getApplicationContext(),"URL is not Correct",Toast.LENGTH_LONG).show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
               // Toast.makeText(getApplicationContext(),"Open Connection is not Correct",Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
}