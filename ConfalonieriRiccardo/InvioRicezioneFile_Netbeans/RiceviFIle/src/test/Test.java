/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.*;
import java.net.*;
import java.nio.file.FileSystems;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    public static int porta = 3333;
    public static String serverName = "192.168.1.106";
    private static Socket socket  = null;
    private static DataOutputStream ScriviAlServer = null;
    private static DataInputStream  console   = null;
    private static DataInputStream Leggi =  null;
    
    public static void main(String[] args) {
        invioFile();
        StartClient(serverName, porta);
    }

    private static Boolean invioFile() {
        org.confalonieri.gestioneorari.WSGestioneOrari_Service service = new org.confalonieri.gestioneorari.WSGestioneOrari_Service();
        org.confalonieri.gestioneorari.WSGestioneOrari port = service.getWSGestioneOrariPort();
        return port.invioFile();
    }
    
    
    public static void StartClient(String serverName, int serverPort)
    {
        System.out.println("Sto cercando di connettermi, attendi...");
        try
        {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connsesso a: " + socket);
            start();
        }
        catch(UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
        }
        catch(IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
        
         ScriviAlServer();
        
    }
    
  
    public static void ScriviAlServer() //scrive un numero di telefono al server il quale lo cerca in un file e gli manda 2 informazioni
    {
        try {
            
            ScriviAlServer.writeUTF("ciao come stai");
            LeggiDaServer();
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static void LeggiDaServer()
    {
        try {
            File filename = new File("Ricevuto.txt");   
            FileOutputStream output = new FileOutputStream(filename);
            BufferedOutputStream b_output = new BufferedOutputStream(output);
           
            int len;
            while((len = Leggi.read()) != -1) {
                b_output.write(len);
            }
            b_output.close();
            System.out.println("end");
         
        } catch (IOException ex) { }
    }
    
    
    public static void start() throws IOException
    {
        console   = new DataInputStream(System.in); //leggo da tastiera
      ScriviAlServer = new DataOutputStream(socket.getOutputStream()); //scrivo in output
        Leggi = new DataInputStream(new BufferedInputStream(socket.getInputStream())); //leggo l'output del server
    }
    
    
    public static void stop()
    {
        //Chiudo tutto
        
        try
        {
            if (ScriviAlServer != null)
                ScriviAlServer.close();
            if (console != null)
                console.close();
            if (socket != null)
                socket.close();
            if (Leggi != null)
                Leggi.close();
        }
        catch(IOException ioe)
        {
            System.err.println("Errore nella chiusura degli oggetti!!");
        }
    }
}
