package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.UsuarioDAO;

@WebServlet(name = "Validar", urlPatterns = {"/Validar"})
public class Validar extends HttpServlet {

    private final UsuarioDAO usuarioDao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("Ingresar".equalsIgnoreCase(accion)) {
            // Se puede mantener 'user' o cambiar a 'email'
            String user = request.getParameter("txtUsuario"); 
            String pass = request.getParameter("txtPassword");

            // La variable 'correo' es válida, pero se podría llamar 'usuario' para ser más consistente
            Usuario usuario = usuarioDao.validar(user, pass);

            if (usuario != null && usuario.getCorreo() != null) {
                // Guardar usuario en sesión
                HttpSession session = request.getSession();
                // Usa "usuario" como clave para ser consistente con la lógica original
                session.setAttribute("usuario", usuario); 

                // Redirigir directamente a ahorcado.jsp
                request.getRequestDispatcher("ahorcado.jsp").forward(request, response);
            } else {
                // Credenciales incorrectas
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            // Acción inválida, redirige al login
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige GET al login
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para validar usuarios y redirigir al juego del ahorcado.";
    }
}