import psutil, time, sys
import requests
from datetime import datetime

class Gerador:
    def __init__(self):
        self.valores = []
        self.data_atual = ''
       

    def gerarDados(self,listaComponente, idServidor):
        self.data_atual = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        i = 0
        while i < len(listaComponente):
            if i == len(listaComponente) - 1:
                pontoFinal = ';'
            else:
                pontoFinal = ','
            self.conversarComMaquina(listaComponente[i][0],pontoFinal, idServidor)
            i +=1
        return self.valores


    def conversarComMaquina(self,componente, pontoFinal, idServidor):
        
        if componente == 'CPU':
            cpu = psutil.cpu_percent(interval=1, percpu=True)
            valor = sum(cpu)/len(cpu)
            idComponente = 1
            
        elif componente == 'RAM':
            valor = (round(psutil.virtual_memory().used/1024**3, 2)) 
            idComponente = 2

        elif componente == 'Disco':
            valor = round(((psutil.disk_usage('/').used)/1024**3),2)
            idComponente = 3

        elif componente == 'conexaoD':
            valor = round(((psutil.disk_usage('/').used)/1024**3),2)
            idComponente = 4

        elif componente == 'conexaoU':
            valor = round(((psutil.disk_usage('/').used)/1024**3),2)
            idComponente = 5
        
        self.valores.append((self.data_atual, valor, idServidor, idComponente))

