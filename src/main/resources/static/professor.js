$(document).ready(function() {
    // Máscaras
    $('.cpf-mask').mask('000.000.000-00', {reverse: true});
    
    // Contador de formações
    let formacaoCount = 0;
    
    // Adicionar formação
    $('#addFormacao').click(function() {
        formacaoCount++;
        const formacaoHtml = `
            <div class="formacao-item mb-2" id="formacao-${formacaoCount}">
                <div class="row g-2">
                    <div class="col-md-6">
                        <input type="text" class="form-control formacao-curso" placeholder="Nome do curso" required>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="form-control formacao-instituicao" placeholder="Instituição de ensino" required>
                    </div>
                    <div class="col-md-1">
                        <button type="button" class="btn btn-danger btn-sm w-100" onclick="removeFormacao(${formacaoCount})">
                            ×
                        </button>
                    </div>
                </div>
            </div>
        `;
        $('#formacoesContainer').append(formacaoHtml);
    });
    
    // Validar formulário
    $('#cursoForm').submit(function(e) {
        e.preventDefault();
        
        if (!this.checkValidity()) {
            e.stopPropagation();
            this.classList.add('was-validated');
            return;
        }
        
        // Coletar formações
        const formacoes = [];
        $('.formacao-item').each(function() {
            const curso = $(this).find('.formacao-curso').val();
            const instituicao = $(this).find('.formacao-instituicao').val();
            formacoes.push({curso, instituicao});
        });
        
        // Criar objeto de curso
        const cursoData = {
            nomeCurso: $('#nomeCurso').val(),
            descricaoCurso: $('#descricaoCurso').val(),
            areaCurso: $('#areaCurso').val(),
            duracaoCurso: $('#duracaoCurso').val(),
            nomeProfessor: $('#nomeProfessor').val(),
            cpfProfessor: $('#cpfProfessor').cleanVal(),
            emailProfessor: $('#emailProfessor').val(),
            formacoes: formacoes
        };
        
        // Enviar para API
        $.ajax({
            url: '/api/cursos',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(cursoData),
            success: function(response) {
                $('#successModal').modal('show');
                $('#cursoForm')[0].reset();
                $('#cursoForm').removeClass('was-validated');
                $('#formacoesContainer').empty();
                formacaoCount = 0;
            },
            error: function(xhr) {
                let errorMessage = 'Ocorreu um erro ao cadastrar o curso.';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                }
                $('#errorMessage').text(errorMessage);
                $('#errorModal').modal('show');
            }
        });
    });
});

// Função global para remover formação
function removeFormacao(id) {
    $(`#formacao-${id}`).remove();
}