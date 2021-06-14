/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.util.Observable;

/**
 * Objeto observable del patron observer.
 * 
 * @author JonatannGuerrero
 */
public class MensajesChat extends Observable{

    private String mensaje;
    
    public MensajesChat(){
    }
    
    public String getMensaje(){
        return mensaje;
    }
    
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
        // Indica que el mensaje ha cambiado
        this.setChanged();
        // Notifica a los observadores que el mensaje ha cambiado y se lo pasa
        // Internamente notify Observers llama al metodo update del observador
        this.notifyObservers(this.getMensaje());
    }
}