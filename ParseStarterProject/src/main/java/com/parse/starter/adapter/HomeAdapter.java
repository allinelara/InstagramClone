package com.parse.starter.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allininha on 04/01/18.
 */

public class HomeAdapter extends ArrayAdapter<ParseObject> {

    private Context context;
    private ArrayList<ParseObject> postagens;
    public HomeAdapter( Context c, ArrayList<ParseObject> objects) {
        super(c, 0, objects);
        this.context = c;
        this.postagens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if(view ==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.lista_postagem, parent, false);
        }

        if(postagens.size()>0){
            ImageView imageView = (ImageView) view.findViewById(R.id.imagePostagem);

            ParseObject parseObject = postagens.get(position);

            Picasso.with(context).load(parseObject.getParseFile("imagem").getUrl()).fit().into(imageView);

        }

        return view;
    }
}
