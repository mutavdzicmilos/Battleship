package pocetak;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import enumeracije.Vrsta_Igre1;
import procesi.Server1;

public class ServerMeni1 extends Meni1 {

	private static String[] imenaDugmica = { "Povezi", "Nazad" };
	private static String[] imenaTextFieldova = { "Unesite broj porta" };

	public ServerMeni1() {
		super(imenaDugmica, imenaTextFieldova);
		EventHandler meniHandler = new MeniEventHandler();
		for (int i = 0; i < 2; i++)
			nizDugmica[i].addActionListener(meniHandler);
		nizTekstFieldova[0].addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(nizTekstFieldova[0].getText().equals("Unesite broj porta"))
					nizTekstFieldova[0].setText("");
			}
		});
	}

	class MeniEventHandler extends Meni1.EventHandler {

		public void actionPerformed(ActionEvent event) {
			Start1.glavniMeni.meniPanel.setVisible(false);
			if (event.getSource() == Meni1.nizDugmica[0]) {
				Start1.vrstaIgre = Vrsta_Igre1.Server;
				Start1.server = new Server1();
			} else if (event.getSource() == Meni1.nizDugmica[1])
				Start1.glavniMeni = new Glavni1();
			Start1.glavniEkran.revalidate(); //revalidate se koristi za dodavanje novih komponenata - kada prelazimo sa menija na postavku brodova


		}
	}

}
