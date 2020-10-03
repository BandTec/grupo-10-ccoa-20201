import psutil, time, sys
import requests
import json
import os
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

    def notificacaoSlack(self, values, parametros):
        url = "68747470733a2f2f686f6f6b732e736c61636b2e636f6d2f73657276696365732f543031414b5138483144452f42303142454a384e31554e2f464e536d637934756b46794e5a5075504b6839755a6c5353"
        url = bytearray.fromhex(url).decode()

        # if (values[0]>=65) or (values[1]>=60) or (values[4]>=120):
        print(values, parametros)
        notificacao = ''
        i = 0
        
        while i<len(values):
            if (values[i][1] >= parametros[i][1] * 0.8):
                notificacao += "%s em alto uso, " % (parametros[i][0])   
            i += 1

        notificacao = notificacao[0:-2] + '.'
        pload = {'text': notificacao}
        requests.post(url, json = pload)
        return notificacao