function abrirFormacao() {
    const modal = new bootstrap.Modal(document.getElementById('modalFormacao'));
    modal.show();
}

function salvarFormacao(event) {
    event.preventDefault();

    const curso = document.getElementById('cursoFormacao').value;
    const instituicao = document.getElementById('instituicaoFormacao').value;

    if (curso && instituicao) {
        const lista = document.getElementById('listaFormacoes');
        const item = document.createElement('li');
        item.className = 'list-group-item';
        item.textContent = `${curso} - ${instituicao}`;
        lista.appendChild(item);

        // Resetar campos e fechar modal
        document.getElementById('cursoFormacao').value = '';
        document.getElementById('instituicaoFormacao').value = '';
        const modal = bootstrap.Modal.getInstance(document.getElementById('modalFormacao'));
        modal.hide();
    }
}


