from services.sqlApp import Mysql


class Menu:
    def __init__(self, usuario, mensagem):
        self.usuario = usuario
        self.mensagem = mensagem
        self.menu = [
            '\n1 - máquinas \n2 - suporte \n0 - voltar/deslogar',
            ]
    def mostrarMenu(self):
        retorno = 'Selecione um dos itens abaixo'
        if self.testeDeCamadas(0,0):
            self.usuario.camada = 1 
            retorno += self.menu[0]
        elif self.testeDeCamadas(0,1):
            if self.mensagem == '0':
                self.usuario.login = None
                self.usuario.senha = None
                self.usuario.loginEstagio = 0
                retorno = 'Você foi deslogado com sucesso'
            else:
                retorno = 'Erro - Selecione um dos itens abaixo'
                retorno += self.menu[self.usuario.camada - 1]
        return retorno
    
    def testeDeCamadas(self, funcao, camada):
        teste = self.usuario.funcao == funcao and self.usuario.camada == camada
        print(teste)
        return teste
