/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp.logic;

import form.JForm;
import java.io.IOException;
import java.net.ServerSocket;
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
                     ServerSocket serverSocket=new ServerSocket(9000);
        System.out.println("Waiting...");
        Socket socket=serverSocket.accept();
        System.out.println("Server:Client connected");
        new JForm(socket,'s').setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }
}
