package modelo;

public class Usuario {
    private int codigo_Usuario;
    private String correo;
    private String contrasena;    

    public int getCodigo_Usuario() {
        return codigo_Usuario;
    }

    public void setCodigo_Usuario(int codigo_Usuario) {
        this.codigo_Usuario = codigo_Usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public Usuario(int codigo_Usuario, String correo, String contrasena) {
        this.codigo_Usuario = codigo_Usuario;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo_Usuario=" + codigo_Usuario + ", correo=" + correo + ", contrasena=" + contrasena + '}';
    }
    
    
}
