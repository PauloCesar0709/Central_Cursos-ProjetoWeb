$(document).ready(function() {
    // Adicionar aria-labels aos botões principais
    $('a.btn-primary').attr('aria-label', 'Acessar área do professor');
    $('a.btn-success').attr('aria-label', 'Acessar área do aluno');
    
    // Adicionar foco personalizado para melhor acessibilidade
    $('a, button').focus(function() {
        $(this).addClass('focus-highlight');
    }).blur(function() {
        $(this).removeClass('focus-highlight');
    });
    
    // Verificar se há mensagens de erro/sucesso na URL (para redirecionamentos)
    const urlParams = new URLSearchParams(window.location.search);
    const message = urlParams.get('message');
    const error = urlParams.get('error');
    
    if (message) {
        alert(message); // Ou você pode mostrar um toast/notification mais bonito
    }
    
    if (error) {
        alert('Erro: ' + error); // Ou você pode mostrar um toast/notification de erro
    }
});


