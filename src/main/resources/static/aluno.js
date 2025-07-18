$(document).ready(function () {
    listarCursos();

    $('#formInscricao').on('submit', salvarInscricao);
});

function listarCursos() {
    $.get(`${API_BASE}/cursos`, function (cursos) {
        const container = $('#listaCursos');
        container.empty();

        cursos.forEach(curso => {
            const card = `
                <div class="card mb-3">
                    <div class="card-body">
                        <h5>${curso.nome}</h5>
                        <button class="btn btn-info btn-sm me-2" onclick='mostrarDetalhes(${JSON.stringify(curso)})'>Detalhes</button>
                        <button class="btn btn-primary btn-sm" onclick='abrirInscricao(${curso.id})'>Inscrever-se</button>
                    </div>
                </div>`;
            container.append(card);
        });
    });
}

function mostrarDetalhes(curso) {
    $('#detalhesCurso').removeClass('d-none');
    $('#descricaoDetalhe').text(curso.descricao);
    $('#areaDetalhe').text(curso.area);
    $('#duracaoDetalhe').text(`${curso.duracao} horas`);
    $('#professorDetalhe').html(`Professor: ${curso.professor.nome} (${curso.professor.email})`);
}

let cursoSelecionado = null;

function abrirInscricao(idCurso) {
    cursoSelecionado = idCurso;
    const modal = new bootstrap.Modal(document.getElementById("modalInscricao"));
    modal.show();
}

function salvarInscricao(event) {
    event.preventDefault();

    const aluno = {
        nome: $('#nomeAluno').val(),
        cpf: $('#cpfAluno').val(),
        email: $('#emailAluno').val(),
        telefone: $('#telefoneAluno').val(),
    };

    $.ajax({
        url: `${API_BASE}/alunos/${cursoSelecionado}`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(aluno),
        success: function () {
            $('#modalInscricao').modal('hide');
            mostrarFeedback('Inscrição realizada com sucesso!', true);
            $('#formInscricao')[0].reset();
        },
        error: function (xhr) {
            mostrarFeedback(xhr.responseText || 'Erro ao se inscrever.', false);
        }
    });
}

function mostrarFeedback(mensagem, sucesso = true) {
    const alerta = $('#alertaFeedback');
    alerta.removeClass('d-none bg-success bg-danger');
    alerta.addClass(sucesso ? 'bg-success' : 'bg-danger');
    $('#mensagemFeedback').text(mensagem);
}



