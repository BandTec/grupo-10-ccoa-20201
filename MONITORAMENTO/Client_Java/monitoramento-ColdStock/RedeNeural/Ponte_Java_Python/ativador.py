from services.sqlApp import ClsSql
from services.geradorLinear import Linear
from datetime import datetime

mysql = ClsSql('ColdUser', 'senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = input(" Digite o id da máquina ")
print("id recebido: ", idMaquina)
# idMaquina = 1

lineares = []
vetorSaida = []
#RETORNO: [[id, nome, maxima], [1, 'cpu', 2.5], [2, 'ram', 8]]
configuracaoMaquina = mysql.consultarConfiguracao(idMaquina)

for componente in configuracaoMaquina:
    # componente = [id, nome, maxima]
    hoje = datetime.today()
    passado = datetime.timestamp(hoje) - 86400 * 7 #7 DIAS
    passado = datetime.fromtimestamp(passado)

    #Pega as informacoes da semana passada
    registros = mysql.pegarRegistros(idMaquina, hoje, passado, componente[0])
    valores = [i[0] for i in registros]
    linearAtual = Linear(valores)
    lineares.append(linearAtual)
    soma = sum(valores)
    mediaPassada = soma/len(valores)

    #Pega as informacoes de ontem/hoje
    passado = datetime.timestamp(hoje) - 86400 #1 DIA
    passado = datetime.fromtimestamp(passado)
    registros = mysql.pegarRegistros(idMaquina, hoje, passado, componente[0])
    valores = [i[0] for i in registros]
    soma = sum(valores)
    mediaOntem = soma/len(valores)
    qtdRegistros = len(valores)

    #Preve as informacoes para amanha com a msm qtd de ontem
    valores = linearAtual.proximosValores(qtdRegistros)
    soma = sum(valores)
    mediaAmanha = soma/len(valores)

    vetorSaida.append({
        'nomeComponente' : registros[0][1],
        'influencia' : "ALGO A COMBINAR",
        'mediaAnterior' : round(mediaPassada,2),
        'mediaHoje' : round(mediaOntem,2),
        'mediaAmanha' : round(mediaAmanha,2),
    })

escrita = str(vetorSaida)
print(escrita)
outF = open("previsao.json", "w")
outF.write(escrita)
outF.close()

print("ARQUIVO JSON CRIADO")