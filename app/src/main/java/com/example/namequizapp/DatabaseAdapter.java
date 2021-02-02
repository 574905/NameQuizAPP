package com.example.namequizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;



public class DatabaseAdapter extends ArrayAdapter<Person> {
    private Context mContext;
    private int mResource;

    // Constructor
    public DatabaseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        // Set imageview to student image
        ImageView imageView = convertView.findViewById(R.id.person_image);
        imageView.setImageDrawable(getItem(position).getDrawable());

        // Set textview to student name
        TextView textName = convertView.findViewById(R.id.person_name);
        textName.setText(getItem(position).getName());

        // Listener on click delete button
        ImageButton deleteBtn = convertView.findViewById(R.id.button_delete);
        deleteBtn.setOnClickListener(v -> remove(getItem(position)));
        return convertView;
    }
}