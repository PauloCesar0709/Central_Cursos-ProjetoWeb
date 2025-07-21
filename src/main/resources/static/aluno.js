$(document).ready(function() {
    // Máscaras
    $('.cpf-mask').mask('000.000.000-00', {reverse: true});
    $('.phone-mask').mask('(00) 00000-0000');
    
    // Carregar cursos
    loadCursos();
    
    // Configurar modal de inscrição
    $('#inscricaoModal').on('show.bs.modal', function(event) {
        const button = $(event.relatedTarget);
        const cursoId = button.data('curso-id');
        $('#cursoId').val(cursoId);
        $('#inscricaoForm')[0].reset();
        $('#inscricaoForm').removeClass('was-validated');
    });
    
    // Validar formulário de inscrição
    $('#inscricaoForm').submit(function(e) {
        e.preventDefault();
        
        if (!this.checkValidity()) {
            e.stopPropagation();
            this.classList.add('was-validated');
            return;
        }
        
        const inscricaoData = {
            nome: $('#nomeAluno').val(),
            cpf: $('#cpfAluno').cleanVal(),
            email: $('#emailAluno').val(),
            telefone: $('#telefoneAluno').cleanVal(),
            cursoId: $('#cursoId').val()
        };
        
        $.ajax({
            url: '/api/inscricoes',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(inscricaoData),
            success: function(response) {
                $('#inscricaoModal').modal('hide');
                $('#successModal').modal('show');
                loadCursos(); // Recarregar cursos para atualizar contagem
            },
            error: function(xhr) {
                let errorMessage = 'Ocorreu um erro ao realizar a inscrição.';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                }
                $('#errorMessage').text(errorMessage);
                $('#errorModal').modal('show');
            }
        });
    });
});

function loadCursos() {
    $('#cursosContainer').html(`
        <div class="col-12 text-center">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Carregando...</span>
            </div>
        </div>
    `);
    
    $.get('/api/cursos', function(cursos) {
        if (cursos.length === 0) {
            $('#cursosContainer').html('<div class="col-12 text-center"><p>Nenhum curso disponível no momento.</p></div>');
            return;
        }
        
        let html = '';
        cursos.forEach(curso => {
            html += `
                <div class="col-md-6">
                    <div class="curso-card">
                        <span class="badge bg-primary area-badge">${curso.area}</span>
                        <h4>${curso.nome}</h4>
                        <p>${curso.descricao}</p>
                        <p><strong>Duração:</strong> ${curso.duracao}</p>
                        <p><strong>Professor:</strong> ${curso.professor.nome}</p>
                        <button class="btn btn-primary btn-inscrever" data-curso-id="${curso.id}">
                            Inscreva-se
                        </button>
                    </div>
                </div>
            `;
        });
        
        $('#cursosContainer').html(html);
        
        // Adicionar evento de clique aos botões de inscrição
        $('.btn-inscrever').click(function() {
            $('#inscricaoModal').modal('show');
        });
    }).fail(function() {
        $('#cursosContainer').html('<div class="col-12 text-center"><p>Erro ao carregar cursos. Tente novamente mais tarde.</p></div>');
    });
}