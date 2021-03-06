/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorg2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author diurno2020
 */
public class HiloServidor extends Thread {

    private Socket skCliente;

    HiloServidor(Socket skCliente) {
        this.skCliente = skCliente;
    }

    @Override
    public void run() {
        String cadMensaje;
        boolean salir = false;

        try {
            // Se crean los streams de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());

            // Se notofica al cliente la conexi�n
            flujo_salida.writeUTF("Se ha conectado el cliente de forma correcta");

            do {
                // Leer texto del cliente y lo muestra por pantalla
                cadMensaje = flujo_entrada.readUTF();
                System.out.println("CLIENTE: " + cadMensaje);

                // Si el cliente manda el mensaje de cierre, la ejecucion finaliza
                if (cadMensaje.equalsIgnoreCase("exit")) {
                    salir = true;
                }
            } while (!salir);

            skCliente.close();
        } catch (IOException ex) {
            System.out.println("Error de E/S: " + ex.getMessage());
        } finally {
            // Se cierra la conexi�n
            System.out.println("Cliente desconectado");
        }
    }

}
