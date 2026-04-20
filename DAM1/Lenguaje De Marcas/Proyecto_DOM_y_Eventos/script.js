let numeroSecreto = Math.floor(Math.random() * 100) + 1;
let intentos = [];
let ganado = false;
let perdido = false;
const MAX_INTENTOS = 10;

function actualizarIntentosTexto() {
    const intentosTexto = document.getElementById('intentosTexto');
    if (intentos.length > 0) {
        intentosTexto.textContent = 'INTENTOS REALIZADOS: ' + intentos.join(' ');
    } else {
        intentosTexto.textContent = 'INTENTOS REALIZADOS';
    }
}

function mostrarMensaje(texto, tipo) {
    const mensajeBox = document.getElementById('mensajeBox');
    mensajeBox.textContent = texto;
    mensajeBox.className = 'mensaje-box ' + tipo;
}

function jugar() {
    if (ganado || perdido) return;
    
    const input = document.getElementById('numeroInput');
    const num = parseInt(input.value);
    
    if (isNaN(num) || num < 1 || num > 100) {
        mostrarMensaje('Ingresa un número entre 1 y 100', 'pista');
        return;
    }
    
    if (intentos.includes(num)) {
        mostrarMensaje('Ya intentaste ese número', 'pista');
        return;
    }
    
    intentos.push(num);
    actualizarIntentosTexto();
    

    const numeroIntento = intentos.length;
    if (numeroIntento >= 1 && numeroIntento <= 10) {
        const marca = document.querySelector(`.celda[data-num="${numeroIntento}"] .marca`);
        marca.classList.add('activo');
    }
    
    if (num === numeroSecreto) {
        ganado = true;
        mostrarMensaje('HAS ACERTADO', 'ganado');
        document.getElementById('numeroInput').disabled = true;
        document.querySelector('.input-section button').disabled = true;
    } else if (intentos.length >= MAX_INTENTOS) {
        perdido = true;
        mostrarMensaje('HAS PERDIDO', 'perdido');
        document.getElementById('numeroInput').disabled = true;
        document.querySelector('.input-section button').disabled = true;
    } else if (num < numeroSecreto) {
        mostrarMensaje(num + ' ES MENOR', 'pista');
    } else {
        mostrarMensaje(num + ' ES MAYOR', 'pista');
    }
    
    input.value = '';
}

document.getElementById('numeroInput').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        jugar();
    }
});