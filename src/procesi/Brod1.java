package procesi;

import enumeracije.Orijentacije1;
import enumeracije.Polje1;

public class Brod1 {
	private Polje1 stanje;   //stanje celog broda
	private  int velicinaBroda;
	private  Koordinate1[] poljaBroda;  // koordinate polja koja zauzima taj brod
	private  Polje1[] stanjePolja;//niz stanja svakog polja jednog broda
	
	public Brod1(Koordinate1 pocetak, int velicina, Orijentacije1 izabranaOrijentacija) {
		velicinaBroda = velicina;
		poljaBroda = new Koordinate1[velicinaBroda];
		stanjePolja = new Polje1[velicinaBroda];
		stanje = Polje1.netaknut;
		int red = pocetak.red;
		int kolona = pocetak.kolona;
		if (izabranaOrijentacija == Orijentacije1.horizontalno)
			for (int i = 0; i < velicinaBroda; i++) {
				poljaBroda[i] = new Koordinate1(red, kolona + i);
				stanjePolja[i] = Polje1.netaknut;
			}
		else
			for (int i = 0; i < velicinaBroda; i++) {
				poljaBroda[i] = new Koordinate1(red + i, kolona);
				stanjePolja[i] = Polje1.netaknut;
			}

	}
	 public Polje1 vratiStanje() {
	        return stanje;
	    }

	    public boolean pripadaBrodu(Koordinate1 polje) {
	        for (int i = 0; i < velicinaBroda; i++)
	            if (poljaBroda[i].red == polje.red && poljaBroda[i].kolona == polje.kolona)
	                return true;
	        return false;
	    }
	    public Koordinate1[] vratiPolja() {
	        return poljaBroda;
	    }
	    public Polje1[] vratiStanja() {
	    	return stanjePolja;
	    }

	    public int vratiVelicinu() {
	        return velicinaBroda;
	    }
	    public void napad(Potez potez) {
	        int redPoteza = potez.vratiRed();
	        int kolonaPoteza = potez.vratiKolonu();
	        for (int i = 0; i < velicinaBroda; i++) {
	            if (redPoteza == poljaBroda[i].red && kolonaPoteza == poljaBroda[i].kolona) {
	                stanje = Polje1.pogodjen;
	                stanjePolja[i] = Polje1.pogodjen;
	            };
	        }
	        int pogodjenoDelova = 0;

	        for (int i = 0; i < velicinaBroda; i++)
	            if (stanjePolja[i] == Polje1.pogodjen)
	                pogodjenoDelova++;

	        if (pogodjenoDelova == velicinaBroda)
	            stanje = Polje1.potopljen;

	    }

}
