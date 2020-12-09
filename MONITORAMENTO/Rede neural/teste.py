from mlp import MLP
import pickle
from timeit import default_timer as timer
import time

start = timer()

redeRecuperada = open('redeSalva.pkl', 'rb')

rede = pickle.load(redeRecuperada)
redeRecuperada.close()
treino_entradas = [[0.57, 0.42, 0.04, 0.0, 0.0, 0.69],[0.0, 0.0, 0.0, 0.0, 0.0, 0.0],[0.39, 0.41, 0.04, 0.0, 0.0, 0.69]]

for exemplo in treino_entradas:
    print(exemplo, rede.teste(exemplo))

end = timer()

tempo = end-start
print("Tempo de processamento:", round(tempo,2),"segundos")
