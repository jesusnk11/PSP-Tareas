/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorg;

import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author diurno2020
 */
public class HiloTeclado extends Thread {

    private Socket skCliente;

    HiloTeclado() {
    }

    @Override
    public void run() {
        String cadMensaje;
        boolean salir = false;

        // Se crea el stream de salida y el scanner para leer del teclado
        Scanner teclado = new Scanner(System.in);

        do {
            // Lee del teclado 
            cadMensaje = teclado.nextLine();

            // Si se lee el mensaje de cierre, la ejecucion finaliza
            if (cadMensaje.equalsIgnoreCase("exit")) {
                salir = true;
            }
        } while (!salir);

        System.exit(0);
        // Se cierra la conexión
        System.out.println("Cliente desconectado");

    }
}
