/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.confalonieri.gestioneOrari;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import javax.jws.WebService;

//NOTA: i tre metodi del database si potrebbero racchiudere dentro un unico metodo con if per capire quale operazione fare
@WebService(serviceName = "WSGestioneOrari")
public class WSGestioneOrari {

    //parametri che utilizzo per cercare un determinato utente nel database!
    public String username="";
    public String password="";

    public String controllaUtente(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
     
        return getUserFromDatabase(username,password); //chiamo il metodo per verificare la corretta login
    }
    
    private String getUserFromDatabase(String username, String password) {

        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/gestione_orari";

        //username e password per accesso al database
        String USER = "root";
        String PASS = "";

        //Oggetto per la conessione
        Connection conn = null;
        
        //Oggetto per le query sql
        Statement stmt = null;

        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //query sql da eseguire
            char virgoletta = '"';
            String sql = "SELECT * FROM utenti WHERE username=" + virgoletta + username + virgoletta;
                
           if (sql != null) {
                ResultSet rs = stmt.executeQuery(sql);

                
                // iterate through the java resultset
                while (rs.next()) {
                    
                    
                    //scorro rs in base al nome della colonna
                    if (rs.getString("username").equals(username)) //se lo username corrisponde controllo la password
                    {
                        if (rs.getString("password").equals(password))
                        {
                            if (rs.getInt("professore")==1)
                            {
                                rs.close();
                                return "corretto";
                            }
                            else
                            {
                                rs.close();
                                return "non sei un prof!";
                            }   
                        }
                        else
                        {
                            rs.close();
                            return "password errata!";
                        }    
                    }
                    else
                    {
                        rs.close();
                        return "username errato!";
                    }
                }
                rs.close();
                
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return "errato!";
    }

    
    private int GetIdProfessore(String NomeProf)
    {
         // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/gestione_orari";

        //username e password per accesso al database
        String USER = "root";
        String PASS = "";

        //Oggetto per la conessione
        Connection conn = null;
        
        //Oggetto per le query sql
        Statement stmt = null;

        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //query sql da eseguire
            char virgoletta = '"';
            String sql = "SELECT * FROM professore WHERE NomeProf=" + virgoletta + NomeProf + virgoletta;
                
            if (sql != null) {
                ResultSet rs = stmt.executeQuery(sql);
                 while (rs.next()) {
                     int id=  rs.getInt(1); //torno il valore dell'id!
                     rs.close();
                     return id;
                     
                 }
                
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        
        return -1;
    }

    private String InserisciValoriDB(int idProf, int ora, int giorno, String classe, String aula)
    {
        //INSERT INTO `orario` (`Id`, `IdProf`, `Ora`, `giorno`, `classe`, `aula`) VALUES (NULL, '1', '1', '1', '5C', '5C');
         // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/gestione_orari";

        //username e password per accesso al database
        String USER = "root";
        String PASS = "";

        //Oggetto per la conessione
        Connection conn = null;
        
        //Oggetto per le query sql
        Statement stmt = null;

        try {
            
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
            String virgoletta = "'";
            //controllo se non esistono già questi valori nel db
            String sqlControllo = "SELECT * FROM orario WHERE IdProf=" + idProf +" and Ora="+ora +" and giorno="+giorno;
             ResultSet rs = stmt.executeQuery(sqlControllo);
            
            if (rs.next()) //se rs.next da true allora vuol dire che ho trovato i dati
            {
               return "dati gia presenti nel DB!";
            }
            else
            {
                //query per inserimento dati!
                String valori = "( "  + null + ", "  + idProf  + ", "  + ora  + ", " 
                         + giorno  + ", " + virgoletta + classe + virgoletta + ", "
                        + virgoletta + aula + virgoletta + " )";
                String sql = "INSERT INTO orario VALUES " + valori;


                stmt.executeUpdate(sql); //invio la query al db
                return "dati inseriti con successo!";
            }

         } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try


        return "Errore sql!";
    }
    
    
    public String InserisciOrario(@WebParam(name = "nomeProf") String nomeProf, @WebParam(name = "ora") int ora, @WebParam(name = "giorno") int giorno, @WebParam(name = "classe") String classe, @WebParam(name = "aula") String aula) {
       
        int idProf = GetIdProfessore(nomeProf);
       
       if (idProf!=-1) //se -1 non ho trovato quel prof!! andrà prima inserito nel database
       {
          return InserisciValoriDB(idProf,ora,giorno,classe,aula); //inserisco i valori nel db
       }
       else
           return "professore non trovato nel DB!";
    }
}
