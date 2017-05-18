package com.confalonieri.inserimentocsv;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Output;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

public class Client extends AsyncTask<String, String, String> {


    String dstAddress;
    int dstPort;
    String response = "";
    Activity activity;

    Client(String addr, int port, Activity activity) {
        dstAddress = addr;
        dstPort = port;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        Socket socket = null;

        try {
           // Toast.makeText(activity, "cancelled", Toast.LENGTH_SHORT).show();
            socket = new Socket(dstAddress, dstPort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //outputStream.write("C:\\Users\\RICCARDO\\Desktop\\WSBrev\\ProviderJava\\orari.txt".getBytes());
			/*
			 * notice: inputStream.read() will block if no data return
			 */
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }

            String sd = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download"; //"/storage/emulated/0/Download/";

            File f = new File(sd, "test2.csv");
            f.createNewFile();
            FileWriter fw = null;
            BufferedWriter bw = null;
            try{
                fw = new FileWriter(f, true);
                bw = new BufferedWriter(fw);
                bw.write(response);
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return "finish";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        activity.startActivity(new Intent(activity, SelezionaFileCSVActivity.class));

    }
}

