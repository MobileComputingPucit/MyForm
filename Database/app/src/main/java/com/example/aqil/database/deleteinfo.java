package com.example.aqil.database;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aqil on 22/10/2017.
 */

public class deleteinfo extends AppCompatActivity {
    EditText Id, Name;
    String name, id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_info_layout);
        Name = (EditText) findViewById(R.id.del_name);
        Id = (EditText) findViewById(R.id.del_id);
    }

    public void delete_data(View view) {
        name = Name.getText().toString();
        id = Id.getText().toString();
        deleteinfo.AsyncHttpTask asyncHttpTask= new deleteinfo.AsyncHttpTask();
        asyncHttpTask.execute(name,id);
    }

    class AsyncHttpTask extends AsyncTask<String, Void, String> {
        String add_info_url;


        @Override
        protected void onPreExecute() {
            add_info_url="http://10.20.19.119/android/delete_product.php";


        }

        @Override
        protected String doInBackground(String... args) {
            String name,url,description;
            name=args[0];
            id=args[1];
            try {
                // Create Apache HttpClient
                URL url1 =new URL(add_info_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "One Row is Deleted.....";
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
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
