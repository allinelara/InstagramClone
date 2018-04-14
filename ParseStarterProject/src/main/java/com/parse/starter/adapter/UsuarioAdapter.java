package com.parse.starter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allininha on 04/01/18.
 */

public class UsuarioAdapter extends ArrayAdapter<ParseUser> {
    private Context context;
    private ArrayList<ParseUser> usuarios;
    public UsuarioAdapter(@NonNull Context c, @NonNull ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.usuarios = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if(view ==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.lista_usuarios, parent, false);
        }

        if(usuarios.size()>0){
            TextView textView = (TextView) view.findViewById(R.id.nome);

            ParseUser parseObject = usuarios.get(position);
            textView.setText(parseObject.getUsername());

        }

        return view;
    }
}
