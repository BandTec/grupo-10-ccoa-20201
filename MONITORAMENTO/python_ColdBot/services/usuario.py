from services.sqlApp import Mysql
from services.maquinaConfig import MaquinaConfigBot
from services.suportebot import SuporteBot
# import time
class Usuario:
    def __init__(self, idChat):
        self.idChat = idChat
        self.login = None
        self.senha = None
        self.loginEstagio = 0
        self.camada = 0
        self.funcao = 0
        self.maquina = MaquinaConfigBot(self)
        self.suporte = SuporteBot(self)
        self.anterior = ['teste'] #vetor que guarda as ultimas mensagens.
    # def verificarUsuario(self):
    #     if self.idChat in users:
    #         print('Usuario ja cadastrado')
    #     else:
    #         users.append(self.idChat)
    def Login(self):
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        mysql.connect()
        usuario = mysql.consultarUsuario(self.login, self.senha)        
        return usuario
    