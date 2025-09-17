package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PalabraDAO {

    public Palabra obtenerPalabraAleatoria() {
        Palabra palabra = null;
        String sql = "{CALL sp_ObtenerPalabraAleatoria()}";
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            // Se crea una nueva instancia de Conexion
            Conexion cn = new Conexion();
            con = cn.Conexion(); // Se usa el método no estático
            cs = con.prepareCall(sql);
            rs = cs.executeQuery();

            if (rs.next()) {
                palabra = new Palabra();
                palabra.setPalabra(rs.getString("palabra"));
                palabra.setPista_uno(rs.getString("pista_uno"));
                palabra.setPista_dos(rs.getString("pista_dos"));
                palabra.setPista_tres(rs.getString("pista_tres"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener palabra: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Es crucial cerrar los recursos manualmente
            try {
                if (rs != null) rs.close();
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return palabra;
    }
}