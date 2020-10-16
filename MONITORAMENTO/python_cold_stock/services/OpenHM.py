import requests
import json
import time

class hardwareMonitor:
    def __init__(self):
        self.url = 'http://localhost:9000/data.json'
        self.data=None
    def getData(self):
        Response = requests.get(self.url)
        data  = Response.json()
        self.data = data
    def getinfo(self):
        self.getData()
        # info ={
        #     "CPU":[]
        # }
        Cpu = []
        Clocks = []
        temperatures = []


        data = self.data
        for i in data['Children']:
            # info['Desktop'] = i['Text']
            for desktop in i['Children']:
                # #MotherBoard
                # if desktop['id'] <=2:
                #     info['MotherBoard'] = desktop['Text']
                # #Todos dispositivos
                # if desktop['Text'].find('Generic Hard Disk') < 0:
                #     info['AllDevices'].append(desktop['Text'])
                #CPU
                if desktop['Text'].find('Intel') >= 0 or desktop['Text'].find("AMD") >=0:
                    for cpu_metrica in desktop['Children']:
                        #Clock
                        if cpu_metrica['Text'] == 'Clocks':
                            for clock in cpu_metrica['Children']:
                                if clock['Text'].find('CPU')>= 0:
                                    Clocks.append(clock['Value'])
                                    
                        #Temperature
                        if cpu_metrica['Text'] == "Temperatures":
                            for temperature in cpu_metrica['Children']:
                                if temperature['Text'].find('CPU')>= 0:
                                    temperatures.append(temperature['Value'])
                                    
                        Cpu = [Clocks,temperatures]
                # #RAM
                # if desktop['Text'].find('Generic Memory') >= 0:
                #     #Load
                #     for Memory in desktop['Children']:
                #         if Memory['Text'] == 'Load':
                #             for loads in Memory['Children']:
                #                 info['Memory']['Load'] = loads['Value']
                    
                #         if Memory['Text'] == 'Data':
                #             for ram in Memory['Children']:
                #                 #Memoria usada
                #                 if(ram['Text'] == 'Used Memory'):
                #                     info['Memory']['Use'] = ram['Value']
                #                 #Memoria Livre
                #                 if(ram['Text'] == 'Available Memory'):
                #                     info['Memory']['Available'] = ram['Value']
                # if desktop['Text'].find('NVIDIA') >= 0 or desktop['Text'].find("Radeon") >=0 or desktop['Text'].find("Graphics") >=0:
                #     for Video in desktop['Children']:
                #         if Video['Text'] == 'Clocks':
                #             for fans in Video['Children']:
                #                 if fans['Text'] == 'GPU Core' :
                #                     info['VideoCard'] = fans['Value']

        contadorTemp = 0
        contadorFreq = 0
        total = 0

        while contadorTemp < len(Cpu[1]):
            conversao = Cpu[1][contadorTemp].replace('°C', '')
            conversao = conversao.replace(',', '.')
            total += float(conversao)
            contadorTemp += 1
        mediaTemp = round((total / len(Cpu[1])), 2)    

        while contadorFreq < len(Cpu[0]):
            conversao = Cpu[0][contadorFreq].replace('MHz', '')
            conversao = conversao.replace(',', '.')
            total += float(conversao)
            contadorFreq += 1
        mediaFreq = round((total / len(Cpu[0])), 2)   
        
        medias= {
            'mediaTemp': mediaTemp,
            'mediaFreq': mediaFreq
        }
        # print(medias)
        return medias


# monitor = hardwareMonitor()
# while(True):
#     print('-'*100)
#     monitor.getinfo()
#     time.sleep(5)