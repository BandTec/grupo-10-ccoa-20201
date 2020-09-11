from services.mysql import Mysql
from services.dataGenerator import getData
import time

#Inserir user, password, host, database
<<<<<<< HEAD
mysql = Mysql('root','senha123', 'localhost', 'coldstock')
=======
mysql = Mysql('vicenteAPI','naruto123', 'localhost', 'PROJETO')
>>>>>>> f6eff963579497bd24275b4ab51b44e1bff5a056

mysql.connect()

while True:
    values = getData()
    mysql.insert(values)
    time.sleep(5)
