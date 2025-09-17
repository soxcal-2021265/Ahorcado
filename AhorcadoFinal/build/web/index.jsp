<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Juego del Ahorcado Mario</title>
        <link rel="stylesheet" href="Styles/Style.css">
    </head>
    <body>
        <div class="form-box">
            <div id="login-container" class="login-container">
                <h1>Iniciar Sesión</h1>
                <form id="login-form" action="Validar" method="post">
                    <div class="input-group">
                        <label for="usuario">Correo Electrónico:</label>                         
                        <input type="text" id="usuario" name="txtUsuario" required>
                    </div>
                    <div class="input-group">
                        <label for="contrasena">Contraseña:</label>
                        <input type="password" id="contrasena" name="txtPassword" required>
                    </div>
                    <button type="submit" name="accion" value="Ingresar">Entrar al Juego</button>
                </form>
            </div>
    </body>
</html>
