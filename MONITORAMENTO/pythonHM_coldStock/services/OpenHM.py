import requests
import json
import time
from datetime import datetime

class hardwareMonitor:
    def __init__(self):
        self.url = 'http://localhost:9000/data.json'
        self.data=None

    def getData(self):
        Response = requests.get(self.url)
        data  = Response.json()
        self.data = data

    def personalizarDados(self, dadosTodos, listaComponentes, idMaquina):
        dataAtual = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        dadosFiltrados = []
        
        contador = 0
        while contador< len(listaComponentes):
            print (listaComponentes[contador][0])

            #O PROBLEMA ESTÁ AQUI, PRECISAMOS MONTAR UMA LISTA PARA O INSERT DE DADOS 
            # IGUAL AO DO OUTRO GERADOR
            valor = dadosTodos[listaComponentes[contador][0]]

            idComp = listaComponentes[contador][2]
            dadosFiltrados.append((dataAtual, valor, idMaquina, idComp))
            contador = contador + 1

        print(dadosFiltrados)
        return dadosFiltrados
            

    def getInfo(self, idMaquina):
        self.getData()
        info ={
            "CPU":[],
            "RAM":[],
            # "DISCO":[],
            "TEMPERATURA":[],

        }
        Cpu = []
        Clocks = []
        temperatures = []
        data = self.data
        for i in data['Children']:
            # info['Desktop'] = i['Text']
            for desktop in i['Children']:
                #CPU
                if desktop['Text'].find('Intel') >= 0 or desktop['Text'].find("AMD") >=0:
                    for cpu_metrica in desktop['Children']:
                        #Clock
                        if cpu_metrica['Text'] == 'Clocks':
                            for clock in cpu_metrica['Children']:
                                if clock['Text'].find('CPU')>= 0:
                                    # conversao(clock['Value'], 'MHz')
                                    Clocks.append(clock['Value'])
                                    
                        #Temperature
                        if cpu_metrica['Text'] == "Temperatures":
                            for temperature in cpu_metrica['Children']:
                                if temperature['Text'].find('CPU')>= 0:
                                    # conversao(temperature['Value'], '°C')
                                    temperatures.append(temperature['Value'])
                                    
                        Cpu = [Clocks,temperatures]
                # #RAM
                if desktop['Text'].find('Generic Memory') >= 0:
                    #Load
                    for Memory in desktop['Children']:
                                        
                        if Memory['Text'] == 'Data':
                            for ram in Memory['Children']:
                                #Memoria usada
                                if(ram['Text'] == 'Used Memory'):
                                    # conversao(ram['Value'], ' GB')
                                    ram['Value'] = ram['Value'].replace(' GB', '')
                                    ram['Value'] = ram['Value'].replace(',', '.')
                                    info['RAM'] = ram['Value']
                                #Memoria Livre
                # if desktop['Text'].find('Generic Hard Disk') >= 0:
                #     #Load
                #     for disc in desktop['Children']:
                                        
                #         if disc['Text'] == 'Load':
                #             for percent in disc['Children']:
                #                 #Memoria usada
                #                 if(percent['Text'] == 'Used Space'):
                #                     info['DISCO'] = percent['Value']
                
        # def conversao(componente, metrica):
        #     componente = componente.replace(metrica, '')
        #     componente = componente.replace(',', '.')
        #     componente = float(componente)

        contadorTemp = 0
        contadorFreq = 0
        
        total = 0

        while contadorTemp < len(Cpu[1]):
            conversao = Cpu[1][contadorTemp].replace('°C', '')
            conversao = conversao.replace(',', '.')
            total += float(conversao)
            contadorTemp += 1
        info['TEMPERATURA'] = round((total / len(Cpu[1])), 2)    

        while contadorFreq < len(Cpu[0]):
            conversao = Cpu[0][contadorFreq].replace('MHz', '')
            conversao = conversao.replace(',', '.')
            total += float(conversao)
            contadorFreq += 1
        info['CPU'] = round((total / len(Cpu[0])/1000), 2)

        
        novoInfo =[info['CPU'], info['RAM'], info['TEMPERATURA']] 
        print(novoInfo)
        contador = 0
        listaInsert = []
        while contador < len(novoInfo):
            fkComponente = 0
            valoresInsert = novoInfo[contador]
            if contador == 0:
                fkComponente = 1
            elif contador == 1:
                fkComponente = 2
            elif contador ==2:
                fkComponente = 6

            itemLista = [
            datetime.now().strftime('%Y-%m-%d %H:%M:%S'),
            valoresInsert,
            idMaquina,
            fkComponente]

            listaInsert.append(itemLista)
            contador += 1

        print("Abaixo é nossa lista:")
        print(listaInsert)
        return listaInsert

# monitor = hardwareMonitor()
# while(True):
#     print('-'*100)
#     print(monitor.getinfo())
#     time.sleep(5)