import pandas as pd
import numpy as np

# def histogram_intersection(a, b):
#     v = np.minimum(a, b).sum().round(decimals=1)
#     return v

entrada = pd.read_csv("./ColetorDadosBD/dados.data", sep=",", header=None)
saida = pd.read_csv("./ColetorDadosBD/saida.data", sep=",", header=None)

b = saida.values.reshape(saida.shape[1],saida.shape[0])

resposta = b

df = pd.DataFrame(columns=['CPU', 'RAM', 'DISCO', 'RESPOSTA'])

for i in range(len(entrada[0])):    
     df = df.append({'CPU': entrada[0][i],'RAM': entrada[1][i],'DISCO': entrada[2][i], 'RESPOSTA': resposta[i][0] }, ignore_index=True)

print(df)
print(df.corr())

