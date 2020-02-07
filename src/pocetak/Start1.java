package pocetak;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import enumeracije.Vrsta_Igre1;
import procesi.Igrac1;
import procesi.Klijent1;
import procesi.NovaIgra1;
import procesi.Server1;


public class Start1 {
	  public static JFrame glavniEkran;
	  public static Meni1 glavniMeni;    
      public static Vrsta_Igre1 vrstaIgre;
      public static Server1 server;
	  public static Klijent1 klijent;
	  public static Igrac1 igrac;
	  public static JPanel prviPanel;
	  public static JPanel drugiPanel;
	  public static JPanel treciPanel;
	  public static JPanel cetvrtiPanel;
	  public static NovaIgra1 novaIgra;
	  public static boolean mojPotez;
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				 glavniEkran = new JFrame("Potapanje brodova");
				 glavniEkran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                glavniEkran.setMinimumSize(new Dimension(1024, 600));
	                glavniEkran.setResizable(false);
	                glavniEkran.setVisible(true);
	                ImageIcon ikonica = new ImageIcon("asd.jpg");
	                glavniEkran.setIconImage(ikonica.getImage());
	                glavniMeni = new Glavni1();
				
			}
		});

	}

}
