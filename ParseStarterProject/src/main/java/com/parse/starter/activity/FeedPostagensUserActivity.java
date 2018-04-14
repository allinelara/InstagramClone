package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.parse.starter.R;

public class FeedPostagensUserActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_postagens_user);
        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(nome);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finish(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }
}
