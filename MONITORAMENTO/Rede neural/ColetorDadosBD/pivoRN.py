from services.sqlApp import ClsSql

mysql = ClsSql('ColdUser','senha123', 'localhost', 'coldstock')

print("Conectando ao BD")
mysql.connect()
idMaquina = input(" Digite o id da máquina ")
#idMaquina = 1

componentes = mysql.listarComponentesMaquina(idMaquina)
porcentagens = []
for componente in componentes:
    porcentagens.append([componente[0], componente[2]]) #idComponente e capacidade max

componentes = mysql.listarComponentes()
componentes = len(componentes)

chamados = mysql.consultarChamados(idMaquina)
#outF = open("saida.dat", "w")
print(chamados)
idChamado = 0
contadorComponente = 0
escrita = ""

for chamado in chamados:
    idComponente = chamado[2]

    #Verifica quando passar a linha
    if(idChamado != chamado[0]):
        contadorComponente = 0
        idChamado = chamado[0]
        escrita += "\n"
        #outF.write("\n")

    if(idComponente == contadorComponente+1):
        for porcentagem in porcentagens:
            if(idComponente == porcentagem[0]):
                numero = round(chamado[3]/porcentagem[1],2)
                escrita += ("%s " % numero)
                break
    else:
        escrita += "0 "

    contadorComponente += 1


    #outF.write(escrita)
    
#outF.close()
print("escrita final:")
print(escrita)
#retorno: %cpu, %ram, %conD...