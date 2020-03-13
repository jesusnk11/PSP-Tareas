/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorg2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author diurno2020
 */
public class ServidorG2 {

    Socket skCliente;
    static final int PUERTO = 15000;

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        ServerSocket skServidor = null;
        Thread tServidor;

        ExecutorService executor = Executors.newFixedThreadPool(100);

        try {
            // Inicio el servidor en el puerto
            skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);

            // Se conecta un cliente
            Socket skCliente = skServidor.accept();
            System.out.println("Cliente conectado: " + skCliente.getInetAddress() + ":" + skCliente.getPort());

            while (true) {
                skCliente = skServidor.accept();
                System.out.println("Cliente conectado: " + skCliente.getInetAddress() + ":" + skCliente.getPort());

                executor.submit(new HiloServidor(skCliente));

            }
            
            
        } catch (IOException e) {
            System.out.println("Error E/S en el servidor: " + e.getMessage());
        } finally {
            // Intentar cerrar el socket del servidor por si ocurre cualquier error
            try {
                if (skServidor != null) {
                    skServidor.close();
                }
            } catch (IOException ex) {
                System.out.println("Error E/S al finalizar el servidor: " + ex.getMessage());
            }
        }

    }

}
