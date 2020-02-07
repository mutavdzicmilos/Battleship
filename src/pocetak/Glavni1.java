package pocetak;

import java.awt.event.ActionEvent;

public class Glavni1 extends Meni1 {

    private static String[] imenaDugmica = {"Server", "Klijent", "Izlaz"};
    private static String[] imenaTextFieldova = {};

    public Glavni1() {
        super(imenaDugmica, imenaTextFieldova);
        EventHandler meniHandler = new MeniEventHandler();
        for (int i = 0; i < 3; i++)
            nizDugmica[i].addActionListener(meniHandler);
    }

    class MeniEventHandler extends Meni1.EventHandler {

        public void actionPerformed(ActionEvent event) { //ugasi glavni meni i poziva koji mu je potreban
        	Start1.glavniMeni.meniPanel.setVisible(false);
            if (event.getSource() == Meni1.nizDugmica[0])
                Start1.glavniMeni = new ServerMeni1();
         else if (event.getSource() == Meni1.nizDugmica[1])
                Start1.glavniMeni = new KlijentMeni1();
            else if (event.getSource() == Meni1.nizDugmica[2])
                    Start1.glavniEkran.dispose();
        }
    }

}
