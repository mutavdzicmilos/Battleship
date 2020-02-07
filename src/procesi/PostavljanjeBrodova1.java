package procesi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import enumeracije.Polje1;
import enumeracije.Vrsta_Igre1;
import pocetak.Start1;
import enumeracije.Orijentacije1;

public class PostavljanjeBrodova1 {

	private DugmeProsireno1 dugmici[][];
	private JLabel labele[][];
	private JButton dugmeOK;
	private Tabla1 tabla;
	private JRadioButton kruzer;
	private JRadioButton tanker;
	private JRadioButton trajekt;
	private JRadioButton gusarski;
	private JComboBox<String> orijentacijaBroda;
	private int brojGusarskih = 0;
	private int brojTrajekta = 0;
	private int brojTankera = 0;
	private int brojKruzera = 0;

	public PostavljanjeBrodova1() {
		
		Start1.glavniMeni.meniPanel.setVisible(false);
		tabla = new Tabla1();
		postaviPanele();
		dugmici = new DugmeProsireno1[10][10];
		labele = new JLabel[2][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				dugmici[i][j] = new DugmeProsireno1(" ");
				dugmici[i][j].red = i;//povezujemo sa nasim poljima table kasnije
				dugmici[i][j].kolona = j;
				dugmici[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 12));

				dugmici[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						DugmeProsireno1 pritisnutoDugme = (DugmeProsireno1) a.getSource(); // citamo na koje dugme je kliknuto
						Orijentacije1 izabranaOrijentacija;
						if (orijentacijaBroda.getSelectedIndex() == 0)
							izabranaOrijentacija = Orijentacije1.vertikalno;
						else
							izabranaOrijentacija = Orijentacije1.horizontalno;
						if (kruzer.isSelected()) {
							if (tabla.probajKruzer(pritisnutoDugme.red, pritisnutoDugme.kolona, izabranaOrijentacija)) {
								brojKruzera++;
							}
							if (brojKruzera == 1) {
								kruzer.setEnabled(false);
								kruzer.setSelected(false);

								if (brojGusarskih != 4)// postavimo na najveci koji moze i dalje
									gusarski.setSelected(true);
								if (brojTrajekta != 3)
									trajekt.setSelected(true);
								if (brojTankera != 2)
									tanker.setSelected(true);
							}
						} else if (tanker.isSelected()) {
							if (tabla.probajTanker(pritisnutoDugme.red, pritisnutoDugme.kolona, izabranaOrijentacija)) {
								brojTankera++;
							}
							if (brojTankera == 2) {
								tanker.setEnabled(false);
								tanker.setSelected(false);

								if (brojGusarskih != 4)
									gusarski.setSelected(true);
								if (brojTrajekta != 3)
									trajekt.setSelected(true);
								if (brojKruzera != 1)
									kruzer.setSelected(true);
							}
						} else if (trajekt.isSelected()) {
							if (tabla.probajTrajekt(pritisnutoDugme.red, pritisnutoDugme.kolona,
									izabranaOrijentacija)) {
								brojTrajekta++;
							}
							if (brojTrajekta == 3) {
								trajekt.setEnabled(false);
								trajekt.setSelected(false);

								if (brojGusarskih != 4)
									gusarski.setSelected(true);
								if (brojTankera != 2)
									tanker.setSelected(true);
								if (brojKruzera != 1)
									kruzer.setSelected(true);
							}
						} else if (gusarski.isSelected()) {
							if (tabla.probajGusarski(pritisnutoDugme.red, pritisnutoDugme.kolona)) {
								brojGusarskih++;
							}
							if (brojGusarskih == 4) {
								gusarski.setEnabled(false);
								gusarski.setSelected(false);

								if (brojTrajekta != 3)
									trajekt.setSelected(true);
								if (brojTankera != 2)
									tanker.setSelected(true);
								if (brojKruzera != 1)
									kruzer.setSelected(true);
							}
						}

						int brojPostavljenihBrodova = brojGusarskih + brojTrajekta + brojTankera + brojKruzera;
						if (brojPostavljenihBrodova == 10) {
							dugmeOK.setEnabled(true);
							for (int i = 0; i < 10; i++)
								for (int j = 0; j < 10; j++)
									dugmici[i][j].setEnabled(false);
						}
						dodajDugmiceNaEkran();// update dugmica nakon svakog klika
					}

				});

				char slova = (char) (i + 'a');
				String s = slova + "";
				labele[0][i] = new JLabel(s);
				labele[1][i] = new JLabel(new Integer(i + 1).toString());

			}
		dodajDugmiceNaEkran();// dodajemo prvi put na ekran dugmice
	}

	
	private void postaviPanele() {
		Start1.prviPanel = new JPanel(new GridBagLayout());// gornji
		Start1.drugiPanel = new JPanel(new GridBagLayout());// levi
		Start1.treciPanel = new JPanel(new GridBagLayout());// desni
		Start1.cetvrtiPanel = new JPanel(new GridBagLayout());// donji

		Start1.glavniEkran.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		Start1.glavniEkran.getContentPane().add(Start1.prviPanel, gbc);

		gbc.gridy = 1; 
		Start1.glavniEkran.getContentPane().add(Start1.drugiPanel, gbc);

		gbc.gridx = 1;
		Start1.glavniEkran.getContentPane().add(Start1.treciPanel, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		Start1.glavniEkran.getContentPane().add(Start1.cetvrtiPanel, gbc);

		JLabel naziv = new JLabel("Postavljanje brodova");

		dugmeOK = new JButton("OK");
		dugmeOK.setEnabled(false);
		naziv.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);// razmak (odozgo,levo,dno,desno)

		Start1.prviPanel.add(naziv, gbc);
		Start1.cetvrtiPanel.add(dugmeOK, gbc);
		dugmeOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  if (Start1.vrstaIgre == Vrsta_Igre1.Klijent) {
	                	Start1.novaIgra = new NovaIgra1();//iscrtava sve dugmice i nase i protivnikove
	                	Start1.novaIgra.Cekanje();//pocinje igra
	                } else if (Start1.vrstaIgre == Vrsta_Igre1.Server)
	                	Start1.novaIgra = new NovaIgra1();
			}
		});

		kruzer = new JRadioButton("KRUZER-VELICINA 4 POLJA (1)", true);
		tanker = new JRadioButton("TANKER-VELICINA 3 POLJA (2)", false);
		trajekt = new JRadioButton("TRAJEKT-VELICINA 2 POLJA (3)", false);
		gusarski = new JRadioButton("GUSARSKI-VELICINA 1 POLJA (4)", false);
		ButtonGroup odabirBroda = new ButtonGroup();// pravimo grupu radioButtona da bi samo jedno od njih moglo da se izabere
		odabirBroda.add(gusarski);
		odabirBroda.add(trajekt);
		odabirBroda.add(tanker);
		odabirBroda.add(kruzer);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;//levo poravnanje za radio buttone
		Start1.treciPanel.add(kruzer, gbc);
		gbc.gridy++;
		Start1.treciPanel.add(tanker, gbc);
		gbc.gridy++;
		Start1.treciPanel.add(trajekt, gbc);
		gbc.gridy++;
		Start1.treciPanel.add(gusarski, gbc);
		String[] tekst = { "Vertikalno", "Horizontalno" };
		orijentacijaBroda = new JComboBox<String>(tekst);
		gbc.gridy++;
		Start1.treciPanel.add(orijentacijaBroda, gbc);

	}

	private void dodajDugmiceNaEkran() {
		Start1.drugiPanel.removeAll();
		Start1.drugiPanel.repaint();// update postojecih dugmica novim prilikom svakog klika
		GridBagConstraints gbc = new GridBagConstraints();

		for (int i = 1; i < 11; i++) {
			gbc.gridy = i; // jer dugmici krecu od (1,1)   
			Start1.drugiPanel.add(labele[0][i - 1], gbc);
		}
		gbc.gridy = 0;
		for (int i = 1; i < 11; i++) {
			gbc.gridx = i;
			Start1.drugiPanel.add(labele[1][i - 1], gbc);
		}

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				gbc.gridx = j + 1;
				gbc.gridy = i + 1;
				dugmici[i][j].setText("  ");//zbog o prosirili dugme, bile tri tackice na dugmetu
				dugmici[i][j].setPreferredSize(dugmici[i][j].getPreferredSize());

				if (tabla.vratiStanjePolja(i, j) == Polje1.zauzet)
					dugmici[i][j].setBackground(new Color(0, 255, 0));//zeleno
				if (tabla.vratiStanjePolja(i, j) == Polje1.nemoguc)
					dugmici[i][j].setText("o");

				Start1.drugiPanel.add(dugmici[i][j], gbc);
			}
	}
public Tabla1 vratiTablu() {
	return this.tabla;
}


}
