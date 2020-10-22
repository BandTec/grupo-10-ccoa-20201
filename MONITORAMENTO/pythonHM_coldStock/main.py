from services.sqlApp import Mysql
from services.OpenHM import hardwareMonitor
import time

print("Configurando BD")
#Inserir user, password, host, database
mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = 2

#Criando obj do HardwareMonitor
openHM = hardwareMonitor()

print("Iniciando o loop")
while True:
    listaComponente = mysql.listarComponente(idMaquina)
    valores = openHM.getInfo(idMaquina)
    # valores = openHM.personalizarDados(valores, listaComponente, idMaquina)
    mysql.insert(valores)
    time.sleep(5)