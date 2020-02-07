package procesi;

import enumeracije.Polje1;
import enumeracije.Orijentacije1;

public class Tabla1 {
	private Polje1[][] tabla;
	private int preostalo;
	public Brod1[] listaBrodova;
	public int brojBrodova;

	public Tabla1() {
		tabla = new Polje1[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				tabla[i][j] = Polje1.slobodan;
		
		listaBrodova = new Brod1[10];
		preostalo = 0;
		brojBrodova = 0;
	}

	public void ucitajPotez(Potez potez) {//posle svakog poteza, postavljamo stanje polja na osnovu cega kasnije bojimo sa azurirajTablu
		int red = potez.vratiRed();
		int kolona = potez.vratiKolonu();
		tabla[red][kolona] = potez.vratiRezultat();

		if (potez.vratiRezultat() == Polje1.potopljen) {
			int gornjiRed, gornjaKolona;
			boolean cont1 = false;
			boolean cont2 = false;
			boolean redar = false;
			boolean kolonar = false;
			while (redar == false) {
				if (red == 0) {
					gornjiRed = 0;
					cont1 = true;
				} else {
					gornjiRed = red - 1;
				}
				if (red == 9) {
					cont2 = true;
				}
				if (tabla[gornjiRed][kolona] == Polje1.pogodjen && cont1 == false) {
					tabla[gornjiRed][kolona] = Polje1.potopljen;
					red--;
				} else {
					cont1 = true;
					gornjiRed++;
				}
				if (cont1 == true) {
					if (tabla[gornjiRed][kolona] == Polje1.pogodjen || tabla[gornjiRed][kolona] == Polje1.potopljen) {
						tabla[gornjiRed][kolona] = Polje1.potopljen;
						red++;
					} else {
						cont2 = true;

					}
				}

				if (cont1 == true && cont2 == true)
					redar = true;
			}
			cont1 = false;
			cont2 = false;
			red = potez.vratiRed();
			while (kolonar == false) {
				if (kolona == 0) {
					gornjaKolona = 0;
					cont1 = true;
				} else {
					gornjaKolona = kolona - 1;
				}
				if (kolona == 9) {
					cont2 = true;
				}
				if (tabla[red][gornjaKolona] == Polje1.pogodjen && cont1 == false) {
					tabla[red][gornjaKolona] = Polje1.potopljen;
					kolona--;
				} else {
					cont1 = true;
					gornjaKolona++;
				}
				if (cont1 == true) {
					if (tabla[red][gornjaKolona] == Polje1.pogodjen || tabla[red][gornjaKolona] == Polje1.potopljen) {
						tabla[red][gornjaKolona] = Polje1.potopljen;
						kolona++;
					} else {
						cont2 = true;
					}
				}

				if (cont1 == true && cont2 == true)
					kolonar = true;

			}

		}


	}

	// red i kolona od 0, tamo od 1 pa sve smanjujemo za 1 indeks
	public boolean probajGusarski(int red, int kolona) {// 1 polje

		if (red > 9 || kolona > 9)
			return false;
		if (tabla[red][kolona] != Polje1.slobodan)
			return false;

		int redIznadNemoguc, kolonaLevoNemoguca, redIspodNemoguc, kolonaDesnoNemoguca;

		if (red == 9)
			redIspodNemoguc = red;
		else
			redIspodNemoguc = red + 1;

		if (red == 0)
			redIznadNemoguc = red;
		else
			redIznadNemoguc = red - 1;

		if (kolona == 9)
			kolonaDesnoNemoguca = kolona;
		else
			kolonaDesnoNemoguca = kolona + 1;

		if (kolona == 0)
			kolonaLevoNemoguca = kolona;
		else
			kolonaLevoNemoguca = kolona - 1;
		for (int i = redIznadNemoguc; i <= redIspodNemoguc; i++)
			for (int j = kolonaLevoNemoguca; j <= kolonaDesnoNemoguca; j++)
				tabla[i][j] = Polje1.nemoguc;

		tabla[red][kolona] = Polje1.zauzet;
		listaBrodova[brojBrodova] = new Brod1(new Koordinate1(red, kolona), 1, Orijentacije1.horizontalno);
		preostalo += 1;
		brojBrodova++;
		return true;
	}

	public boolean probajTrajekt(int red, int kolona, Orijentacije1 izabranaOrijentacija) {// 2 polja

		int redIznadNemoguc, kolonaLevoNemoguca, redIspodNemoguc, kolonaDesnoNemoguca;

		if (izabranaOrijentacija == Orijentacije1.vertikalno) {
			if (red + 1 > 9 || kolona > 9)
				return false;
			if (tabla[red][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red + 1][kolona] != Polje1.slobodan)
				return false;

			if (red + 1 == 9)
				redIspodNemoguc = red + 1;
			else
				redIspodNemoguc = red + 2;

			if (red == 0)
				redIznadNemoguc = red;
			else
				redIznadNemoguc = red - 1;

			if (kolona == 9)
				kolonaDesnoNemoguca = kolona;
			else
				kolonaDesnoNemoguca = kolona + 1;

			if (kolona == 0)
				kolonaLevoNemoguca = kolona;
			else
				kolonaLevoNemoguca = kolona - 1;

			tabla[red][kolona] = Polje1.zauzet;
			tabla[red + 1][kolona] = Polje1.zauzet;
		} else {

			if (red > 9 || kolona + 1 > 9)
				return false;
			if (tabla[red][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red][kolona + 1] != Polje1.slobodan)
				return false;

			if (red == 9)
				redIspodNemoguc = red;
			else
				redIspodNemoguc = red + 1;

			if (red == 0)
				redIznadNemoguc = red;
			else
				redIznadNemoguc = red - 1;

			if (kolona + 1 == 9)
				kolonaDesnoNemoguca = kolona + 1;
			else
				kolonaDesnoNemoguca = kolona + 2;

			if (kolona == 0)
				kolonaLevoNemoguca = kolona;
			else
				kolonaLevoNemoguca = kolona - 1;

			tabla[red][kolona] = Polje1.zauzet;
			tabla[red][kolona + 1] = Polje1.zauzet;
		}

		for (int i = redIznadNemoguc; i <= redIspodNemoguc; i++)
			for (int j = kolonaLevoNemoguca; j <= kolonaDesnoNemoguca; j++)
				if (tabla[i][j] != Polje1.zauzet)
					tabla[i][j] = Polje1.nemoguc;
		listaBrodova[brojBrodova] = new Brod1(new Koordinate1(red, kolona), 2, izabranaOrijentacija);
		preostalo += 2;
		brojBrodova++;

		return true;
	}

	public boolean probajTanker(int red, int kolona, Orijentacije1 izabranaOrijentacija) {// 3 polja

		int redIznadNemoguc, kolonaLevoNemoguca, redIspodNemoguc, kolonaDesnoNemoguca;

		if (izabranaOrijentacija == Orijentacije1.vertikalno) {
			if (red + 2 > 9 || kolona > 9)
				return false;
			if (tabla[red][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red + 1][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red + 2][kolona] != Polje1.slobodan)
				return false;

			if (red + 2 == 9)
				redIspodNemoguc = red + 2;
			else
				redIspodNemoguc = red + 3;

			if (red == 0)
				redIznadNemoguc = red;
			else
				redIznadNemoguc = red - 1;

			if (kolona == 9)
				kolonaDesnoNemoguca = kolona;
			else
				kolonaDesnoNemoguca = kolona + 1;

			if (kolona == 0)
				kolonaLevoNemoguca = kolona;
			else
				kolonaLevoNemoguca = kolona - 1;

			tabla[red][kolona] = Polje1.zauzet;
			tabla[red + 1][kolona] = Polje1.zauzet;
			tabla[red + 2][kolona] = Polje1.zauzet;
		} else {

			if (red > 9 || kolona + 2 > 9)
				return false;
			if (tabla[red][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red][kolona + 1] != Polje1.slobodan)
				return false;
			if (tabla[red][kolona + 2] != Polje1.slobodan)
				return false;

			if (red == 9)
				redIspodNemoguc = red;
			else
				redIspodNemoguc = red + 1;

			if (red == 0)
				redIznadNemoguc = red;
			else
				redIznadNemoguc = red - 1;

			if (kolona + 2 == 9)
				kolonaDesnoNemoguca = kolona + 2;
			else
				kolonaDesnoNemoguca = kolona + 3;

			if (kolona == 0)
				kolonaLevoNemoguca = kolona;
			else
				kolonaLevoNemoguca = kolona - 1;

			tabla[red][kolona] = Polje1.zauzet;
			tabla[red][kolona + 1] = Polje1.zauzet;
			tabla[red][kolona + 2] = Polje1.zauzet;
		}

		for (int i = redIznadNemoguc; i <= redIspodNemoguc; i++)
			for (int j = kolonaLevoNemoguca; j <= kolonaDesnoNemoguca; j++)
				if (tabla[i][j] != Polje1.zauzet)
					tabla[i][j] = Polje1.nemoguc;

		listaBrodova[brojBrodova] = new Brod1(new Koordinate1(red, kolona), 3, izabranaOrijentacija);
		brojBrodova++;
		preostalo += 3;
		return true;
	}

	public void provera() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				System.out.print(tabla[i][j] + "\t");
			System.out.print("\n");
		}
	}

	public boolean probajKruzer(int red, int kolona, Orijentacije1 izabranaOrijentacija) {// 4polja

		int redIznadNemoguc, kolonaLevoNemoguca, redIspodNemoguc, kolonaDesnoNemoguca;

		if (izabranaOrijentacija == Orijentacije1.vertikalno) {
			if (red + 3 > 9 || kolona > 9)
				return false;
			if (tabla[red][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red + 1][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red + 2][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red + 3][kolona] != Polje1.slobodan)
				return false;

			if (red + 3 == 9)
				redIspodNemoguc = red + 3;
			else
				redIspodNemoguc = red + 4;

			if (red == 0)
				redIznadNemoguc = red;
			else
				redIznadNemoguc = red - 1;

			if (kolona == 9)
				kolonaDesnoNemoguca = kolona;
			else
				kolonaDesnoNemoguca = kolona + 1;

			if (kolona == 0)
				kolonaLevoNemoguca = kolona;
			else
				kolonaLevoNemoguca = kolona - 1;

			tabla[red][kolona] = Polje1.zauzet;
			tabla[red + 1][kolona] = Polje1.zauzet;
			tabla[red + 2][kolona] = Polje1.zauzet;
			tabla[red + 3][kolona] = Polje1.zauzet;
		} else {

			if (red > 9 || kolona + 3 > 9)
				return false;
			if (tabla[red][kolona] != Polje1.slobodan)
				return false;
			if (tabla[red][kolona + 1] != Polje1.slobodan)
				return false;
			if (tabla[red][kolona + 2] != Polje1.slobodan)
				return false;
			if (tabla[red][kolona + 3] != Polje1.slobodan)
				return false;

			if (red == 9)
				redIspodNemoguc = red;
			else
				redIspodNemoguc = red + 1;

			if (red == 0)
				redIznadNemoguc = red;
			else
				redIznadNemoguc = red - 1;

			if (kolona + 3 == 9)
				kolonaDesnoNemoguca = kolona + 3;
			else
				kolonaDesnoNemoguca = kolona + 4;

			if (kolona == 0)
				kolonaLevoNemoguca = kolona;
			else
				kolonaLevoNemoguca = kolona - 1;

			tabla[red][kolona] = Polje1.zauzet;
			tabla[red][kolona + 1] = Polje1.zauzet;
			tabla[red][kolona + 2] = Polje1.zauzet;
			tabla[red][kolona + 3] = Polje1.zauzet;
		}

		for (int i = redIznadNemoguc; i <= redIspodNemoguc; i++)
			for (int j = kolonaLevoNemoguca; j <= kolonaDesnoNemoguca; j++)
				if (tabla[i][j] != Polje1.zauzet)
					tabla[i][j] = Polje1.nemoguc;
		listaBrodova[brojBrodova] = new Brod1(new Koordinate1(red, kolona), 4, izabranaOrijentacija);
		brojBrodova++;
		preostalo += 4;
		return true;
	}

	public void obradiPotez(Potez potez) {// promeni naziv

		int red = potez.vratiRed();
		int kolona = potez.vratiKolonu();
		Polje1 rezultat = Polje1.promasen;

		if (tabla[red][kolona] == Polje1.zauzet) {
			tabla[red][kolona] = Polje1.pogodjen;
			preostalo--;

		} else {
			tabla[red][kolona] = Polje1.promasen;// apdejtujemo u tabli stanje polja koje je gadjano

			potez.podesiRezultat(Polje1.promasen);
			return;
		}

		Koordinate1 par = new Koordinate1(red, kolona);
		for (int i = 0; i < brojBrodova; i++) {
			listaBrodova[i].napad(potez); // apdejtujemo u listi brodova stanje brodova
			if (listaBrodova[i].pripadaBrodu(par))
				rezultat = listaBrodova[i].vratiStanje(); // pogodjen ili potopljen
		}
		potez.podesiRezultat(rezultat);
		ucitajPotez(potez);

	}

	public boolean krajIgre() {
		return preostalo == 0;
	}

	public boolean daLiSmemDaKliknem(int red, int kolona) {
		if (tabla[red][kolona] == Polje1.promasen || tabla[red][kolona] == Polje1.pogodjen
				|| tabla[red][kolona] == Polje1.potopljen)
			return false;
		else
			return true;
	}

	public Polje1 vratiStanjePolja(int red, int kolona) {
		return this.tabla[red][kolona];
	}

}
