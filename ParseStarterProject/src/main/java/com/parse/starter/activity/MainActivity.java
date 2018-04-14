/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.adapter.TabsAdapter;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.util.SlidingTabLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarPrincipal;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      toolbarPrincipal = (Toolbar) findViewById(R.id.toolbar);
      toolbarPrincipal.setLogo(R.drawable.instagramlogo);

      slidingTabLayout = (SlidingTabLayout)findViewById(R.id.slidingTab);
      viewPager = (ViewPager) findViewById(R.id.viewPager);
      setSupportActionBar(toolbarPrincipal);

      TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), this);
      viewPager.setAdapter(tabsAdapter);
      slidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.text_item_tab);
      slidingTabLayout.setDistributeEvenly(true);
      slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.cinzaEscuro));
      slidingTabLayout.setViewPager(viewPager);

      //cadastar usuario
     /* ParseUser user = new ParseUser();
      user.setUsername("alline");
      user.setPassword("123456");
      user.setEmail("alline@teste.com");

      user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
              if( e == null ){//não temos erros, pois o objeto está nulo
                  Log.i("salvarusuario", "Dados salvos com sucesso");
              }else {
                  Log.i("salvarusuario", "Erro ao salvar os dados");
              }
          }
      });*/

     //deslogar
     /* ParseUser.logOut();

     //verificar usuario logado
      if(ParseUser.getCurrentUser()!=null){
          Log.i("loginusuario", "Usuário lagado");
      }else {
          Log.i("loginusuario", "Usuário nao lagado");
      }*/

     //login
     /* ParseUser.logInInBackground("alline", "123456", new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {
              if( e == null ){//não temos erros, pois o objeto está nulo
                  Log.i("logadousuario", "Dados salvos com sucesso");
              }else {
                  Log.i("logadousuario", "Erro ao salvar os dados");
              }
          }
      });*/

    /*  ParseObject pontuacao = new ParseObject("Pontuacao");
      pontuacao.put("nome", "Pedro");
      pontuacao.put("pontos", 50 );
      pontuacao.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if( e == null ){//não temos erros, pois o objeto está nulo
                  Log.i("salvarPontos", "Dados salvos com sucesso");
              }else {
                  Log.i("salvarPontos", "Erro ao salvar os dados");
              }
          }
      });


      ParseQuery<ParseObject> consulta = ParseQuery.getQuery("Pontuacao");
      consulta.getInBackground("zUAAYzbYsn", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {

              if( e == null ){

                object.put("pontos", 1200 );
                object.saveInBackground();

              }else{
                  Log.i("consultaObjeto", "Erro ao consultar objeto" + e.getMessage() );
              }

          }
      });

      ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Pontuacao");

      //Aplicando filtros na listagem de objetos
      //filtro.whereGreaterThan("pontos", 800);
      filtro.whereGreaterThanOrEqualTo("pontos", 8);
      //filtro.whereLessThan("pontos", 500);
      //filtro.whereEndsWith("nome", "ia");
      //filtro.whereStartsWith("nome", "Ja");
      //filtro.addAscendingOrder("pontos");
      filtro.addDescendingOrder("pontos");
      filtro.setLimit(1);


      //Listar os dados
      filtro.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {

              if( e == null ){//efetuada a listagem sem erros

                  for( ParseObject object : objects ){
                      Log.i("ListarDados", "objetos - Nome: " + object.get("nome") + " ponto: " + object.get("pontos") );
                  }

              }else{//Erro ao listar
                  Log.i("ListarDados", "Erro ao Listar os objetos - " + e.getMessage() );
              }

          }
      });


  }*/
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()){
          case R.id.sair:
              deslogarUsuario();
              return true;
          case R.id.action_settings:
              return true;
          case R.id.compartilhar:
              compartilharFoto();
              return true;
          default:
              return super.onOptionsItemSelected(item);
      }
    }

    public void deslogarUsuario(){
        ParseUser.logOut();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void compartilharFoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode== RESULT_OK && data !=null){
            Uri localImagemSelecionada = data.getData();

            try {
                // comprimir em formato png
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);

                byte[]bytes = stream.toByteArray();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
                String nomeImagem = simpleDateFormat.format(new Date());

                //criar um arquivo com formato proprio do parse
                ParseFile parseFile = new ParseFile(nomeImagem+"imagem.png", bytes );

                ParseObject parseObject = new ParseObject("Imagem");
                parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                parseObject.put("imagem", parseFile);

                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this, "Imagem postada", Toast.LENGTH_SHORT).show();
                            TabsAdapter tabsAdapter = (TabsAdapter) viewPager.getAdapter();
                            HomeFragment homeFragment = (HomeFragment) tabsAdapter.getFragment(0);
                            homeFragment.getPostagens();//att postagnes
                        }else{
                            Toast.makeText(MainActivity.this, "Ocorreu um erro.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
