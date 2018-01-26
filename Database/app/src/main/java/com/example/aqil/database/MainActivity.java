package com.example.aqil.database;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.b1);
        btn2=(Button)findViewById(R.id.b2);
        textView=(TextView)findViewById(R.id.textView);

    }

    public void add_info(View view)
    {
        Intent intent = new Intent(MainActivity.this,Addinfo.class);
        startActivity(intent);
    }
    public void view_info(View view)
    {
        Intent intent = new Intent(MainActivity.this,Viewinfo.class);
        startActivity(intent);
    }
    public void delete_info(View view)
    {
        Intent intent = new Intent(MainActivity.this,deleteinfo.class);
        startActivity(intent);
    }
    public void update_info(View view)
    {
        Intent intent = new Intent(MainActivity.this,updateinfo.class);
        startActivity(intent);
    }


}

