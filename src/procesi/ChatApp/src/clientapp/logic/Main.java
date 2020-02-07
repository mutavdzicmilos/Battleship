/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapp.logic;

import form.JForm;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Milos <mm20160088@student.fon.bg.ac.rs>
 */
public class Main {

    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost", 9000);
                    System.out.println("Client: Connected with server");
                    new JForm(socket, 'c').setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }
}
