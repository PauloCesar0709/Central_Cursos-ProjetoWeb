$(document).ready(function () {
    $('#formCurso').on('submit', cadastrarCurso);
});

function cadastrarCurso(event) {
    event.preventDefault();

    const professor = {
        nome: $('#nomeProfessor').val(),
        cpf: $('#cpfProfessor').val(),
        email: $('#emailProfessor').val(),
        formacoes: []
    };

    $('#listaFormacoes li').each(function () {
        const partes = $(this).text().split(' - ');
        professor.formacoes.push({ curso: partes[0], instituicao: partes[1] });
    });

    const curso = {
        nome: $('#nomeCurso').val(),
        descricao: $('#descricaoCurso').val(),
        area: $('#areaCurso').val(),
        duracao: $('#duracaoCurso').val(),
        professor: professor
    };

    $.ajax({
        url: `${API_BASE}/cursos`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(curso),
        success: function () {
            mostrarFeedback("Curso cadastrado com sucesso!", true);
            $('#formCurso')[0].reset();
            $('#listaFormacoes').empty();
        },
        error: function (xhr) {
            mostrarFeedback(xhr.responseText || "Erro ao cadastrar curso.", false);
        }
    });
}

function mostrarFeedback(mensagem, sucesso = true) {
    const alerta = $('#alertaFeedback');
    alerta.removeClass('d-none bg-success bg-danger');
    alerta.addClass(sucesso ? 'bg-success' : 'bg-danger');
    $('#mensagemFeedback').text(mensagem);
}


