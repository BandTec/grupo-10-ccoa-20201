import requests

class SuporteBot:
    def __init__(self, usuario):
        self.usuario = usuario
        self.chamado = {
        "chave": None,
        "tipo" : None,
        "resumo" : None,
        "descricao" : None
        }

    def pegarChave(self, mensagem):
        if mensagem.upper() == 'CK':
            self.chamado["chave"] = '10003'
            self.usuario.camada += 1
            return 'Escolha o tipo de chamado\n 1 - Chamado Comum'
        else:
            return 'Não existe nenhum projeto com esse nome \nDigite Corretamente a chave do projeto'

    def pegarTipoChamado(self, mensagem):
        if mensagem == '1' :
            self.usuario.camada += 1
            self.chamado["tipo"] = mensagem
            return 'Digite o resumo do seu chamado:'
            
        else: 
            return 'Não existe esse tipo de chamado \nDigite novamente o tipo de chamado'

    # def pegarResumo(self, mensagem):














    def criandoChamado(self, mensagem):
        self.chamado["tipo"] = mensagem
        self.usuario.camada += 1
        return 'Insira o titulo'
    
    def criandoTipo(self, mensagem):
        self.chamado["tipo"] = mensagem
        self.usuario.camada += 1
        return 'Insira a descricao'

    def criandoTitulo(self, mensagem):
        self.chamado["titulo"] = mensagem
        self.usuario.camada += 1
        return "ok"

    def criandoDescricao(self, mensagem):
        self.chamado["descricao"] = mensagem
        self.usuario.camada += 1
        chamadoFinal = self.chamado["tipo"] + "\n" + self.chamado["titulo"] + "\n" + self.chamado["descricao"]
        return "Esses dados estão corretos? " + chamadoFinal



        