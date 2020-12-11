let login_usuario;
let nome_usuario;
let localidade;

function redirecionar_login() {
    window.location.href = 'login.html';
}

function redirecionar_grafico(){
    window.location.href = 'indexGrafico.html';
}

function redirecionar_produto(){
    window.location.href = 'indexProduto.html';
}


function comentar() {
    let formulario = new URLSearchParams(new FormData(form_avaliacao));
    let texto = document.querySelectorAll('#MensagemDeRegistro')
    if(sessionStorage.idConsumidor == undefined || txtMensagem.value.trim() == ''){
    window.alert('Deve-se estar conectado e não pode conter nenhuma avalição vazia')
    return false
    }
    fetch("/avaliacoes/comentar/" + sessionStorage.idConsumidor ,
        {
            method: "POST",
            body: formulario
        }).then(resposta => {

            if (resposta.ok) {
                texto.forEach(element => element.innerHTML = 'Avaliacão enviada com sucesso')
                txtMensagem.value = '';
                
                resposta.json().then(json => {

                    console.log(json)
                });

                
                
            }
        })
        return false;
    }

function verificar_autenticacao_localidade() {
    login_usuario = sessionStorage.login_usuario_meuapp;
    nome_usuario = sessionStorage.nome_usuario_meuapp;
    localidade = sessionStorage.localidade_usuario_meuapp;

    if(localidade == undefined){
        logoff();
    }else{
        validar_sessao();
    }
    
}



function verificar_autenticacao_fornecedora() {
    login_usuario = sessionStorage.login_usuario_meuapp;
    nome_usuario = sessionStorage.nome_usuario_meuapp;
    fornecedora = sessionStorage.fornecedora_usuario_meuapp;

    if(fornecedora == undefined){
        if (fornecedora == undefined)  {
            logoff();
        }else{
            validar_sessao();
        }
    }

    
}

function verificar_localidade(){
    divGel.innerHTML = ''
    var dados = {
        localidade: localidade
    };

    
    fetch('/produtos/procurar',  {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
            },
        body: JSON.stringify(dados)
    })
    .then(resposta =>{
        if (resposta.ok) {
            resposta.json().then(json => {
                for (i = 0; i < json.length; i++) {
                        divGel.innerHTML += `<div class="box-geladeiras"><button onclick="Selecionar(${json[i].idGeladeira})">Geladeira ${json[i].idGeladeira}</button></div>`
                        console.log(`isso aqui: ${json[i].idGeladeira}`);

                    }
                   
              });

        } else {

            console.log('Erro de busca!');

        }
    })

}


function logoff() {
    finalizar_sessao();
    sessionStorage.clear();
    redirecionar_login();
}

function validar_sessao() {
    fetch(`/usuarios/sessao/${login_usuario}`, {cache:'no-store'})
    .then(resposta => {
        if (resposta.ok) {
            resposta.text().then(texto => {
                console.log('Sessão :) ', texto);   
                console.log(localidade);
                 
            });
        } else {
            console.error('Sessão :.( ');
            logoff();
        } 
    });    
}

function finalizar_sessao() {
    fetch(`/usuarios/sair/${login_usuario}`, {cache:'no-store'}); 
}