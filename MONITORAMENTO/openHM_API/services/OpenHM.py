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
            "TEMPERATURA":[],
        }
        
        qtdClocks = 0
        temperatures = []
        totalClock = 0.0
        totalTemp = 0.0 

        # Pega um componente EX: "1,8 GHZ"
        # Pega a métrica a ser removida EX: "GHZ"
        # Retorna: 1.8
        def converter(componente, metrica):
            componente = componente.replace(metrica, '')
            componente = componente.replace(',', '.')
            componente = float(componente)
            return componente

        # Traz o JSON que veio do site para dentro desta função
        data = self.data

        # Vasculha item por item que veio do site
        for i in data['Children']:
            for desktop in i['Children']:
                #CPU
                if desktop['Text'].find('Intel') >= 0 or desktop['Text'].find("AMD") >=0:
                    for cpu_metrica in desktop['Children']:
                        #Clock
                        if cpu_metrica['Text'] == 'Clocks':
                            for clock in cpu_metrica['Children']:
                                if clock['Text'].find('CPU')>= 0:
                                    qtdClocks += 1
                                    totalClock += converter(clock['Value'], 'MHz')
                                    
                        #Temperature
                        if cpu_metrica['Text'] == "Temperatures":
                            for temperature in cpu_metrica['Children']:
                                if temperature['Text'].find('CPU')>= 0:
                                    temperatures.append(temperature['Value'])
                                    totalTemp += converter(temperature['Value'], '°C')
                                    
                    
                if desktop['Text'].find('Generic Memory') >=0:
                    #Load
                    for Memory in desktop['Children']:
                                        
                        if Memory['Text'] == 'Data':
                            for ram in Memory['Children']:
                                #Memoria usada
                                if(ram['Text'] == 'Used Memory'):
                                    # ram['Value'] = ram['Value'].replace(' GB', '')
                                    # ram['Value'] = ram['Value'].replace(',', '.')
                                    info['RAM'] = converter(ram['Value'], ' GB')

        # Pra dentro do dicionario, vai a media dos valores
        info['CPU'] = round((totalClock / qtdClocks/1000),2)
        info['TEMPERATURA'] = round((totalTemp / len(temperatures)),2)  

        novoInfo =[info['CPU'], info['RAM'], info['TEMPERATURA']]         
        print(novoInfo)

        contador = 0
        listaInsert = []

        while contador < len(novoInfo):
            fkComponente = 0            

            if contador == 0:
                fkComponente = 1
            elif contador == 1:
                fkComponente = 2
            elif contador == 2:
                fkComponente = 6

            valorInsert = novoInfo[contador]

            dataRegistro = datetime.now().strftime('%Y-%m-%d %H:%M:%S')

            itemLista = [
            dataRegistro,
            valorInsert,
            idMaquina,
            fkComponente
            ]

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