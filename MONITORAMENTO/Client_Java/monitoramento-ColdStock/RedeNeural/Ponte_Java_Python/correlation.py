import pandas as pd


class Correlacao:
     def criarCorrelacao(self):
          entrada = pd.read_csv("../ColetorDadosBD/dados.data", sep=",", header=None)
          saida = pd.read_csv("../ColetorDadosBD/saida.data", sep=",", header=None)

          b = saida.values.reshape(saida.shape[1],saida.shape[0])
          resposta = b

          df = pd.DataFrame(columns=['CPU', 'RAM', 'DISCO', 'CONEXAO D', 'CONEXAO U', 'TEMPERATURA', 'RESPOSTA'])

          for i in range(len(entrada[0])):    
               df = df.append({'CPU': entrada[0][i],'RAM': entrada[1][i],'DISCO': entrada[2][i], 'CONEXAO D': entrada[3][i], 'CONEXAO U': entrada[4][i], 'TEMPERATURA': entrada[5][i],  'RESPOSTA': resposta[i][0]}, ignore_index=True)

          correlacao = round(df.corr(),3)
          df = correlacao

          respostas = []

          for i in range(6):
               respostas.append(df.iloc[6][i])
          return respostas




