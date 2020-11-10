# -*- coding: UTF-8 -*-
from services.sqlApp import ClsSql
from services.gerador import Gerador
import time

print("Configurando BD")
#Inserir user, password, host, database
mysql = ClsSql('ColdUser','senha123', 'localhost', 'coldstock')
gerador = Gerador()

print("Conectando ao BD")
mysql.connect()
#idServidor = input(" Digite o id da máquina ")
idServidor = 2

print("Iniciando o loop")
while True:
    listaComponente = mysql.listarComponente(idServidor)
    valores = gerador.gerarDados(listaComponente, idServidor)
    #Slack = gerador.notificacaoSlack(valores, listaComponente)
    mysql.insert(valores)
    gerador.valores = []
    # mysql.alterarCamada(1,2,1)
    time.sleep(5)
