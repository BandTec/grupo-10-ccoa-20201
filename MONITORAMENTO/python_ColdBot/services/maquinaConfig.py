import requests
from datetime import datetime
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
        #Registra o caminho que o usuario deseja seguir
        if mensagem == '1':
            self.relatorio = 1
            self.componente = 0
        else:
            self.componente = 1
            self.relatorio = 0
        
        self.usuario.camada += 1
        self.pedirId = True
        retorno = '\nDigite o Id da máquina que deseja \n0 - voltar'

        return retorno

    def direcionarCaminho(self, mensagem):
        if (self.id == 0):
            self.id = int(mensagem)

        if (not self.relatorio == 0):
            #Gerar relatório
            retorno = self.gerarRelatorio(mensagem)
            print('Caminho relatório')

        elif (not self.componente == 0):
            #Pegar componentes
            self.id = mensagem
            print('Caminho componentes')
            retorno = self.recolherComponentes(mensagem)

        return retorno


    def gerarRelatorio(self, mensagem):
        if self.relatorio == 1:
            self.relatorio = 2
            return '1 - Últimos 7 dias \n2 - Últimos 15 dias\n3 - Últimos 30 dias \n0 - Voltar'

        elif self.relatorio == 2:
            self.relatorio = 3
            self.prazo = ("7" if mensagem == '1' else  "15" if mensagem == '2' else '30')
            retorno = self.montarRelatorio(self.id, int(self.prazo))
            return retorno

    
    def recolherComponentes(self, idMaquina):
        #Select dos componentes e retorno
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        componentes = mysql.listarComponente(idMaquina)
        mensagem = 'Componentes registrados: \n'
        for componente in componentes:
            mensagem += "%s \nCapacidade max: %s \nPorcentagem para alertas: %s\n \n" % (componente[0], componente[1], componente[2])
        return mensagem
            
    def montarRelatorio(self, idMaquina, prazo):
        mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
        dataAtual = datetime.now()
        stampAtual = datetime.timestamp(dataAtual)
        datas = []
        while(len(datas)<2):
            #Formato mysql --> 'yyyy-mm-dd'
            dataConvert = datetime.fromtimestamp(stampAtual)
            datas.append('%s-%s-%s'%(dataConvert.year, dataConvert.month, dataConvert.day))
            stampAtual -= 60*60*24*prazo+1

        retorno = mysql.consultarRelatorio(datas, idMaquina)
        mensagem = "--Relatório de desempenho ID %s--"%self.id
        dataMsg = None
        for item in retorno:
            if(dataMsg != "%s/%s"%(item[0], item[1])):
                dataMsg = "%s/%s"%(item[0], item[1])
                mensagem += "\n\nData: %s" % dataMsg
            
            mensagem+= "\nComponente: %s"%item[2]
            mensagem+= "\nMáxima: %s Média: %s Mínimo: %s" % (item[4],item[3],item[5])

        return mensagem