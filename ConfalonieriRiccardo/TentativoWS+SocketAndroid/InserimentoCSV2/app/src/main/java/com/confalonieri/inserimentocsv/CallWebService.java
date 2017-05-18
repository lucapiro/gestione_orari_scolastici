
package com.confalonieri.inserimentocsv;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import android.app.Activity;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

// AsyncTask problem with orientation https://androidresearch.wordpress.com/2013/05/10/dealing-with-asynctask-and-screen-orientation/


// Uso di AsyncTask https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/

//  AsyncTask is a generic class, it uses 3 types: AsyncTask<Params, Progress, Result>.
//        Params – the input. what you pass to the AsyncTask
//        Progress – if you have any updates, passed to onProgressUpdate()
//        Result – the output. what returns doInBackground()
public class CallWebService extends AsyncTask<String, String, String> {

    //activity per la gestione del thread grafico
    private Activity activity=null;

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

    private String operazione="";// operazioneLogin=true; //boolean per capire se sta chiamando l'operazione per il login o quella per l'inserimento!

    public final static String METHOD_File = "InvioFile"; //metodo per ricevere il file con gli orari dal ws

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {
        String str;

        String SOAP_ACTION = NAMESPACE + params[0];

        if (params[0].equals(METHOD_Utente)) //sto richiamando il web service per il controllo dell'esistenza dell'utente
        {
            operazione=METHOD_Utente;//operazioneLogin=true;
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
                return e.toString();
                // e.printStackTrace();
            }
        }
        else if (params[0].equals(METHOD_Inserisci))
        {
            operazione=METHOD_Inserisci;
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
                return e.toString();
                //e.printStackTrace();
            }
        }
        else if (params[0].equals(METHOD_File))
        {
            operazione=METHOD_File;
            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_File);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);


            try {
                httpTransportSE.call(SOAP_ACTION, envelope);

            } catch (Exception e) {
                return e.toString();
                //e.printStackTrace();
            }


            return "method file";
        }
        else
            return "no method call";
        //return null;
    }


    @Override
    protected void onPostExecute(String esito) {
        super.onPostExecute(esito);

        // richiamato al termine della chiamata al web service
        // il parametro esito è il valore ritornato da doInBackground()

        if ( operazione.equals(METHOD_Utente)) //se non è nulla allora la classe è stata istanziata dall'activity main
        {
            //se la login non è andata a buon fine stampo una messagge box con l'errore
            if (!esito.equals("corretto"))
                Toast.makeText(activity, esito, Toast.LENGTH_SHORT).show();
            else
                activity.startActivity(new Intent(activity, AskForFile.class)); //passo alla seconda activity
        }
        else if (operazione.equals(METHOD_Inserisci))
        {


            if (esito.equals("professore non trovato nel DB!"))
            {
                if (SelezionaFileCSVActivity.stampaResultWS.equals(""))
                    Toast.makeText(activity, esito, Toast.LENGTH_SHORT).show();
                SelezionaFileCSVActivity.stampaResultWS = "gia stampato"; //passo l'esito all'activity tramite variabile pubblica!
            }

            else if (esito.equals("Errore sql!")) {

                if (SelezionaFileCSVActivity.stampaResultWS.equals(""))
                    Toast.makeText(activity, "non tutti i dati sono stati inseriti, riprova!", Toast.LENGTH_SHORT).show();

                SelezionaFileCSVActivity.stampaResultWS = "gia stampato";
            }
            else
            {

                if (SelezionaFileCSVActivity.stampaResultWS.equals(""))
                    Toast.makeText(activity, esito, Toast.LENGTH_SHORT).show();
                SelezionaFileCSVActivity.stampaResultWS = "gia stampato";
            }




        }



    }

    //con questo metodo posso gestire tutto quando riguarda la grafica (Stampa toast e cambio activity!!!)
    public void setActivity (Activity act)
    {
        this.activity= act;
    }
}