from services.sqlApp import Mysql
from services.maquinaConfig import MaquinaConfigBot
from services.suporte import SuporteBot

class Menu:
    def __init__(self, usuario, mensagem):
        self.usuario = usuario
        self.mensagem = mensagem
        self.menu = [
            ['\n1 - máquinas \n2 - suporte \n0 - voltar/deslogar', ''],
            ['\n1 - escolher máquina \n2 - maquina BD \n0 - voltar','\n1 - abrir chamado \n2 - FAQ \n0 - voltar'],
            ['\n1 - verificar histórico \n2 -escolher componentes \n0 - voltar','\n1 - abrir chamado \n2 - FAQ \n0 - voltar']
            ]
    def mostrarMenu(self):
        maquina = MaquinaConfigBot()
        suporte = SuporteBot()
        retorno = 'Selecione um dos itens abaixo'
        if self.testeDeCamadas(0,0):
            self.usuario.camada = 1 
            retorno += self.menu[0][0]
        elif self.testeDeCamadas(0,1):
            if self.mensagem == '0':
                self.usuario.login = None
                self.usuario.senha = None
                self.usuario.loginEstagio = 0
                retorno = 'Você foi deslogado com sucesso'
            elif self.mensagem == '1':
                self.usuario.funcao = 1
                self.usuario.camada = 2
                retorno += self.menu[1][0]
            elif self.mensagem == '2':
                self.usuario.funcao = 2 
                self.usuario.camada = 2
                retorno += self.menu[1][1] 
            else:
                retorno = self.textoErro()
        # MaquinaDeConfig

        elif self.testeDeCamadas(1,2):
            if self.mensagem == '0':
                retorno += self.voltar()
            elif self.mensagem == '1':
                self.usuario.funcao = 1
                self.usuario.camada = 2
                retorno += self.menu[1]
            elif self.mensagem == '2':
                self.usuario.funcao = 2 
                self.usuario.camada = 2
                retorno += self.menu[2] 
            else:
                retorno = self.textoErro()
        
        # Suporte  
        elif self.testeDeCamadas(2,2):
            if self.mensagem == '0':
                retorno += self.voltar()
            elif self.mensagem == '1':
                self.usuario.camada = 2
                retorno += self.menu[1]
            elif self.mensagem == '2': 
                self.usuario.camada = 2
                retorno += self.menu[2] 
            else:
                retorno = self.textoErro()
        return retorno
    
    def testeDeCamadas(self, funcao, camada):
        teste = self.usuario.funcao == funcao and self.usuario.camada == camada
        print(teste)
        return teste
    def voltar(self):
        self.usuario.camada -= 1
        if self.usuario.camada < 3:
            self.usuario.funcao = 0
        return self.menu[self.usuario.camada-1][self.usuario.funcao]
    def textoErro(self):
        return 'Erro - Selecione um dos itens abaixo' + self.menu[self.usuario.camada - 1]