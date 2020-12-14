# -*- coding: UTF-8 -*-
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
            self.conversarComMaquina(listaComponente[i][0], idServidor)
            i +=1

        matriz=[]
        coluna = 0
        for linha in self.valores:
            matriz.append([])
            for tupla in linha:
                matriz[coluna].append(tupla)
            coluna += 1

        self.valores = self.abrirChamadoTalvez(matriz, listaComponente)
        return self.valores


    def conversarComMaquina(self,componente, idServidor):
        
        if componente == 'CPU':
            # from services.OpenHM import hardwareMonitor
            # hMonitor= hardwareMonitor()
            # valor = round((hMonitor.getInfo(idServidor)[0][1]), 2)
            # idComponente = 1
            print('CPU requerida e não entregue! para sua captação utilize a outra API')
            return

        elif componente == 'RAM':
            valor = (round(psutil.virtual_memory().used/1024**3, 2)) 
            idComponente = 2

        elif componente == 'Disco':
            valor = round(((psutil.disk_usage('/').used)/1024**3),2)
            idComponente = 3

        elif componente == 'conexaoD':
            valor = round(((psutil.net_io_counters().bytes_recv)*8)/10**9 ,2)
            idComponente = 4

        elif componente == 'conexaoU':
            valor = round(((psutil.net_io_counters().bytes_sent)*8)/10**9 ,2)
            idComponente = 5

        elif componente == 'temperatura':
            # from services.OpenHM import hardwareMonitor
            # hMonitor= hardwareMonitor()
            # valor = hMonitor.getInfo(idServidor)[2][1]
            # idComponente = 6
            print('TEMPERATURA requerida e não entregue! para sua captação utilize a outra API')
            return

        
        self.valores.append((self.data_atual, valor, idServidor, idComponente))

    def abrirChamadoTalvez(self, values, parametros):
        i = 0
        
        chamado = False
        while i<len(values):
            if (values[i][1]/parametros[i][2] >= parametros[i][1]/100):
                chamado = True
            i += 1

        if(chamado):
            # for componente in values:
            #     componente.append(1)
            print("DEVE ABRIR CHAMADO")
        else:
            print("PASSOU")

        return values