package net.porrow.tfchat;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Connection extends Thread{

    private static final String HOST = "192.168.43.128";
    private static final int PORT = 8525;
    private static final String[] FLAGS = {"SYN", "ROOM","ACK", "TXT", "FILE", "FIN"};
    private static final String S = "#";                                        //Caractère séparateur

    private static boolean running = true;

    private Socket soc;
    private String name;
    private PrintWriter writer;
    private BufferedInputStream reader;

    public Connection(String name) {
        this.name = name;
        try{
            soc = new Socket(HOST, PORT);
            Log.i("Connection", "Connection établie...");
        }
        catch(UnknownHostException e){Log.i("Connection", e.getMessage());}
        catch(IOException e) {
            Log.i("Connection", "Impossible de se connecter au serveur " + HOST + "/" + PORT + " : "+e.getMessage());
            running = false;
        }
    }

    @Override
    public void run() {
        if(!running) return;
        Log.i("Connection", "Lancement de la boucle de traitement");
        String[] message;
        try {
            writer = new PrintWriter(soc.getOutputStream());
            send(FLAGS[0]+S+name);
            while(running) {                                                    //Tant que la connexion est active, on traite les demandes
                writer = new PrintWriter(soc.getOutputStream());
                reader = new BufferedInputStream(soc.getInputStream());
                //BufferedOutputStream writer2 = new BufferedOutputStream(soc.getOutputStream());
                message = receive();                                            //On lit la demande du client
                debug(message);                                                 //On affiche quelques informations
                handling(message);                                              //On traite la requête
            }
        }
        catch (SocketException e) {
            Log.i("Connection", "User : " + name + ". La connexion a été interrompue : " + e.getMessage());
            running = false;
        }
        catch (IOException e) {
            Log.i("Connection", e.getMessage());
            running = false;
        }
    }

    //Traitement du message reçu, et génération de la réponse à envoyer
    private void handling(String[] mes) throws IOException {
        switch(mes[0]) {
            case "SYN":
                break;
            case "ROOM":
                break;
            case "ACK":
                send(FLAGS[2]+S+" ");
                break;
            case "TXT":
                break;
            case "FILE":
                break;
            case "FIN":
                send(FLAGS[2]+S+" ");
                close();
                break;
        }
    }

    //Information de debuggage
    private void debug(String[] mes) {
        String debug;
        InetSocketAddress remote;
        remote = (InetSocketAddress) soc.getRemoteSocketAddress();
        debug = "    Thread : " + Thread.currentThread().getName() + ".";
        debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() + ".";
        debug += " Sur le port : " + remote.getPort() + ".";
        debug += "-> Commande reçue : " + mes[0] + " " + mes[1];
        if(!mes[0].equals(FLAGS[2]) && mes[1].length() > 1)
            Log.i("Connection", debug);
    }

    //Envoie un message à l'utilisateur
    private void send(String mes) {
        if(mes == null) mes = FLAGS[2];
        writer.write(mes);														//On envoie la réponse au client
        writer.flush();
    }


    //Lit la réponse reçue par l'utilisateur
    private String[] receive() throws IOException {
        String response;
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response.split(S);
    }

    //Ferme la connexion de l'utilisateur
    private void close(){														//Arrête les flux et supprime la session{
        writer.close();
    }
}
