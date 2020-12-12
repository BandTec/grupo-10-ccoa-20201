import pickle
from timeit import default_timer as timer
import time
from mlp import MLP

class Teste():
    def recuperarRede(self):
        redeRecuperada = open('redeSalva.pkl', 'rb')
        rede = pickle.load(redeRecuperada)
        redeRecuperada.close()
        return rede

    def realizarTeste(self, valores):
        start = timer()
        treino_entradas = valores 
        contadorChamados = 0
        for exemplo in treino_entradas:
            resposta = self.recuperarRede().teste(exemplo)
            for i in range(len(resposta)):
                print(exemplo,round(resposta[i],0))
                if round(resposta[i],1) == 1.0:
                    contadorChamados += 1
        print(contadorChamados)

        end = timer()
        tempo = end-start
        print("Tempo de processamento:", round(tempo,8),"segundos")
        return contadorChamados









