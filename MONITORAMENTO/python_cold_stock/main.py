from services.mysql import Mysql
from services.gerador import Gerador
import time

#Inserir user, password, host, database
mysql = Mysql('ColdUser','senha123', 'localhost', 'coldstock')
gerador = Gerador()

mysql.connect()
idServidor = 1
while True:
    #values = getData(idServidor)
    #parametros = mysql.selectServer(idServidor)
    #Slack = notificacaoSlack(values, parametros)
    #print(Slack)

    listaComponente = mysql.listarComponente(idServidor)
    valores = gerador.gerarDados(listaComponente, idServidor)
    Slack = gerador.notificacaoSlack(valores, listaComponente)
    mysql.insert(valores)
    gerador.valores = []
    time.sleep(5)