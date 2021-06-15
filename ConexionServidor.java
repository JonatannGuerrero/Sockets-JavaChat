/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteFinal;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author JonatannGuerrero
 */
public class ConexionServidor {
    
    private Socket socket; 
    private String usuario;
    private String tfMensaje;
    private DataOutputStream salidaDatos;    
    Scanner Leer= new Scanner(System.in);
    
    public ConexionServidor(Socket socket, String tfMensajes, String usuario) {
        this.socket = socket;
        this.tfMensaje = tfMensajes;
        this.usuario = usuario;       
        try {
            this.salidaDatos = new DataOutputStream(socket.getOutputStream());
            salidaDatos.writeUTF(usuario + ": " + this.tfMensaje );                 
        } catch (IOException ex) {
            System.out.println("Error en Conexion Servidor : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no se creo correctamente. "+ ex.getMessage());
        }
    }    
}
