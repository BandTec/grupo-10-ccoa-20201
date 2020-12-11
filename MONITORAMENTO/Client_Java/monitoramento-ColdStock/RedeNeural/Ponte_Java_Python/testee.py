from services.sqlApp import ClsSql
mysql = ClsSql('ColdUser', 'senha123', 'localhost', 'coldstock')


mysql.connect()
idMaquina = input(" Digite o id da máquina ")
configuracaoMaquina = mysql.consultarConfiguracao(idMaquina) 

    # Pegando Linha x do export do BD
    # Os campos que estao sendo pegos sao desde o 1 ate o N° total de colunas que tiver [1:final]


print(configuracaoMaquina)
    valorMedido = linhaAtual[1]
    for config in configuracaoMaquina:
        if config[0] == linhaAtual[2]:
            valorPorcent = valorMedido / config[1]
            break
        valorPercent = 0.0
    matriz[linhaMatriz][linhaAtual[2]] = round(valorPorcent,2)