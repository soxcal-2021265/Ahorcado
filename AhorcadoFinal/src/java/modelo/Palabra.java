package modelo;

public class Palabra {
    
    private int id_Palabra; 
    private String palabra;
    private String pista_Uno;
    private String pista_Dos;
    private String pista_Tres;

    public Palabra() {
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

    public String getPista_Uno() {
        return pista_Uno;
    }

    public void setPista_Uno(String pista_Uno) {
        this.pista_Uno = pista_Uno;
    }

    public String getPista_Dos() {
        return pista_Dos;
    }

    public void setPista_Dos(String pista_Dos) {
        this.pista_Dos = pista_Dos;
    }

    public String getPista_Tres() {
        return pista_Tres;
    }

    public void setPista_Tres(String pista_Tres) {
        this.pista_Tres = pista_Tres;
    }

    @Override
    public String toString() {
        return "Palabra{" + "id_Palabra=" + id_Palabra + ", palabra=" + palabra + ", pista_Uno=" + pista_Uno + ", pista_Dos=" + pista_Dos + ", pista_Tres=" + pista_Tres + '}';
    }
    
    
}
