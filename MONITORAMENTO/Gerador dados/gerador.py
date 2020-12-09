from services.sqlApp import ClsSql
from time import sleep
from random import *

print("Conectando ao BD")
mysql = ClsSql('ColdUser', 'senha123', 'localhost', 'coldstock')

idMaquina = input(" Digite o id da máquina ")
# idMaquina = 1
tempo = {
    "hora": 0,
    "minuto": 0,
    "segundo": 0
}
data = input(" Digite a data de inicio para geração de dados (YYYY/MM/DD) ")


def relogio():
    #hora = minuto = segundo = 0
    tempo["segundo"] += 10
    if(tempo["segundo"] == 60):
        tempo["segundo"] = 0
        tempo["minuto"] += 1
    if(tempo["minuto"] == 60):
        tempo["minuto"] = 0
        tempo["hora"] += 1
    if tempo["hora"] == 24:
        tempo["hora"] = 0

    segundo = minuto = hora = 0

    segundo = tempo["segundo"]
    minuto = tempo["minuto"]
    hora = tempo["hora"]

    if tempo["segundo"] < 10:
        tempo["segundo"] = "0" + str(tempo["segundo"])

    if tempo["minuto"] < 10:
        tempo["minuto"] = "0" + str(tempo["minuto"])

    if tempo["hora"] < 10:
        tempo["hora"] = "0" + str(tempo["hora"])

    # if len(str(tempo["hora"]))
    texto = ("{}:{}:{}".format(tempo["hora"],
                               tempo["minuto"], tempo["segundo"]))
    
    tempo["segundo"] = segundo
    tempo["minuto"] = minuto
    tempo["hora"] = hora
    return texto

#RETORNO: [[id, nome, maxima], [1, 'cpu', 2.5], [2, 'ram', 8]]
configuracaoMaquina = mysql.consultarConfiguracao(idMaquina)
i = 0
while(True):
    valor = round(uniform(0,configuracaoMaquina[i % len(configuracaoMaquina)][2]),2)
    print(i % len(configuracaoMaquina))
    print(valor)
    fkComponente = i % len(configuracaoMaquina)
    i+=1

    mysql.inserirNoBanco(data + " "+ relogio(),valor,idMaquina , fkComponente + 1)
    
    sleep(1)
