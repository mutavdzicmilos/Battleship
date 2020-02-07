package procesi;


import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

import enumeracije.Vrsta_Igre1;
import pocetak.Glavni1;
import pocetak.Start1;

public class Server1 {
	  private ServerSocket serverskiSoket;
	    public Socket klijentskiSoket;
	    private int port;

    public Server1() {
        try {
        	port = Integer.parseInt(Start1.glavniMeni.nizTekstFieldova[0].getText());
        	Start1.vrstaIgre = Vrsta_Igre1.Server;
            serverskiSoket = new ServerSocket(port);
            serverskiSoket.setSoTimeout(20000);
          	if (serverskiSoket != null) {
        		klijentskiSoket = serverskiSoket.accept();
        		JOptionPane.showConfirmDialog(Start1.glavniEkran, "Uspešno povezan sa protivnikom!",
        				"Obavestenje", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        		
        		Start1.igrac = new Igrac1();

            }
        } catch ( Exception e) {

        	JOptionPane.showConfirmDialog(Start1.glavniEkran, "Neuspesno povezivanje sa protivnikom!",
        			"Greska", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        	
        	Start1.glavniMeni = new Glavni1();
        	
        }
    }
}
