import requests
from services.sqlApp import Mysql


class MaquinaConfigBot:
    def __init__(self, usuario):
        self.pedirId = False
        self.usuario = usuario
        self.id = 0
        self.historico = 0
        self.componente = 0
        print(usuario)

    def maquinasId(self, mensagem):
        if self.pedirId == False:
            if(mensagem == '1'):                
                self.pedirId = True
                return '\nDigite o Id da máquina que deseja visualizar \n0 - voltar'

            elif mensagem == '2':                
                self.usuario.camada += 1
                return '\n1 - verificar histórico \n2 - escolher componentes \n 0 - voltar'

            else:
                return 'Digite um núero válido!'

        else:
            self.usuario.camada += 1
            self.pedirId = False
            self.id = mensagem
            return '\n1 - verificar histórico \n2 - escolher componentes \n0 - voltar'
            
    

    def consultarBD(self, mensagem):
        if not self.componente and not self.historico:
            if(mensagem == '1'):
                return self.pegarHistorico(mensagem)

            elif mensagem == '2':
                return self.escolherComponentes(mensagem)

        elif self.historico:
            return self.pegarHistorico(mensagem)

        elif self.componente:
            return self.escolherComponentes(mensagem)

    def pegarHistorico(self, mensagem):
        if not self.historico:
            self.historico = 1
            return '1 - últimos 7 dias \n2 - últimos 15 dias\n3 - últimos 30 dias'
        elif int(mensagem) >= 1 and int(mensagem) <= 3:
            return 'Relatorio dos últimos ' + ("7" if mensagem == '1' else  "15" if mensagem == '2' else '30') + '\n0 - voltar'

    
    def escolherComponentes(self, mensagem):
        if not self.componente:
            return 'Relatorio \nCPU - 80% \n RAM - 90% \n0 - voltar'
            

    def consultarMaquinas(self, idMaquina):
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        return 'Esse é o Id da sua maquina: ' + idMaquina