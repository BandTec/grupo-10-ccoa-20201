from services.sqlApp import Mysql
from services.gerador import Gerador
from BotTelegramResponse import TelegramBot
import time

print("Configurando BD")
#Inserir user, password, host, database
mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
gerador = Gerador()

print("Conectando ao BD")
mysql.connect()
idServidor = input(" Digite o id da máquina ")
# idMaquina = 2

print("Iniciando o loop")
while True:
    listaComponente = mysql.listarComponente(idServidor)
    valores = gerador.gerarDados(listaComponente, idServidor)
    #Slack = gerador.notificacaoSlack(valores, listaComponente)
    mysql.insert(valores)
    gerador.valores = []
    time.sleep(5)