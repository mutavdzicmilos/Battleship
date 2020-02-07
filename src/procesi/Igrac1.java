package procesi;


public  class Igrac1 {
    public Tabla1 mojaTabla;
    public Tabla1 protivnikovaTabla;
    private PostavljanjeBrodova1 postavka;

    public Igrac1() {
    	protivnikovaTabla= new Tabla1();
        postavka = new PostavljanjeBrodova1();
        mojaTabla= postavka.vratiTablu();//referentni tip podataka,postavljamo pokazivac mojaTabla na tabla iz PostavljanjaBrodova
        
    }

    public void provera() {
        mojaTabla.provera();
    }

    protected void ucitajPotez(Potez potez) {
        protivnikovaTabla.ucitajPotez(potez);
    }

    public boolean krajIgre() {
        return mojaTabla.krajIgre();
    }
}

