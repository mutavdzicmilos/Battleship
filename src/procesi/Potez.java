package procesi;

import java.io.Serializable;

import enumeracije.Polje1;

@SuppressWarnings("serial")
public class Potez implements Serializable{

	private int red;
    private int kolona;
    private Polje1 rezultat;
    
    public Potez(int red, int kolona) {
        this.red = red;
        this.kolona = kolona;
    }
    public int vratiRed() {
        return this.red;
    }

    public int vratiKolonu() {
        return this.kolona;
    }

    public void podesiRezultat(Polje1 rezultat) {
        this.rezultat = rezultat;
    }

    public Polje1 vratiRezultat() {
        return this.rezultat;
    }

}
