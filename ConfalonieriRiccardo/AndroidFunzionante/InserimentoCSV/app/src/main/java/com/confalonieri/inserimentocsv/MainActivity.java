package com.confalonieri.inserimentocsv;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.startActivity(new Intent(this, SelezionaFileCSVActivity.class));

        
        //PopUp permessi di lettura scrittura in android!
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        /*
        //creo un nuovo file-> la prima volta che copiavo il file in android non andava finche non ne ho creato uno da codice! non so perche
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "test2.csv");

        try {
            if (file.exists()==false)
                file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        //prendo username e password inserti dall'utente
        username = (EditText) findViewById(R.id.ET_LoginUsername);
        password = (EditText) findViewById(R.id.ET_LoginPassword);

        //associo l'oggetto buttonLogin al bottone grafico
        buttonLogin = (Button) findViewById(R.id.btn_Login);
        //metodo listner sul click del bottone
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creo oggetto per comunicare con il web service che lavora in un thread di tipo AsyncTask
                CallWebService callWs = new CallWebService();

                callWs.setActivity(MainActivity.this);
                // richiamo il thread di tipo AsyncTask chiedendo l'esecuzione del web service
                // primo parametro: identificatore dell'operazione da richiamare
                // secondo e terzo parametro i valori numerici da inviare
                callWs.execute(CallWebService.METHOD_Utente, username.getText().toString(), password.getText().toString());

                }
        });


        //attivo il bottone solo quando ha scritto qualcosa in username e psw
        ControlloText();

    }

    private void ControlloText()
    {
        //evento sulla editText dello username
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //controllo se tutte e due le editText sono riempite
                if (username.getText().toString().equals("") || password.getText().toString().equals(""))
                    buttonLogin.setEnabled(false);
                else
                    buttonLogin.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        //evento sulla editText dello password
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //controllo se tutte e due le editText sono riempite
                if (username.getText().toString().equals("") || password.getText().toString().equals(""))
                    buttonLogin.setEnabled(false);
                else
                    buttonLogin.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }



}
