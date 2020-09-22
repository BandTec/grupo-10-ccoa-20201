from services.mysql import Mysql
from services.dataGenerator import getData, notificacaoSlack
import time

#Inserir user, password, host, database
mysql = Mysql('testeApi','testeApi1', 'localhost', 'coldstock')


mysql.connect()
idServidor = 1
while True:
    values = getData(idServidor)
    parametros = mysql.selectServer(idServidor)
    Slack = notificacaoSlack(values, parametros)
    print(Slack)
    mysql.insert(values)
    time.sleep(5)
