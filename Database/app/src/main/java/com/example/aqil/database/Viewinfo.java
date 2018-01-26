package com.example.aqil.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aqil on 21/10/2017.
 */

public class Viewinfo extends AppCompatActivity{
    String jason_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ArrayList<List_item> list;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_view);
        listView=(ListView)findViewById(R.id.listview);

        list = new ArrayList<>();
        AsyncHttpTask asyncHttpTask=new AsyncHttpTask();
        asyncHttpTask.execute();


    }

    class AsyncHttpTask extends AsyncTask<Void, Void, String> {
        String add_info_url;
        String JSON_STRING;

        @Override
        protected void onPreExecute() {
            add_info_url="http://10.20.19.119/android/get_all_ads.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                // Create Apache HttpClient
                URL url1 =new URL(add_info_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url1.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while ((JSON_STRING= bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
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
            jason_string=result;

            if(!jason_string.isEmpty())
            {

                String id,name,url,desc;
                try {
                    jsonObject = new JSONObject(jason_string);
                    jsonArray = jsonObject.getJSONArray("food_info");
                    int count=0;
                    while(count<jsonArray.length())
                    {
                        JSONObject jo=jsonArray.getJSONObject(count);
                        id=jo.getString("ID");
                        name=jo.getString("Name");
                        url=jo.getString("URL");
                        desc=jo.getString("Description");
                        list.add(new List_item(name,R.mipmap.ic_launcher, desc));

                        count++;
                    }
                   CustomListAdapter adapter= new CustomListAdapter(getApplicationContext(),0,list);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            else
            {
                Toast.makeText(getApplicationContext(),"Plz Enter Any Data",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

}
