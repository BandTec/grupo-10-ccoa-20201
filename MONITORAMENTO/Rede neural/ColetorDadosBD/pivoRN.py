from services.sqlApp import ClsSql

mysql = ClsSql('ColdUser','senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = input(" Digite o id da máquina ")
#idMaquina = 1

componentes = mysql.listarComponentes()




#retorno: %cpu, %ram, %conD...