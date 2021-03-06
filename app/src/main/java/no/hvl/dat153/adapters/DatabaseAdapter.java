package no.hvl.dat153.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import no.hvl.dat153.classes.Person;
import no.hvl.dat153.classes.PersonDatabase;

import com.example.namequizapp.R;

import java.util.List;


public class DatabaseAdapter extends ArrayAdapter<Person> {
    private final Context mContext;
    private final int mResource;
    PersonDatabase db;

    // constructor
    public DatabaseAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        db = PersonDatabase.getInstance(context);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        // Set imageview to student image
        ImageView imageView = convertView.findViewById(R.id.person_image);
        imageView.setImageDrawable(getItem(position).getImage());

        // Set textview to student name
        TextView textName = convertView.findViewById(R.id.person_name);
        textName.setText(getItem(position).getName());

        // Listener on click delete button
        ImageButton deleteBtn = convertView.findViewById(R.id.button_delete);
        deleteBtn.setOnClickListener(v -> removePerson(position));
        return convertView;
    }

    public void removePerson(int pos){
        Person person = getItem(pos);
        remove(person);
        db.personDao().deletePerson(person);
        Toast.makeText(mContext, "Successfully removed " + person.getName(), Toast.LENGTH_SHORT).show();
    }
}