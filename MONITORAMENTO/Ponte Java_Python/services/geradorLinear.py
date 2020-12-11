
class Linear:

    def __init__(self, registros):
        self.registros = registros
        self.m = 0
        self.b = 0

    def gerarLinear(self, informacoes):
        pontos = set()

        x = 1
        for informacao in informacoes:
            pontos.add((x, informacao))
            x += 1

        #Achando as médias de X e Y
        #X - X0
        mediax = 0.0
        #Y -Y0
        mediay = 0.0
        for i in pontos:
            mediax += i[0]/len(pontos)
            mediay += i[1]/len(pontos)

        totalxx = 0
        totalxy = 0
        for i in pontos:
            totalxx += (i[0]-mediax)**2
            totalxy += (i[0]-mediax)*(i[1]-mediay)

        #Calculando o coeficiente angular
        #para que seja montada a formula da reta
        self.m = totalxy/totalxx
        self.b = mediay-self.m*mediax
        print("Linha de melhor ajuste:")
        print("y = "+str(self.m)+"x + "+str(self.b))

    def proximoValor(self, x):
        print("y = "+str(self.m*x+self.b))