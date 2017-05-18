package com.confalonieri.inserimentocsv;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class AskForFile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_file);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Richiesta file");
        builder.setMessage("Vuoi chiedere al WS il file?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //se clicca si richiamo il metodo del ws per farmi passare il file!
                        CallWebService callWs = new CallWebService();
                        callWs.execute(CallWebService.METHOD_File);

                            Client myClient = new Client("192.168.1.106", 3333, AskForFile.this);
                            myClient.execute();

                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent openAct = new Intent(AskForFile.this, SelezionaFileCSVActivity.class); //passo dall'activity AskForFile al activity SelezionaFileCSVActivity
                startActivity(openAct);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
