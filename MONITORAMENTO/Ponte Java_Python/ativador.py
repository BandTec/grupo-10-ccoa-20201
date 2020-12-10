from services.sqlApp import ClsSql
from services.geradorLinear import Linear
from datetime import datetime

mysql = ClsSql('ColdUser', 'senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = input(" Digite o id da máquina ")
# idMaquina = 1

lineares = set()

#RETORNO: [[id, nome, maxima], [1, 'cpu', 2.5], [2, 'ram', 8]]
configuracaoMaquina = mysql.consultarConfiguracao(idMaquina)

for componente in configuracaoMaquina:
    # componente = [id, nome, maxima]
    hoje = datetime.today()
    passado = datetime.timestamp(hoje) - 86.400 #7 DIAS
    passado = datetime.fromtimestamp(passado)

    valores = mysql.pegarRegistros(componente[0], hoje, passado)

    lineares.append(Linear())

escrita = ""
print(escrita)
outF = open("previsao.json", "w")
outF.write(escrita)
outF.close()