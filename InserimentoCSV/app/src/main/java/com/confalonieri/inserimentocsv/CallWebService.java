package com.confalonieri.inserimentocsv;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

// AsyncTask problem with orientation https://androidresearch.wordpress.com/2013/05/10/dealing-with-asynctask-and-screen-orientation/


// Uso di AsyncTask https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/

//  AsyncTask is a generic class, it uses 3 types: AsyncTask<Params, Progress, Result>.
//        Params – the input. what you pass to the AsyncTask
//        Progress – if you have any updates, passed to onProgressUpdate()
//        Result – the output. what returns doInBackground()
public class CallWebService extends AsyncTask<String, String, String> {

    //activity per la gestione del thread grafico
    private Activity activity_Main=null;

    // URL corrispondente al web service
    public final static String URL = "http://10.0.2.2:8080/ProviderJava/WSGestioneOrari"; //"http://localhost/ProviderJava/WSGestioneOrari";
    // target name space
    public final static String NAMESPACE = "http://gestioneOrari.confalonieri.org/";

    // nome dell'operazione
    public final static String METHOD_Utente = "controllaUtente";

    private static final String PARAMETER_USERNAME_NAME = "username";

    private static final String PARAMETER_PASSWORD_NAME = "password";

    //metodo per inserire un nuovo orario
    public final static String METHOD_Inserisci = "InserisciOrario";
//tutti i parametri necessari
    private static final String PARAMETER_NomeProf ="nomeProf";
    private static final String PARAMETER_ora ="ora";
    private static final String PARAMETER_giorno ="giorno";
    private static final String PARAMETER_classe ="classe";
    private static final String PARAMETER_aula ="aula";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {
        String str;

        String SOAP_ACTION = NAMESPACE + params[0];

        if (params[0]==METHOD_Utente) //sto richiamando il web service per il controllo dell'esistenza dell'utente
        {

            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_Utente);

            //aggiungo i due parametri con i rispettivi valori all'oggetto soap
            // preparazione delle proprietà/parametri dell'operazione
            PropertyInfo propertyInfo = new PropertyInfo();

            //aggiungo i paraemtri username e password in base ai valori inseriti dall'utente!
            soapObject.addProperty(PARAMETER_USERNAME_NAME, params[1]);
            soapObject.addProperty(PARAMETER_PASSWORD_NAME, params[2]);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);


            try {
                httpTransportSE.call(SOAP_ACTION, envelope);


                // ricezione della risposta
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

                // FORMA SEMPLIFICATA: estrazione dalla risposta del contenuto
                str = soapPrimitive.toString();

                return str; //ritorno il risultato
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (params[0]==METHOD_Inserisci)
        {
            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_Inserisci);

            //aggiungo i due parametri con i rispettivi valori all'oggetto soap
            // preparazione delle proprietà/parametri dell'operazione
            PropertyInfo propertyInfo = new PropertyInfo();

            //aggiungo i paraemtri username e password in base ai valori inseriti dall'utente!
            soapObject.addProperty(PARAMETER_NomeProf, params[1]);
            soapObject.addProperty(PARAMETER_ora, Integer.parseInt(params[2]));
            soapObject.addProperty(PARAMETER_giorno, Integer.parseInt(params[3]));
            soapObject.addProperty(PARAMETER_classe, params[4]);
            soapObject.addProperty(PARAMETER_aula, params[5]);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);


            try {
                httpTransportSE.call(SOAP_ACTION, envelope);


                // ricezione della risposta
                SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

                // FORMA SEMPLIFICATA: estrazione dalla risposta del contenuto
                str = soapPrimitive.toString();

                return str; //ritorno il risultato
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @Override
    protected void onPostExecute(String esito) {
        super.onPostExecute(esito);

        // richiamato al termine della chiamata al web service
        // il parametro esito è il valore ritornato da doInBackground()

        if (activity_Main!=null) //se non è nulla allora la classe è stata istanziata dall'activity main
        {
            //se la login non è andata a buon fine stampo una messagge box con l'errore
            if (!esito.equals("corretto"))
                Toast.makeText(activity_Main, esito, Toast.LENGTH_SHORT).show();
            else
                activity_Main.startActivity(new Intent(activity_Main, SelezionaFileCSV.class)); //passo alla seconda activity
        }


    }

    //con questo metodo posso gestire tutto quando riguarda la grafica (Stampa toast e cambio activity!!!)
    public void setActivity (Activity act)
    {
        this.activity_Main= act;
    }
}