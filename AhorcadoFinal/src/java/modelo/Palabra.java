package modelo;

public class Palabra {
    
    private int id_Palabra; 
    private String palabra;
    private String pista_uno;
    private String pista_dos;
    private String pista_tres;

    public Palabra() {
    }

    public Palabra(int id_Palabra, String palabra, String pista_uno, String pista_dos, String pista_tres) {
        this.id_Palabra = id_Palabra;
        this.palabra = palabra;
        this.pista_uno = pista_uno;
        this.pista_dos = pista_dos;
        this.pista_tres = pista_tres;
    }
    
    public int getId_Palabra() {
        return id_Palabra;
    }

    public void setId_Palabra(int id_Palabra) {
        this.id_Palabra = id_Palabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPista_uno() {
        return pista_uno;
    }

    public void setPista_uno(String pista_uno) {
        this.pista_uno = pista_uno;
    }

    public String getPista_dos() {
        return pista_dos;
    }

    public void setPista_dos(String pista_dos) {
        this.pista_dos = pista_dos;
    }

    public String getPista_tres() {
        return pista_tres;
    }

    public void setPista_tres(String pista_tres) {
        this.pista_tres = pista_tres;
    }

    @Override
    public String toString() {
        return "Palabra{" + "id_Palabra=" + id_Palabra + ", palabra=" + palabra + ", pista_uno=" + pista_uno + ", pista_dos=" + pista_dos + ", pista_tres=" + pista_tres + '}';
    }
    
}
