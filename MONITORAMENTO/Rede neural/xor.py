from mlp import MLP
import pickle

#aqui criamos as entradas
#[cpu, ram, disco, conexaoU, conexaoD, temp]
treino_entradas = [[0.8,0.5,0.2,0.65,0.56,0.48], [0.8,0.5,0.4,0.65,0.3,0.57], [0.7,0.54,0.13,0.25,0.24,0.58], [0.8,0.5,0.13,0.4,0.3,0.48], [0.9,0.3,0.1,0.6,0.56,0.6],[0.78,0.51,0.1,0.30,0.25,0.58]]
#e o resultado esperado para a saida
treino_saidas = [[1], [1], [1], [0], [0],[0]]

#aqui,criamos a quantidade de neuronios para cada camada, 
#temos 6 neuronios para entrada, 6 para oculta e um para resultado
rede = MLP(6, 6, 1)
#executamos o treinamento, passando as entradas e saidas
rede.treinamento(treino_entradas, treino_saidas, eta=0.1)
for exemplo in treino_entradas:
    #e no fim, imprimimos cada exemplo da entrada e o resultado da RN
    print(exemplo, rede.teste(exemplo))

redeSalva = open('redeSalva.pkl', 'wb')

pickle.dump(rede, redeSalva)

redeSalva.close()