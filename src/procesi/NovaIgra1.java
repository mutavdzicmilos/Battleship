package procesi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import enumeracije.Polje1;
import enumeracije.Vrsta_Igre1;
import pocetak.Start1;

public class NovaIgra1 {
	private JLabel[][] mojeLabele;
	private JLabel[][] protivnikoveLabele;
	private DugmeProsireno1[][] protivnikoviDugmici;
	private DugmeProsireno1[][] mojiDugmici;
	private JLabel preostaloBrodova;
	private int potopljenobrodova;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	public Thread nit;

	public NovaIgra1() {

		potopljenobrodova = 0;
		if (Start1.vrstaIgre == Vrsta_Igre1.Klijent)
			Start1.mojPotez = false;
		if (Start1.vrstaIgre == Vrsta_Igre1.Server)
			Start1.mojPotez = true;

		if (Start1.vrstaIgre == Vrsta_Igre1.Klijent)
			try {

				outputStream = new ObjectOutputStream(Start1.klijent.klijentskiSoket.getOutputStream());// slanje klika na dugme ok
				outputStream.flush();
				inputStream = new ObjectInputStream(Start1.klijent.klijentskiSoket.getInputStream());// prijem protivnikovog ok
			} catch (IOException e) {
			}
		else
			try {
				outputStream = new ObjectOutputStream(Start1.server.klijentskiSoket.getOutputStream()); //slanje klika na dugme ok
				outputStream.flush();
				inputStream = new ObjectInputStream(Start1.server.klijentskiSoket.getInputStream()); // prijem protivnikovog ok
			} catch (IOException e) {
			}
		postaviPanele();
		mojiDugmici = new DugmeProsireno1[10][10];
		protivnikoviDugmici = new DugmeProsireno1[10][10];
		mojeLabele = new JLabel[2][10];
		protivnikoveLabele = new JLabel[2][10];

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				mojiDugmici[i][j] = new DugmeProsireno1("  ");
				mojiDugmici[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 12));
				mojiDugmici[i][j].setEnabled(false);

				protivnikoviDugmici[i][j] = new DugmeProsireno1("  ");
				protivnikoviDugmici[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 12));
				protivnikoviDugmici[i][j].red = i;
				protivnikoviDugmici[i][j].kolona = j;

				protivnikoviDugmici[i][j].addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						DugmeProsireno1 pritisnutoDugme = (DugmeProsireno1) arg0.getSource();
						int red = pritisnutoDugme.red;
						int kolona = pritisnutoDugme.kolona;
						Potez potez;
						if (Start1.igrac.protivnikovaTabla.daLiSmemDaKliknem(red, kolona)) {
							potez = new Potez(red, kolona);
							try {
								outputStream.writeObject(potez);
								outputStream.flush();
								potez = (Potez) inputStream.readObject();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}

							Start1.igrac.ucitajPotez(potez);
							if (potez.vratiRezultat() != Polje1.promasen) {
								if (potez.vratiRezultat() == Polje1.potopljen)
									potopljenobrodova = potopljenobrodova + 1;
								if (potopljenobrodova == 10) {
									Start1.mojPotez = false;
									azurirajTable();
									Start1.glavniEkran.repaint();
									JOptionPane.showConfirmDialog(Start1.glavniEkran, "Pobeda!", "Obavestenje",
											JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
									return;
								}
							} else {
								Start1.mojPotez = false;
								Cekanje();
							}
						}
						azurirajTable();
					}
				});

				char letter = (char) (i + 'a');
				String string = letter + "";
				mojeLabele[0][i] = new JLabel(string);
				mojeLabele[1][i] = new JLabel(new Integer(i + 1).toString());

				protivnikoveLabele[0][i] = new JLabel(string);
				protivnikoveLabele[1][i] = new JLabel(new Integer(i + 1).toString());

			}
		azurirajTable();
	}

	protected void postaviPanele() {

		Start1.prviPanel.setVisible(false);
		Start1.drugiPanel.setVisible(false);
		Start1.treciPanel.setVisible(false);
		Start1.cetvrtiPanel.setVisible(false);

		Start1.prviPanel = new JPanel(new GridBagLayout());
		Start1.drugiPanel = new JPanel(new GridBagLayout());
		Start1.treciPanel = new JPanel(new GridBagLayout());
		Start1.cetvrtiPanel = new JPanel(new GridBagLayout());

		Start1.glavniEkran.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 0.1;
		Start1.glavniEkran.getContentPane().add(Start1.prviPanel, gbc);
		gbc.gridy = 1;
		Start1.glavniEkran.getContentPane().add(Start1.drugiPanel, gbc);
		gbc.gridx = 1;
		Start1.glavniEkran.getContentPane().add(Start1.treciPanel, gbc);
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		gbc.gridx = 0;
		Start1.glavniEkran.getContentPane().add(Start1.cetvrtiPanel, gbc);

		Start1.glavniEkran.repaint();

		gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.insets = new Insets(50, 0, 0, 0);
		preostaloBrodova = new JLabel("Preostalo brodova: " + (10 - potopljenobrodova));
		Start1.cetvrtiPanel.add(preostaloBrodova, gbc);
	}

	protected void azurirajTable() {
		Start1.drugiPanel.removeAll();
		Start1.drugiPanel.repaint();
		Start1.treciPanel.removeAll();
		Start1.treciPanel.repaint();

		preostaloBrodova.setText("Preostalo brodova : " + (10 - potopljenobrodova));

		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 1; i < 11; i++) {
			gbc.gridx = i;
			Start1.drugiPanel.add(mojeLabele[1][i - 1], gbc);// dodajemo labele nama i protivniku
			Start1.treciPanel.add(protivnikoveLabele[1][i - 1], gbc);
		}
		gbc.gridx = 0;
		for (int i = 1; i < 11; i++) {
			gbc.gridy = i;
			Start1.drugiPanel.add(mojeLabele[0][i - 1], gbc);
			Start1.treciPanel.add(protivnikoveLabele[0][i - 1], gbc);
		}
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				gbc.gridx = j + 1;
				gbc.gridy = i + 1;
				// odredjujemo boju mojih dugmica
				if (Start1.igrac.mojaTabla.vratiStanjePolja(i, j) == Polje1.zauzet)
					mojiDugmici[i][j].setBackground(new Color(0, 255, 0));// zelena
				else if (Start1.igrac.mojaTabla.vratiStanjePolja(i, j) == Polje1.promasen)
					mojiDugmici[i][j].setText("o");// protivnik gadjao i promasio
				else if (Start1.igrac.mojaTabla.vratiStanjePolja(i, j) == Polje1.pogodjen)
					mojiDugmici[i][j].setBackground(new Color(255, 0, 0));// protivnik gadjao i pogodio
				else if (Start1.igrac.mojaTabla.vratiStanjePolja(i, j) == Polje1.potopljen)
					mojiDugmici[i][j].setBackground(new Color(0, 0, 0));

				Start1.drugiPanel.add(mojiDugmici[i][j], gbc); // dodao moje dugme

				// odredjujemo boju protivnikovih dugmica
				if (Start1.igrac.protivnikovaTabla.vratiStanjePolja(i, j) == Polje1.promasen)
					protivnikoviDugmici[i][j].setText("o");
				else if (Start1.igrac.protivnikovaTabla.vratiStanjePolja(i, j) == Polje1.pogodjen)
					protivnikoviDugmici[i][j].setBackground(new Color(255, 0, 0));// gadjamo protivnika i pogadjamo
				else if (Start1.igrac.protivnikovaTabla.vratiStanjePolja(i, j) == Polje1.potopljen)
					protivnikoviDugmici[i][j].setBackground(new Color(0, 0, 0));

				if (Start1.mojPotez == false)
					protivnikoviDugmici[i][j].setEnabled(false);
				else
					protivnikoviDugmici[i][j].setEnabled(true);

				Start1.treciPanel.add(protivnikoviDugmici[i][j], gbc);// dodao protivnikovo dugme
			}

	}

	public void Cekanje() {
		Start1.novaIgra.nit = new Thread(new Runnable() {
			public void run() {
				Potez potez = null;
				while (Start1.mojPotez == false) {
					try {
						potez = (Potez) inputStream.readObject();
						Start1.igrac.mojaTabla.obradiPotez(potez);

						azurirajTable();
						Start1.glavniEkran.repaint();
						outputStream.writeObject(potez);
						outputStream.flush();
					} catch (ClassNotFoundException e) {
					} catch (IOException e) {
					}
					if (potez.vratiRezultat() != Polje1.promasen) {
						if (Start1.igrac.krajIgre()) {
							Start1.mojPotez = false;
							azurirajTable();
							Start1.glavniEkran.repaint();
							JOptionPane.showConfirmDialog(Start1.glavniEkran, "Poraz!", "Obavestenje",
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else {
						Start1.mojPotez = true;
						azurirajTable();
						Start1.glavniEkran.repaint();
					}
				}
			}
		});
		Start1.novaIgra.nit.start();
	}
}
