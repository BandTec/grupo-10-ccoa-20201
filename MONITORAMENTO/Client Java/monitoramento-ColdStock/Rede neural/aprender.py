import math
import pandas as pd
from mlp import MLP
import pickle
from timeit import default_timer as timer

start = timer()

entrada = pd.read_csv("./ColetorDadosBD/dados.data", sep=",", header=None)
saida = pd.read_csv("./ColetorDadosBD/saida.data", sep=",", header=None)

b = saida.values.reshape(saida.shape[1],saida.shape[0])
entrada['saida'] = b

treinoE = entrada.sample(40)
treinoS = treinoE['saida']
treinoE = treinoE.drop(columns = ['saida'])

treino_entradas = treinoE.values.tolist()
treino_saidas = treinoS.values.tolist()
print(treino_entradas[0])
print(treino_saidas[0])

rede = MLP(6,6,1)
rede.treinamento(treino_entradas, treino_saidas, eta=0.1)

redeSalva = open('redeSalva.pkl', 'wb')

pickle.dump(rede, redeSalva)

redeSalva.close()

end = timer()

tempo = (end-start)/60
print("Tempo de processamento:", round(tempo,2),"minutos")
#print("Número de iterações:",rede.get_epochs())

for exemplo in treino_entradas[:5]:
    
    print(exemplo, rede.teste(exemplo))