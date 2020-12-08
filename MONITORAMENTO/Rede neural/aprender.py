import math
import pandas as pd
from mlp import MLP
import pickle

entrada = pd.read_csv("./ColetorDadosBD/dados.data", sep=",", header=None)
saida = pd.read_csv("./ColetorDadosBD/saida.data", sep=",", header=None)
treinoE = entrada.sample(80)
treinoS = saida.sample(80, replace = True)
treino_entradas = treinoE.values.tolist()
treino_saidas = treinoS.values.tolist()

rede = MLP(6,6,1)
rede.treinamento(treino_entradas, treino_saidas, eta=0.1)

redeSalva = open('redeSalva.pkl', 'wb')

pickle.dump(rede, redeSalva)

redeSalva.close()