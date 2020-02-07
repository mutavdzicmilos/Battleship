package procesi;

import java.net.Socket;
import javax.swing.JOptionPane;

import enumeracije.Vrsta_Igre1;
import pocetak.Glavni1;
import pocetak.Start1;

public class Klijent1 {
	public Socket klijentskiSoket;
	private int port;
	private String ip;

	public Klijent1() {
		try {
			ip = Start1.glavniMeni.nizTekstFieldova[0].getText();
			port = Integer.parseInt(Start1.glavniMeni.nizTekstFieldova[1].getText());
			Start1.vrstaIgre = Vrsta_Igre1.Klijent;
			klijentskiSoket = new Socket(ip, port);
			JOptionPane.showConfirmDialog(Start1.glavniEkran, "Uspešno povezan sa protivnikom!", "Obavestenje",
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

			Start1.igrac = new Igrac1();
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(Start1.glavniEkran, "Neuspesno povezivanje s protivnikom!", "Greska",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

			Start1.glavniMeni = new Glavni1();

		}
	}

}
