package com.parse.starter.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.color.black;

public class FeedUsuarioActivity extends AppCompatActivity {
    private ListView listView;
    private String nome;
    private ArrayAdapter<ParseObject>adapter;
    private ArrayList<ParseObject> postagens = new ArrayList<>();
    private ParseQuery<ParseObject>query;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuario);

        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(nome);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.lista);

        adapter = new HomeAdapter(this, postagens);

        listView.setAdapter(adapter);

        getPostagens();

    }

    public void getPostagens(){
        query = ParseQuery.getQuery("Imagem");
        query.whereEqualTo("username", nome);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        postagens.clear();
                        for(ParseObject parseObject: objects){
                            postagens.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(FeedUsuarioActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
