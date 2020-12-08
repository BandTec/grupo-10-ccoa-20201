from services.sqlApp import ClsSql

mysql = ClsSql('ColdUser', 'senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = input(" Digite o id da máquina ")
# idMaquina = 1

tabela = mysql.consultarMaquina(idMaquina)

configuracao = mysql.consultarConfiguracao(idMaquina)
dataAtual = ''
maiorQtdItens = 0
# Pegando quais componentes vieram do BD
componentes = []
componenteAtual = ''
for linha in range(len(tabela)):
    componenteAtual = tabela[linha][2]  # Nome componente
    if componenteAtual not in componentes:
        componentes.append(componenteAtual)

# Matriz sera um esboco do dataset que iremos criar no final
matriz = {
    0 : {"dataHora" : tabela[0][0]} # Adicionando a primeira data
}

#Fazendo o pivot
linhaMatriz=0
for linhaTabela in range(len(tabela)):
    # Pegando Linha x do export do BD
    # Os campos que estao sendo pegos sao desde o 1 ate o N° total de colunas que tiver [1:final]
    linhaAtual=tabela[linhaTabela]

    dataAtual=linhaAtual[0]
    # Se a data nao e null, e diferente da atual, add linha
    if(matriz[linhaMatriz]['dataHora'] != dataAtual):
        #Adicionando vazios aos que não foram registrados
        for componente in componentes :
            if componente not in matriz[linhaMatriz]:
                matriz[linhaMatriz][componente] = '0.0'

        print('Linha adicionada')
        linhaMatriz = linhaMatriz + 1
        matriz[linhaMatriz] = {'dataHora' : dataAtual}

    #Verifica se algum componente da linha gerou chamado
    if(linhaAtual[3] != None):
        matriz[linhaMatriz]['chamado'] = True

    # Valor sendo adicionado na matriz
    valorMedido = linhaAtual[1]
    for config in configuracao:
        if config[0] == linhaAtual[2]:
            valorPorcent = valorMedido / config[1]
            break
    matriz[linhaMatriz][linhaAtual[2]] = round(valorPorcent,2)

print(matriz)

escrita = ""

#Preparando texto que ira para o arquivo .dat
#Linha por linha da matriz coletada
for linha in range(len(matriz)):
    #Componente por componente, pra colocar na ordem certa
    for componente in componentes:
        escrita += "%s, "%(matriz[linha][componente])
    #Coloco no fim da linha se gerou chamado ou não
    chamadoStatus = "1" if 'chamado' in matriz[linha] else "0"
    escrita += chamadoStatus
    #Próxima linha
    escrita += "\n"

print(escrita)
outF = open("saida.dat", "w")
outF.write(escrita)
outF.close()