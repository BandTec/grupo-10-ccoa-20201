import requests
from services.sqlApp import Mysql


class MaquinaConfigBot:
    def __init__(self, usuario):
        self.pedirId = False
        self.usuario = usuario
        print(usuario)
    def maquinasId(self, mensagem):
        if self.pedirId == False:
            if(mensagem == '1'):
                
                    self.pedirId = True
                    return 'Digite o Id da máquina que deseja visualizar \n 0 - voltar'
            elif mensagem == '2':
                return 'Estas são as suas maquina'
        else:
            if self.pedirId == True:
                self.pedirId = False
                return self.consultarMaquinas(mensagem)
            else:
                return 'Deu erro'
    

    def consultarMaquinas(self, idMaquina):
        # mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        return 'Esse é o Id da sua maquina: ' + idMaquina