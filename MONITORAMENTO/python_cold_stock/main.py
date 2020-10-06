from services.sqlApp import Mysql
from services.gerador import Gerador
from services.BotTelegramResponse import TelegramBot
from threading import Thread
import time

class ThreadTelegram(Thread):
    def __init__(self):
        Thread.__init__(self)
    
    def run(self):
        telegram = TelegramBot()
        telegram.Iniciar()

print("Iniciando Thread para Telegram")
Telegram = ThreadTelegram()
Telegram.start()

print("Configurando BD")
#Inserir user, password, host, database
mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
gerador = Gerador()

print("Conectando ao BD")
mysql.connect()
idServidor = 2

print("Iniciando o loop")
while True:
    listaComponente = mysql.listarComponente(idServidor)
    valores = gerador.gerarDados(listaComponente, idServidor)
    Slack = gerador.notificacaoSlack(valores, listaComponente)
    mysql.insert(valores)
    gerador.valores = []
    time.sleep(5)