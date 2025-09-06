package modelo;

public class Palabra {
    
    private int idPalabra; 
    private String palabra;
    private String pistaUno;
    private String pistaDos;
    private String pistaTres;

    public Palabra() {
    }

    public Palabra(int idPalabra, String palabra, String pistaUno, String pistaDos, String pistaTres) {
        this.idPalabra = idPalabra;
        this.palabra = palabra;
        this.pistaUno = pistaUno;
        this.pistaDos = pistaDos;
        this.pistaTres = pistaTres;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPistaUno() {
        return pistaUno;
    }

    public void setPistaUno(String pistaUno) {
        this.pistaUno = pistaUno;
    }

    public String getPistaDos() {
        return pistaDos;
    }

    public void setPistaDos(String pistaDos) {
        this.pistaDos = pistaDos;
    }

    public String getPistaTres() {
        return pistaTres;
    }

    public void setPistaTres(String pistaTres) {
        this.pistaTres = pistaTres;
    }

    @Override
    public String toString() {
        return "Palabra{" + "idPalabra=" + idPalabra + ", palabra=" + palabra + ", pistaUno=" + pistaUno + ", pistaDos=" + pistaDos + ", pistaTres=" + pistaTres + '}';
    }
    
    
}
