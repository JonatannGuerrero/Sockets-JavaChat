/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteFinal;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.*;
/**
 *
 * @author JonatannGuerrero
 */


public class ClienteFinal implements Runnable{
    
    int Cont=0;
    String Mensaje[] =new String[20];
    @Override
    public void run(){
        while (true){
         String Mensajes=Leer.nextLine(); 
            if (Mensajes.equalsIgnoreCase("Menu")) {
                MenuOp();                
            } else {
             //Mensaje[Cont]=(Usuario+": "+Mensajes);
             ConexionServidor conexionServidor = new ConexionServidor(socket, Mensajes, Usuario); // Envia Mensajes al Servidor
             //Cont++;
            } 
        }        
    }
    
    public void MenuOp(){   
        String Op=null;
        System.out.println("Elija Lo que desea realizar: ");
        System.out.println("1. Eliminar Mensajes.");
        System.out.println("2. Salir.");
        Op=Leer.nextLine();        
        if(Op.equalsIgnoreCase("1")){
            EliminarMsj(Cont);
        }else{
            if(Op.equalsIgnoreCase("2")){
                System.out.println("Salió del menú.");                
            }else{
                System.out.println("Opcion invalida");
                MenuOp();
            }
        }      
    }
    
    
    public void EliminarMsj(int cont){       
        
        System.out.println("Elija el mensaje a borrar: ");
        for (int i = 0; i < this.Mensaje.length; i++){
            if (Mensaje[i]==null) {                
            } else {
                System.out.println(i+1+" "+this.Mensaje[i]);
            }                     
        }
        int Elec=Leer.nextInt();
        Mensaje[Elec-1]="Mensaje Eliminado.";
        System.out.println("****Mensajes Actualizados****");
        for (int i = 0; i < Mensaje.length; i++) {
            if (Mensaje[i]==null) {                
            } else {
                System.out.println(i+1+" "+this.Mensaje[i]);
            }
        }
        String Mensajes=Leer.nextLine();
    }
    
    
    public static String Usuario=null;
    public static String Host="192.168.3.3";
    public static int Puerto=1234;
    private Socket socket;
    Scanner Leer= new Scanner(System.in);
    
    public ClienteFinal(){
        
        System.out.print("Por Favor Ingrese Su Nombre: ");        
        Usuario=Leer.nextLine();
        // Se crea el socket para conectar con el Sevidor del Chat
	System.out.println("| ***Bienvenido al chat "+Usuario+"*** |");
        try {
            socket = new Socket(Host, Puerto);
        } catch (IOException ex) {
            Logger.getLogger(ClienteFinal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage()+" Error En Cliente Conexion!");
        }
        Thread Hilo1=new Thread(this);
        Hilo1.start();
 }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        ClienteFinal CF=new ClienteFinal();
        CF.recibirMensajesServidor();
    }
    
    public void recibirMensajesServidor(){
        
        /**
     * Recibe los mensajes del chat reenviados por el servidor
     */
     // Obtiene el flujo de entrada del socket
        DataInputStream entradaDatos = null;
        String mensaje;
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear el stream de entrada: " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no se creo correctamente1. "+ ex.getMessage());
        }
        
        // Bucle infinito que recibe mensajes del servidor
        boolean conectado = true;
        while (conectado) {            
            try {           
                mensaje = entradaDatos.readUTF();
                Mensaje[Cont]=(mensaje);                
                System.out.println(mensaje);  
                Cont++;
            } catch (IOException ex) {
                System.out.println("Error : " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                System.out.println("El socket no se creo correctamente. "+ ex.getMessage());
                conectado = false;
            }           
        }
    }
}
