/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online;

import Card.Card;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Matterms
 */
public class Client {

    //attr(s)
    private String nomeServer = "127.0.0.1";
    private int portaServer = 6789;
    private Socket socketClientCorrente;
    private String nomeClient ;

    private BufferedReader input;
    private String stringaUtenteDaInviare;
    private String stringaRicevutaDalServer;

    private DataOutputStream outVersoServer;
    private BufferedReader inDalServer;
     
    
    /**
     * Apre il socket del server a cui vuole collegarsi e definisce i canali dati in input e in output
     * @throws IOException 
     */
    
    public void connetti() throws IOException {
        try {
            //Per input da tastiera uso la classe scanner
            input = new BufferedReader(new InputStreamReader(System.in));

            //creo stream  del client (host, porta e nome)
            socketClientCorrente = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(socketClientCorrente.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(socketClientCorrente.getInputStream()));
            System.out.println("(Collegato al server)");
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore durante la connessione");
            System.exit(1);
        }
        outVersoServer.writeBytes("Player\n");
        // adesso il client si registra al server
        // registrati();
    }
/**
 * Il client invia il proprio nome al Server, se esiste già un client con lo stesso nome sul Server,
 * viene richiesto di inserire un altro nome; solo dopo, il client può inserire l'età
 * @throws IOException 
 */
/*    private void registrati() throws IOException {
        //invio nome del client
        String prova,anni ;
        Boolean nomeNuovo = true;

        System.out.print("Qual è il tuo nome?: ");  
        nomeClient = input.readLine();
        outVersoServer.writeBytes(nomeClient + '\n');   //invio al server il nome del cliet
        
        System.out.print("Inserisci password: ");
            anni= input.readLine();
            outVersoServer.writeBytes(anni + '\n');     //invio al server la password del client
        comunica();
    }*/
    /**
     * Il client comunica con il server
     * PROTOCOLLO 
     * 1.Lista dei client collegati
     * 2.Lista dei client non collegati
     * 3.Chiusura connessione e rispettiva gestiona
     * @throws IOException 
     */
    public void comunica(Card selezionata) throws IOException{
        int p = selezionata.getPosizione();
        outVersoServer.writeBytes(p + "\n");
    }
/**
 * manda al Server la richiesta della lista e, una vota ricevuta, la visualizza
 * @throws IOException 
 */
    private void riceviCarta() throws IOException {

    }  
}
