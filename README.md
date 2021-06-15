# Chat Con Java, NetBeans 📱📲

Programa desarrollado con NetBeans. Contiene el servidor por el cual se conectan los clientes, simulando un chat. Tiene un máximo de 10 clientes, que se pueden conectar con servidor, los mensajes se guardan de manera local en el cliente con un máximo de 20 con la posibilidad de expandir el arreglo tipo String que guarda los mensajes para que pueda eliminarlos localmente.

# 👨‍💻 ¿Como Funciona? 

**¿Que son los Sockets en Java?**

los Sockets son un sistema de comunicación entre procesos de distintas máquinas de una red, básicamente un puente por el cual un proceso puede enviar y recibir información. Los sockets pueden utilizar el protocolo streams TCP (Transmission Control Protocol) y datagramas UDP (User Datagram Protocol). 

Gracias al uso de los Sockets y Thread (Hilos) se puede lograr la comunicación cliente - servidor, el cliente se puede conectar con el servidor indicándole la IP a la cual se conecta y el puerto donde se está ejecutando el servidor.

>  Código del lado del cliente donde le indicamos IP y puerto al cual se debe conectar. Se crea el socket para conectar con el Servidor del Chat (*ClienteFinal.java*).
```Java
    try {
            socket = new Socket(Host, Puerto);
    } catch (IOException ex) {
            Logger.getLogger(ClienteFinal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage()+" Error En Cliente Conexion!");
    }

```
> Código del lado del servidor donde se crea el *ServerSocket* y le indicamos el puerto en escucha y el máximo de conexiones, por otra parte está el bucle infinito el cual va estar esperando por la conexión (*ServidorChat.java*).

```Java
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
        }
```

# Ejecución del código 🔥📈





# 🔧 Built With
- NetBeans - Java 
# 📝 License
Copyright © 2021 | JonatannGuerrero