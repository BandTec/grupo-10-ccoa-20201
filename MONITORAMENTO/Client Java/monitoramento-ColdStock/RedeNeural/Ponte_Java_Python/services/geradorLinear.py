import numpy as np
from sklearn.linear_model import LinearRegression
class Linear:

    def __init__(self, registros):
        self.registros = registros
        self.model = None
        self.gerarLinear(registros)
        self.qtdPontos = len(registros)

    def gerarLinear(self, informacoes):
        x = np.array(range(1, len(informacoes)+1)).reshape((-1, 1))
        y = np.array(informacoes)
        self.model = LinearRegression().fit(x, y)

    def proximoValor(self):
        x = self.qtdPontos+1
        return (self.model.predict(x))

    def proximosValores(self, qtd):
        print("Calculando os próximos %s valores"%self.qtdPontos)
        valores = []
        x = np.array(self.qtdPontos).reshape((-1, 1))
        valores = self.model.predict(x)
        return (valores)