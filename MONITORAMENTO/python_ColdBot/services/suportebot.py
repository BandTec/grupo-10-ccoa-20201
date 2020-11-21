import requests

class SuporteBot:
    def __init__(self, usuario):
        self.usuario = usuario
        self.chave = None
        self.chamado = {
        "tipo" : None,
        "titulo" : None,
        "descricao" : None
        }

    def pegarChave(self, mensagem):
        self.chave = mensagem
        return 'Digite o resumo do chamado'















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



        