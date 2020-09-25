from services.mysql import Mysql
from services.gerador import Gerador
import time

#Inserir user, password, host, database
mysql = Mysql('testeApi','testeApi1', 'localhost', 'coldstock')
gerador = Gerador()

mysql.connect()
idServidor = 1
while True:
    #values = getData(idServidor)
    #parametros = mysql.selectServer(idServidor)
    listaComponente = mysql.listarComponente(idServidor)
    #Slack = notificacaoSlack(values, parametros)
    #print(Slack)
    valores = gerador.gerarDados(listaComponente, idServidor)
    print(valores)
    mysql.insert(valores)
    gerador.valores = []
    time.sleep(5)
