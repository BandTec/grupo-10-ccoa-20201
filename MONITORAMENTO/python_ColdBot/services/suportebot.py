import requests

class SuporteBot:
    def __init__(self, usuario):
        self.usuario = usuario
        print(usuario)
        self.chamado = {
        "tipo" : None,
        "titulo" : None,
        "descricao" : None
        }

    def criandoChamado(self, mensagem):
        self.chamado["tipo"] = mensagem
        return 'Insira o titulo'
    
    def criandoTipo(self, mensagem):
        self.chamado["tipo"] = mensagem
        return 'Insira a descricao'

    def criandoTitulo(self, mensagem):
        self.chamado["titulo"] = mensagem
        return "ok"

    def criandoDescricao(self, mensagem):
        self.chamado["descricao"] = mensagem
        chamadoFinal = self.chamado["tipo"] + "\n" + self.chamado["titulo"] + "\n" + self.chamado["descricao"]
        return "Esses dados estão corretos? " + chamadoFinal



        