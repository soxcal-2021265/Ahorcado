document.addEventListener('DOMContentLoaded', () => {

    const mainContainer = document.getElementById('main-container');
    const palabraOculta = document.getElementById('palabra-oculta');
    const imagenAhorcado = document.getElementById('imagen-ahorcado');
    const teclado = document.getElementById('teclado-letras');
    const mensaje = document.getElementById('mensaje');
    const pistas = document.getElementById('pistas');
    const temporizadorElemento = document.getElementById('temporizador');

    const imagenPalabraAdivinadaContainer = document.querySelector('.imagen-palabra-adivinada-container');
    const imagenPalabraAdivinada = document.getElementById('imagen-palabra-adivinada');

    const btnIniciar = document.getElementById('btn-iniciar');
    const btnPausar = document.getElementById('btn-pausar');
    const btnReiniciar = document.getElementById('btn-reiniciar');
    const btnSalir = document.getElementById('btn-salir');

    let objetoSeleccionado = null;
    let palabraMostrada = '';
    let errores = 0;
    const maxErrores = 6;
    let juegoEnPausa = false;

    let tiempoRestante = 120;
    let temporizadorIntervalo;

    async function iniciarJuego() {
        document.querySelector('.pantalla-inicio').style.display = "none";

        mainContainer.style.display = "block";
        juegoEnPausa = false;
        
        imagenPalabraAdivinadaContainer.style.display = 'none';

        try {
            const respuesta = await fetch('./Controlador?accion=obtenerPalabra');
            if (!respuesta.ok) {
                throw new Error('Error al obtener la palabra del servidor.');
            }
            const palabraData = await respuesta.json();
            
            objetoSeleccionado = {
                palabra: palabraData.palabra,
                pistas: [palabraData.pista_uno, palabraData.pista_dos, palabraData.pista_tres]
            };
            
            palabraMostrada = '_'.repeat(objetoSeleccionado.palabra.length);
            errores = 0;
            
            palabraOculta.textContent = palabraMostrada.split('').join(' ');
            imagenAhorcado.src = 'Image/1.png';
            mensaje.textContent = '';
            mensaje.className = 'mensaje';
            
            pistas.innerHTML = '<h4>Pistas:</h4>';
            objetoSeleccionado.pistas.forEach(pista => {
                const pas = document.createElement('p');
                pas.textContent = `- ${pista}`;
                pistas.appendChild(pas);
            });
            crearTeclado();
            manejarTeclado(true);
            iniciarTemporizador();
            
        } catch (error) {
            console.error("Error en la petición:", error);
            mensaje.textContent = "Error al conectar con el servidor. No se puede iniciar el juego.";
            mensaje.className = 'mensaje perdiste';
            desactivarTeclado();
        }
    }

    function iniciarTemporizador() {
        clearInterval(temporizadorIntervalo);
        tiempoRestante = 120;
        actualizarTemporizador();
        temporizadorIntervalo = setInterval(() => {
            tiempoRestante--;
            actualizarTemporizador();
            if (tiempoRestante <= 0) {
                clearInterval(temporizadorIntervalo);
                manejarJuegoPerdidoPorTiempo();
            }
        }, 1000);
    }

    function actualizarTemporizador() {
        const minutos = Math.floor(tiempoRestante / 60);
        const segundos = tiempoRestante % 60;
        temporizadorElemento.textContent = `Tiempo: ${minutos.toString().padStart(2, '0')}:${segundos.toString().padStart(2, '0')}`;
    }

    function manejarJuegoPerdidoPorTiempo() {
        mensaje.textContent = `Se acabo el tiempo. La palabra era: ${objetoSeleccionado.palabra}`;
        mensaje.className = 'mensaje perdiste';
        desactivarTeclado();
        juegoEnPausa = true;
    }

    function crearTeclado() {
        teclado.innerHTML = '';
        for (let i = 65; i <= 90; i++) {
            const letra = String.fromCharCode(i);
            const boton = document.createElement('button');
            boton.textContent = letra;
            boton.addEventListener('click', () => manejarAdivinanza(letra));
            teclado.appendChild(boton);
        }
    }

    function manejarAdivinanza(letra) {
        if (juegoEnPausa || errores >= maxErrores) {
            return;
        }

        let acierto = false;
        let nuevaPalabraMostrada = '';
        for (let i = 0; i < objetoSeleccionado.palabra.length; i++) {
            if (objetoSeleccionado.palabra[i] === letra) {
                nuevaPalabraMostrada += letra;
                acierto = true;
            } else {
                nuevaPalabraMostrada += palabraMostrada[i];
            }
        }

        palabraMostrada = nuevaPalabraMostrada;
        palabraOculta.textContent = palabraMostrada.split('').join(' ');

        if (!acierto) {
            errores++;
            imagenAhorcado.src = `Image/${errores + 1}.png`;
        }

        const botonClickeado = Array.from(teclado.children).find(b => b.textContent === letra);
        if (botonClickeado) {
            botonClickeado.disabled = true;
        }

        verificarEstadoJuego();
    }

    function verificarEstadoJuego() {
        if (palabraMostrada === objetoSeleccionado.palabra) {
            mensaje.textContent = 'Felicidades, ganaste!';
            mensaje.className = 'mensaje ganaste';
            desactivarTeclado();
            clearInterval(temporizadorIntervalo);

            const nombreImagen = objetoSeleccionado.palabra.toLowerCase();
            imagenPalabraAdivinada.src = `Image/${nombreImagen}.png`;
            imagenPalabraAdivinadaContainer.style.display = 'block';

        } else if (errores >= maxErrores) {
            mensaje.textContent = `Perdiste. La palabra era: ${objetoSeleccionado.palabra}`;
            mensaje.className = 'mensaje perdiste';
            desactivarTeclado();
            clearInterval(temporizadorIntervalo);
            imagenPalabraAdivinadaContainer.style.display = 'none';
        }
    }

    function manejarTeclado(estado) {
        Array.from(teclado.children).forEach(button => {
            button.disabled = !estado;
        });
    }

    function pausarJuego() {
        juegoEnPausa = true;
        mensaje.textContent = 'Juego en pausa';
        manejarTeclado(false);
        clearInterval(temporizadorIntervalo);
    }

    function reanudarJuego() {
        juegoEnPausa = false;
        mensaje.textContent = '';
        Array.from(teclado.children).forEach(button => {
            if (palabraMostrada.includes(button.textContent)) {
                button.disabled = true;
            } else {
                button.disabled = false;
            }
        });
        iniciarTemporizadorReanudar();
    }

    function iniciarTemporizadorReanudar() {
        clearInterval(temporizadorIntervalo);
        actualizarTemporizador();
        temporizadorIntervalo = setInterval(() => {
            tiempoRestante--;
            actualizarTemporizador();
            if (tiempoRestante <= 0) {
                clearInterval(temporizadorIntervalo);
                manejarJuegoPerdidoPorTiempo();
            }
        }, 1000);
    }

    function salirJuego() {
        juegoEnPausa = true;
        palabraOculta.textContent = '---';
        imagenAhorcado.src = 'Image/7.png';
        mensaje.textContent = 'Gracias por jugar';
        mensaje.className = 'mensaje perdiste';
        desactivarTeclado();
        pistas.innerHTML = '';
        clearInterval(temporizadorIntervalo);
        imagenPalabraAdivinadaContainer.style.display = 'none';
    }

    function desactivarTeclado() {
        Array.from(teclado.children).forEach(button => {
            button.disabled = true;
        });
    }

    btnIniciar.addEventListener('click', iniciarJuego);
    btnReiniciar.addEventListener('click', iniciarJuego);

    btnPausar.addEventListener('click', () => {
        if (juegoEnPausa) {
            reanudarJuego();
            btnPausar.textContent = "Pausar";
        } else {
            pausarJuego();
            btnPausar.textContent = "Reanudar";
        }
    });

    btnSalir.addEventListener('click', salirJuego);

    mainContainer.style.display = "none";
});