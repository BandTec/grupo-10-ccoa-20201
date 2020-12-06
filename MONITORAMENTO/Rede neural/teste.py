from mlp import MLP
import pickle

redeRecuperada = open('redeSalva.pkl', 'rb')

rede = pickle.load(redeRecuperada)
redeRecuperada.close()
treino_entradas = [[0.7,0.5,0.3,0.6,0.8,0.7]]

for exemplo in treino_entradas:
    #e no fim, imprimimos cada exemplo da entrada e o resultado da RN
    print(exemplo, rede.teste(exemplo))

