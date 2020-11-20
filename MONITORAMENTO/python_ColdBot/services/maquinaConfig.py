import requests
from services.sqlApp import Mysql


class MaquinaConfigBot:
    def __init__(self, usuario):
        self.pedirId = False
        self.usuario = usuario
        self.id = 0
        self.relatorio = 0
        self.componente = 0

    def iniciarMaquinas(self, mensagem):
        retorno = '\n1 - Solicitar relatórios de uma máquina \n2 - Visualizar componentes cadastrados \n0 - voltar'
        self.usuario.camada += 1
        return retorno

    def maquinasId(self, mensagem):
        retorno = None
        if self.pedirId == False:
            #Registra o caminho que o usuario deseja seguir
            if mensagem == '1':
                self.relatorio = 1
                self.componente = 0
            else:
                self.componente = 1
                self.relatorio = 0

            self.pedirId = True
            retorno = '\nDigite o Id da máquina que deseja visualizar \n0 - voltar'

        else:
            self.id = mensagem
            if (not self.relatorio == 0):
                #Gerar relatório
                retorno = self.gerarRelatorio(mensagem)
                print('Caminho relatório')

            elif (not self.componente == 0):
                #Pegar componentes
                print('Caminho componentes')
                retorno = self.recolherComponentes(mensagem)
                self.usuario.camada += 1

        return retorno

    def gerarRelatorio(self, mensagem):
        if self.relatorio == 1:
            self.relatorio = 2
            return '1 - Últimos 7 dias \n2 - Últimos 15 dias\n3 - Últimos 30 dias \n0 - Voltar'

        if self.relatorio == 2:
            self.relatorio = 3
            self.prazo = ("7" if mensagem == '1' else  "15" if mensagem == '2' else '30')
            #Fazer select
            return 'Prazo selecionado é de: ' + self.prazo

    
    def recolherComponentes(self, idMaquina):
        #Select dos componentes e retorno
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        componentes = mysql.listarComponente(idMaquina)
        mensagem = 'Componentes registrados: \n'
        for componente in componentes:
            mensagem += (componente[0] + 'Capacidade Max: ' + componente[1] +
            'Porcentagem de alerta: ' + componente[2] + '\n')
            
    def consultarMaquinas(self, idMaquina):
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        return 'Esse é o Id da sua maquina: ' + idMaquina