package com.example.aqil.database;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Aqil on 21/10/2017.
 */

public class CustomListAdapter extends ArrayAdapter<List_item> {

    public CustomListAdapter(Context context, int resourse , List<List_item> items){
        super(context,resourse,items);
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v=convertView;
        if(v==null)
        {
            LayoutInflater vi;
            vi=LayoutInflater.from(getContext());
            v=vi.inflate(R.layout.mylist,null);
        }
        List_item p=getItem(position);
        if(p!=null)
        {
            TextView textView=(TextView) v.findViewById(R.id.item);
            ImageView imageView=(ImageView) v.findViewById(R.id.icon);
            TextView textView1=(TextView)v.findViewById(R.id.descrip);
            if(textView1!=null)
            {
                textView1.setText(p.getDescription());
            }
            if(textView!=null)
            {
                textView.setText(p.getName());
            }
            if(imageView!=null)
            {
                imageView.setImageResource(p.getImage());
            }
        }
        return v;
    }
}
