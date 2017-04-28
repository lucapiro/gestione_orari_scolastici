package com.confalonieri.inserimentocsv;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;

/*NOTA!!: per selezionare il file con OpenDialog seguire i segueti passi:
    1. far partire l'emulatore
    2. trascinare il file con gli orari nella schermata home dell'emulatore
    3. attendere la fine della copia automatica
    4. il file dovrebbe essere stato copiato nell'emulatore in storage/device/emulator/0
*/

/*TODO:
    4: capire come visualizzare gli inserimenti andati a buon fine e quali no!?!?!?!?!?!? -> conteggio i result??
    5: sistemare aula di lezione che al momento è uguale a quella di classe!
*/
public class SelezionaFileCSV extends AppCompatActivity {
    String filePath=""; //path del file scelto
    boolean continua=false;
    private TextView TextV_FilePath;//text per visualizzare il percorso del file scelto!
    private RadioButton radio_Classe; //radio button per scegliere come aprire il file
    private RadioButton radio_Prof;
    private TextView TextView_ScrittaPostScelta;
    private EditText ET_InserimentoDatiRicerca;
    private Button btn_LogOut;
    private Button btn_LeggiFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleziona_file_csv);

        TextV_FilePath = (TextView) findViewById(R.id.TextView_FilePath); //text per la path del file scelto
        radio_Classe =   (RadioButton) findViewById(R.id.radio_CaricaPerClasse); //radio button per scelta su lettura file
        radio_Prof =   (RadioButton) findViewById(R.id.radio_CaricaPerProf);//radio button per scelta su lettura file
        TextView_ScrittaPostScelta = (TextView) findViewById(R.id.TextView_ScrittaPostScelta); //text per inserimento del nome della classe/prof
        ET_InserimentoDatiRicerca = (EditText) findViewById(R.id.ET_InserimentoDatiRicerca);//edittext per inserimento del nome della classe/prof
        btn_LogOut = (Button) findViewById(R.id.btn_Logout); //bottone per il log out
        btn_LeggiFile= (Button) findViewById(R.id.btn_LetturaFile); //bottone per conferma lettura file

        //metodo listner sul click del bottone per selezionare il file
        Button buttonSelezionaFile = (Button) findViewById(R.id.btn_SelezionaFile);
        buttonSelezionaFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileDialog(); //finestra per scegliere il file
            }
        });

        //listenere sulla scelta del radio button
        radio_Classe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(radio_Classe.isChecked())
                {
                    //setto la text per l'inserimeno del nome della classe
                    TextView_ScrittaPostScelta.setText("inserisci la classe");
                    TextView_ScrittaPostScelta.setVisibility(View.VISIBLE); //rendo la text visibile
                    ET_InserimentoDatiRicerca.setVisibility(View.VISIBLE); //rendo la edittext visibile
                }
                else //ha selezionato il radio per l'inserimento del prof
                {
                    //setto la text per l'inserimeno del nome della classe
                    TextView_ScrittaPostScelta.setText("inserisci il nome del prof"); //si può inserire anche l'inziale del cognome come nel file .csv nel caso di nome uguali
                    TextView_ScrittaPostScelta.setVisibility(View.VISIBLE);//rendo la text visibile
                    ET_InserimentoDatiRicerca.setVisibility(View.VISIBLE); //rendo la edittext visibile
                }
            }
        });

        radio_Prof.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(radio_Prof.isChecked())
                {
                    //setto la text per l'inserimeno del nome della classe
                    TextView_ScrittaPostScelta.setText("inserisci il nome del prof");
                    TextView_ScrittaPostScelta.setVisibility(View.VISIBLE); //rendo la text visibile
                    ET_InserimentoDatiRicerca.setVisibility(View.VISIBLE); //rendo la edittext visibile
                }
                else //ha selezionato il radio per l'inserimento del prof
                {
                    //setto la text per l'inserimeno del nome della classe
                    TextView_ScrittaPostScelta.setText("inserisci la classe"); //si può inserire anche l'inziale del cognome come nel file .csv nel caso di nome uguali
                    TextView_ScrittaPostScelta.setVisibility(View.VISIBLE);//rendo la text visibile
                    ET_InserimentoDatiRicerca.setVisibility(View.VISIBLE); //rendo la edittext visibile
                }
            }
        });

        //evento sulla editText dell'inserimento
        ET_InserimentoDatiRicerca.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //controllo se ha inserito per cosa cercare nel file!
                if (ET_InserimentoDatiRicerca.getText().toString().equals(""))
                    btn_LeggiFile.setVisibility(View.INVISIBLE);
                else
                    btn_LeggiFile.setVisibility(View.VISIBLE);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        //metodo listner sul click del bottone per logout
        btn_LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //torno alla prima activity
                Intent openHomeAct = new Intent(SelezionaFileCSV.this, MainActivity.class); //passo dall'activity main alla seconda activity
                startActivity(openHomeAct); //apro effettivamente l'activity
            }
        });


        //metodo listner sul click del bottone per leggere il file
        btn_LeggiFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*uno dei due radio è sicuramente selezionato per via degli if precedenti
                quindi controllo solo se ho la path del file!*/
                if (filePath.equals("") )
                    Toast.makeText(getApplicationContext(), "Selezionare un file!", Toast.LENGTH_LONG).show();
                else if (radio_Classe.isChecked())
                    Toast.makeText(getApplicationContext(), "Funzionalita non disponibile!", Toast.LENGTH_LONG).show();
                else
                {
                    //passo il percorso del file e il nome del prof da cercare!
                    LeggiFilePerProf(filePath, ET_InserimentoDatiRicerca.getText().toString());
                }
            }
        });
    }

    public void LeggiFilePerProf(String fileName, String NomeProf)
    {
        File file = new File( fileName);

        InputStream csvStream = null;
        try {
            CSVReader csvReader = new CSVReader( new FileReader(fileName));

            List content = csvReader.readAll(); //lista contenete tutti i valori letti dal file csv
            List<String> stringDaElaborare = new ArrayList<String>();
            OrarioProfDatabase temp = new OrarioProfDatabase();
            String[] row = null;
            for (int i=0; i<content.size();i++)
            {
                row = (String[]) content.get(i); //prendo l'oggetto
                if (row[0].contains(NomeProf.toUpperCase())) //se contiene il nome del prof allora sono arrivato alla riga giusta
                {
                    stringDaElaborare.add(row[0]); //aggiungo al mio vettore la stringa da elaborare contenente l'orario del prof
                    row = (String[]) content.get(i+1);
                    if (row[0].charAt(0)==';') //se il primo carattere è il ';' è la riga delle aule corrispondenti al prof prof
                        stringDaElaborare.add(row[0]);

                    break; //se sono entrato ho gia trovato la riga del prof quindi ho finito!
                }
            }

            csvReader.close();
            ElaboraString(stringDaElaborare.get(0)); //elaboro tutta la stringa in posizione 0

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    private void FileDialog()
    {
        //apro il fileDialog per permettere all'utente di selezionare il file da leggere!!!
        SimpleFileDialog FileOpenDialog =  new SimpleFileDialog(SelezionaFileCSV.this, "FileOpen",
                new SimpleFileDialog.SimpleFileDialogListener()
                {

                    @Override
                    public void onChosenDir(String chosenDir)
                    {
                        //salvo la path del file scelto!!
                        filePath = chosenDir;
                        if (!filePath.equals("")) //se ha scelto un file!
                        {
                            String extension = filePath.substring(filePath.lastIndexOf(".")+1); //prendo tutto ciò che c'è dopo l'utlimo punto (estensione del file)
                            if (extension.equals("csv")) //leggo solo file csv
                            {
                                //setto in grafica la scritta col percorso del file per poterla visualizzare all'utente!
                                TextV_FilePath.setText(filePath);
                                AttivaRadioRichiesta(); //rendo attivi i radio button!
                            }
                            else
                            {
                                Toast.makeText(SelezionaFileCSV.this, "File no valido, scegliere un file .csv", Toast.LENGTH_LONG).show();
                                filePath=""; //file non valido
                                TextV_FilePath.setText(filePath); //risetto la grafica nel caso in cui prima avesse selezionato i file e poi tolto
                            }
                        }
                    }
                });

        FileOpenDialog.Default_File_Name = "";
        FileOpenDialog.chooseFile_or_Dir();
    }

    private void AttivaRadioRichiesta()
    {
        //modifico i radio button in modo che se erano di settare l'opposto della condizione precedente
        //uno è gia selezionato di default
        radio_Classe.setEnabled(!radio_Classe.isEnabled());  radio_Prof.setEnabled(!radio_Prof.isEnabled());


    }


    private static void ElaboraString(String daElaborare) //elaboro la stringa per ottenere i valori necessari!
    {
        int h=1; //vanno dalla prima alla 6
        int g=1; //vanno da 1 a 6 (lun-sab)
        String nomeProf = daElaborare.substring(0,daElaborare.indexOf(";")); //prendo il nome del prof e poi lo tolgo dalla stringa
        daElaborare=daElaborare.substring(daElaborare.indexOf(";")+1);
        String[] splittato = daElaborare.split(";"); //splitto tutti i punti e virgola
        List<OrarioProfDatabase> professore = new ArrayList<OrarioProfDatabase>();
        OrarioProfDatabase temp = new OrarioProfDatabase();

        //oggetto della classe  CallWebService per inserire tutti i valori elaborati!
        CallWebService callWS = new CallWebService();

        //passo tutti i parametri splittati! ogni valore corrisponde a una nuova ora!
        for (int i=0; i<splittato.length;i++)
        {
            callWS = new CallWebService(); //risetto la chiamata al webService
            temp.SetNewValue(nomeProf, h, g, splittato[i],splittato[i]); //passo tutti i valori
            callWS.execute(CallWebService.METHOD_Inserisci,nomeProf, Integer.toString(h),Integer.toString(g), splittato[i],splittato[i]);
            //se sono arrivato a 6 allora ho riempito tutta la giornata!
            if (h<6 ) //il sabato ha solo 5 ore!!! --> esce comununque dal ciclo while perche non trova più punti e virgola!!!
                h++;
            else {
                h=1; g++;}

            //aggiungo il valore alla lista
            professore.add(temp);
            temp = new OrarioProfDatabase(); //svuoto temp

        }




    }
}