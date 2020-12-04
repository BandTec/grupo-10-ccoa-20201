from mlp import MLP
import pickle

#aqui criamos as entradas
#[cpu, ram, disco, conexaoU, conexaoD, temp]
treino_entradas = [[0.7,0.5,0.6,0.3,0.6,0.9], [0.4,0.8,0.4,0.5,0.7,0.8], [0.7,0.2,0.5,0.6,0.8,0.3], [0.6,0.7,0.8,0.4,0.8,0.9], [0.7,0.5,0.3,0.6,0.8,0.7]]
#e o resultado esperado para a saida
treino_saidas = [[0], [1], [1], [0], [1]]

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