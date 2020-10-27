from services.sqlApp import Mysql
from services.OpenHM import hardwareMonitor
import time

print("Configurando BD")
#Inserir user, password, host, database
mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = input(" Digite o id da máquina ")
# idMaquina = 2

#Criando obj do HardwareMonitor
openHM = hardwareMonitor()

print("Iniciando o loop")

while True:
    valores = openHM.getInfo(idMaquina)
    mysql.insert(valores)
    time.sleep(5)