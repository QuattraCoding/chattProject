package org.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private String clientUsername;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
     public ClientHandler(Socket socket) throws IOException {
         try{
         this.socket = socket;
         this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
         this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         this.clientUsername = bufferedReader.readLine();
         clientHandlers.add(this);

         broadCastMessage("Server: "+clientUsername+" has joined the chat!");
         }catch(IOException e){
             closeEverything(socket, bufferedReader, bufferedWriter);
         }

     }

     public void broadCastMessage(String string) throws IOException {

         try{
             for(ClientHandler c : clientHandlers ){
                 if(!c.clientUsername.equals(this.clientUsername)){
                 bufferedWriter.write(string);
                 bufferedWriter.newLine();
                 bufferedWriter.flush();}
             }
         }catch(IOException e){

         }

     }
     public void removeClientHandler() throws IOException {
         clientHandlers.remove(this);
         broadCastMessage("SERVER: "+clientUsername+ " has left the chat!");

     }

     public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
         removeClientHandler();
         try{
             if(bufferedReader != null){
                bufferedReader.close();
             }
             if (bufferedWriter != null){
                 bufferedWriter.close();
             }
             if(socket != null){
                socket.close();
             }
         }catch(IOException e) {
             e.printStackTrace();
         }
         ;
     }
    @Override
    public void run() {
        String messageFromClient;

        while(socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine();
                broadCastMessage(messageFromClient);
            }catch(IOException e){
                try {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            }
        }
    }
}
