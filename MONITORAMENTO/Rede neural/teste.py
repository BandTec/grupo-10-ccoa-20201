from mlp import MLP
import pickle

redeRecuperada = open('redeSalva.pkl', 'rb')

rede = pickle.load(redeRecuperada)
redeRecuperada.close()
treino_entradas = [[0.57, 0.42, 0.04, 0.0, 0.0, 0.69],[0.0, 0.0, 0.0, 0.0, 0.0, 0.0]]

for exemplo in treino_entradas:
    print(exemplo, rede.teste(exemplo))

