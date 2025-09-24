<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Juego del Ahorcado</title>
    <link rel="stylesheet" href="Styles/Style.css">
</head>
<body>

    <div class="pantalla-inicio">
        <button id="btn-iniciar">Iniciar Juego</button>
    </div>

    <div id="main-container" class="main-container">
        <div class="game-layout">
            <div class="imagen-container">
                <img id="imagen-ahorcado" src="Image/1.png" alt="Imagen del Ahorcado">
            </div>

            <div class="juego-container">
                <h1>El Ahorcado</h1>
                <div id="palabra-oculta" class="palabra-oculta"></div>
                <div id="temporizador" class="temporizador">Tiempo: 02:00</div>
                <div id="letras-usadas" class="letras-usadas"></div>
                <div id="teclado-letras" class="teclado-letras"></div>
                <div class="mensaje" id="mensaje"></div>
                <div class="controles">
                    <button id="btn-pausar">Pausar</button>
                    <button id="btn-reiniciar">Reiniciar</button>
                    <button id="btn-salir">Salir</button>
                </div>
            </div>

            <div class="side-panel">
                <div class="pistas-container">
                    <div id="pistas" class="pistas"></div>
                </div>
                <div class="imagen-palabra-adivinada-container" style="display: none;">
                    <img id="imagen-palabra-adivinada" class="imagen-palabra-adivinada" src="" alt="Imagen de la palabra">
                </div>
            </div>
        </div>
    </div>

    <script src="Js/script.js"></script>
</body>
</html>