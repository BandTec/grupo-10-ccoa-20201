from services.sqlApp import ClsSql
from services.geradorLinear import Linear
from datetime import datetime
from correlation import Correlacao

mysql = ClsSql('ColdUser', 'senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = input(" Digite o id da máquina ")
print("id recebido: ", idMaquina)
# idMaquina = 1
todosValores = []
lineares = []
vetorSaida = []
influencias = Correlacao().criarCorrelacao()
#RETORNO: [[id, nome, maxima], [1, 'cpu', 2.5], [2, 'ram', 8]]
configuracaoMaquina = mysql.consultarConfiguracao(idMaquina)

for componente in configuracaoMaquina:
    #componente = [id, nome, maxima]
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
    todosValores.append(valores)
    vazio = ([], shape=(0, 6))
    vetorSaida.append({
        'nomeComponente' : registros[0][1],
        'influencia' : vazio,
        'mediaAnterior' : round(mediaPassada,2),
        'mediaHoje' : round(mediaOntem,2),
        'mediaAmanha' : round(mediaAmanha,2),
    })
#for i in range(len(influencias)):
    #print(influencias[i])
    print(influencias[0])
    vetorSaida[0]['influencia'][0][0].append(influencias[0])
    #vetorSaida[0]['influencia'][i] =    
    print(vetorSaida[0]['influencia'][0][0])

print(vetorSaida[0])
escrita = ""
print(escrita)
outF = open("previsao.json", "w")
outF.write(escrita)
outF.close()

print("ARQUIVO JSON CRIADO")