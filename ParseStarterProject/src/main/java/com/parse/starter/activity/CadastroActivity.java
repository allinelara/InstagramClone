package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.util.ParseErros;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome, senha, email;
    private Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.nome);
        senha = (EditText) findViewById(R.id.senha);
        email = (EditText) findViewById(R.id.email);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarUsuario();
            }
        });
    }

    public void abrirLogin(View view){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void cadastrarUsuario(){
        ParseUser user = new ParseUser();
        user.setEmail(email.getText().toString());
        user.setPassword(senha.getText().toString());
        user.setUsername(nome.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso.", Toast.LENGTH_SHORT).show();
                    abrirLogin(null);
                }else{
                    ParseErros  erros = new ParseErros();
                    String erro =  erros.getErro(e.getCode());
                    Toast.makeText(CadastroActivity.this, erro, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
