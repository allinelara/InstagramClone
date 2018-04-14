package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {

    private EditText nome, senha;
    private Button btnLogin;
    private TextView criarConta;
    private String nomeString, senhaString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome = (EditText) findViewById(R.id.nome);
        senha = (EditText) findViewById(R.id.senha);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        criarConta = (TextView) findViewById(R.id.criarConta);


        verificarUsuarioLogado();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomeString =nome.getText().toString();
                senhaString = senha.getText().toString();
                ParseUser.logInInBackground(nomeString, senhaString, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "Login realizado com sucesso.", Toast.LENGTH_SHORT).show();
                            abrirAreaPrincipal();
                        } else {
                            Toast.makeText(LoginActivity.this, "Erro ao logar.", Toast.LENGTH_SHORT).show();

                        }
                    }


            });

            }

        });

    }

    public void abrirCadastrarUsuario(View view){
        Intent intent = new Intent (LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private void verificarUsuarioLogado(){

        if( ParseUser.getCurrentUser() != null ){
            //Enviar usuário para tela principal do App
            abrirAreaPrincipal();
        }

    }

    private void abrirAreaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity( intent );
        finish();
    }
}
