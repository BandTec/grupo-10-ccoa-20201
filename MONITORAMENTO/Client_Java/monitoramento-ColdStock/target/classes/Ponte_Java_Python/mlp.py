import math
import random

class Neuron():
    def __init__(self, n_entradas):
        #nos criamos valores aleatorios para bias, tirando 0.5 do valor criado
        self.bias = random.random() - 0.5
        #nos criamos valores aleatorios para os pesos, para cada neuronio de entrada, tirando 0.5 do valor criado
        self.peso = [random.random() - 0.5 for _ in range(n_entradas)]
    
    def ativacao(self, u):
        #aqui temos a funcao de ativacao!
        try:
            y = 1 / (1 + math.exp(-u))
        except OverflowError:
            y = 1 / (1 + math.inf)
        return y

    def saida(self, entrada):
        #aqui criamos uma variavel u que recebe o valor do bias
        u = self.bias
        for i in range(len(entrada)):
            #para cada valor da entrada, adicionamos o peso * valor da entrada no valor do bias
            u += self.peso[i] * entrada[i]
        
        return self.ativacao(u)

class MLP():
    def __init__(self, n_entradas, n_oculta, n_saida):
        #essa funcao é a que cria a rede neural, ela recebe o numero de neuronios na camada
        #de entrada, oculta e de saida
        self.camada_oculta = [Neuron(n_entradas) for _ in range(n_oculta)]
        self.camada_saida = [Neuron(n_oculta) for _ in range(n_saida)]
        #self.epochs = 0

    def teste(self, exemplo_entrada):
        #no teste, nos recebemos um conjunto de informações iniciais
        #aqui executamos a funcao saida para cada n na camada oculta
        y_h = [n.saida(exemplo_entrada) for n in self.camada_oculta]
        y_s = [n.saida(y_h) for n in self.camada_saida]
        return y_s

    # def get_epochs(self):
    #     return self.epochs

    def treinamento(self, exemplos_entrada, exemplos_saida, eta):
        #aqui setamos o valor do erro como 1, sendo o maior valor possivel 
        erro = 1
        #agora criamos um while que vai rodar até o erro alcançar 0.001 :)
        while (erro > 0.001):
            erro = 0
            #self.epochs += 1
            
            #agora, esse FOR vai rodar pela quantidade de exempos na entrada
            for ex_index in range(len(exemplos_entrada)):
                #a entrada vao ser os valores que a RN vai testar
                entrada = exemplos_entrada[ex_index]
                #e a saida sao os valores que a RN vai comparar com seu resultado
                saida = [exemplos_saida[ex_index]]

                y_h = [n.saida(entrada) for n in self.camada_oculta]
                y_s = [n.saida(y_h) for n in self.camada_saida]

                erro_exemplo = 0
                erros = []
                for k in range(len(self.camada_saida)):
                    e_atual = saida[k] - y_s[k]
                    erros.append(e_atual)
                    erro_exemplo += e_atual ** 2
                erro_exemplo = erro_exemplo / 2
                erro += erro_exemplo

                delta_s = []
                #delta_s é um vetor que guarda o delta da camada de saida
                for k in range(len(self.camada_saida)):
                    #o delta da camada oculta é calculado pelo erro na posicao k, multiplicado
                    #pelo y_s e por 1 - y_s, todos na posiçao k
                    delta = erros[k] * y_s[k] * (1 - y_s[k])
                    #e adicionamos o valor desse delta no vetor
                    delta_s.append(delta)

                delta_h = []
                for j in range(len(self.camada_oculta)):
                    delta = y_h[j] * (1 - y_h[j])
                    soma = 0
                    for k in range(len(self.camada_saida)):
                        soma += delta_s[k] * self.camada_saida[k].peso[j]
                    delta = delta * soma
                    delta_h.append(delta)

                for k in range(len(self.camada_saida)):
                    self.camada_saida[k].bias += eta * delta_s[k]
                    for j in range(len(self.camada_saida[k].peso)):
                        self.camada_saida[k].peso[j] += eta * delta_s[k] * y_h[j]

                for j in range(len(self.camada_oculta)):
                    self.camada_oculta[j].bias += eta * delta_h[j]
                    for i in range(len(self.camada_oculta[j].peso)):
                        self.camada_oculta[j].peso[i] += eta * delta_h[j] * entrada[i]
            #aqui imprimimos 
            print(erro)
