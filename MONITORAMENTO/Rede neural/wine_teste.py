import math
import pandas as pd
from mlp import MLP

ds = pd.read_csv("wine.data", sep=",", header=None)
ds[14] = [0] * len(ds)
ds[15] = [0] * len(ds)
ds[16] = [0] * len(ds)
ds.loc[ds[0]==1, 14] = 1
ds.loc[ds[0]==2, 15] = 1
ds.loc[ds[0]==3, 16] = 1
treino = ds.sample(120)
treino_entradas = treino.drop(columns=[0,14,15,16]).values.tolist()
treino_saidas = treino.drop(columns=range(14)).values.tolist()
teste = ds.drop(treino.index)
teste_entradas = teste.drop(columns=[0,14,15,16]).values.tolist()

for i in range(13):
    fmin = min(treino[i+1])
    fmax = max(treino[i+1])
    for ex_index in range(len(treino_entradas)):
        treino_entradas[ex_index][i] = (treino_entradas[ex_index][i] - fmin) / (fmax - fmin)

rede = MLP(13,13,3)
rede.treinamento(treino_entradas, treino_saidas, eta=0.9)
