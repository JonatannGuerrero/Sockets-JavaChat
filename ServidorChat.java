/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Servidor para el chat.
 * 
 * @author JonatannGuerrero
 */
public class ServidorChat {

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Servidor Listo :D");
        int puerto = 1234;
        int maximoConexiones = 10; // Maximo de conexiones simultaneas
        ServerSocket servidor = null; 
        Socket socket = null;
        MensajesChat mensajes = new MensajesChat();
        
        try {
            // Se crea el serverSocket
            servidor = new ServerSocket(puerto, maximoConexiones);            
            // Bucle infinito para esperar conexiones
            while (true) {                                
                socket = servidor.accept();
                System.out.println("Cliente " + socket.getInetAddress().getHostName() + " conectado.");                
                ConexionCliente cc = new ConexionCliente(socket, mensajes);
                cc.start();                
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally{
            try {
                socket.close();
                servidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor: " + ex.getMessage());
            }
        }
    }
}
