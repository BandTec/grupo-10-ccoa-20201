from services.mysql import Mysql
from services.dataGenerator import getData
import time

#Inserir user, password, host, database
mysql = Mysql('usuario','senha', 'localhost', 'coldstock')



mysql.connect()

while True:
    values = getData()
    mysql.insert(values)
    time.sleep(5)
