/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diurno2020
 */
public class ServidorG {

    Socket skCliente;
    static final int PUERTO = 15000;
    static HiloTeclado hTeclado;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        Thread tServidor;
        ServerSocket skServidor = null;
        ExecutorService executor = Executors.newFixedThreadPool(100);

        try {
            skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);

            hTeclado = new HiloTeclado();
            hTeclado.start();

            while (hTeclado.isAlive()) {
                Socket skCliente = skServidor.accept();
                System.out.println("Cliente conectado: " + skCliente.getInetAddress() + ":" + skCliente.getPort());

                executor.submit(new RunServidor(skCliente));

                tServidor = new Thread(new RunServidor(skCliente));
                tServidor.start();

            }
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (IOException e) {
            System.out.println("Error E/S en el servidor: " + e.getMessage());
        } finally {
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
