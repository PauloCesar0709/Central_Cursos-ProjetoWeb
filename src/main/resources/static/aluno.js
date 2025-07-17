function mostrarDetalhes() {
    document.getElementById("detalhesCurso").classList.remove("d-none");
}

function abrirInscricao() {
    const modal = new bootstrap.Modal(document.getElementById("modalInscricao"));
    modal.show();
}

function salvarInscricao(event) {
    event.preventDefault();

    const form = event.target;
    if (!form.checkValidity()) {
        form.classList.add("was-validated");
        return;
    }

    // Simular sucesso
    const modal = bootstrap.Modal.getInstance(document.getElementById("modalInscricao"));
    modal.hide();

    form.reset();
    form.classList.remove("was-validated");

    mostrarFeedback("Inscrição realizada com sucesso!", true);
}

function mostrarFeedback(mensagem, sucesso = true) {
    const alerta = document.getElementById("alertaFeedback");
    const mensagemBox = document.getElementById("mensagemFeedback");

    alerta.classList.remove("bg-success", "bg-danger", "d-none");
    alerta.classList.add(sucesso ? "bg-success" : "bg-danger");
    mensagemBox.textContent = mensagem;

    alerta.classList.add("show");
}

function ocultarFeedback() {
    const alerta = document.getElementById("alertaFeedback");
    alerta.classList.remove("show");
}



